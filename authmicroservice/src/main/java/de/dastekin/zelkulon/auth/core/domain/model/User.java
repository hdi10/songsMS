/**
 * Dastekin created on 19.07.2023 the User-Class inside the package - de.dastekin.zelkulon.auth.core.domain.model
 */
package de.dastekin.zelkulon.auth.core.domain.model;
import jakarta.persistence.*;

import java.security.SecureRandom;



/**
 * Model class for User
 */

// TODO: In diese Klasse den Builder aus oldSpringMVC einbauen
// TODO lombok um Boilerplate zu vermeiden
@Entity
@Table(name = "user_account")
public class User {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="token")
    private String token;

/*
    @OneToMany(mappedBy = "userId") //mögliches problem mit dem mapping wegen name user_id oder userId?
    Set<SongList> songLists;
*/

    //TODO BoilerPlate Code Löschen --> Lombok
    public User() {
    }

    public User(String userId, String password, String firstName, String lastName, String token) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getToken() {
        return token;
    }

    /**
     * Generiert einen nicht all zulangen Token
     * @return String Token
     */
    public String generateToken() {
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

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
