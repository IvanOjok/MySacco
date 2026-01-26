package com.jambo.mysacco.service;


import com.jambo.mysacco.models.LoginRequest;
import com.jambo.mysacco.models.LoginResponse;
import com.jambo.mysacco.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public User createUser(User user);

    public LoginResponse login(LoginRequest request);

    public User getUserById(int userId);

    public User updateUser(User user);

    public String deleteUser(int userId);
}

