package io.swagger.repository;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Query("update User as u set u.locked = true where u.email = :email")
    void lockUserByEmail(@PathVariable("email") String email);

    @Modifying
    @Query("update User as u set u.locked = true where u.id = :id")
    void lockUserById(@PathVariable("id") Integer id);

    List<User> findAllByEmailContaining(String searchString);

    List<User> findAllUsers();

    @Query("")
    User findUserByEmail(String email);

    User findUserById(Integer id);
}