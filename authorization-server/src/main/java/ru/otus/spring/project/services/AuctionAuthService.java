package ru.otus.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.dto.JwtAuthenticationResponse;
import ru.otus.spring.project.dto.UserDto;
import ru.otus.spring.project.models.Role;
import ru.otus.spring.project.models.User;

@Service
@RequiredArgsConstructor
public class AuctionAuthService {

    private final AuctionUserService auctionUserService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse signUp(UserDto userDto) {
        var user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        auctionUserService.create(user);

        var jwt = jwtTokenService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(UserDto userDto) {
        var user = auctionUserService.userDetailsService().loadUserByUsername(userDto.getUsername());
        var jwt = jwtTokenService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}