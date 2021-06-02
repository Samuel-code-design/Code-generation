package io.swagger.service;
import io.swagger.model.Account;
import io.swagger.model.Role;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
public class EmployeeService {
    //TODO: messages werken niet
    //todo: responses Httpstatus 200 message (wanneer het goed gaat)

    //TODO: ook savings account aanmaken?
    private final EmployeeRepository repository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public EmployeeService(EmployeeRepository repository, AccountRepository accountRepository, AccountService accountService) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public User createUser(CreateUserDTO body){
        //some standard values
        Long DAY_LIMIT = 1000L;
        Long TRANSACTION_LIMIT = 1000L;
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

        //check if new user is also a customer
        if (body.getRole().contains(Role.ROLE_CUSTOMER)){
            //save the user and generate a CURRENT account
            generateAccountForUser(repository.save(user));
        } else {
            repository.save(user);
        }
        return user;
    }

    public User lockUserById(Long id) {
        User u = userById(id);
        u.setLocked(true);
        return repository.save(u);
    }

    public User updateUser(User body) {
        //Moet dit wel zo??
        // als je een username update kan je dan nog inloggen?
        // wat als de user alleen zijn daylimit wil update?

        if (repository.existsByUsername(body.getUsername())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Username is already in use choose a different one");
        }
        //encode the password
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        return repository.save(body);
    }

    public List<User> getUsers(String searchString) {
        if (searchString != null && !searchString.equals("")){
            return repository.findByEmailContainsOrUsernameContainsOrFirstNameContainsOrLastNameContaining
                    (searchString, searchString, searchString, searchString);
        }
        return repository.findAll();
    }

    public User userById(Long id) {
        try {
            return repository.findByIdEquals(id);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "Username is already in use choose a different one");
        }
    }

    public void generateAccountForUser(User u){
        BigDecimal ABSOLUTE_LIMIT = BigDecimal.valueOf(0);
        Account acc = new Account();
        acc.userId(u.getId());

        acc.setBalance(0.0);
        acc.setAbsoluteLimit(ABSOLUTE_LIMIT);
        acc.setIban(accountService.generateIban());
        acc.locked(false);
        acc.type(Account.TypeEnum.CURRENT);
        accountRepository.save(acc);
    }
}