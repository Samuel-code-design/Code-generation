package io.swagger.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class RegisterDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public RegisterDTO(String username, String password, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Schema(example = "JD0001", description = "")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.trim().isEmpty() || username == null){
            throw new IllegalArgumentException("Username should not be empty");
        }
        this.username = username;
    }

    @Schema(example = "Wachtwoord1#", required = true, description = "")
        @NotNull

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.trim().isEmpty() || password == null){
            throw new IllegalArgumentException("Password should not be empty");
        }
        this.password = password;
    }


    @Schema(example = "John", required = true, description = "")
        @NotNull

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.trim().isEmpty() || firstName == null){
            throw new IllegalArgumentException("Firstname should not be empty");
        }
        this.firstName = firstName;
    }

    @Schema(example = "Doe", required = true, description = "")
        @NotNull

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.trim().isEmpty() || lastName == null){
            throw new IllegalArgumentException("Lastname should not be empty");
        }
        this.lastName = lastName;
    }

    @Schema(example = "JohnDoe@gmail.com", required = true, description = "")
        @NotNull

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.trim().isEmpty() || email == null){
            throw new IllegalArgumentException("Email should not be empty");
        }
        this.email = email;
    }

    @Schema(example = "06 12345678", required = true, description = "")
        @NotNull

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.trim().isEmpty() || phone == null){
            throw new IllegalArgumentException("Phone should not be empty");
        }
        this.phone = phone;
    }
}
