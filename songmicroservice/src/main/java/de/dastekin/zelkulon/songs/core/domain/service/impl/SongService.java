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
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class SongService implements ISongService {
    Logger logger = Logger.getLogger(SongService.class.getName());
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

        Long songId = songToPut.getId().longValue();
        logger.info("übergebene ID " + id + "mit songToPut.getId() " + songToPut.getId());
        logger.info("der songtoPut lautet " + songToPut);

        Song songToUpdate = songRepository.selectSongById(id);
        logger.info("id " + id + "mit songToUpdate.getId() " + songToUpdate.getId());
        logger.info("songToUpdate " + songToUpdate);

        if (songId.equals(id)) {

            if (!Objects.equals(songToUpdate.getTitle(), songToPut.getTitle()) && songToPut.getTitle()!=null) {
                songToUpdate.setTitle(songToPut.getTitle());
                logger.info("songToUpdate.getTitle() " + songToUpdate.getTitle());
                logger.info("songToPut.getTitle() " + songToPut.getTitle());

            }
            if (!Objects.equals(songToUpdate.getArtist(), songToPut.getArtist()) && songToPut.getArtist()!=null) {
                songToUpdate.setArtist(songToPut.getArtist());
            }
            if (!Objects.equals(songToUpdate.getLabel(), songToPut.getLabel()) && songToPut.getLabel()!=null) {
                songToUpdate.setLabel(songToPut.getLabel());
            }
            if (!Objects.equals(songToUpdate.getReleased(), songToPut.getReleased()) && songToPut.getReleased()!=null) {
                songToUpdate.setReleased(songToPut.getReleased());
            }

            songRepository.save(songToUpdate);

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
