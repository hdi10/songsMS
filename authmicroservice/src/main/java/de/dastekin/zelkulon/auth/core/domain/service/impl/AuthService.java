/**
 * Dastekin created on 04.09.2023 the AuthService-Class inside the package - de.dastekin.zelkulon.auth.core.domain.service.impl
 */
package de.dastekin.zelkulon.auth.core.domain.service.impl;

import de.dastekin.zelkulon.auth.core.domain.model.User;
import de.dastekin.zelkulon.auth.core.domain.service.interfaces.IAuthService;
import de.dastekin.zelkulon.auth.core.domain.service.interfaces.UserRepository;
import de.dastekin.zelkulon.auth.port.user.controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private static final Logger logger = LogManager.getLogger(AuthService.class);
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> checkUser(String authToken) {
        User user = userRepository.getUserByToken(authToken);
        if (user == null) {
            logger.info("Nutzer nicht gefunden mit dem Token: " + authToken);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.info("Nutzer gefunden mit dem TOKEN: " + authToken);
        return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> loginUser(User searchedUser) {
        String searchedUserId = searchedUser.getUserId();
        String searchedUserPw = searchedUser.getPassword();
        User user = userRepository.selectUserByUserId(searchedUserId);

        if (user == null || user.getUserId() == null ||
                user.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        logger.info("bis hierhin gekommen " + searchedUserId + " " + searchedUserPw);

        if (user.getUserId().equals(searchedUserId) && user.getPassword().equals(searchedUserPw)) {
            String token = user.generateToken();
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.add("Authorization", token);
            logger.info("im nächsten schritt speicher nutzer " + user.getUserId() + " mit dem token " + token);
            userRepository.save(user);

            logger.info("Nutzer Speicher Schritt getriggert " + userRepository.save(user).toString());

            return new ResponseEntity<>(token, authHeader, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
