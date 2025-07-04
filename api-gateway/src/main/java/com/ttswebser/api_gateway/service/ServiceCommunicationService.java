package com.ttswebser.api_gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ttswebser.api_gateway.dto.KartuStudiDTO;
import com.ttswebser.api_gateway.dto.MataKuliahDTO;
import com.ttswebser.api_gateway.dto.UserResponseDTO;

import reactor.core.publisher.Mono;

@Service
public class ServiceCommunicationService {

    private final WebClient webClient;

    public ServiceCommunicationService(WebClient webClient) {
        this.webClient = webClient;
    }

    // service login

    // Get user data from user-service
    public Mono<UserResponseDTO> getUserData(String nim) {
        return webClient.get()
                .uri("lb://user-service/api/users/{nim}", nim)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .onErrorReturn(new UserResponseDTO());
    }

    // Get mata kuliah data from matakuliah-service
    public Mono<MataKuliahDTO> getMataKuliahData(Long id) {
        return webClient.get()
                .uri("lb://matakuliah-service/api/matakuliah/{id}", id)
                .retrieve()
                .bodyToMono(MataKuliahDTO.class)
                .onErrorReturn(new MataKuliahDTO());
    }

    // Get kartu studi from nilai-service
    public Mono<List<KartuStudiDTO>> getKartuStudiByNim(String nim) {
        return webClient.get()
                .uri("lb://nilai-service/api/nilai/kartu-studi/mahasiswa/{nim}", nim)
                .retrieve()
                .bodyToFlux(KartuStudiDTO.class)
                .collectList()
                .onErrorReturn(new ArrayList<>());
    }

    public Mono<List<MataKuliahDTO>> getMataKuliahByDosen(String dosenNim) {
        return webClient.get()
                .uri("lb://matakuliah-service/api/matakuliah/dosen/" + dosenNim)
                .retrieve()
                .bodyToFlux(MataKuliahDTO.class)
                .collectList();
    }

}