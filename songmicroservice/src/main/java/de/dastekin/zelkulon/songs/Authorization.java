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

/**
 * Diese abstrakte Klasse dient als Basisklasse für verschiedene
 * Autorisierungsmechanismen.
 *
 * Sie verwendet RestTemplate, um eine externe Authentifizierungs-API
 * für die Benutzerautorisierung anzusprechen.
 */
public abstract class Authorization {

    Logger log = org.slf4j.LoggerFactory.getLogger(Authorization.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * Autorisiert einen Benutzer durch den Aufruf einer externen Authentifizierungs-API.
     *
     * @param authToken Das Authentifizierungstoken für den Benutzer.
     * @return Eine Zeichenfolge, die das Ergebnis der Autorisierung darstellt.
     */
    public String authUser(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "http://localhost:9010/songMS/rest/auth";
        log.info("Sende Request an folgende URL: " + url);

        return restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class).getBody();
    }


}
