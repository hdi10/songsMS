/**
 * Dastekin created on 21.07.2023 the SongController-Class inside the package - de.dastekin.zelkulon.songs.port.song.controller
 */
package de.dastekin.zelkulon.songs.port.song.controller;
import de.dastekin.zelkulon.songs.core.domain.model.Song;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import de.dastekin.zelkulon.songs.port.song.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/songsMS/rest/songs")
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SongController {
    private final SongRepository songsRepository;

    public SongController(SongRepository songsRepository) {
        this.songsRepository = songsRepository;
    }

    /**
     * Hello Test Endpoint
     * @return "Teststring"
     */
    @RequestMapping(value="/hello1",
            method= RequestMethod.GET,
            produces = "text/plain;charset=UTF-8")
    @ResponseBody
    String sayHelloToUser() {
        return "Teststring";
    }


    /**
     * CREATE -> POST 1 Song
     * @param song -  Song to Post
     * @return - when Successful STATUS 201 and URI
     *           else 400 or 404
     */
    // @PostMapping(value = "")
    @PostMapping("")
    @ResponseBody
    ResponseEntity<?> postSong(@RequestBody Song song){
        //TODO: authenticate hier rein
        //TODO: STATUS
        Song tmpSong = songsRepository.save(song);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", "/rest/songs/" + tmpSong.getId());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }


    /**
     * READ - GET 1 Song By ID
     * @param id - ID of Song
     * @return - when Successful STATUS 200 and URI
     *           else 400 or 404
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Song getSongById(@PathVariable(value = "id") Long id){
        //TODO: authenticate hier rein
        //TODO: STATUS
        Song song = songsRepository.selectSongById(id);
        if (song == null) {
            throw new ResourceNotFoundException("Note", "id", id);
        }
        return song;
    }


    /**
     * UPDATE 1 By ID -> PUT 1 as JSON
     * @param id - ID of Song
     * @param songForUpdate - Song to Put
     * @return - when succesful Status 204
     *           else 400 or 404
     */
    @PutMapping(value="/{id}")
    @ResponseBody
    ResponseEntity<?> updateSong(@PathVariable(value = "id") Long id,
                                 @RequestBody Song songForUpdate){
        //TODO: authenticate hier rein
        //TODO: STATUS
        //todo nur erfolgreicH?

        songsRepository.save(songForUpdate);
        return ResponseEntity.noContent().build();

    }

    /**
     * UPDATE 1 By ID
     * @param id - ID of Song
     * @return - when succesful Status 204
     *           else 400 or 404
     */
    @DeleteMapping(value="/{id}")
    @ResponseBody
    ResponseEntity<?> deleteSong(@PathVariable(value = "id") Long id){
        if (songsRepository.existsById(id)){
            songsRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(400).build();
        }
    }


    /**
     * READ -> GET ALL
     * @return - when Successful STATUS 200 and URI
     *           else 400 or 404
     */
    @RequestMapping(value="/all",
            method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8"
    )
    @ResponseBody
    ResponseEntity<List<Song>> getAllSongs(){
        //////if (userController.authenticateUser())-------> TODO: hier weiter mit authenticate Business logic
        try {
            List<Song> songs =  songsRepository.getAllSongs();
            if (songs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
