package io.swagger.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Role;

import java.util.List;

public class CreateUserDTO {

    private String username;
    private String password;
    private String password2;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Role> roles;

    public List<Role> getRole() {
        return roles;
    }

    public void setRole(List<Role> role) {
        this.roles = role;
    }

    public CreateUserDTO(String username, String password, String password2, String firstName, String lastName, String email, String phone, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}