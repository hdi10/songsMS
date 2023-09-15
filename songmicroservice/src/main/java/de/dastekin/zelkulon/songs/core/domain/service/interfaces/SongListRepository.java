/**
 * Dastekin created on 15.09.2023 the SongListRepository-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SongListRepository extends CrudRepository<SongList, Long> {

    Iterable<SongList> findByOwnerIdOrderById(String userId);
    Iterable<SongList> findByOwnerIdOrIsPrivateOrderById(String userId, boolean isPrivate);
    Iterable<SongList> findByOwnerIdAndIsPrivateOrderById(String userId, boolean isPrivate);
    Optional<SongList> findByIdOrderById(Integer id);


}
