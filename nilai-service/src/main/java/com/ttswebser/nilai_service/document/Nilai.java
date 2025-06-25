package com.ttswebser.nilai_service.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nilai")
public class Nilai {
    @Id
    private String id;
    private String kartuStudiId;
    private Double nilaiAngka;
    private String nilaiHuruf;
    
    // constructors, getters, setters
    public Nilai () {
        // Default constructor
    }

    public Nilai(String kartuStudiId, Double nilaiAngka, String nilaiHuruf) {
        this.kartuStudiId = kartuStudiId;
        this.nilaiAngka = nilaiAngka;
        this.nilaiHuruf = nilaiHuruf;
    }

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