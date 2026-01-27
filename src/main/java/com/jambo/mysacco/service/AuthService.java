package com.jambo.mysacco.service;


import com.jambo.mysacco.models.util.LoginRequest;
import com.jambo.mysacco.models.util.LoginResponse;
import com.jambo.mysacco.models.entities.User;

public interface AuthService {
    public User createUser(User user);

    public LoginResponse login(LoginRequest request);

    public User getUserById(Long userId);

    public User updateUser(User user);

    public String deleteUser(Long userId);
}

