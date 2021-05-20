package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")


public class Account   {
  @JsonProperty("iban")
  private String iban = null;

  /**
   * Account type
   */
  public enum TypeEnum {
    SAVING("SAVING"),
    
    CURRENT("CURRENT");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  @JsonProperty("absoluteLimit")
  private BigDecimal absoluteLimit = null;

  @JsonProperty("userId")
  private Long userId = null;

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * Get iban
   * @return iban
   **/
  @Schema(example = "NL01INHO0123456789", required = true, description = "")
      @NotNull

    public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Account type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Account type
   * @return type
   **/
  @Schema(required = true, description = "Account type")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Account balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account absoluteLimit(BigDecimal absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
    return this;
  }

  /**
   * Get absoluteLimit
   * @return absoluteLimit
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public BigDecimal getAbsoluteLimit() {
    return absoluteLimit;
  }

  public void setAbsoluteLimit(BigDecimal absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
  }

  public Account userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.iban, account.iban) &&
        Objects.equals(this.type, account.type) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.absoluteLimit, account.absoluteLimit) &&
        Objects.equals(this.userId, account.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, type, balance, absoluteLimit, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
