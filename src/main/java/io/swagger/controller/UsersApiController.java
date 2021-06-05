package io.swagger.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.UsersApi;
import io.swagger.model.Role;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.model.User;
import io.swagger.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final EmployeeService service;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, EmployeeService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "New user body", required=true, schema=@Schema()) @Valid @RequestBody CreateUserDTO body) {
        User u = service.createUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    public ResponseEntity<User> lockUserById(@Parameter(in = ParameterIn.PATH, description = "The userID", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        User u = service.lockUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "The new user information", required=true, schema=@Schema()) @Valid @RequestBody User body) {
//        User u = service.updateUser(body);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        User u = new User(1l, "JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
                , 1000L, 1000L);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Search a user by a string" ,schema=@Schema()) @Valid @RequestParam(value = "searchstring", required = false) String searchstring) throws IOException {
        List<User> users = service.getUsers(searchstring);
        if (users.toArray().length != 0){
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(users);
    }

    public ResponseEntity<User> userById(@Parameter(in = ParameterIn.PATH, description = "the userID", required=true, schema=@Schema()) @PathVariable("id") Long id) throws IOException {
        User user = service.userById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}