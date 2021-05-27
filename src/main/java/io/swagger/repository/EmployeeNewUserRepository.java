package io.swagger.repository;

import io.swagger.model.NewUser;
import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeNewUserRepository extends CrudRepository<NewUser, Integer> {

}