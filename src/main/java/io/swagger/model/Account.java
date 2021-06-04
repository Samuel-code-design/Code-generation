package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")

@Entity
public class Account   {
  @JsonProperty("iban")
  @Id  private String iban = null;


  @JsonProperty("type")
  private AccountType type = null;

  @JsonProperty("balance")
  private Double balance = null;

  @JsonProperty("absoluteLimit")
  private Double absoluteLimit = null;

  @JsonProperty("locked")
  private Boolean locked = null;

  @JsonProperty("user")
  @ManyToOne
  private User user = null;

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  public Account() {
  }

  public Account(String iban, AccountType type, Double balance, Double absoluteLimit, Boolean locked, User user) {
    this.iban = iban;
    this.type = type;
    this.balance = balance;
    this.absoluteLimit = absoluteLimit;
    this.locked = locked;
    this.user = user;
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
    if (iban == null) throw new IllegalArgumentException("Iban cannot be empty");
    if(!iban.matches("NL\\d{2}INHO0\\d{9}")) throw new IllegalArgumentException("Iban is not in correct format");
    this.iban = iban;
  }

  public Account type(AccountType type) {
    this.type = type;
    return this;
  }

  /**
   * Account type
   * @return type
   **/
  @Schema(required = true, description = "Account type")
      @NotNull

    public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    if(type == null) throw new IllegalArgumentException("Account type cannot be empty");
    this.type = type;
  }

  public Account balance(Double balance) {
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
    public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Account absoluteLimit(Double absoluteLimit) {
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
    public Double getAbsoluteLimit() {
    return absoluteLimit;
  }

  public void setAbsoluteLimit(Double absoluteLimit) {
    if (absoluteLimit == null) throw new IllegalArgumentException("Absolutelimit cannot be empty");
    this.absoluteLimit = absoluteLimit;
  }

  public Account locked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  /**
   * Get absoluteLimit
   * @return absoluteLimit
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Account user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @Schema(required = true, description = "")
      @NotNull

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    if (user == null) throw new IllegalArgumentException("User cannot be empty");
    this.user = user;
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
        Objects.equals(this.locked, account.locked) &&
        Objects.equals(this.user, account.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, type, balance, absoluteLimit, locked, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
    sb.append("    locked: ").append(toIndentedString(locked)).append("\n");
    sb.append("    userId: ").append(toIndentedString(user)).append("\n");
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
