package com.ttswebser.nilai_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttswebser.nilai_service.document.KartuStudi;
import com.ttswebser.nilai_service.repository.KartuStudiRepository;

@Service
public class KartuStudiService {
    
    @Autowired
    private KartuStudiRepository kartuStudiRepository;
    
    public List<KartuStudi> getAllKartuStudi() {
        return kartuStudiRepository.findAll();
    }
    
    public Optional<KartuStudi> getKartuStudiById(String id) {
        return kartuStudiRepository.findById(id);
    }
    
    public KartuStudi createKartuStudi(KartuStudi kartuStudi) {
        return kartuStudiRepository.save(kartuStudi);
    }
    
    public KartuStudi updateKartuStudi(String id, KartuStudi kartuStudi) {
        kartuStudi.setId(id);
        return kartuStudiRepository.save(kartuStudi);
    }
    
    public void deleteKartuStudi(String id) {
        kartuStudiRepository.deleteById(id);
    }
    
    public List<KartuStudi> getKartuStudiByNim(String nim) {
        return kartuStudiRepository.findByNim(nim);
    }
    
    public List<KartuStudi> getKartuStudiBySemester(String semester) {
        return kartuStudiRepository.findBySemester(semester);
    }
    
    public List<KartuStudi> getKartuStudiByNimAndSemester(String nim, String semester) {
        return kartuStudiRepository.findByNimAndSemester(nim, semester);
    }
}