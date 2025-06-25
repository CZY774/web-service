package com.ttswebser.api_gateway.dto;

public class NilaiDTO {
    private String id;
    private String kartuStudiId;
    private Double nilaiAngka;
    private String nilaiHuruf;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKartuStudiId() {
        return kartuStudiId;
    }
    public void setKartuStudiId(String kartuStudiId) {
        this.kartuStudiId = kartuStudiId;
    }
    public Double getNilaiAngka() {
        return nilaiAngka;
    }
    public void setNilaiAngka(Double nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }
    public String getNilaiHuruf() {
        return nilaiHuruf;
    }
    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }
    
}