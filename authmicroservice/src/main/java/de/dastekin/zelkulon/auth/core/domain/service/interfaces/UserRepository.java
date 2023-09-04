package de.dastekin.zelkulon.auth.core.domain.service.interfaces;

import de.dastekin.zelkulon.auth.core.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM Users WHERE UserId = ?1", nativeQuery = true)
    User selectUserByUserId(String userId);

    @Query(value = "SELECT * FROM Users WHERE UserId = ?1 AND password = ?2", nativeQuery = true)
    List<User> authenticateByPassword(String userId, String password);

    @Query(value = "SELECT * FROM Users WHERE UserId = ?1 AND password = ?2 AND token IS NOT NULL", nativeQuery = true)
    User authenticateByPasswordAndToken(String userId, String password);

    @Query(value = "SELECT UserId FROM Users WHERE Token = ?1", nativeQuery = true)
    User getUserByToken(String token);


}
