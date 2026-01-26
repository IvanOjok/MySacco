package com.jambo.mysacco.service;

import com.jambo.mysacco.models.User;

import java.util.List;

public interface UserService {
    public String createUser(User user);
    public User getUser(int userId);
    public List<User> getAllUsers();
    public String updateUser(User user);
    public String deleteUser(int userId);
}
