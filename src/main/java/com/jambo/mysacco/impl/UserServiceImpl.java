package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.User;
import com.jambo.mysacco.repository.UserRepository;
import com.jambo.mysacco.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "User Created Successfully";
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "User Updated Successfully";
    }

    @Override
    public String deleteUser(int userId) {
        userRepository.deleteById(userId);
        return "User Successfully Deleted";
    }
}