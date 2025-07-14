package com.siam.controller;

import com.siam.model.User;
import com.siam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is a user endpoint";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an admin endpoint";
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User created successfully!";
    }
}
