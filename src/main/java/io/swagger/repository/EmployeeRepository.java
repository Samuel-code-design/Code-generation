package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContainsOrUsernameContainsOrFirstNameContainsOrLastNameContaining(String email, String username, String firstName, String lastName);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    User findByIdEquals(Long id);

}
