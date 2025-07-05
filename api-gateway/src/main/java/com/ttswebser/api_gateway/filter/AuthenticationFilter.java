package com.ttswebser.api_gateway.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.ttswebser.api_gateway.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

        @Autowired
        private JwtUtil jwtUtil;

        // Role-based access config
        private static final Map<String, List<String>> roleAccessMap = new HashMap<>();

        static {
                // Akses hanya untuk dosen
                roleAccessMap.put("/api/matakuliah/tambah", List.of("dosen"));
                roleAccessMap.put("/api/matakuliah/edit", List.of("dosen"));
                roleAccessMap.put("/api/matakuliah/delete", List.of("dosen"));

                // Akses hanya untuk mahasiswa
                roleAccessMap.put("/api/nilai/kartu-studi", List.of("mahasiswa"));

                // Tambahkan aturan lain sesuai kebutuhan
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String path = exchange.getRequest().getURI().getPath();

                // Allow login and register without authentication
                if (path.contains("/login") || path.contains("/register")) {
                        return chain.filter(exchange);
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
                }

                String token = authHeader.substring(7);

                try {
                        if (!jwtUtil.validateToken(token)) {
                                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
                        }

                        String role = jwtUtil.extractRole(token);

                        // Ganti pengecekan role per-path dengan roleAccessMap
                        for (Map.Entry<String, List<String>> entry : roleAccessMap.entrySet()) {
                                String protectedPath = entry.getKey();
                                List<String> allowedRoles = entry.getValue();

                                if (path.startsWith(protectedPath) && !allowedRoles.contains(role)) {
                                        return onError(exchange, "Access denied for role: " + role,
                                                        HttpStatus.FORBIDDEN);
                                }
                        }

                } catch (Exception e) {
                        return onError(exchange, "Token validation failed", HttpStatus.UNAUTHORIZED);
                }

                return chain.filter(
                                exchange.mutate().request(builder -> builder.header("Authorization", authHeader))
                                                .build());
        }

        private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(httpStatus);
                return response.setComplete();
        }
}