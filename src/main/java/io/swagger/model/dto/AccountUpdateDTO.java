package io.swagger.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class AccountUpdateDTO {
    @JsonProperty("iban")
    private String iban;

    @JsonProperty("type")
    private AccountType type;

    @JsonProperty("absoluteLimit")
    private Double absoluteLimit;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("owner userId")
    private Long userId;

    public AccountUpdateDTO(String iban, AccountType type, Double absoluteLimit, Boolean locked, Long userId) {
        this.iban = iban;
        this.type = type;
        this.absoluteLimit = absoluteLimit;
        this.locked = locked;
        this.userId = userId;
    }

    @Schema(example = "NLxxINHO0xxxxxxxxx", required = true, description = "Fill in your iban of the account you want to update")
        @NotNull
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Schema(example = "CURRENT", description = "")
        @NotNull
    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Schema(example = "500", description = "")
        @NotNull
    public Double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(Double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    @Schema(example = "false", description = "")
        @NotNull
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Schema(example = "1", description = "")
        @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}


