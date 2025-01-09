package ru.otus.spring.project.services;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class JwtService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String getUsernameByRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Optional<String> token = Optional.empty();
        if (cookies != null) {
            token = Arrays.stream(cookies)
                    .filter(c -> "Authorization".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findAny();
        }

        if (token.isEmpty()) {
            throw new NotFoundException();
        }

        return Jwts.parserBuilder().setSigningKey(jwtSigningKey).build()
                .parseClaimsJws(token.get())
                .getBody()
                .getSubject();
    }
}
