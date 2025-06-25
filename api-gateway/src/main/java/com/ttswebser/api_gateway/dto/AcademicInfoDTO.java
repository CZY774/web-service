package com.ttswebser.api_gateway.dto;

import java.util.List;

public class AcademicInfoDTO {
    private String nim;
    private List<MataKuliahDetailDTO> mataKuliahDetails;
    private Integer totalSks;
    private String status;

    // getters and setters
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public List<MataKuliahDetailDTO> getMataKuliahDetails() {
        return mataKuliahDetails;
    }
    public void setMataKuliahDetails(List<MataKuliahDetailDTO> mataKuliahDetails) {
        this.mataKuliahDetails = mataKuliahDetails;
    }
    public Integer getTotalSks() {
        return totalSks;
    }
    public void setTotalSks(Integer totalSks) {
        this.totalSks = totalSks;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}