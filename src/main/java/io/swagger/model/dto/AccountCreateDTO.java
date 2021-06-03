package io.swagger.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountCreateDTO {

    @JsonProperty("type")
    private AccountType type;

    @JsonProperty("absoluteLimit")
    private Double absoluteLimit;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("userId")
    private Long userId;

    public AccountCreateDTO(AccountType type, Double absoluteLimit, Boolean locked, Long userId) {
        this.type = type;
        this.absoluteLimit = absoluteLimit;
        this.locked = locked;
        this.userId = userId;
    }

    @Schema(example = "CURRENT", required = true, description = "")
        @NotNull
    public AccountType getType() { return type; }

    public void setType(AccountType type) { this.type = type; }

    @Schema(example = "500", description = "")
    public Double getAbsoluteLimit() { return absoluteLimit; }

    public void setAbsoluteLimit(Double absoluteLimit) { this.absoluteLimit = absoluteLimit; }

    @Schema(example = "false", required = true, description = "")
        @NotNull
    public Boolean getLocked() { return locked; }

    public void setLocked(Boolean locked) { this.locked = locked; }

    @Schema(example = "1", required = true, description = "")
        @NotNull
    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
