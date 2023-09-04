/**
 * Dastekin created on 04.09.2023 the AuthService-Class inside the package - de.dastekin.zelkulon.auth.core.domain.service.interfaces
 */

package de.dastekin.zelkulon.auth.core.domain.service.interfaces;

import de.dastekin.zelkulon.auth.core.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    /**
     * Checks if the user is authorized
     * @param authToken - der Token der zur authentication dient
     * @return - der User der eingeloggt ist
     */
    ResponseEntity<Object>  checkUser(String authToken);

    /**
     * Logs in the user
     * @param user -  der User der eingeloggt werden soll
     * @return - der Token der zur authentication dient
     */
    ResponseEntity<Object> loginUser(User user);
}
