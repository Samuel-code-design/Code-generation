package io.swagger.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.cucumber.java.en_old.Ac;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewTransaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")

@MappedSuperclass
public class NewTransaction   {
  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("performingUser")
  private Long performingUser = null;

  public NewTransaction() {
  }

  public NewTransaction(Double amount, LocalDateTime timestamp, String accountFrom, String accountTo, Long performingUser) {
    this.amount = amount;
    this.timestamp = timestamp;
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.performingUser = performingUser;
  }

  public NewTransaction amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "100", required = true, description = "")
      @NotNull

    @Valid
    public Double getAmount() {
      return amount;
  }

  public void setAmount(Double amount) {
    if (amount < 0.01)
      throw new IllegalArgumentException("Amount cannot be negative.");
    this.amount = amount;
  }

  public NewTransaction timestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   **/
  @Schema(example = "2017-07-21T17:32:28Z", required = true, description = "")
      @NotNull

    @Valid
    public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public NewTransaction accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * iban from where the money is taken
   * @return accountFrom
   **/
  @Schema(example = "NL01INHO0123456789", required = true, description = "iban from where the money is taken")
      @NotNull

    public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    if(accountFrom.equals("NL01INHO0000000001"))
      throw new IllegalArgumentException("Transactions to or from are prohibited.");
    if(accountFrom == accountTo)
      throw new IllegalArgumentException("Transactions cannot go to the same account.");
    this.accountFrom = accountFrom;
  }

  public NewTransaction accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * iban from where the money is going
   * @return accountTo
   **/
  @Schema(example = "NL02INHO0123456789", required = true, description = "iban from where the money is going")
      @NotNull

    public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    if(accountTo.equals("NL01INHO0000000001"))
      throw new IllegalArgumentException("Transactions to or from are prohibited.");
    this.accountTo = accountTo;
  }

  public NewTransaction performingUser(Long performingUser) {
    this.performingUser = performingUser;
    return this;
  }

  /**
   * user id from the person who did the transaction (either customer or employee)
   * @return performingUser
   **/
  @Schema(example = "1", required = true, description = "user id from the person who did the transaction (either customer or employee)")
      @NotNull

    public Long getPerformingUser() {
    return performingUser;
  }

  public void setPerformingUser(Long performingUser) {
    this.performingUser = performingUser;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewTransaction newTransaction = (NewTransaction) o;
    return Objects.equals(this.amount, newTransaction.amount) &&
        Objects.equals(this.timestamp, newTransaction.timestamp) &&
        Objects.equals(this.accountFrom, newTransaction.accountFrom) &&
        Objects.equals(this.accountTo, newTransaction.accountTo) &&
        Objects.equals(this.performingUser, newTransaction.performingUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, timestamp, accountFrom, accountTo, performingUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewTransaction {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    performingUser: ").append(toIndentedString(performingUser)).append("\n");
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
