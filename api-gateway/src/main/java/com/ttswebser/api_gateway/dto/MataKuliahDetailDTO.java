package com.ttswebser.api_gateway.dto;

public class MataKuliahDetailDTO {
    private KartuStudiDTO kartuStudi;
    private MataKuliahDTO mataKuliah;
    private NilaiDTO nilai;

    // getters and setters
    public KartuStudiDTO getKartuStudi() {
        return kartuStudi;
    }
    public void setKartuStudi(KartuStudiDTO kartuStudi) {
        this.kartuStudi = kartuStudi;
    }
    public MataKuliahDTO getMataKuliah() {
        return mataKuliah;
    }
    public void setMataKuliah(MataKuliahDTO mataKuliah) {
        this.mataKuliah = mataKuliah;
    }
    public NilaiDTO getNilai() {
        return nilai;
    }
    public void setNilai(NilaiDTO nilai) {
        this.nilai = nilai;
    }
    
}