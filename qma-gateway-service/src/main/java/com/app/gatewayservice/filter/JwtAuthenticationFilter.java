package com.app.gatewayservice.filter;

import com.app.gatewayservice.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // ✅ Skip ONLY public endpoints
        if (path.startsWith("/auth") ||
            path.startsWith("/oauth2") ||
            path.startsWith("/login/oauth2")) {
            return chain.filter(exchange);
        }

        // Get Authorization header
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // =========================
        // 🔐 IF TOKEN PRESENT → VALIDATE
        // =========================
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            // ❌ Invalid token
            if (!jwtUtil.validateToken(token)) {
                return onError(exchange, "Invalid Token");
            }

            // ✅ Extract email
            String email = jwtUtil.extractEmail(token);

            // ✅ Add header for downstream services
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(builder -> builder.header("X-User-Email", email))
                    .build();

            return chain.filter(modifiedExchange);
        }

        // =========================
        // 👤 NO TOKEN → GUEST MODE
        // =========================
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}