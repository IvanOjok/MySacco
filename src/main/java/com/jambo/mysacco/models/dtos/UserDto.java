package com.jambo.mysacco.models.dtos;


public class UserDto {
    private Long userId;
    String userName;
    String userPhone;
    String userStatus;
    String dob;
    String gender;
    Long saccoId;
    String saccoName;
    String userRole;
    boolean active;


    public UserDto(Long userId, String userName, String userPhone, String userStatus, String dob, String gender, Long saccoId, String saccoName, String userRole, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
        this.dob = dob;
        this.gender = gender;
        this.saccoId = saccoId;
        this.saccoName = saccoName;
        this.userRole = userRole;
        this.active = active;
    }

    public String getSaccoName() {
        return saccoName;
    }

    public void setSaccoName(String saccoName) {
        this.saccoName = saccoName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(Long saccoId) {
        this.saccoId = saccoId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
