package com.ttswebser.nilai_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NilaiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NilaiServiceApplication.class, args);
	}

}
