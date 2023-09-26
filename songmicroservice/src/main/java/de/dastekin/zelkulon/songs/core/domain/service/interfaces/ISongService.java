/**
 * Dastekin created on 12.07.2023 the SongService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */
package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISongService {

    Song getSongById(Long id);

    List<Song> getAllSongs();

    Song postSong(Song songToAdd);

    Song updateSong(Long id, Song songToPut);

    boolean deleteSong(Long id);
}
