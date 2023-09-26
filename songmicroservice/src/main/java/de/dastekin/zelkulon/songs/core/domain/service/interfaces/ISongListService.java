/**
 * Dastekin created on 14.09.2023 the ISongListService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import org.springframework.http.ResponseEntity;

public interface ISongListService {

    /**
     * Get ALL SONGLIST ob private oder nicht von einem bestimmten User
     * @param userId - der User, von dem die SongListe geholt werden soll
     * @return - ResponseEntity mit der Liste der SongListe
     */
    ResponseEntity<?> getAllSongListsVonOwnerObPrivateOderNicht(String userId);

    /**
     * Get ALL SONGLIST die public sind von einem bestimmten User
     * @param ownerId - der User, von dem die SongListe geholt werden soll
     * @return - ResponseEntity mit der Liste der SongListe
     */
    ResponseEntity<?> getAllSongListVonJemandAnderem(String ownerId);

    /**
     * Check ob es den User überhaupt gibt
     * @param ownerId - der User, von dem die SongListe geholt werden soll
     * @return - true wenn es den User gibt, false wenn nicht
     */
    boolean gibtEsDenUser(String ownerId);

    /**
     * Check ob es die SongListe mit der ID überhaupt gibt
     * @param songListId - die ID der SongListe
     * @return - true wenn es die SongListe gibt, false wenn nicht
     */
    boolean gibtEsDieSonglisteMitDerID(Long songListId);

    /**
     * Get die SongListe mit der ID
     * @param songListId - die ID der SongListe
     * @return - ResponseEntity mit der SongListe
     */
    ResponseEntity<?> gibMirDieSongListeMitDerId(Long songListId);

    /**
     * Get den Namen des Besitzers der SongListe
     * @param songListId - die ID der SongListe
     * @return - den Namen des Besitzers der SongListe
     */
    String gibMirBitteDenNamenDesBesitzerDerSongListId(Long songListId);

    /**
     * Get die Songs der SongListe
     * @param songListId - die ID der SongListe
     * @return - ResponseEntity mit der Liste der Songs
     */
    boolean istDieseListePublic(Long songListId);

    /**
     * Adde Songliste eines Users
     * @param songList2Add - die ID der SongListe die hinzugefügt werde soll
     * @return - ResponseEntity mit der Liste der Songs
     */
    ResponseEntity<?> addSongList(String userId, SongList songList2Add);

    /**
     * Update Songliste eines Users
     * @param id - die ID der SongListe die geupdated werden soll
     * @param songList2Update - die SongListe die geupdated werden soll
     * @return - ResponseEntity mit der Liste der Songs
     */
    ResponseEntity<?> updateSongList(String userId, Long id, SongList songList2Update);

    /**
     * Delete Songliste eines Users
     * @param songListId - die ID der SongListe die gelöscht werden soll
     */
    void deleteThisSongList(Long songListId);
}
