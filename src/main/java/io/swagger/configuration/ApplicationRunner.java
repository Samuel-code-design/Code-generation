package io.swagger.configuration;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    private UserService service;

    public ApplicationRunner(UserService service) {
        this.service =service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_CUSTOMER);

        User u = new User("JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678",
                roles, false, true, 1000L, 1000L);
        service.signup(u);

    }
}