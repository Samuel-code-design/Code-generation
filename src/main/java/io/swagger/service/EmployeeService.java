package io.swagger.service;

import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class EmployeeService {
    private final UserRepository repository;
    private final AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(UserRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public User createUser(CreateUserDTO body){
        int MINIMUM_PASSWORD_LENGTH = 6;
        //check if the user is valid

        if (repository.existsByUsername(body.getUsername())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Username is already in use, please try again and choose a different one");
        }
        else if (repository.existsByEmail(body.getEmail()) || repository.existsByPhone(body.getPhone())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User already exists");
        }
        else if (body.getPassword().length() < MINIMUM_PASSWORD_LENGTH){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Password is too short, choose a longer one and try again");
        }
        //make the user
        User user = createUserFromDTO(body);

        //check if new user is also a customer
        if (body.getRole().contains(Role.ROLE_CUSTOMER)){
            //save the user and generate a CURRENT account
            repository.save(user);
            accountService.generateCurrentAccountForUser(user);
        }
        else{
            repository.save(user);
        }
        return user;
    }

    public User lockUserById(Long id) {
        User u = userById(id);
        if (u.getLocked().equals(true)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User is already locked");
        } else {
            u.setLocked(true);
            return repository.save(u);
        }
    }

    public User updateUser(User body) {
        //get the user with the given id
        User u = repository.findByIdEquals(body.getId());

        //check if the user exists
        if (u != null) {

            //check if username is being changed
            if (!u.getUsername().equals(body.getUsername())){
                //check if another user is using this username
                if (repository.existsByUsername(body.getUsername())){
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Username is already in use by a different user");
                }
            }
            //check if email is being changed
            else if (!u.getEmail().equals(body.getEmail())){
                //check if another user is using this email
                if (repository.existsByEmail(body.getEmail())){
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Email is already in use by a different user");
                }
            }
            //check if phone is being changed
            else if (!u.getPhone().equals(body.getPhone())){
                //check if another user is using this phone
                if (repository.existsByPhone(body.getPhone())){
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Phone is already in use by a different user");
                }
            }

            //encode the password
            body.setPassword(passwordEncoder.encode(body.getPassword()));
            return repository.save(body);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User with this id does not exist");
        }
    }

    public List<User> getUsers(String searchString) {
        //check if the field is not empty
        if (searchString != null && !searchString.equals("")){
            List<User> users =  repository.findByEmailContainsOrUsernameContainsOrFirstNameContainsOrLastNameContaining
                    (searchString, searchString, searchString, searchString);

            //check if a user was found
            if (users.toArray().length == 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "No user found");
            }
            return users;
        }
        else{
            List<User> u = repository.findAll();
            if(u.toArray().length == 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "There are no users");
            }
            return repository.findAll();
        }
    }

    public User userById(Long id) {
        User u = repository.findByIdEquals(id);
        if (u == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "No User with this id");
        }
        return u;
    }

    public User createUserFromDTO(CreateUserDTO body){
        Long DAY_LIMIT = 1000L;
        Long TRANSACTION_LIMIT = 1000L;

        User user = new User();
        user.setId(null);
        user.setLocked(false);
        user.setDayLimit(DAY_LIMIT);
        user.setTransactionLimit(TRANSACTION_LIMIT);

        user.setEmail(body.getEmail());
        user.setUsername(body.getUsername());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setPhone(body.getPhone());
        user.setRoles(body.getRole());

        //encode the password
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        return  user;
    }

}