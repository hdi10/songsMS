/**
 * Dastekin created on 21.07.2023 the SongController-Class inside the package - de.dastekin.zelkulon.songs.port.song.controller
 */
package de.dastekin.zelkulon.songs.port.song.controller;

import de.dastekin.zelkulon.songs.Authorization;
import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.service.impl.SongService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/songs")
public class SongController extends Authorization {

    @Autowired
    private final ISongService service;
    private final SongRepository songRepository;

    public SongController(SongRepository songsRepository,
                          SongRepository songRepository) {
        this.service = new SongService(songsRepository);
        this.songRepository = songRepository;
    }


    /**
     * Hello Test Endpoint
     *
     * @return "Teststring"
     */
    @RequestMapping(value = "/hello1",
            method = RequestMethod.GET,
            produces = "text/plain;charset=UTF-8")
    @ResponseBody
    String sayHelloToUser(@RequestHeader(value = "Authorization") String authHeader) {
        try {

            authUser(authHeader);



            return "Teststring";
        }catch (Exception e){
            e.printStackTrace();
            return String.valueOf(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////      GET Methods Start////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> getSongById(
            @RequestHeader(value = "Authorization") String authHeader,
            @PathVariable(value = "id") Long id) {
        try {
            authUser(authHeader);
            return service.getSongById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    //TODO STAtus
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> getAllSongs(
            @RequestHeader(value = "Authorization") String authHeader) {
        try {
            authUser(authHeader);
            return service.getAllSongs();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    // TODO: 21.07.2023  GETALL Status übermnehemn!
    /*@RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8"
    )
    @ResponseBody
    ResponseEntity<List<Song>> getAllSongs() {
        //////if (userController.authenticateUser())-------> TODO: hier weiter mit authenticate Business logic
        try {
            List<Song> songs = songsRepository.getAllSongs();
            if (songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    ////////////////////////////////////////////////////////////////////////////
    //////////      GET Methods END ////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////
    //////////      POST Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Object>
    addSong(
            @RequestHeader("Authorization") String authToken, @RequestBody Song songToAdd) {
        try {
            authUser(authToken);
            return service.postSong(songToAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

   /*
    // @PostMapping(value = "")
    @PostMapping("")
    @ResponseBody
    ResponseEntity<?> postSong(@RequestBody Song song) {
        //TODO: authenticate hier rein
        //TODO: STATUS
        Song tmpSong = songsRepository.save(song);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", "/rest/songs/" + tmpSong.getId());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

*/
    //////////////////////////////////////////////////////////////////////////////
    //////////      POST Methods END    /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    //////////      PUT Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Object>
    updateSong(
            @RequestHeader("Authorization") String authToken,
            @PathVariable(value = "id") Long id,
            @RequestBody Song songToPut) {
        try {
            authUser(authToken);
            return service.updateSong(id, songToPut);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

/*

    /*

    @PutMapping(value = "/{id}")
    @ResponseBody
    ResponseEntity<?> updateSong(@PathVariable(value = "id") Long id,
                                 @RequestBody Song songForUpdate) {
        //TODO: authenticate hier rein
        //TODO: STATUS
        //todo nur erfolgreicH?

        songsRepository.save(songForUpdate);
        return ResponseEntity.noContent().build();

    }
*/



    //////////////////////////////////////////////////////////////////////////////
    //////////      PUT Methods END  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    //////////      DELETE Methods Start  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////



    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSong(
            @RequestHeader("Authorization") String authToken,
            @PathVariable(value = "id") Long id) {
        try {
            authUser(authToken);
            return service.deleteSong(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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
