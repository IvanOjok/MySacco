package com.jambo.mysacco.controllers;

import com.jambo.mysacco.models.LoginRequest;
import com.jambo.mysacco.models.LoginResponse;
import com.jambo.mysacco.models.Sacco;
import com.jambo.mysacco.models.User;
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
    public User addUser(@RequestBody User user) {
        return authService.createUser(user);
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
    public String deleteUser(@PathVariable int userId) {
        return authService.deleteUser(userId);
    }

}

