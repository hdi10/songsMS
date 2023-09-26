package de.dastekin.zelkulon.zelkulon.port.songelastic.controller;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.impl.SongElasticService;
import de.dastekin.zelkulon.zelkulon.core.domain.service.impl.TestKlasseSpotifyAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
@EnableScheduling
@RestController
@RequestMapping(value="/esong")
public class SongElasticController {
    Logger logger = LoggerFactory.getLogger(SongElasticController.class);

    @Autowired
    private SongElasticService service;


    @PostMapping("/songElastic")
    public void addSong(@RequestBody SongElastic songElastic) {
        service.saveSong(songElastic);
    }


    @Scheduled(fixedRate = 150000) // alle 2,5 minuten
    @PostMapping
    public ResponseEntity<?> saveCurrentPlayingSong() {
        try {
            return service.saveCurrentPlayingSong();
        }catch (Exception e) {
            return new ResponseEntity<>("Unauthorized FALSCHER TOKEN", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/count/{artist}")
    public ResponseEntity<?> countByName(@PathVariable String artist) {
        try {
            logger.info("Count by artist: " + artist);
            return service.countByArtistName(artist);

        }catch (Exception e){
            return new ResponseEntity<>("Unauthorized FALSCHER TOKEN", HttpStatus.UNAUTHORIZED);
        }

    }

//    @GetMapping("/saveCurrentPlayingSong")
//    public void saveCurrentPlayingSong() {
//
//        service.saveCurrentPlayingSong();
//
//    }

}
