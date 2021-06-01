package io.swagger.service;
import io.swagger.model.Account;
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

    public void createUser(CreateUserDTO newUser){
        Long DAY_LIMIT = 1000L;
        Long TRANSACTION_LIMIT = 1000L;

        int MINIMUM_PASSWORD_LENGTH = 6;
        String password = newUser.getPassword();
        String email = newUser.getEmail();
        String username = newUser.getUsername();
        String phone = newUser.getPhone();

        for (User u: repository.findAll()) {
            if (u.getUsername().equals(username)){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Username is already in use, please try again and choose a different one");
            }
            else if (u.getEmail().equals(email) || u.getPhone().equals(phone)){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "User already exists");
            }
            else if (password.length() < MINIMUM_PASSWORD_LENGTH){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Password is too short, choose a longer one and try again");
            }
        }
        User user = new User();
        user.setId(null);
        user.setLocked(false);
        user.setDayLimit(DAY_LIMIT);
        user.setTransactionLimit(TRANSACTION_LIMIT);

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(username);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhone(phone);
        user.setRoles(newUser.getRole());


        //save the user and generate an account
        generateAccountForUser(repository.save(user));
    }


    public void lockUserById(Long id) {
        User u = repository.findByIdEquals(id);
        if (u == null){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "This user does not exist");
        }
        u.setLocked(true);
        repository.save(u);
    }

    public void updateUser(User body) {
        for (User u: repository.findAll()) {
            if (u.getUsername().equals(body.getUsername())){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Username is already in use choose a different one");
            }
        }
        repository.save(body);
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
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "This user does not exist");
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