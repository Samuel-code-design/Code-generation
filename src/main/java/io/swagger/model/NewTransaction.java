package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewTransaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")


public class NewTransaction   {
  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("performingUser")
  private Integer performingUser = null;

  public NewTransaction amount(BigDecimal amount) {
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
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public NewTransaction timestamp(OffsetDateTime timestamp) {
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
    public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
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
    this.accountTo = accountTo;
  }

  public NewTransaction performingUser(Integer performingUser) {
    this.performingUser = performingUser;
    return this;
  }

  /**
   * user id from the person who did the transaction (either customer or employee)
   * @return performingUser
   **/
  @Schema(example = "1", required = true, description = "user id from the person who did the transaction (either customer or employee)")
      @NotNull

    public Integer getPerformingUser() {
    return performingUser;
  }

  public void setPerformingUser(Integer performingUser) {
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