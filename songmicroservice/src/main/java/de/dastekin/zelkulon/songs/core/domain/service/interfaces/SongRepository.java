/**
 * Dastekin created on 12.07.2023 the SongsRepository-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    @Query(value = "SELECT * FROM songs WHERE id = ?1", nativeQuery = true)
    Song selectSongById(Long id);

    @Query(value = "SELECT * FROM songs", nativeQuery = true)
    List<Song> getAllSongs();

    /*@Query(value = "SELECT * FROM songs ", nativeQuery = true)
    List<Song> selectAllSongs();

    @Query(value = "UPDATE songs SET title = ?2 WHERE id = ?1 RETURNING *", nativeQuery = true)
    String updateSongTitle(String id, String title);

    @Query(value = "UPDATE songs SET artist=?2 WHERE id = ?1", nativeQuery = true)
    void updateSongArtist(String id, String artist);

    @Query(value = "UPDATE songs SET label=?2 WHERE id = ?1", nativeQuery = true)
    void updateSongLabel(String id, String label);

    @Query(value = "UPDATE songs SET released=?2 WHERE id = ?1", nativeQuery = true)
    void updateSongReleased(String id, String released);

    @Query(value= "DELETE *  WHERE id=?1", nativeQuery = true)
    void deleteSongsBy(Long id);*/
}
