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


        //generate account for user
        User generated = repository.save(user);
        generateAccountForUser(generated);
    }
    public void generateAccountForUser(User u){
        Account account = new Account();
        account.setBalance(0.0);
        account.setAbsoluteLimit(BigDecimal.valueOf(0));
        account.setIban(accountService.generateIban());
        account.locked(false);
        account.type(Account.TypeEnum.CURRENT);
        account.userId(u.getId());

        accountRepository.save(account);
    }

    //TODO: hoe krijg ik dit weg?
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
        for (User u: repository.findAll()) {
            if (u.getUsername().equals(body.getUsername())){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Username is already in use choose a different one");
            }
        }
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