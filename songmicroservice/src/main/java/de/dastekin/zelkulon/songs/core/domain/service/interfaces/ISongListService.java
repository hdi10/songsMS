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

    ResponseEntity<?> getAllSongListVonJemandAnderem(String ownerId);

    boolean gibtEsDenUser(String ownerId);

    boolean gibtEsDieSonglisteMitDerID(Long songListId);

    ResponseEntity<?> gibMirDieSongListeMitDerId(Long songListId);

    String gibMirBitteDenNamenDesBesitzerDerSongListId(Long songListId);

    ResponseEntity<?> gibMirBitteSonglisteMitIdWennPublic(Long songListId);

    boolean istDieseListePublic(Long songListId);

    ResponseEntity<?> addSongList(String userId , SongList songList2Add);


    ResponseEntity<?> updateSongList1(String userId, Long id, SongList songList2Update);

    ResponseEntity<?> updateSongList2(String userId, Long id, SongList songList2Update);

    boolean deleteSongList(String userId , Long id);

    void deleteThisSongList(Long songListId);
}
