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
import java.util.List;
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
    public Song getSongById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public List<Song> getAllSongs(){
        return songRepository.getAllSongs();
    }

    @Override
    public Song postSong(Song songToAdd) {
        if (songToAdd.getTitle() != null && !songToAdd.getTitle().isEmpty()) {
            return songRepository.save(songToAdd);
        }
        return null;
    }

    @Override
    public Song updateSong(Long id, Song songToPut) {

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

            return songToUpdate;
        } else {
            return null;
        }


    }

    @Override
    public boolean deleteSong(Long id) {
        if(songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }




}
