package com.example.entity;


import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id // it shows that this variable is the primary key
    private String id;
    private String username;
    private String password;

    private enum userRole {
        Admin, Student
    };

    private userRole role;
    private int telephone;
    private String email;
    private int points;

    // Getter & Setters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role.toString();
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = userRole.valueOf(role);
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
