/**
 * Dastekin created on 19.07.2023 the User-Class inside the package - de.dastekin.zelkulon.auth.core.domain.model
 */
package de.dastekin.zelkulon.auth.core.domain.model;
import jakarta.persistence.*;

import java.util.Set;
/*
CREATE TABLE IF NOT EXISTS 'usertable' (
    userid VARCHAR ( 20 ) PRIMARY KEY,
    password VARCHAR ( 20 ) NOT NULL,
    firstName VARCHAR ( 50 ) NOT NULL,
    lastName VARCHAR ( 50 ) NOT NULL,
    token TEXT NOT NULL
);


 */

// TODO: In diese Klasse den Builder aus oldSpringMVC einbauen

@Entity
@Table(name = "usertable")
public class User {

    @Id
    @Column(name="user_id")
    private String userId;

    private String password;

    private String firstName;

    private String lastName;

    private String token;

/*
    @OneToMany(mappedBy = "userId") //mögliches problem mit dem mapping wegen name user_id oder userId?
    Set<SongList> songLists;
*/

    public User() {
    }

    public User(String userId, String password, String firstName, String lastName, String token) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
