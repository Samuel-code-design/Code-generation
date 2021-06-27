package io.swagger.configuration;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    private AuthenticationService authenticationService;

    public ApplicationRunner(UserRepository userRepository, AccountRepository accountRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        List<Role> role = new ArrayList<>();
        role.add(Role.ROLE_CUSTOMER);


        User bank = new User("bank", passwordEncoder.encode("Wachtwoord!1"), "bank", "bank", "bak@gmail.com", "06 12345678",
                roles, false,  1000L, 1000L);
        userRepository.save(bank);

        User u = new User("JD0001", passwordEncoder.encode("Wachtwoord1#"), "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678",
                roles, false, 1000L, 1000L);
        userRepository.save(u);


        Account account = new Account("NL01INHO0000000001", AccountType.CURRENT, 100000.00, 100.00, false, bank);
        accountRepository.save(account);


        User customer = new User("TestCustomer", "Wachtwoord1#", "Serah", "Visser", "serah@gmail.com", "06 12345678",
                role, false, 1000L, 1000L);



        User customer2 = new User("TestCustomer2", "Wachtwoord1#", "Emma", "Haan", "emma@gmail.com", "06 12345678",
                role, false, 1000L, 1000L);

        authenticationService.signup(customer);
        authenticationService.signup(customer2);

        Account account1 = new Account("NL02INHO0123456789", AccountType.CURRENT, 100000.00, 100.00, false, customer);
        accountRepository.save(account1);

        Account account2 = new Account("NL02INHO0987654321", AccountType.CURRENT, 100000.00, 100.00, false, customer);
        accountRepository.save(account2);
    }


}

