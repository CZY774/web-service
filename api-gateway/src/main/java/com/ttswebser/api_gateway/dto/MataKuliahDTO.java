package com.ttswebser.api_gateway.dto;

public class MataKuliahDTO {
    private Long id;
    private String kode;
    private String nama;
    private Integer sks;
    private String dosen;
    private String jadwalMulai;
    private String jadwalSelesai;
    private String ruangan;
    
    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Integer getSks() {
        return sks;
    }
    public void setSks(Integer sks) {
        this.sks = sks;
    }
    public String getDosen() {
        return dosen;
    }
    public void setDosen(String dosen) {
        this.dosen = dosen;
    }
    public String getJadwalMulai() {
        return jadwalMulai;
    }
    public void setJadwalMulai(String jadwalMulai) {
        this.jadwalMulai = jadwalMulai;
    }
    public String getJadwalSelesai() {
        return jadwalSelesai;
    }
    public void setJadwalSelesai(String jadwalSelesai) {
        this.jadwalSelesai = jadwalSelesai;
    }
    public String getRuangan() {
        return ruangan;
    }
    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
    
}