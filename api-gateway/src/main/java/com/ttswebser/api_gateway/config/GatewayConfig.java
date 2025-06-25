package com.ttswebser.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.ttswebser.api_gateway.filter.AuthenticationFilter;

@Configuration
public class GatewayConfig {
    
    @Autowired
    private AuthenticationFilter authFilter;
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://user-service"))
                .route("matakuliah-service", r -> r.path("/api/matakuliah/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://matakuliah-service"))
                .route("nilai-service", r -> r.path("/api/nilai/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://nilai-service"))
                // New aggregation endpoints
                .route("dashboard", r -> r.path("/api/dashboard/**")
                        .filters(f -> f.filter(authFilter))
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