package de.dastekin.zelkulon.songs.port.song.controller;

import de.dastekin.zelkulon.songs.Authorization;
import de.dastekin.zelkulon.songs.core.domain.model.SongList;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;
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


    /*
    * Das Hier ist die erste GET Anfrage aus dem Übungsblatt 3
    * Hier soll ein Get REQUEST mit dem Header Authorization dem @RequestParam (also /songLists?owner_id=...) übergeben werden
    * token=maxime und liste maxime dann private und public von maxime
    * token=maxime und liste jane dann nur public von jane
    * token=maxime und liste von nichtExistentemUser dann 404
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllSongListObPrivateOderNichtVonOwner(@RequestHeader("Authorization") String authToken, @RequestParam(name = "owner_id") String ownerID) {

        Mono<String> derAuthentifizierteUser = authUser(authToken);


        // Mein GUARD
        if(!service.gibtEsDenUser(ownerID)){
            myLogger.info("Der User existiert nicht");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        /*
         * Wenn der authentifizierte User der Owner ist, dann gib alle SongLists zurück, ob private oder nicht
         * Wenn der authentifizierte User nicht der Owner ist, dann gib nur die public SongLists zurück
         */
        if (Objects.equals(derAuthentifizierteUser.block(), ownerID)) {
            return service.getAllSongListsVonOwnerObPrivateOderNicht(derAuthentifizierteUser.block()) ;
        }else {
            return service.getAllSongListVonJemandAnderem(ownerID);
        }


    }


//    @GetMapping(value = "/{song_list_id}", produces = "application/json")
//    public ResponseEntity<?> getSongListeMitBestimmterIdAnDenNutzerMitDemToken(@RequestHeader("Authorization") String authToken, @PathVariable("song_list_id") Long songListId) {
//
//        Mono<String> derAuthentifizierteUser = authUser(authToken);
//
//        // Mein GUARD
//        if (!service.gibtEsDieSonglisteMitDerID(songListId)) {
//            myLogger.info("Die Songliste existiert nicht");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        /*
//         * Wenn der authentifizierte User der Owner ist, dann gib alle SongLists zurück, ob private oder nicht
//         * Wenn der authentifizierte User nicht der Owner ist, dann gib nur die public SongLists zurück
//         */
//        if (Objects.equals(derAuthentifizierteUser.block(), service.gibMirBitteDenNamenDesBesitzerDerSongListId(songListId))) {
//            myLogger.info("Der User(token) ist der Owner");
//            return service.gibMirDieSongListeMitDerId(songListId);
//        } else {
//            myLogger.info("Der User(token) ist nicht der Owner");
//            if (!service.istDieseListePublic(songListId)) {
//                myLogger.info("Die Songliste ist nicht public"+"der User(token) ");
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//            }else {
//                myLogger.info("Die Songliste ist public");
//                return service.gibMirBitteSonglisteMitIdWennPublic(songListId);
//            }
//        }
//    }

    @GetMapping(value = "/{songListId}", produces = "application/json")
    public ResponseEntity<?> getSongListeMitBestimmterIdAnDenNutzerMitDemToken(
            @RequestHeader("Authorization") String authToken,
            @PathVariable("songListId") Long songListId) {

        Mono<String> derAuthentifizierteUser = authUser(authToken);

        // Überprüfung, ob die Songliste existiert
        if (!gibtEsDieSongliste(songListId)) {
            logSongListeNichtExistiert();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String besitzerDerListe = gibBesitzerDerListe(songListId);
        String aktuellerBenutzer = derAuthentifizierteUser.block();

        // Überprüfung, ob der authentifizierte Benutzer der Besitzer ist
        if (Objects.equals(aktuellerBenutzer, besitzerDerListe)) {
            logBenutzerIstBesitzer();
            return service.gibMirDieSongListeMitDerId(songListId);
        }

        // Überprüfung, ob die Liste öffentlich ist, wenn der Benutzer nicht der Besitzer ist
        if (istListeOeffentlich(songListId)) {
            logListeIstOeffentlich();
            return service.gibMirDieSongListeMitDerId(songListId);
        }

        logZugriffVerboten();
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private boolean gibtEsDieSongliste(Long id) {
        return service.gibtEsDieSonglisteMitDerID(id);
    }

    private String gibBesitzerDerListe(Long id) {
        return service.gibMirBitteDenNamenDesBesitzerDerSongListId(id);
    }

    private boolean istListeOeffentlich(Long id) {
        return service.istDieseListePublic(id);
    }

    private void logSongListeNichtExistiert() {
        myLogger.info("Die Songliste existiert nicht");
    }

    private void logBenutzerIstBesitzer() {
        myLogger.info("Der User(token) ist der Owner");
    }

    private void logListeIstOeffentlich() {
        myLogger.info("Die Songliste ist public");
    }

    private void logZugriffVerboten() {
        myLogger.info("Die Songliste ist nicht public und der User(token) ist nicht der Owner");
    }


    //POST

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> postSongList(
            @RequestHeader("Authorization") String authToken,
            @RequestBody SongList songList) {

        // Guard #1: Token ist leer
        if (authToken == null || authToken.isEmpty()) {
            return new ResponseEntity<>("Unauthorized KEIN TOKEN", HttpStatus.UNAUTHORIZED);
        }

        // Guard #2: TOken ist nicht leer, aber falsch TODO: try-catch für 401?!?!
        try {

            Mono<String> derAuthentifizierteUser = authUser(authToken);
            return service.addSongList(derAuthentifizierteUser.block(), songList);

        } catch (WebClientResponseException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                return new ResponseEntity<>("Unauthorized FALSCHER TOKEN", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
