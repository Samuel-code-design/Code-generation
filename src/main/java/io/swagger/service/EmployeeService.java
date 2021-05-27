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
        newUserApiRepository.save(u);
    }

    public void lockUserByEmail(String email) {
        repository.lockUserByEmail(email);
    }

    public void lockUserById( Integer id) {
        repository.lockUserById(id);
    }

    public void updateUser(User body) {
        repository.save(body);
    }

    public List<User> getUsers(String searchString) {
        if (searchString != null){
            return repository.findAllByEmailContaining(searchString);
        }
        return repository.findAllUsers();
    }

    public User userByEmail(String email) {
        return  repository.findUserByEmail(email);
    }

    public User userById(Integer id) {
        return repository.findUserById(id);
    }

}
