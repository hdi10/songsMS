//package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import se.michaelthelin.spotify.SpotifyApi;
//import se.michaelthelin.spotify.SpotifyHttpManager;
//
//import java.net.URI;
//
//@Service
//public class SpotifyConfig {
//
//    @Value("${spotify.clientId}")
//    private String clientId;
//
//    @Value("${spotify.clientSecret}")
//    private String clientSecret;
//
//
//    @Value("$[redirect.server.ip]")
//    private String customIp;
//
//    public SpotifyApi getSpotifyObject(){
//        URI redirectUri = SpotifyHttpManager.makeUri(customIp+"/api/het-user-code/");
//
//        return new SpotifyApi.Builder()
//                .setClientId(clientId)
//                .setClientSecret(clientSecret)
//                .setRedirectUri(redirectUri)
//                .build();
//
//    }
//
//
//}
