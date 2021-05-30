package io.swagger.service;

import io.swagger.model.dto.CreateUserDTO;
import io.swagger.model.User;
import io.swagger.models.auth.In;
import io.swagger.repository.EmployeeRepository;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void createUser(CreateUserDTO newUser){
        Long DAY_LIMIT = 1000L;
        Long TRANSACTION_LIMIT = 1000L;

//        int MINIMUM_PASSWORD_LENGTH = 10;
//        String password = newUser.getPassword();
//        String email = newUser.getEmail();
//        String username = newUser.getUsername();
//        String phone = newUser.getPhone();
//
//        boolean userExists = false;
//        boolean passTooShort = false;
//        boolean usernameInUse = false;
//
//        for (User u: repository.findAll()) {
//            if (u.getEmail().equals(email) ||
//                    u.getPhone().equals(phone)){
//                userExists = true;
//                break;
//            }
//            else if (password.length() < MINIMUM_PASSWORD_LENGTH){
//                passTooShort = true;
//                break;
//            }
//            else if (u.getUsername().equals(username)){
//                usernameInUse = true;
//                break;
//            }
//        }
//
//        if (userExists){
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists");
//        }
//        else if (passTooShort){
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Password is too short, choose a longer one and try again");
//        }else if(usernameInUse){
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username is already in use, please try again and choose a different one");
//        }
//        else{
//            User user = new User();
//            user.setId(null);
//            user.setLocked(false);
//            user.setDayLimit(DAY_LIMIT);
//            user.setTransactionLimit(TRANSACTION_LIMIT);
//
//            user.setEmail(email);
//            user.setPassword(password);
//            user.setUsername(username);
//            user.setFirstName(newUser.getFirstName());
//            user.setLastName(newUser.getLastName());
//            user.setPhone(newUser.getPhone());
//            user.setRoles(newUser.getRole());
//
//            repository.save(user);
//        }
        User user = new User();
            user.setId(null);
            user.setLocked(false);
            user.setDayLimit(DAY_LIMIT);
            user.setTransactionLimit(TRANSACTION_LIMIT);

            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setUsername(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhone(newUser.getPhone());
            user.setRoles(newUser.getRole());

            repository.save(user);
    }

    public void lockUserByEmail(String email) {
        User u = repository.findByEmailEquals(email);
        u.setLocked(true);
        repository.save(u);
    }

    public void lockUserById( Long id) {
        User u = repository.findByIdEquals(id);
        u.setLocked(true);
        repository.save(u);
    }

    public void updateUser(User body) {
        repository.save(body);
    }

    public List<User> getUsers(String searchString) {
        if (searchString != null){
            return repository.findAllByEmailContaining(searchString); //TODO: ook username fistname en lastname
        }
        return repository.findAll();
    }

    public User userByEmail(String email) {
        return  repository.findByEmailEquals(email);
    }

    public User userById(Long id) {
        return repository.findByIdEquals(id);
    }

}
