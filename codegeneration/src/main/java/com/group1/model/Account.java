package com.group1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private String iban; //the format: NLxxINHO0xxxxxxxxx
    private AccountType type;
    private double balance;
    private double absoluteLimit;

    @ManyToOne
    private User user;

    public Account(long id, String iban, AccountType type, double balance, double absoluteLimit, User user) {
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.balance = balance;
        this.absoluteLimit = absoluteLimit;
        this.user = user;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
