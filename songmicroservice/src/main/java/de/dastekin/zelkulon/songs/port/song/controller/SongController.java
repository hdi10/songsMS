/**
 * Dastekin created on 21.07.2023 the SongController-Class inside the package - de.dastekin.zelkulon.songs.port.song.controller
 */
package de.dastekin.zelkulon.songs.port.song.controller;

import de.dastekin.zelkulon.songs.Authorization;
import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.service.impl.SongService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/songs")
public class SongController extends Authorization {
    Logger logger = org.slf4j.LoggerFactory.getLogger(SongController.class);

    @Autowired
    private final ISongService service;

    public SongController(SongRepository songsRepository) {
        this.service = new SongService(songsRepository);
    }


    //////////////////////////////////////////////////////////////////////////////
    //////////      GET Methods Start////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getSongById(
            @RequestHeader(value = "Authorization") String authHeader,
            @PathVariable(value = "id") Long id) {
        try {
            authUser(authHeader).block();
            return service.getSongById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getAllSongs(
            @RequestHeader(value = "Authorization") String authHeader) {
        try {

            authUser(authHeader).block();
            return service.getAllSongs();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


    //////////////////////////////////////////////////////////////////////////////
    //////////      POST Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?>
    addSong(
            @RequestHeader("Authorization") String authToken, @RequestBody Song songToAdd) {
        try {
            authUser(authToken).block();
            return service.postSong(songToAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////      POST Methods END    /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    //////////      PUT Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

   @PutMapping(value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?>
    updateSong(
            @RequestHeader("Authorization") String authToken,
            @PathVariable(value = "id") Long id,
            @RequestBody Song songToPut) {
        try {
            String dieserUser= authUser(authToken).block();

            logger.info("id: " + id);
            logger.info("songToPut: " + songToPut);
            logger.info("dieser User "+ dieserUser);

            ResponseEntity<?> myResponse = service.updateSong(id, songToPut);

            logger.info("myResponse: " + myResponse);

            return myResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////      PUT Methods END  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    //////////      DELETE Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //TODO DELETE MAPPING rausnehmen
//    @RequestMapping(value = "/{id}",
//            method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteSong(
//            @RequestHeader("Authorization") String authToken,
//            @PathVariable(value = "id") Long id) {
//        try {
//            authUser(authToken).block();
//            return service.deleteSong(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

/*

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    ResponseEntity<?> deleteSong(@PathVariable(value = "id") Long id) {
        if (songsRepository.existsById(id)) {
            songsRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

*/


    //////////////////////////////////////////////////////////////////////////////
    //////////      DELETE Methods END  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

}
