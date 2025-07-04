package com.ttswebser.api_gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("user-service", r -> r.path("/api/users/**")
                                                .uri("lb://user-service"))
                                .route("matakuliah-service", r -> r.path("/api/matakuliah/**")
                                                .uri("lb://matakuliah-service"))
                                .route("nilai-service", r -> r.path("/api/nilai/**")
                                                .uri("lb://nilai-service"))
                                .route("dashboard", r -> r.path("/api/dashboard/**")
                                                .uri("http://localhost:8080"))
                                .build();
        }

        @Bean
        @LoadBalanced
        public WebClient.Builder webClientBuilder() {
                return WebClient.builder();
        }

        @Bean
        public WebClient webClient(WebClient.Builder builder) {
                return builder.build();
        }
}
