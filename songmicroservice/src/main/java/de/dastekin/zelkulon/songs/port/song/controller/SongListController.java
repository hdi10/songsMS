package de.dastekin.zelkulon.songs.port.song.controller;

import de.dastekin.zelkulon.songs.Authorization;
import de.dastekin.zelkulon.songs.core.domain.service.impl.SongListService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.ISongListService;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongListRepository;
import de.dastekin.zelkulon.songs.core.domain.service.interfaces.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import de.dastekin.zelkulon.songs.Authorization;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping(value = "/songLists")
public class SongListController extends Authorization {
    private static final Logger myLogger = LoggerFactory.getLogger(SongListController.class);

    @Autowired
    private final ISongListService service;

    public SongListController(SongListRepository songListRepository, SongRepository songRepository) {
        this.service = new SongListService(songListRepository, songRepository);
    }

    /*
     * Get All SongLists aus dem service aufrufen
     */
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<?> getAllSongListsByUserId(
//            @RequestHeader("Authorization") String authToken,
//            @RequestParam(required = false, name = "userId") Optional<String> userIdSearch4) {
//
//        try {
//                // TODD: HIER HARDCODED !!!!!!!!!!!!!!!!!!!
//            //String authorizedUser = String.valueOf(authUser(authToken));
//
//                myLogger.info("Fetching all song lists");
//                myLogger.info(service.getAllSongLists("maxime", 3L).toString());
//                return service.getAllSongLists("maxime", 3L);
//
//
//        } catch (Exception e) {
//            myLogger.error("An error occurred while fetching song lists: ", e);
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllSongListObPrivateOderNichtVonOwner(@RequestHeader("Authorization") String authToken, @RequestParam(name = "owner_id") String ownerID) {

        Mono<String> derAuthentifizierteUser = authUser(authToken);



        return service.getAllSongListsVonOwnerObPrivateOderNicht(derAuthentifizierteUser.block());
    }



}
