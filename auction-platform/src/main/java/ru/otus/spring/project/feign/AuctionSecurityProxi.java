package ru.otus.spring.project.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.project.dto.JwtAuthenticationResponse;
import ru.otus.spring.project.dto.UserDto;

@FeignClient(name = "authorization-server")
public interface AuctionSecurityProxi {

    @CircuitBreaker(name = "authorization-server")
    @PostMapping(value = "/auth/sign-in")
    JwtAuthenticationResponse signIn(UserDto userDto);

    @CircuitBreaker(name = "authorization-server")
    @PostMapping(value = "/auth/sign-up")
    JwtAuthenticationResponse signUp(UserDto userDto);
}