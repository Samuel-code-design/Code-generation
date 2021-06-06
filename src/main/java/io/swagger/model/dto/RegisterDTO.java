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
        this.username = username;
    }

    @Schema(example = "Wachtwoord1#", required = true, description = "")
        @NotNull

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Schema(example = "John", required = true, description = "")
        @NotNull

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Schema(example = "Doe", required = true, description = "")
        @NotNull

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Schema(example = "JohnDoe@gmail.com", required = true, description = "")
        @NotNull

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(example = "06 12345678", required = true, description = "")
        @NotNull

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
