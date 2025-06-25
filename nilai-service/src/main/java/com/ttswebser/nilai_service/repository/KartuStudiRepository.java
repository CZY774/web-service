package com.ttswebser.nilai_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ttswebser.nilai_service.document.KartuStudi;

@Repository
public interface KartuStudiRepository extends MongoRepository<KartuStudi, String> {
    List<KartuStudi> findByNim(String nim);
    List<KartuStudi> findBySemester(String semester);
    List<KartuStudi> findByTahunAjaran(String tahunAjaran);
    List<KartuStudi> findByNimAndSemester(String nim, String semester);
}