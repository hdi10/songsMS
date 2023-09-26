/**
 * Dastekin created on 04.09.2023 the Authorization-Class inside the package - de.dastekin.zelkulon.songs
 */
package de.dastekin.zelkulon.songs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public abstract class Authorization {

    private static final Logger log = LoggerFactory.getLogger(Authorization.class);

    @Autowired
    WebClient.Builder webClientBuilder;


    public Mono<String> authUser(String authToken) {
        WebClient webClient = webClientBuilder.build();

        //Bspw. Maxime
        Mono<String> responseIsUserName = webClient.get()
                .uri("http://auth-service:9000/auth")
                .header("Authorization", authToken)
                .retrieve()
//                .onStatus(status -> status.is4xxClientError(), response -> {
//                    // Authentifizierungsfehler
//                    return Mono.error(new WebClientResponseException(
//                            "Unauthorized",
//                            401,
//                            "Unauthorized",
//                            null,
//                            null,
//                            null
//                    ));
//                })
//                .onStatus(status -> status.is5xxServerError(), response -> {
//                    // Serverfehler
//                    return Mono.error(new WebClientResponseException(
//                            "Internal Server Error",
//                            500,
//                            "Internal Server Error",
//                            null,
//                            null,
//                            null
//                    ));
//                })
                .bodyToMono(String.class)
                .doOnNext(response -> log.info(response))
                .doOnError(error -> log.error("Error during authorization: ", error));


        log.info(responseIsUserName.toString());

        return responseIsUserName;
    }


}
