/**
 * Dastekin created on 04.09.2023 the Authorization-Class inside the package - de.dastekin.zelkulon.songs
 */
package de.dastekin.zelkulon.songs;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Diese abstrakte Klasse dient als Basisklasse für verschiedene
 * Autorisierungsmechanismen.
 *
 * Sie verwendet RestTemplate, um eine externe Authentifizierungs-API
 * für die Benutzerautorisierung anzusprechen.
 */
public abstract class Authorization {

    Logger log = org.slf4j.LoggerFactory.getLogger(Authorization.class);

    private WebClient webClient = WebClient.create();

    public boolean authUser(String token) {
        Boolean isValid = webClient.post()
                .uri("http://auth-service/validateToken")
                .bodyValue(token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return isValid != null && isValid;
    }


}
