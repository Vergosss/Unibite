package com.example.dto;

public class UserSignupDTO {
    private String username;
    private String email;
    private int telephone;
    private String password;

    private enum userRole {
        Admin, Student
    };

    private userRole role;

    // getters
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
}
