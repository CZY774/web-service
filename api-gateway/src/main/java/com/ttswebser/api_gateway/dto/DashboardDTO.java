package com.ttswebser.api_gateway.dto;

import java.util.List;

public class DashboardDTO {
    private UserResponseDTO user;
    private List<KartuStudiDTO> kartuStudi;
    private Integer totalMataKuliah;
    private Double currentIPK;
    private String currentSemester;

    // getters and setters
    public UserResponseDTO getUser() {
        return user;
    }
    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
    public List<KartuStudiDTO> getKartuStudi() {
        return kartuStudi;
    }
    public void setKartuStudi(List<KartuStudiDTO> kartuStudi) {
        this.kartuStudi = kartuStudi;
    }
    public Integer getTotalMataKuliah() {
        return totalMataKuliah;
    }
    public void setTotalMataKuliah(Integer totalMataKuliah) {
        this.totalMataKuliah = totalMataKuliah;
    }
    public Double getCurrentIPK() {
        return currentIPK;
    }
    public void setCurrentIPK(Double currentIPK) {
        this.currentIPK = currentIPK;
    }
    public String getCurrentSemester() {
        return currentSemester;
    }
    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }
    
}