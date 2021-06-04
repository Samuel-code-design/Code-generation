package io.swagger.configuration;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import io.swagger.service.AuthenticationService;
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

    public ApplicationRunner(UserRepository userRepository) {
        this.userRepository =userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        User u = new User("JD0001", passwordEncoder.encode("Wachtwoord1#"), "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678",
                roles, false, true, 1000L, 1000L);
        userRepository.save(u);

        User bank = new User("bank", passwordEncoder.encode("Wachtwoord!1"), "bank", "bank", "bak@gmail.com", "06 12345678",
                roles, false, true, 1000L, 1000L);
        userRepository.save(bank);

        Account account = new Account("NL01INHO0000000001", AccountType.CURRENT, 100000.00, 100.00, false, bank);
    }


}

