package com.vougue.tontineApp.controller;

import com.vougue.tontineApp.model.dto.User;
import com.vougue.tontineApp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (authService.authenticate(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok("Authentification réussie");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
    }
}
