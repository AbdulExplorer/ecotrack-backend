package com.ecotrack.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecotrack.backend.repository.UserRepository;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody Map<String, String> body) {
        userRepository.saveUser(
            body.get("name"),
            body.get("email"),
            body.get("state"),
            body.get("city"),
            body.get("password")
        );
        return "User Registered!";
    }
    
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        return userRepository.login(
            body.get("email"),
            body.get("password")
        );
    }
    
}
