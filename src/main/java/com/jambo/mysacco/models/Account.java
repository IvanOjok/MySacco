package com.jambo.mysacco.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
    @Id
    private int id;
    int userId;
    String balance;
    String saccoId;

    public Account() {

    }

    public Account(int userId, String balance, String saccoId) {
        this.userId = userId;
        this.balance = balance;
        this.saccoId = saccoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(String saccoId) {
        this.saccoId = saccoId;
    }
}
