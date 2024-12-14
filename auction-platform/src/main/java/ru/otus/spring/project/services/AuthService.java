package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.JwtAuthenticationResponse;
import ru.otus.spring.project.dto.UserDto;
import ru.otus.spring.project.feign.AuctionSecurityProxi;

@Service
@Slf4j
@ConditionalOnProperty(name = "use", havingValue = "feign")
@RequiredArgsConstructor
public class AuthService {

    private final AuctionSecurityProxi auctionSecurityProxi;

    public JwtAuthenticationResponse signIn(UserDto userDto) {
        return auctionSecurityProxi.signIn(userDto);
    }

    public JwtAuthenticationResponse signUp(UserDto userDto) {
        return auctionSecurityProxi.signUp(userDto);
    }
}