package io.swagger.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.jwt")
@Component
public class JwtConfig {

    private String secretKey;
    private Integer tokenExpiration;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Integer tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }


}
