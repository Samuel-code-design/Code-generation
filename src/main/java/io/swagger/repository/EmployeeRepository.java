package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Long> {

//    @Modifying
//    @Query("update User as u set u.locked = true where u.email = :email")
//    void lockUserByEmail(@Param("email") String email);
//
//    @Modifying
//    @Query("update User as u set u.locked = true where u.id = :id")
//    void lockUserById(@Param("id") Integer id);


    List<User> findByEmailStartingWithOrUsernameStartingWithOrFirstNameStartingWithOrLastNameStartingWith(String email, String username, String firstName, String lastName);
    List<User> findByEmailContainsOrUsernameContainsOrFirstNameContainsOrLastNameContaining(String email, String username, String firstName, String lastName);


//    @Query("select u.id, u.locked, u.role, u.username, " +
//            "u.firstName, u.lastName, u.email, u.password, " +
//            "u.dayLimit, u.transactionLimit, u.phone " +
//            "from User as u")
//    List<User> findAllUsers();

//    @Query("select u.id, u.locked, u.role, u.username, " +
//            "u.firstName, u.lastName, u.email, u.password, " +
//            "u.dayLimit, u.transactionLimit, u.phone " +
//            "from User as u where u.email = :email")
//    User findUserByEmail(@Param("email") String email);

    //    @Query("select u.id, u.locked, u.role, u.username, " +
//            "u.firstName, u.lastName, u.email, u.password, " +
//            "u.dayLimit, u.transactionLimit, u.phone " +
//            "from User as u where u.id = :id")
//    User userById(@Param("id") Integer id);

    User findByEmailEquals(String email);

    User findByIdEquals(Long id);

}
