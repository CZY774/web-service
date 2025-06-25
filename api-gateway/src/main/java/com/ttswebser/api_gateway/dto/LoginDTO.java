package com.ttswebser.api_gateway.dto;

public class LoginDTO {
    private String nim;
    private String password;
    
    // getters and setters
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}