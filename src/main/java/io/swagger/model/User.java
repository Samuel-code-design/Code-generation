package io.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    private Boolean locked = false;
    private Long dayLimit;
    private Long transactionLimit;


    public User(Long id, String username, String password, String firstName, String lastName, String email, String phone, List<Role> roles, Boolean locked, Long dayLimit, Long transactionLimit) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.locked = locked;
        this.dayLimit = dayLimit;
        this.transactionLimit = transactionLimit;
    }


    public User(String username, String password, String firstName, String lastName, String email, String phone, List<Role> roles, Boolean locked, Long dayLimit, Long transactionLimit) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.locked = locked;
        this.dayLimit = dayLimit;
        this.transactionLimit = transactionLimit;
    }

    public User(){
    }

    @Schema(example = "1", description = "")
    @NotNull
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        if (id != null && id < 0) throw new IllegalArgumentException("Cannot be lower than zero");
        this.id = id;
    }

    @Schema(example = "JD0001", description = "")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if (username.trim().isEmpty() || username == null){
           // throw new IllegalArgumentException("Username can not be empty");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username can not be empty");
        }
        this.username = username;
    }

    @Schema(example = "Wachtwoord1#", description = "")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Schema(example = "John", description = "")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Schema(example = "Doe", description = "")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Schema(example = "JohnDoe@gmail.com", description = "")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (!email.contains("@")) throw new IllegalArgumentException("Email must contain the at sign");
        this.email = email;
    }

    @Schema(example = "06 12345678", description = "")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Schema(example = "false", description = "")
    public Boolean getLocked() {
        return locked;
    }
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Schema(example = "1000", description = "")

    public Long getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Long dayLimit) {
        if (dayLimit < 0) throw new IllegalArgumentException("Cannot be lower than zero");
        this.dayLimit = dayLimit;
    }

    @Schema(example = "1000", description = "")

    public Long getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(Long transactionLimit) {
        if (transactionLimit < 0) throw new IllegalArgumentException("Cannot be lower than zero");
        this.transactionLimit = transactionLimit;
    }
}
