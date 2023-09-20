package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyAuthService {

//    @Value("${spotify.client.id}")
//    private String clientId;
//
//    @Value("${spotify.client.secret}")
//    private String clientSecret;
//
//    @Value("${spotify.redirect.uri}")
//    private String redirectUri;

    private static final String CLIENT_ID = "3373434b15ef4563bef4c04432187fd3";
    private static final String CLIENT_SECRET = "f8d62d1d75fa41e79641f9e8e8edbaec";
    private static final String REDIRECT_URI = "http://localhost:8080/api/spotify/callback";

    private SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(SpotifyHttpManager.makeUri(REDIRECT_URI))
            .build();

    private Map<String, AuthorizationCodeCredentials> tokenMap = new HashMap<>();

    public URI getAuthorizationUri() {
        AuthorizationCodeUriRequest request = spotifyApi.authorizationCodeUri()
                .scope("user-read-playback-state")
                .show_dialog(true)
                .build();
        return request.execute();
    }

    public AuthorizationCodeCredentials getTokens(String code) {
        AuthorizationCodeRequest request = spotifyApi.authorizationCode(code).build();
        try {
            AuthorizationCodeCredentials credentials = request.execute();
            return credentials;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveTokens(String userId, AuthorizationCodeCredentials credentials) {
        tokenMap.put(userId, credentials);
    }

    public AuthorizationCodeCredentials getStoredTokens(String userId) {
        return tokenMap.get(userId);
    }
}
