package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.UserAlreadyExistsException;
import com.example.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    public AuthController(AuthenticationManager authenticationManager, AuthService authservice) {
        this.authenticationManager = authenticationManager;
        this.authService = authservice;
    }

    public record LoginRequest(String username, String password) {

    }//

    public record SignupRequest(String username, String password, String email, String telephone, String role) {
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                    .unauthenticated(loginRequest.username(), loginRequest.password());
            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
            //
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            //
            return ResponseEntity.ok("Welcome!");
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }

    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        try {
            authService.signup(signupRequest);
            return ResponseEntity.ok("Registration Complete!");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email unavailable");
        }
    }

}