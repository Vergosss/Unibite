package com.example.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity // signal spring that i dont want to use its default security rules i want to
                   // configure my rules for web security. to control how MY web security works
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http is a builder object,
        http.cors(Customizer.withDefaults()) // use the available configuration not default values. the available
                                             // configuration source is the bean corsConfigurationSource
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // if you are unauthenticated redirect to login
                        .permitAll());

        return http.build();// build the object.HttpSecurity is a builder
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // different origins we allow requests
                                                                                 // from
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // allowed methods
                                                                                                   // from cross origin
        configuration.setAllowedHeaders(Arrays.asList("*"));// allowed headers from cross origin
        configuration.setAllowCredentials(true);// allow cookie sessionid from cross origin
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return corsConfigurationSource;
    }

    //
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
