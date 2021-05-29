package io.swagger.service;

import io.swagger.model.dto.CreateUserDTO;
import io.swagger.model.User;
import io.swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void createUser(CreateUserDTO u){
        //TODO: const maken voor daylimit en trasnlimit
        //bestaan gegevens al
        //passwrod lang genoeg??

        User user = new User();
        user.setId(null);
        user.setLocked(false);
        user.setDayLimit(1000L);
        user.setEmail(u.getEmail());
        user.setUsername(u.getUsername());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setPassword(u.getPassword());
        user.setPhone(u.getPhone());
        user.setRoles(u.getRole());
        user.setTransactionLimit(1000L);

        repository.save(user);

        if (true) {

        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username is already in use, , please try again and choose a different one");
        }
    }

    public void lockUserByEmail(String email) {
        User u = repository.findByEmailEquals(email);
        u.setLocked(true);
        repository.save(u);
    }

    public void lockUserById( Long id) {
        User u = repository.findByIdEquals(id);
        u.setLocked(true);
        repository.save(u);
    }

    public void updateUser(User body) {
        //TODO: bestaat new username al
        repository.save(body);
    }

    public List<User> getUsers(String searchString) {
        if (searchString != null){
            return repository.findAllByEmailContaining(searchString);
        }
        return repository.findAll();
    }

    public User userByEmail(String email) {
        return  repository.findByEmailEquals(email);
    }

    public User userById(Long id) {
        return repository.findByIdEquals(id);
//        return repository.getOne(id);
    }

}
