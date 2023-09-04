/**
 * Dastekin created on 04.09.2023 the Authorization-Class inside the package - de.dastekin.zelkulon.songs
 */
package de.dastekin.zelkulon.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Diese abstrakte Klasse dient als Basisklasse für verschiedene
 * Autorisierungsmechanismen.
 *
 * Sie verwendet RestTemplate, um eine externe Authentifizierungs-API
 * für die Benutzerautorisierung anzusprechen.
 */
public abstract class Authorization {

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

        return restTemplate.exchange(   // TODO prüfen ob endpoint richtig ist
                "http://authService/songsMS/rest/auth", HttpMethod.GET, entity, String.class).getBody();
    }


}
