package com.example.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override // i override the loadUserByUsername function of the UserDetailsService
              // interface
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new CustomUserDetails(foundUser);
    }
}
