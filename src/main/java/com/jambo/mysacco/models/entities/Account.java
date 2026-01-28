/***
 * this class identifies every individual member's account in the given sacco (group)
 * /** Balance is better calculated from the transactions table performed after transactions
 */

package com.jambo.mysacco.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    Long userId;
    Long saccoId;
    String type;
    float balance;

    public Account() {

    }

    public Account(int id, Long userId, Long saccoId, String type, float balance) {
        this.id = id;
        this.userId = userId;
        this.saccoId = saccoId;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
