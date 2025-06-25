package com.ttswebser.nilai_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttswebser.nilai_service.document.Nilai;
import com.ttswebser.nilai_service.service.NilaiService;

@RestController
@RequestMapping("/api/nilai")
public class NilaiController {
    
    @Autowired
    private NilaiService nilaiService;
    
    @GetMapping
    public List<Nilai> getAllNilai() {
        return nilaiService.getAllNilai();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Nilai> getNilaiById(@PathVariable String id) {
        return nilaiService.getNilaiById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createNilai(@RequestBody Nilai nilai) {
        try {
            Nilai savedNilai = nilaiService.createNilai(nilai);
            return ResponseEntity.ok(savedNilai);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public Nilai updateNilai(@PathVariable String id, @RequestBody Nilai nilai) {
        return nilaiService.updateNilai(id, nilai);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNilai(@PathVariable String id) {
        nilaiService.deleteNilai(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/kartu-studi/{kartuStudiId}/nilai")
    public List<Nilai> getNilaiByKartuStudiId(@PathVariable String kartuStudiId) {
        return nilaiService.getNilaiByKartuStudiId(kartuStudiId);
    }
    
    @GetMapping("/transkrip/{nim}")
    public Map<String, Object> getTranskrip(@PathVariable String nim) {
        return nilaiService.getTranskrip(nim);
    }
    
    // Helper endpoint to create sample data for testing
    @PostMapping("/sample-data/{nim}")
    public Map<String, Object> createSampleData(@PathVariable String nim) {
        return nilaiService.createSampleData(nim);
    }
}