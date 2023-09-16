/**
 * Dastekin created on 21.07.2023 the SongListService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.impl
 */
package de.dastekin.zelkulon.songs.core.domain.service.impl;

import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongListService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongListRepository;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SongListService implements ISongListService {

    private final SongListRepository songListRepository;
    private final SongRepository songRepository;

    public SongListService(SongListRepository songListRepository, SongRepository songRepository) {
        this.songListRepository = songListRepository;
        this.songRepository = songRepository;
    }

    /** //TODO: HARDCODED
     * @param userId
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> getAllSongLists(String userId, Long id) {
        return new ResponseEntity<>(songListRepository.findByOwnerIdOrIsPrivateOrderById(userId, false), null, 200);
    }

    @Override
    public ResponseEntity<?> getSongListByUserId(String userId, String userId2Search4) {
        return null;
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
    public ResponseEntity<?> gibMirBitteSonglisteMitIdWennPublic(Long songListId) {
        return null;
    }

    @Override
    public boolean istDieseListePublic(Long songListId) {
        return songListRepository.istVerlangteSongListePublic(songListId);
    }

    @Override
    public ResponseEntity<?> addSongList(String userId, SongList songList2Add) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateSongList(String userId, SongList songList2Update) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSongList(String userId, Long id) {
        return null;
    }
}
