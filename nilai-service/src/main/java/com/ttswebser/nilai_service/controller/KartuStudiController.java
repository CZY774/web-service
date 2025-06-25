package com.ttswebser.nilai_service.controller;

import java.util.List;

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

import com.ttswebser.nilai_service.document.KartuStudi;
import com.ttswebser.nilai_service.service.KartuStudiService;

@RestController
@RequestMapping("/api/nilai/kartu-studi")
public class KartuStudiController {
    
    @Autowired
    private KartuStudiService kartuStudiService;
    
    @GetMapping
    public List<KartuStudi> getAllKartuStudi() {
        return kartuStudiService.getAllKartuStudi();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<KartuStudi> getKartuStudiById(@PathVariable String id) {
        return kartuStudiService.getKartuStudiById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public KartuStudi createKartuStudi(@RequestBody KartuStudi kartuStudi) {
        return kartuStudiService.createKartuStudi(kartuStudi);
    }
    
    @PutMapping("/{id}")
    public KartuStudi updateKartuStudi(@PathVariable String id, @RequestBody KartuStudi kartuStudi) {
        return kartuStudiService.updateKartuStudi(id, kartuStudi);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKartuStudi(@PathVariable String id) {
        kartuStudiService.deleteKartuStudi(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/mahasiswa/{nim}")
    public List<KartuStudi> getKartuStudiByNim(@PathVariable String nim) {
        return kartuStudiService.getKartuStudiByNim(nim);
    }
    
    @GetMapping("/semester/{semester}")
    public List<KartuStudi> getKartuStudiBySemester(@PathVariable String semester) {
        return kartuStudiService.getKartuStudiBySemester(semester);
    }
    
    @GetMapping("/mahasiswa/{nim}/semester/{semester}")
    public List<KartuStudi> getKartuStudiByNimAndSemester(@PathVariable String nim, @PathVariable String semester) {
        return kartuStudiService.getKartuStudiByNimAndSemester(nim, semester);
    }
}