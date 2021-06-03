package io.swagger.configuration;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.service.AuthenticationService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    private AuthenticationService service;

    public ApplicationRunner(AuthenticationService service) {
        this.service =service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        User u = new User("JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678",
                roles, false, 1000L, 1000L);
        service.signup(u);

        User bank = new User("bank", "hehehe", "bank", "bank", "bak@gmail.com", "06 12345678",
                roles, false, 1000L, 1000L);
        service.signup(bank);

        Account account = new Account("NL01INHO0000000001", AccountType.CURRENT, 100000.00, 100.00, false, bank);
    }


}

