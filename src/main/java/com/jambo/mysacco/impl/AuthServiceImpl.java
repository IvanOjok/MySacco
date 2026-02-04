package com.jambo.mysacco.impl;

import com.jambo.mysacco.models.dtos.UserDto;
import com.jambo.mysacco.models.entities.AuditLog;
import com.jambo.mysacco.models.util.LoginRequest;
import com.jambo.mysacco.models.util.LoginResponse;
import com.jambo.mysacco.models.entities.Sacco;
import com.jambo.mysacco.models.entities.User;
import com.jambo.mysacco.repository.SaccoRepository;
import com.jambo.mysacco.repository.AuthRepository;
import com.jambo.mysacco.service.AccountService;
import com.jambo.mysacco.service.AuditService;
import com.jambo.mysacco.service.AuthService;
import com.jambo.mysacco.utils.JWTService;
import com.jambo.mysacco.utils.RequestContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final SaccoRepository saccoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AccountService accountService;
    private final AuditService auditService;


    public AuthServiceImpl(AuthRepository authRepository, SaccoRepository saccoRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AccountService accountService, AuditService auditService) {
        this.authRepository = authRepository;
        this.saccoRepository = saccoRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.accountService = accountService;
        this.auditService = auditService;
    }

    @Override
    public User createUser(User request) {
        if (authRepository.existsByUserPhone(request.getUserPhone())) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        Sacco sacco = saccoRepository.findSaccoById(request.getSaccoId())
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

        User createdUser = authRepository.save(user);
        //create the different accounts for every individual user
        accountService.createAccount(createdUser.getUserId());
        //audit
        AuditLog log = new AuditLog();
        log.setAction("User Account Creation");
        log.setEntity(String.valueOf(createdUser));
        log.setEntityId(createdUser.getUserId());
        log.setDescription("New Registration and Account Creation");
        log.setPerformedBy(createdUser.getUserId());
        log.setPerformedByRole(createdUser.getUserRole());
        log.setIpAddress(RequestContext.getClientIp());
        auditService.createLog(log);

        return createdUser;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = authRepository
                .findByUserPhone(request.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getUserPin(), user.getUserPin())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        Sacco sacco = saccoRepository.findSaccoById(user.getSaccoId()).orElseThrow(() -> new IllegalArgumentException("Sacco Doesn't Exist"));

        String token = jwtService.generateToken(user);
        UserDto userResponse = new UserDto(user.getUserId(),
                user.getUserName(),
                user.getUserPhone(),
                user.getUserStatus(),
                user.getDob(),
                user.getGender(),
                user.getSaccoId(),
                sacco.getName(),
                user.getUserRole(),
                user.isActive());

        //audit
        AuditLog log = new AuditLog();
        log.setAction("Login");
        log.setEntity(String.valueOf(userResponse));
        log.setEntityId(user.getUserId());
        log.setDescription("User logging into the app");
        log.setPerformedBy(user.getUserId());
        log.setPerformedByRole(user.getUserRole());
        log.setIpAddress(RequestContext.getClientIp());
        auditService.createLog(log);

        return new LoginResponse(
                token,
                userResponse
        );
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

        //audit
        AuditLog log = new AuditLog();
        log.setAction("Update");
        log.setEntity(String.valueOf(user));
        log.setEntityId(user.getUserId());
        log.setDescription("Updating user data");
        log.setPerformedBy(user.getUserId());
        log.setPerformedByRole(user.getUserRole());
        log.setIpAddress(RequestContext.getClientIp());
        auditService.createLog(log);

        return authRepository.save(user);
    }

    @Override
    public String deleteUser(Long userId) {
        authRepository.deleteById(userId);

        //audit
        AuditLog log = new AuditLog();
        log.setAction("Login");
        log.setEntity(String.valueOf(userId));
        log.setEntityId(userId);
        log.setDescription("User account deletion");
        log.setPerformedBy(userId);
        log.setPerformedByRole("User");
        log.setIpAddress(RequestContext.getClientIp());
        auditService.createLog(log);

        return "User Successfully Deleted";
    }
}
