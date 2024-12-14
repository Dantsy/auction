package ru.otus.spring.project.gatewayservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.project.gatewayservice.filter.JwtAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuctionGatewayConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .uri("lb://authorization-server"))
                .route("auction", r -> r.path("/auction")
                        .uri("lb://auction-client"))
                .route("auction", r -> r.path("/auction/login")
                        .uri("lb://auction-client"))
                .route("auction", r -> r.path("/auction/register")
                        .uri("lb://auction-client"))
                .route("auction2", r -> r.path("/auction/form").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-client"))
                .route("bid", r -> r.path("/auction/bid").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-client"))
                .route("cabinet", r -> r.path("/auction/cabinet").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-client"))
                .route("logout", r -> r.path("/auction/logout").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-client"))
                .route("admin", r -> r.path("/auction/admin").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-client"))
                .route("api", r -> r.path("/api/**").filters(f -> f.filter(jwtFilter))
                        .uri("lb://auction-api"))
                .build();
    }
}