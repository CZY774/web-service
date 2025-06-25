package com.ttswebser.nilai_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ttswebser.nilai_service.document.Nilai;

@Repository
public interface NilaiRepository extends MongoRepository<Nilai, String> {
    List<Nilai> findByKartuStudiId(String kartuStudiId);
    List<Nilai> findByNilaiHuruf(String nilaiHuruf);
    List<Nilai> findByNilaiAngkaGreaterThan(Double nilai);
}