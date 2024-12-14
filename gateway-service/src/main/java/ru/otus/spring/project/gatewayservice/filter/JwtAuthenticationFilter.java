package ru.otus.spring.project.gatewayservice.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.otus.spring.project.gatewayservice.exeptions.InvalidJwtTokenException;
import ru.otus.spring.project.gatewayservice.exeptions.MissingJwtTokenException;
import ru.otus.spring.project.gatewayservice.service.JwtTokenService;

import java.util.List;
import java.util.function.Predicate;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ALREADY_PREFIXED_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    public static final String AUTHORIZATION_PREFIX = "Authorization= ";

    public static final String LOGIN_URI = "/secret/login";

    private final JwtTokenService jwtTokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> apiEndpoints = List.of("/auth/sign-in", "/auth/sign-up");
        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));
        if (isApiSecured.test(request)) {
            if (!request.getCookies().containsKey("Authorization")) {
                return chain.filter(setLoginPath(exchange));
            }
            final String token = request.getCookies().get("Authorization")
                    .toString().substring(AUTHORIZATION_PREFIX.length());
            try {
                jwtTokenService.validateToken(token);
            } catch (InvalidJwtTokenException | MissingJwtTokenException e) {
                return chain.filter(setLoginPath(exchange));
            }
        }
        return chain.filter(exchange);
    }

    private ServerWebExchange setLoginPath(ServerWebExchange exchange) {
        exchange.getAttributes().put(GATEWAY_ALREADY_PREFIXED_ATTR, true);
        ServerHttpRequest req = exchange.getRequest();
        addOriginalRequestUrl(exchange, req.getURI());
        ServerHttpRequest request = req.mutate().path(LOGIN_URI).build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
        return exchange.mutate().request(request).build();
    }
}
