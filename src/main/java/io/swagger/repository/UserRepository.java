package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByEmailContainsOrUsernameContainsOrFirstNameContainsOrLastNameContaining(String email, String username, String firstName, String lastName);

    boolean existsByPhone(String phone);

    User findByIdEquals(Long id);
    //User findByUsernameEquals(String username);

    boolean existsByid(Long id);
}
