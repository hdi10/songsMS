/**
 * Dastekin created on 15.09.2023 the SongListRepository-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SongListRepository extends CrudRepository<SongList, Long> {

    Iterable<SongList> findByOwnerIdOrderById(String userId);
    Iterable<SongList> findByOwnerIdOrIsPrivateOrderById(String userId, boolean isPrivate);
    Iterable<SongList> findByOwnerIdAndIsPrivateOrderById(String userId, boolean isPrivate);
    Optional<SongList> findByIdOrderById(Integer id);

    @Query(value = "SELECT * FROM song_list WHERE owner_id = ?1 AND (is_private = true OR is_private = false)", nativeQuery = true)
    Iterable<SongList> getAllSongListOfSpecificOwnerPrivateAndPublic(String ownerId);

    @Query(value = "SELECT * FROM song_list WHERE owner_id = ?1 AND is_private = false", nativeQuery = true)
    Iterable<SongList> getAllSongListVonAnderemUserDiePublicSind(String ownerId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM song_list WHERE owner_id = ?1)", nativeQuery = true)
    boolean gibtEsDenUserUeberhaupt(String ownerId);

    @Query(value = "SELECT * FROM song_list WHERE song_list_id = ?1", nativeQuery = true)
    Iterable<SongList> gibMirDieSongListMitDerId(Long id);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM song_list WHERE song_list_id = ?1)", nativeQuery = true)
    boolean gibtEsUeberhauptDieSonglisteMitDerId(Long id);

    @Query(value = "SELECT owner_id FROM song_list WHERE song_list_id = ?1", nativeQuery = true)
    String gibMirBitteDenNamenDesUsersMitDerSongListId(Long id);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM song_list WHERE song_list_id = ?1 AND is_private = false)", nativeQuery = true)
    boolean istVerlangteSongListePublic(Long id);

}
