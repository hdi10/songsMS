package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.interfaces.SongElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;

import java.util.Arrays;

@Service
public class SongElasticService {

    Logger logger = LoggerFactory.getLogger(SongElasticService.class);

    @Autowired
    private SongElasticRepository songElasticRepository;

    @Autowired
    private TestKlasseSpotifyAPI testKlasseSpotifyAPI;

    public void saveSong(SongElastic songElastic) {
        songElasticRepository.save(songElastic);
    }

    public void saveCurrentPlayingSong() {
        // Rufe die Spotify-API auf, um den aktuell abgespielten Song zu erhalten
        CurrentlyPlaying currentlyPlaying = testKlasseSpotifyAPI.getUsersCurrentlyPlayingTrack_Async();

        // Erstelle ein neues SongElastic-Objekt und speichere es
        SongElastic songElastic = new SongElastic();

        logger.info("Currently playing track: " + currentlyPlaying.getItem().getName());
        logger.info("Currently playing artist: " + testKlasseSpotifyAPI.getArtistNameFromCurrentlyPlaying(currentlyPlaying));
        logger.info("Currently playing label: " + currentlyPlaying.getItem().toString());
        logger.info("Currently playing released: " + testKlasseSpotifyAPI.getReleaseYearFromCurrentlyPlaying(currentlyPlaying));

        songElastic.setTitle(currentlyPlaying.getItem().getName());
        songElastic.setArtist(testKlasseSpotifyAPI.getArtistNameFromCurrentlyPlaying(currentlyPlaying));

        songElastic.setReleaseYear(testKlasseSpotifyAPI.getReleaseYearFromCurrentlyPlaying(currentlyPlaying));

        saveSong(songElastic);
    }




}
