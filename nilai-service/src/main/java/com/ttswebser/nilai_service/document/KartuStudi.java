package com.ttswebser.nilai_service.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kartu_studi")
public class KartuStudi {
    @Id
    private String id;
    private String nim;
    private Long mataKuliahId;
    private String semester;
    private String tahunAjaran;
    
    // constructors, getters, setters
    public KartuStudi() {
        // Default constructor
    }

    public KartuStudi(String nim, Long mataKuliahId, String semester, String tahunAjaran) {
        this.nim = nim;
        this.mataKuliahId = mataKuliahId;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
    }

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