package com.ttswebser.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String nim;
    private String nama;
    private String email;
    private String password;
    private String role;
    
    // constructors, getters, setters
    public User() {
        // Default constructor
    }

    public User(String nim, String nama, String email, String password, String role) {
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}