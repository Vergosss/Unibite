package com.example.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.entity.User;
import com.example.exception.UserAlreadyExistsException;
import com.example.controller.AuthController.SignupRequest;
import com.example.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequest signupRequest) {
        if (repository.existsByUsername(signupRequest.username()) || repository.existsByEmail(signupRequest.email())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        // record/dto to User object
        User user = new User();
        user.setUsername(signupRequest.username());
        user.setEmail(signupRequest.email());
        String password = passwordEncoder.encode(signupRequest.password());
        user.setPassword(password);
        user.setRole(signupRequest.role());
        user.setTelephone(signupRequest.telephone());
        user.setPoints(5);
        repository.save(user);
    }
}
