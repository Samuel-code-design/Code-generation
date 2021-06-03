package io.swagger.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.AccountType;

public class AccountResponseDTO {

    @JsonProperty("iban")
    private String iban;

    @JsonProperty("type")
    private AccountType type;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("absoluteLimit")
    private Double absoluteLimit;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("owner userId")
    private Long userId;

    @JsonProperty("owner name")
    private String userName;

    public AccountResponseDTO(String iban, AccountType type, Double balance, Double absoluteLimit, Boolean locked, Long userId, String userName) {
        this.iban = iban;
        this.type = type;
        this.balance = balance;
        this.absoluteLimit = absoluteLimit;
        this.locked = locked;
        this.userId = userId;
        this.userName = userName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(Double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
