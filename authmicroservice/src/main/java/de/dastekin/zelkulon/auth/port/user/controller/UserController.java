package de.dastekin.zelkulon.auth.port.user.controller;


import de.dastekin.zelkulon.auth.core.domain.model.User;
import de.dastekin.zelkulon.auth.core.domain.service.interfaces.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;


@RestController
@RequestMapping(value = "/songsWS-zelkulon/rest")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Methode melden User an, und gibt den vergebenen Token zurück
     * bsp anfrage http://localhost:8080/songsWS-zelkulon/rest/auth?userId=maxime&password=pass1234
     * @return Response header mit aktuellem token
     */
    @PostMapping("/auth")
    public ResponseEntity<String> authenticateUser(@RequestBody User user,
                                                   @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        logger.info("try to authenticate user");

        HttpHeaders headers = new HttpHeaders();

        if (!isValidPassword(user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } else {
            if (!isTokenNull(user)) {
                if (isTokenValid(authorizationHeader, user)) {
                    headers.set("Authorization", authorizationHeader);
                    headers.set("Message", "Autorized with old token " + authorizationHeader);
                    return new ResponseEntity<>(headers, HttpStatus.OK);
                } else {
                    headers.set("Message", "Not autorized with token " + authorizationHeader);
                    return new ResponseEntity<>(headers, HttpStatus.UNAUTHORIZED);
                }
            } else {
                String newToken = generateToken();
                User oldUser = userRepository.selectUserByUserId(user.getUserId());
                User userWithNewToken = new User(
                        oldUser.getUserId(),
                        oldUser.getPassword(),
                        oldUser.getFirstName(),
                        oldUser.getLastName(),
                        newToken
                );
                userRepository.save(userWithNewToken);
                headers.set("Authorization", newToken);
                headers.set("Message", "Autorized with new token " + newToken);
                return new  ResponseEntity<>(headers, HttpStatus.OK);
            }
        }
    }

    /**
     * Generiert einen nicht all zulangen Token
     * @return String Token
     */
    private String generateToken() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 13;
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (
                int i = 0;
                i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (secureRandom.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * Prüft ob Passwort übereinstimmt mit User in DB
     * @return true wenn user zurückkommt, false sonst
     * @param user user to Authenticate
     */
    private boolean isValidPassword(User user) {
        if (!userRepository.authenticateByPassword(user.getUserId(), user.getPassword()).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prüft ob irgendein Token da
     * @return true wenn token null, false sonst
     * @param user user to Authenticate
     */
    private boolean isTokenNull(User user) {
        if (userRepository.authenticateByPasswordAndToken(user.getUserId(), user.getPassword()) == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTokenValid(String tokenFromAuthHeader, User user) {
        String tokenFromDB = userRepository.selectUserByUserId(user.getUserId()).getToken();
        if (tokenFromDB.contentEquals(tokenFromAuthHeader)) {
            return true;
        } else {
            return false;
        }
    }

}
