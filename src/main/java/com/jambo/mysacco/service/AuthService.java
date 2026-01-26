package com.jambo.mysacco.service;


import com.jambo.mysacco.models.LoginRequest;
import com.jambo.mysacco.models.LoginResponse;
import com.jambo.mysacco.models.User;
import org.springframework.stereotype.Service;

public interface AuthService {
    public User createUser(User user);

    public LoginResponse login(LoginRequest request);

    public User getUserById(Long userId);

    public User updateUser(User user);

    public String deleteUser(Long userId);
}

