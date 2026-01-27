package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.util.LoginRequest;
import com.jambo.mysacco.models.util.LoginResponse;
import com.jambo.mysacco.models.entities.Sacco;
import com.jambo.mysacco.models.entities.User;
import com.jambo.mysacco.repository.SaccoRepository;
import com.jambo.mysacco.repository.AuthRepository;
import com.jambo.mysacco.service.AuthService;
import com.jambo.mysacco.utils.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final SaccoRepository saccoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public AuthServiceImpl(AuthRepository authRepository, SaccoRepository saccoRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.authRepository = authRepository;
        this.saccoRepository = saccoRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User createUser(User request) {
        if (authRepository.existsByUserPhone(request.getUserPhone())) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        Sacco sacco = saccoRepository.findById(request.getSaccoId())
                .orElseThrow(() -> new IllegalArgumentException("Sacco not found"));

        String hashedPin = passwordEncoder.encode(request.getUserPin());

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserPhone(request.getUserPhone());
        user.setUserStatus(request.getUserStatus());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setSaccoId(sacco.getId());
        user.setUserPin(hashedPin);
        user.setUserRole(request.getUserRole());
        user.setActive(true);

        return authRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = authRepository
                .findByUserPhone(request.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getUserPin(), user.getUserPin())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user
        );
    }

    @Override
    public User getUserById(Long userId) {
        return authRepository.findById(userId).get();
    }

    @Override
    public User updateUser(User request) {

        String hashedPin = passwordEncoder.encode(request.getUserPin());

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserPhone(request.getUserPhone());
        user.setUserStatus(request.getUserStatus());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setSaccoId(request.getSaccoId());
        user.setUserPin(hashedPin);
        user.setUserRole(request.getUserRole());
        user.setActive(request.isActive());

        return authRepository.save(user);
    }

    @Override
    public String deleteUser(Long userId) {
        authRepository.deleteById(userId);
        return "User Successfully Deleted";
    }
}
