package io.swagger.api;

import io.swagger.model.NewUser;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@RestController
public class UsersApiController{

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private final EmployeeService service;


    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, EmployeeService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "withdraw", required=true, schema=@Schema()) @Valid @RequestBody NewUser body) {
        service.createUser(body);
        return (ResponseEntity<Void>) ResponseEntity.status(HttpStatus.OK);
    }

    public ResponseEntity<Void> lockUserByEmail(@Parameter(in = ParameterIn.PATH, description = "the email", required=true, schema=@Schema()) @PathVariable("email") String email) {
        service.lockUserByEmail(email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> lockUserById(@Parameter(in = ParameterIn.PATH, description = "the userID", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        service.lockUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody List<User> body) {
        service.updateUsers(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Search a user by a string" ,schema=@Schema()) @Valid @RequestParam(value = "searchstring", required = false) String searchstring) throws IOException {
        ResponseEntity<List<User>> users = service.getUsers(searchstring);
        return ResponseEntity.status(HttpStatus.OK).body(users.getBody());
    }

    public ResponseEntity<User> userByEmail(@Parameter(in = ParameterIn.PATH, description = "the userEmail", required=true, schema=@Schema()) @PathVariable("email") String email) throws IOException {
        ResponseEntity<User> user = service.userByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }

    public ResponseEntity<User> userById(@Parameter(in = ParameterIn.PATH, description = "the userID", required=true, schema=@Schema()) @PathVariable("id") Integer id) throws IOException {
        ResponseEntity<User>  user = service.userById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }

}
