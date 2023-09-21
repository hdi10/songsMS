package de.dastekin.zelkulon.zelkulon.port.songelastic.controller;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.impl.SongElasticService;
import de.dastekin.zelkulon.zelkulon.core.domain.service.impl.TestKlasseSpotifyAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongElasticController {
    Logger logger = LoggerFactory.getLogger(SongElasticController.class);

    @Autowired
    private SongElasticService service;

    @PostMapping("/songElastic")
    public void addSong(@RequestBody SongElastic songElastic) {
        service.saveSong(songElastic);
    }


    @GetMapping("/saveCurrentPlayingSong")
    public ResponseEntity<?> saveCurrentPlayingSong() {
        try {
            return service.saveCurrentPlayingSong();
        }catch (Exception e) {
            return new ResponseEntity<>("Unauthorized FALSCHER TOKEN", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/countByName/{artist}")
    public ResponseEntity<?> countByName(@PathVariable String artist) {
        logger.info("Count by artist: " + artist);
       return service.countByArtistName(artist);

    }

//    @GetMapping("/saveCurrentPlayingSong")
//    public void saveCurrentPlayingSong() {
//
//        service.saveCurrentPlayingSong();
//
//    }

}
