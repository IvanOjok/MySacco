package com.jambo.mysacco.controllers;

import com.jambo.mysacco.models.dtos.UserDto;
import com.jambo.mysacco.models.util.LoginRequest;
import com.jambo.mysacco.models.util.LoginResponse;
import com.jambo.mysacco.models.entities.Sacco;
import com.jambo.mysacco.models.entities.User;
import com.jambo.mysacco.service.AuthService;
import com.jambo.mysacco.service.SaccoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    AuthService authService;
    SaccoService saccoService;

    public AuthController(AuthService authService, SaccoService saccoService) {
        this.authService = authService;
        this.saccoService = saccoService;
    }

    @PostMapping("sacco")
    public Sacco addSacco(@RequestBody Sacco sacco) {
        return saccoService.createSacco(sacco);
    }

    @PostMapping("user")
    public UserDto addUser(@RequestBody User user) {
        User userAcc = authService.createUser(user);
        Sacco sacco = saccoService.getSaccoById(user.getSaccoId());
        return new UserDto(userAcc.getUserId(),
                userAcc.getUserName(),
                userAcc.getUserPhone(),
                userAcc.getUserStatus(),
                userAcc.getDob(),
                userAcc.getGender(),
                userAcc.getSaccoId(),
                sacco.getName(),
                userAcc.getUserRole(),
                userAcc.isActive());
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("user")
    public User updateUser(@RequestBody User user) {
        return authService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return authService.deleteUser(userId);
    }

}

