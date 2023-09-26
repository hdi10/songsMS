package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class TestKlasseSpotifyAPI {
    private static final String accessToken = "BQAZGJJMGzVCfpY1y1nwpbz56j4-DO4XHW6BDXsqpt0mr2ICCIESEEI3IP5m0RegVuWGETQcjkhqt1fgSoEy4wo7G48uAkanvdSXBFWIgMFAf_WTofv8zfezqS1-VKXdxBUZqgA0vzU_EIE-Xn7pL1znUMD6VGAmVWKBHnhV5kLHhTrEGWMj9T7Zugq64IkzGK7my_zdYLEQ_w";

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


    public String getArtistNameFromCurrentlyPlaying(CurrentlyPlaying currentlyPlaying) {
        if (currentlyPlaying != null && currentlyPlaying.getItem() != null) {
            Object item = currentlyPlaying.getItem();
            if (item instanceof Track) {
                Track track = (Track) item;
                ArtistSimplified[] artists = track.getArtists();
                if (artists != null && artists.length > 0) {
                    return artists[0].getName();
                }
            }
        }
        return null; // Rückgabe von null, wenn der Künstlername nicht gefunden werden kann
    }

    public String getReleaseYearFromCurrentlyPlaying(CurrentlyPlaying currentlyPlaying) {
        if (currentlyPlaying != null && currentlyPlaying.getItem() != null) {
            Object item = currentlyPlaying.getItem();
            if (item instanceof Track) {
                Track track = (Track) item;
                AlbumSimplified album = track.getAlbum();
                if (album != null) {
                    String releaseDate = album.getReleaseDate();
                    if (releaseDate != null && !releaseDate.isEmpty()) {
                        String[] parts = releaseDate.split("-");
                        if (parts.length > 0) {
                            return parts[0];  // Das erste Element sollte das Jahr sein
                        }
                    }
                }
            }
        }
        return null;  // Rückgabe von null, wenn das Veröffentlichungsjahr nicht gefunden werden kann
    }


}
