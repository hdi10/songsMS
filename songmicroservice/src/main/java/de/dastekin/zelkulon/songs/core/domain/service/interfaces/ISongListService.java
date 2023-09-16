/**
 * Dastekin created on 14.09.2023 the ISongListService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import org.springframework.http.ResponseEntity;

public interface ISongListService {

    ResponseEntity<?> getAllSongLists(String userId , Long id);

    ResponseEntity<?> getSongListByUserId(String userId , String userId2Search4);


    /*
        * Get All SongLists aus dem service aufrufen (Eigene Postgresqql native query) ---> SongListRepository
     */
    ResponseEntity<?> getAllSongListsVonOwnerObPrivateOderNicht(String userId);

    ResponseEntity<?> addSongList(String userId , SongList songList2Add);

    ResponseEntity<?> updateSongList(String userId , SongList songList2Update);

    ResponseEntity<?> deleteSongList(String userId , Long id);

}
