package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.UserSignupDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User validate(UserSignupDTO user) {
        // create User that will be inserted into DB
        User newUser = new User();
        // Extract data from DTO
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String role = user.getRole();
        int telephone = user.getTelephone();
        // Append to User
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setTelephone(telephone);
        newUser.setPoints(5); // each user starts with 5 points
        return repository.save(newUser);
    }
}
