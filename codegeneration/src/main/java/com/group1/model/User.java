package com.group1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private long dayLimit;
    private long transactionLimit;

    public User(long id, String username, String password, long dayLimit, long transactionLimit) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dayLimit = dayLimit;
        this.transactionLimit = transactionLimit;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(long dayLimit) {
        this.dayLimit = dayLimit;
    }

    public long getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(long transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}
