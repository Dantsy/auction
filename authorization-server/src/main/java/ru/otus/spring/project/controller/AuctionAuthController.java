package ru.otus.spring.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.project.dto.JwtAuthenticationResponse;
import ru.otus.spring.project.dto.UserDto;
import ru.otus.spring.project.services.AuctionAuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuctionAuthController {
    private final AuctionAuthService auctionAuthenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid UserDto userDto) {
        return auctionAuthenticationService.signUp(userDto);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid UserDto userDto) {
        return auctionAuthenticationService.signIn(userDto);
    }
}