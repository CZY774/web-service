package com.ttswebser.matakuliah_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttswebser.matakuliah_service.entity.MataKuliah;

@Repository
public interface MataKuliahRepository extends JpaRepository<MataKuliah, Long> {
    List<MataKuliah> findByKode(String kode);
    List<MataKuliah> findByNamaContaining(String nama);
    List<MataKuliah> findByDosen(String dosen);
    List<MataKuliah> findBySks(Integer sks);
}