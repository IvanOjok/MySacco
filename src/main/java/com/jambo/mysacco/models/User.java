package com.jambo.mysacco.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    private int userId;
    String userName;
    String userPhone;
    String userStatus;
    String dob;
    String gender;
    String saccoId;
    String userPin;

    public User(int userId, String userName, String userPhone, String userStatus, String dob, String gender, String saccoId, String userPin) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
        this.dob = dob;
        this.gender = gender;
        this.saccoId = saccoId;
        this.userPin = userPin;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(String saccoId) {
        this.saccoId = saccoId;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

}
