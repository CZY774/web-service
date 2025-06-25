package com.ttswebser.matakuliah_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttswebser.matakuliah_service.entity.MataKuliah;
import com.ttswebser.matakuliah_service.repository.MataKuliahRepository;

@Service
public class MataKuliahService {
    
    @Autowired
    private MataKuliahRepository mataKuliahRepository;
    
    public List<MataKuliah> getAllMataKuliah() {
        return mataKuliahRepository.findAll();
    }
    
    public Optional<MataKuliah> getMataKuliahById(Long id) {
        return mataKuliahRepository.findById(id);
    }
    
    public MataKuliah createMataKuliah(MataKuliah mataKuliah) {
        return mataKuliahRepository.save(mataKuliah);
    }
    
    public MataKuliah updateMataKuliah(Long id, MataKuliah mataKuliah) {
        mataKuliah.setId(id);
        return mataKuliahRepository.save(mataKuliah);
    }
    
    public void deleteMataKuliah(Long id) {
        mataKuliahRepository.deleteById(id);
    }
    
    public List<MataKuliah> getMataKuliahByKode(String kode) {
        return mataKuliahRepository.findByKode(kode);
    }
    
    public List<MataKuliah> searchMataKuliahByNama(String nama) {
        return mataKuliahRepository.findByNamaContaining(nama);
    }
    
    public List<MataKuliah> getMataKuliahByDosen(String dosen) {
        return mataKuliahRepository.findByDosen(dosen);
    }
}