/**
 * Dastekin created on 21.07.2023 the SongListService-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.interfaces
 */
package de.dastekin.zelkulon.songs.core.domain.service.interfaces;

import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.model.SongList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface SongListService {

    SongList postSongList(@RequestHeader("Authorization") String authorization,@RequestBody SongList songList);

    SongList getSongListWithUser_id(@RequestHeader("Authorization") String authorization,@RequestParam Long user_id);

    SongList getSongListById(@RequestHeader("Authorization") String authorization, @RequestParam Long id);

    void deleteSongListById(@RequestHeader("Authorization") String authorization,@RequestBody SongList songList);

    /**
     * Update SongList
     * @param authorization - Token in Authentication Header
     * @param id - SongList id
     * @return - SongList
     */
    SongList putSongListById(@RequestHeader("Authorization") String authorization, @RequestParam Long id);



}
