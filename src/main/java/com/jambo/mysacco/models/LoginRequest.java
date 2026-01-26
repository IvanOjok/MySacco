package com.jambo.mysacco.models;


public class LoginRequest {
    private String userPhone;
    private String userPin;

    public LoginRequest(String userPhone, String userPin) {
        this.userPhone = userPhone;
        this.userPin = userPin;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

}

