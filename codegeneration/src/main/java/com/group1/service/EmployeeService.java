package com.group1.service;


import com.group1.model.User;
import com.group1.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void createUser(User u){
        repository.save(u);
    }

    public List<User> getAllUsers(){
       return repository.findAll();
    }

    public User getUserById(long id){
        return repository.getOne(id);
    }

    public void updateUser(User u){
        //werkt dit bij jullie?
        repository.save(u);
    }
    public void deleteUserById(long id)
    {
        repository.deleteById(id);
    }
}
