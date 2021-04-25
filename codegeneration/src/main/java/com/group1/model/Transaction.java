package com.group1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    private double amount;
    private LocalDateTime timestamp;

    @ManyToOne
    private Account accountFrom;
    @ManyToOne
    private Account accountTo;
    @ManyToOne
    private User preformingUser;

    public Transaction(double amount, LocalDateTime timestamp, Account accountFrom, Account accountTo, User preformingUser) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.preformingUser = preformingUser;
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public User getPreformingUser() {
        return preformingUser;
    }

    public void setPreformingUser(User preformingUser) {
        this.preformingUser = preformingUser;
    }
}
