package io.swagger.service;
import io.swagger.api.UsersApi;
import io.swagger.model.NewUser;
import io.swagger.model.User;
import io.swagger.repository.EmployeeNewUserRepository;
import io.swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository repository;
    EmployeeNewUserRepository newUserApiRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repository, EmployeeNewUserRepository newUserApi) {
        this.repository = repository;
        this.newUserApiRepository = newUserApi;
    }

    public void createUser(NewUser u){
//        newUserApiRepository.createUser(u);
    }

    public void lockUserByEmail(String email) {
//        repository.lockUserByEmail(email);
    }

    public void lockUserById( Integer id) {
//        repository.lockUserById(id);
    }

    public void updateUsers( List<User> body) {
//        repository.updateUsers(body);
    }

    public ResponseEntity<List<User>> getUsers(String searchString) {
        if (searchString != null){
//            return repository.getAllByEmailContainingOrFirstNameContainingOrLastNameContaining(searchString);
        }
//        return repository.findAllUsers();
        return null;
    }

    public ResponseEntity<User> userByEmail(String email) {
//        return  repository.findUsersByEmail(email);
        return null;

    }

    public ResponseEntity<User> userById(Integer id) {
//        return repository.getUserById(id);
        return null;

    }

}
