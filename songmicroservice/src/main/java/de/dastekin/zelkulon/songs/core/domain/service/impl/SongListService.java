/**
 * Dastekin created on 21.07.2023 the SongListService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.impl
 */
package de.dastekin.zelkulon.songs.core.domain.service.impl;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongListService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongListRepository;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class SongListService implements ISongListService {

    private final SongListRepository songListRepository;
    private final SongRepository songRepository;

    public SongListService(SongListRepository songListRepository, SongRepository songRepository) {
        this.songListRepository = songListRepository;
        this.songRepository = songRepository;
    }


    /*
     * Get All SongLists aus dem service aufrufen (Eigene Postgresqql native query) ---> SongListRepository
     */
    @Override
    public ResponseEntity<?> getAllSongListsVonOwnerObPrivateOderNicht(String ownerId) {
        return new ResponseEntity<>(songListRepository.getAllSongListOfSpecificOwnerPrivateAndPublic(ownerId), null, 200);
    }

    @Override
    public ResponseEntity<?> getAllSongListVonJemandAnderem(String ownerId) {
        return new ResponseEntity<>(songListRepository.getAllSongListVonAnderemUserDiePublicSind(ownerId), null, 200);
    }

    @Override
    public boolean gibtEsDenUser(String ownerId) {
        return songListRepository.gibtEsDenUserUeberhaupt(ownerId);
    }

    @Override
    public boolean gibtEsDieSonglisteMitDerID(Long songListId) {
        return songListRepository.gibtEsUeberhauptDieSonglisteMitDerId(songListId);
    }

    @Override
    public ResponseEntity<?> gibMirDieSongListeMitDerId(Long songListId) {
        return new ResponseEntity<>(songListRepository.gibMirDieSongListMitDerId(songListId), null, 200);
    }

    @Override
    public String gibMirBitteDenNamenDesBesitzerDerSongListId(Long songListId) {
        return songListRepository.gibMirBitteDenNamenDesUsersMitDerSongListId(songListId);
    }

    @Override
    public boolean istDieseListePublic(Long songListId) {
        return songListRepository.istVerlangteSongListePublic(songListId);
    }

    @Override
    public ResponseEntity<?> addSongList(String userId, SongList songList2Add) {

        // serze owner
        songList2Add.setOwnerId(userId);

        // Alle Songs aus der Datenbank holen
        List<Song> allSongs = songRepository.getAllSongs();

        // Überprüfen Sie, ob alle Songs in der Datenbank existieren
        boolean allSongsExist = songList2Add.getSongList().stream()
                .allMatch(inputSong -> doesSongExistInDatabase(inputSong, allSongs));

        // Wenn alle Songs existieren, speichern Sie die SongListe und senden Sie die Antwort
        if (allSongsExist) {
            SongList songList = songListRepository.save(songList2Add);
            HttpHeaders header = new HttpHeaders();
            header.setLocation(URI.create("/songlists/" + songList.getId()));
            return new ResponseEntity<>(songList2Add, header, HttpStatus.CREATED);
        } else {
            // Wenn nicht alle Songs existieren, senden Sie eine entsprechende Antwort
            return new ResponseEntity<>("Some songs do not exist in the database", HttpStatus.BAD_REQUEST);
        }

    }


    private boolean doesSongExistInDatabase(Song inputSong, List<Song> allSongs) {
        return allSongs.stream().anyMatch(dbSong -> dbSong.getId().equals(inputSong.getId()));
    }


    @Override
    public ResponseEntity<?> updateSongList(String userId, Long id, SongList songList2Update) {

        Long songListId = songList2Update.getId().longValue();

        if (songListId.equals(id)) {
            songList2Update.setOwnerId(userId);
            songListRepository.save(songList2Update);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public void deleteThisSongList(Long songListId) {
        songListRepository.deleteById(songListId);
    }

}
