package com.ttswebser.api_gateway.dto;

public class KartuStudiDTO {
    private String id;
    private String nim;
    private Long mataKuliahId;
    private String semester;
    private String tahunAjaran;

    // getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public Long getMataKuliahId() {
        return mataKuliahId;
    }
    public void setMataKuliahId(Long mataKuliahId) {
        this.mataKuliahId = mataKuliahId;
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