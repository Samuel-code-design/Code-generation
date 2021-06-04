package io.swagger.service;

import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.repository.UserRepository;
import io.swagger.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    private int MINIMUM_PASSWORD_LENGTH = 6;

    public String login(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return "Bearer " + jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username/password supplied");
        }

    }

    public String signup(User user) {
        //Checks if username already exists
        if (!userRepository.existsByUsername(user.getUsername())) {
            //Checks if email already exists
            if (!userRepository.existsByEmail(user.getEmail())){
                //Checks if password is right lenght
//                if (user.getPassword().length() > MINIMUM_PASSWORD_LENGTH){
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setDayLimit(1000L);
                    user.setTransactionLimit(100L);
                    user.setRoles(Arrays.asList(Role.ROLE_CUSTOMER));
//                    user.setRoles(Arrays.asList(Role.ROLE_EMPLOYEE));
                userRepository.save(user);
                    AccountCreateDTO accountCreateDTO = new AccountCreateDTO(AccountType.SAVING,  0.00, false, user.getId());
                    accountService.addAccount(accountCreateDTO);
                    return "Account created, you can login now.";
//                } else {
//                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Password length to short, minimum length 7.");
//                }
            } else {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already in use, please try again nd choose a different one");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username is already in use, please try again and choose a different one");
        }
    }
}
