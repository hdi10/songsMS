/**
 * Dastekin created on 12.07.2023 the SongService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.impl
 */
package de.dastekin.zelkulon.songs.core.domain.service.impl;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import de.dastekin.zelkulon.songs.port.song.exception.ResourceNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class SongService implements ISongService {
    private final SongRepository songRepository;
    //@Autowired
    //WebClient.Builder webClientBuilder;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @Override
    public ResponseEntity<Object> getSongById(Long id) {
        try {
            Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + id));
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getAllSongs(){
        return new ResponseEntity<>(songRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postSong (Song songToAdd) {
        if (songToAdd.getTitle() != null && !songToAdd.getTitle().isEmpty()) {
            Song newSong = songRepository.save(songToAdd);
            HttpHeaders header = new HttpHeaders();
            header.setLocation(URI.create("/songs/" + newSong.getId()));
            return new ResponseEntity<>(songToAdd, header, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateSong(Long id, Song songToPut) {
        var val = songRepository.findById(id);
        if (val.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Song song = val.get();

        if (songToPut.getId() != null && songToPut.getId().equals(id)) {

            if (songToPut.getTitle() != null && !songToPut.getTitle().isEmpty()) {
                song.setTitle(songToPut.getTitle());
            }

            if (songToPut.getArtist() != null) {
                song.setArtist(songToPut.getArtist());
            }

            if (songToPut.getLabel() != null) {
                song.setLabel(songToPut.getLabel());
            }

            if (songToPut.getReleased() != null) {
                song.setReleased(songToPut.getReleased());
            }

            songRepository.save(song);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object>  deleteSong (Long id) {
        var song = songRepository.findById(id);
        if (song.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            songRepository.delete(song.get());
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }

        return ResponseEntity.noContent().build();
    }




}
