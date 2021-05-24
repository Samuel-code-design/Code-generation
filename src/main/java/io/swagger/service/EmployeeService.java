package io.swagger.service;

import io.swagger.api.NewUserApi;
import io.swagger.api.UsersApi;
import io.swagger.model.NewUser;
import io.swagger.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    UsersApi repository;
    NewUserApi newUserApiRepository;


    public EmployeeService(UsersApi repository,NewUserApi newUserApi ) {
        this.repository = repository;
        this.newUserApiRepository = newUserApi;
    }
    public void createUser(NewUser u){
        newUserApiRepository.createUser(u);
    }

    public void lockUserByEmail(String email) {

    }

    public void lockUserById( Integer id) {

    }

    public void updateUsers( List<User> body) {
        repository.updateUsers(body);
    }

    public List<User> getUsers(String searchstring) {
        return repository.findAll();
    }

    public ResponseEntity<User> userByEmail(String email) {
        return repository.userByEmail(email);
    }

    public ResponseEntity<List<User>> userById(Long id) {
        return repository.userById(id);
    }

}
