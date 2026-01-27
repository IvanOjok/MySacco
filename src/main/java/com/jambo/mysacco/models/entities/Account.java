package com.jambo.mysacco.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    Long userId;
    Long saccoId;
    float balance;

    public Account() {

    }

    public Account(int id, Long userId, Long saccoId, float balance) {
        this.id = id;
        this.userId = userId;
        this.saccoId = saccoId;
        this.balance = balance;
    }

    public Long getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(Long saccoId) {
        this.saccoId = saccoId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
