package com.post.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

import java.util.Set;

public class SignupRequestDto {
    @NotBlank(message = "The username is required.")
    private String username;
    @NotEmpty(message = "The email is required.")
    @Email(message = "The email is not a valid email.")
    private String email;
    @NotBlank(message = "The password is required.")
    private String password;
   private  Set<String> role;

    public SignupRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
