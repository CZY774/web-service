package com.ttswebser.api_gateway.filter;

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

                        // Check role-based access
                        if (path.contains("/dosen") && !"dosen".equals(role)) {
                                return onError(exchange, "Access denied", HttpStatus.FORBIDDEN);
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