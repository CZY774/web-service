package com.ttswebser.api_gateway.dto;

import java.util.List;

public class TranskripDTO {
    private String nim;
    private String nama;
    private List<TranskripItemDTO> items;
    private Double ipk;
    private Integer totalSks;
    private String generatedAt;

    // getters and setters
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
    public List<TranskripItemDTO> getItems() {
        return items;
    }
    public void setItems(List<TranskripItemDTO> items) {
        this.items = items;
    }
    public Double getIpk() {
        return ipk;
    }
    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }
    public Integer getTotalSks() {
        return totalSks;
    }
    public void setTotalSks(Integer totalSks) {
        this.totalSks = totalSks;
    }
    public String getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    
}