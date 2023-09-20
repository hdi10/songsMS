package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class TestKlasseSpotifyAPI {
    private static final String accessToken = "BQD80ChpetphHaKx5J6c-swiMiOK4gfPeniNA-_n8EH1l7_EjRC-zi59oVU6osNDXWnigSciKGweqZmZlOg5kPFECfsFAZZ7tlvPlDCr2Jtvgp9hNpSLTjfee-J4EvZAMKZxNUyE20_PaMdmoXT5DZLcpjSbI-UMRJj-8WaQgRzBI1NM65SLBz1AAO_TOxsGG5FwS64RIsMbww";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
            .getUsersCurrentlyPlayingTrack()
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
            .build();

    public static CurrentlyPlaying getUsersCurrentlyPlayingTrack_Sync() {
        try {
            final CurrentlyPlaying currentlyPlaying = getUsersCurrentlyPlayingTrackRequest.execute();


            System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());

            return currentlyPlaying;
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (org.apache.hc.core5.http.ParseException e) {
            throw new RuntimeException(e);

        }

    }

    public static CurrentlyPlaying getUsersCurrentlyPlayingTrack_Async() {
        try {
            final CompletableFuture<CurrentlyPlaying> currentlyPlayingFuture = getUsersCurrentlyPlayingTrackRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final CurrentlyPlaying currentlyPlaying = currentlyPlayingFuture.join();

            System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());

            System.out.println("Currently playing track: " + currentlyPlaying.getItem().getName());

            return currentlyPlaying;
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
            return null;
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
            return null;
        }
    }



}
