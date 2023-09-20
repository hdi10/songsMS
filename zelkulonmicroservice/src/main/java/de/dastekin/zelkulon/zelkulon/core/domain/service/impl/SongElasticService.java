package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.interfaces.SongElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;

@Service
public class SongElasticService {
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
        songElastic.setTitle(currentlyPlaying.getItem().getName());


        songElastic.setArtist("testArtist");
        saveSong(songElastic);
    }
}
