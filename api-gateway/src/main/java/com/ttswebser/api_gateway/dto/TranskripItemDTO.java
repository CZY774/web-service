package com.ttswebser.api_gateway.dto;

public class TranskripItemDTO {
    private String kodeMataKuliah;
    private String namaMataKuliah;
    private Integer sks;
    private String nilaiHuruf;
    private Double nilaiAngka;
    private String semester;
    private String tahunAjaran;

    // getters and setters
    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }
    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }
    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }
    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }
    public Integer getSks() {
        return sks;
    }
    public void setSks(Integer sks) {
        this.sks = sks;
    }
    public String getNilaiHuruf() {
        return nilaiHuruf;
    }
    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }
    public Double getNilaiAngka() {
        return nilaiAngka;
    }
    public void setNilaiAngka(Double nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getTahunAjaran() {
        return tahunAjaran;
    }
    public void setTahunAjaran(String tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }
    
}