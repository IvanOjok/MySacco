package com.jambo.mysacco.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
    @Id
    private int id;
    String userId;
    String balance;
    String saccoId;

    public Account() {

    }

    public Account(String userId, String balance, String saccoId, String role) {
        this.userId = userId;
        this.balance = balance;
        this.saccoId = saccoId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    String role;
}
