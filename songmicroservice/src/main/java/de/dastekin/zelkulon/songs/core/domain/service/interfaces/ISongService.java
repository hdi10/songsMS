/**
 * Dastekin created on 12.07.2023 the SongService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */
package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISongService {

    /**
     * Gets the song by ID.
     * @param id - ID of the song
     * @return - Song with the given ID
     * 404 - Not Found if no song with the given ID exists
     * 500 - Internal Server Error if an error occurs
     * 200 - OK if everything is successful
     */
    public ResponseEntity<Object> getSongById(Long id);

    /**
     * Gets all the songs.
     * @return - List of all songs
     * 204 - No Content if no songs exist
     * 500 - Internal Server Error if an error occurs
     * 200 - OK if everything is successful
     */
    public ResponseEntity<Object> getAllSongs();

    /**
     * Adds a song.
     * @param songToAdd - Song to be added
     * @return - ResponseEntity with the added song
     * 201 - Created if the song was successfully added
     * 400 - Bad Request if the song could not be added
     * 500 - Internal Server Error if an error occurs
     */
    public  ResponseEntity<Object> postSong(Song songToAdd);

    /**
     * Updates a song.
     * @param id - ID of the song
     * @param songToPut - Song to be updated
     * @return - ResponseEntity with the updated song
     * 200 - OK if the song was successfully updated
     * 400 - Bad Request if the song could not be updated
     * 500 - Internal Server Error if an error occurs
     * 404 - Not Found if the song does not exist
     */
    public ResponseEntity<Object> updateSong(Long id, Song songToPut);

    /**
     * Deletes a song.
     * @param id - ID of the song
     * @return - ResponseEntity with the deleted song
     * 200 - OK if the song was successfully deleted
     * 400 - Bad Request if the song could not be deleted
     * 500 - Internal Server Error if an error occurs
     */
    public ResponseEntity<Object>  deleteSong(Long id);
}
