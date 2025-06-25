package com.ttswebser.matakuliah_service.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttswebser.matakuliah_service.entity.MataKuliah;
import com.ttswebser.matakuliah_service.service.MataKuliahService;

@RestController
@RequestMapping("/api/matakuliah")
public class MataKuliahController {
    
    @Autowired
    private MataKuliahService mataKuliahService;
    
    @GetMapping
    public List<MataKuliah> getAllMataKuliah() {
        return mataKuliahService.getAllMataKuliah();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MataKuliah> getMataKuliahById(@PathVariable Long id) {
        return mataKuliahService.getMataKuliahById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public MataKuliah createMataKuliah(@RequestBody MataKuliah mataKuliah) {
        return mataKuliahService.createMataKuliah(mataKuliah);
    }
    
    @PutMapping("/{id}")
    public MataKuliah updateMataKuliah(@PathVariable Long id, @RequestBody MataKuliah mataKuliah) {
        return mataKuliahService.updateMataKuliah(id, mataKuliah);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMataKuliah(@PathVariable Long id) {
        mataKuliahService.deleteMataKuliah(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/kode/{kode}")
    public List<MataKuliah> getMataKuliahByKode(@PathVariable String kode) {
        return mataKuliahService.getMataKuliahByKode(kode);
    }
    
    @GetMapping("/search")
    public List<MataKuliah> searchMataKuliahByNama(@RequestParam String nama) {
        return mataKuliahService.searchMataKuliahByNama(nama);
    }
    
    @GetMapping("/dosen/{dosen}")
    public List<MataKuliah> getMataKuliahByDosen(@PathVariable String dosen) {
        return mataKuliahService.getMataKuliahByDosen(dosen);
    }
}