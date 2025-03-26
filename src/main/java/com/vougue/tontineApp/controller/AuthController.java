package com.vougue.tontineApp.controller;

import com.vougue.tontineApp.model.dto.RegisterDto;
import com.vougue.tontineApp.model.dto.User;
import com.vougue.tontineApp.repository.UserRepository;
import com.vougue.tontineApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    // http://localhost:8080/api/auth/register

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setRoles("USER");


        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("/registeralt")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user.getUsername(), user.getPassword()));
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register2")
    public String registerUser(@ModelAttribute User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Nom d'utilisateur déjà existant");
        }
        user.setRoles("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }



    // http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (authService.authenticate(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok("Successful authentication");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failure");
    }
}
