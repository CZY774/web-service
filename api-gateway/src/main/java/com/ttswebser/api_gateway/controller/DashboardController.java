package com.ttswebser.api_gateway.controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ttswebser.api_gateway.dto.AcademicInfoDTO;
import com.ttswebser.api_gateway.dto.DashboardDTO;
import com.ttswebser.api_gateway.dto.KartuStudiDTO;
import com.ttswebser.api_gateway.dto.MataKuliahDetailDTO;
import com.ttswebser.api_gateway.dto.UserResponseDTO;
import com.ttswebser.api_gateway.service.ServiceCommunicationService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    
    private final ServiceCommunicationService serviceComm;
    WebClient webClient = WebClient.create();
    
    public DashboardController(ServiceCommunicationService serviceComm) {
        this.serviceComm = serviceComm;
    }
    
    // Aggregate data dari multiple services
    @GetMapping("/mahasiswa/{nim}")
    public Mono<DashboardDTO> getMahasiswaDashboard(@PathVariable String nim) {
        return Mono.zip(
            serviceComm.getUserData(nim),
            serviceComm.getKartuStudiByNim(nim)
        ).map(tuple -> {
            UserResponseDTO user = tuple.getT1();
            List<KartuStudiDTO> kartuStudi = tuple.getT2();
            
            DashboardDTO dashboard = new DashboardDTO();
            dashboard.setUser(user);
            dashboard.setKartuStudi(kartuStudi);
            dashboard.setTotalMataKuliah(kartuStudi.size());
            
            return dashboard;
        });
    }
    
    // Aggregate detailed academic info
    @GetMapping("/academic-info/{nim}")
    public Mono<AcademicInfoDTO> getAcademicInfo(@PathVariable String nim) {
        return serviceComm.getKartuStudiByNim(nim)
            .flatMap(kartuStudiList -> {
                // Get mata kuliah details for each kartu studi
                List<Mono<MataKuliahDetailDTO>> mataKuliahMonos = kartuStudiList.stream()
                    .map(ks -> serviceComm.getMataKuliahData(ks.getMataKuliahId())
                        .map(mk -> {
                            MataKuliahDetailDTO detail = new MataKuliahDetailDTO();
                            detail.setKartuStudi(ks);
                            detail.setMataKuliah(mk);
                            return detail;
                        }))
                    .collect(Collectors.toList());
                
                return Mono.zip(mataKuliahMonos, objects -> {
                    AcademicInfoDTO academicInfo = new AcademicInfoDTO();
                    academicInfo.setNim(nim);
                    academicInfo.setMataKuliahDetails(Arrays.asList(objects)
                        .stream()
                        .map(obj -> (MataKuliahDetailDTO) obj)
                        .collect(Collectors.toList()));
                    
                    // Calculate total SKS
                    int totalSks = academicInfo.getMataKuliahDetails().stream()
                        .mapToInt(detail -> detail.getMataKuliah().getSks() != null ? 
                            detail.getMataKuliah().getSks() : 0)
                        .sum();
                    academicInfo.setTotalSks(totalSks);
                    
                    return academicInfo;
                });
            });
    }
    
    // Health check untuk semua services
    @GetMapping("/health-check")
    public Mono<Map<String, String>> checkAllServicesHealth() {
        return Mono.zip(
            checkServiceHealth("lb://user-service/api/users"),
            checkServiceHealth("lb://matakuliah-service/api/matakuliah"),
            checkServiceHealth("lb://nilai-service/api/nilai")
        ).map(tuple -> {
            Map<String, String> healthStatus = new HashMap<>();
            healthStatus.put("user-service", tuple.getT1());
            healthStatus.put("matakuliah-service", tuple.getT2());
            healthStatus.put("nilai-service", tuple.getT3());
            return healthStatus;
        });
    }
    
    private Mono<String> checkServiceHealth(String serviceUrl) {
        return webClient.get()
                .uri(serviceUrl)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> "UP")
                .onErrorReturn("DOWN")
                .timeout(Duration.ofSeconds(5));
    }
}