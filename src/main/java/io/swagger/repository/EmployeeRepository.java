package io.swagger.repository;

import io.swagger.model.Account;
import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<User, Integer> {



}