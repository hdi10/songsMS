/**
 * Dastekin created on 04.09.2023 the Authorization-Class inside the package - de.dastekin.zelkulon.songs
 */
package de.dastekin.zelkulon.songs;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.Serial;


/**
 * Diese abstrakte Klasse dient als Basisklasse für verschiedene
 * Autorisierungsmechanismen.
 *
 * Sie verwendet RestTemplate, um eine externe Authentifizierungs-API
 * für die Benutzerautorisierung anzusprechen.
 */
@Service
public abstract class Authorization {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Authorization.class);


    private WebClient.Builder webClientBuilder;

    public Mono<String> authUser(String authToken) {
        WebClient webClient = webClientBuilder.build();

        return webClient.get()
                .uri("http://auth-service/auth")
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(authResponse -> {
                    System.out.println("Received auth response: " + authResponse);
                });
    }


}
