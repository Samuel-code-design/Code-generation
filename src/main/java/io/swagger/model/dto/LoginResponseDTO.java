package io.swagger.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponseDTO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}