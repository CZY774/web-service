package com.ttswebser.nilai_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttswebser.nilai_service.document.KartuStudi;
import com.ttswebser.nilai_service.document.Nilai;
import com.ttswebser.nilai_service.repository.KartuStudiRepository;
import com.ttswebser.nilai_service.repository.NilaiRepository;

@Service
public class NilaiService {
    
    @Autowired
    private NilaiRepository nilaiRepository;
    
    @Autowired
    private KartuStudiRepository kartuStudiRepository;
    
    public List<Nilai> getAllNilai() {
        return nilaiRepository.findAll();
    }
    
    public Optional<Nilai> getNilaiById(String id) {
        return nilaiRepository.findById(id);
    }
    
    public Nilai createNilai(Nilai nilai) {
        // Validate that kartuStudiId exists
        if (nilai.getKartuStudiId() != null) {
            Optional<KartuStudi> kartuStudi = kartuStudiRepository.findById(nilai.getKartuStudiId());
            if (kartuStudi.isEmpty()) {
                throw new RuntimeException("KartuStudi with id " + nilai.getKartuStudiId() + " not found");
            }
        }
        return nilaiRepository.save(nilai);
    }
    
    public Nilai updateNilai(String id, Nilai nilai) {
        nilai.setId(id);
        return nilaiRepository.save(nilai);
    }
    
    public void deleteNilai(String id) {
        nilaiRepository.deleteById(id);
    }
    
    public List<Nilai> getNilaiByKartuStudiId(String kartuStudiId) {
        return nilaiRepository.findByKartuStudiId(kartuStudiId);
    }
    
    public Map<String, Object> getTranskrip(String nim) {
        // Get all KartuStudi for the student
        List<KartuStudi> kartuStudiList = kartuStudiRepository.findByNim(nim);
        List<Map<String, Object>> transkrip = new ArrayList<>();
        double totalNilai = 0;
        int totalSks = 0;
        int jumlahMataKuliah = 0;
        
        System.out.println("Found " + kartuStudiList.size() + " kartu studi for NIM: " + nim);
        
        for (KartuStudi ks : kartuStudiList) {
            System.out.println("Processing KartuStudi ID: " + ks.getId());
            
            // Get nilai for this kartu studi
            List<Nilai> nilaiList = nilaiRepository.findByKartuStudiId(ks.getId());
            System.out.println("Found " + nilaiList.size() + " nilai for KartuStudi ID: " + ks.getId());
            
            if (nilaiList.isEmpty()) {
                // Create entry even if no nilai yet
                Map<String, Object> item = new HashMap<>();
                item.put("kartuStudi", ks);
                item.put("nilai", null);
                item.put("status", "Belum ada nilai");
                transkrip.add(item);
            } else {
                for (Nilai nilai : nilaiList) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("kartuStudi", ks);
                    item.put("nilai", nilai);
                    item.put("status", "Sudah dinilai");
                    transkrip.add(item);
                    
                    if (nilai.getNilaiAngka() != null && nilai.getNilaiAngka() > 0) {
                        totalNilai += nilai.getNilaiAngka();
                        totalSks += 3; // Assuming 3 SKS per course
                        jumlahMataKuliah++;
                    }
                }
            }
        }
        
        double ipk = jumlahMataKuliah > 0 ? totalNilai / jumlahMataKuliah : 0.0;
        
        Map<String, Object> result = new HashMap<>();
        result.put("nim", nim);
        result.put("transkrip", transkrip);
        result.put("ipk", Math.round(ipk * 100.0) / 100.0); // Round to 2 decimal places
        result.put("totalSks", totalSks);
        result.put("jumlahMataKuliah", jumlahMataKuliah);
        
        System.out.println("Final result: IPK = " + ipk + ", Total SKS = " + totalSks);
        
        return result;
    }
    
    // Helper method to create sample data for testing
    public Map<String, Object> createSampleData(String nim) {
        // Create sample KartuStudi
        KartuStudi ks1 = new KartuStudi(nim, 1L, "Ganjil", "2024/2025");
        KartuStudi ks2 = new KartuStudi(nim, 2L, "Ganjil", "2024/2025");
        
        KartuStudi savedKs1 = kartuStudiRepository.save(ks1);
        KartuStudi savedKs2 = kartuStudiRepository.save(ks2);
        
        // Create sample Nilai
        Nilai nilai1 = new Nilai(savedKs1.getId(), 85.0, "A");
        Nilai nilai2 = new Nilai(savedKs2.getId(), 78.0, "B");
        
        nilaiRepository.save(nilai1);
        nilaiRepository.save(nilai2);
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Sample data created successfully");
        result.put("kartuStudi1", savedKs1);
        result.put("kartuStudi2", savedKs2);
        result.put("nilai1", nilai1);
        result.put("nilai2", nilai2);
        
        return result;
    }
}