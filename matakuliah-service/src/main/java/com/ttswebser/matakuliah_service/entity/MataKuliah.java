package com.ttswebser.matakuliah_service.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "matakuliah")
public class MataKuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kode;
    private String nama;
    private Integer sks;
    private String dosen;
    private LocalTime jadwalMulai;
    private LocalTime jadwalSelesai;
    private String ruangan;
    
    // constructors, getters, setters
    public MataKuliah() {
        // Default constructor
    }

    public MataKuliah(Long id, String kode, String nama, Integer sks, String dosen, LocalTime jadwalMulai,
            LocalTime jadwalSelesai, String ruangan) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.dosen = dosen;
        this.jadwalMulai = jadwalMulai;
        this.jadwalSelesai = jadwalSelesai;
        this.ruangan = ruangan;
    }

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

    public LocalTime getJadwalMulai() {
        return jadwalMulai;
    }

    public void setJadwalMulai(LocalTime jadwalMulai) {
        this.jadwalMulai = jadwalMulai;
    }

    public LocalTime getJadwalSelesai() {
        return jadwalSelesai;
    }

    public void setJadwalSelesai(LocalTime jadwalSelesai) {
        this.jadwalSelesai = jadwalSelesai;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
    
}