package io.swagger.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Schema(example = "JD0001", required = true, description = "")
    @NotNull
    public String getUsername() {
        if (username.trim().isEmpty() || username == null){
            throw new IllegalArgumentException("Username should not be empty");
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Schema(example = "Wachtwoord1#", required = true, description = "")
    @NotNull
    public String getPassword() {
        if (password.trim().isEmpty() || password == null){
            throw new IllegalArgumentException("Password should not be empty");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
