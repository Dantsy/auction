package ru.otus.spring.project.gatewayservice.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.project.gatewayservice.exeptions.InvalidJwtTokenException;
import ru.otus.spring.project.gatewayservice.exeptions.MissingJwtTokenException;

@Service
@Slf4j
public class JwtTokenService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public void validateToken(final String token) throws InvalidJwtTokenException, MissingJwtTokenException {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSigningKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (MalformedJwtException ex) {
            throw new InvalidJwtTokenException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new InvalidJwtTokenException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new InvalidJwtTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new MissingJwtTokenException("JWT claims string is empty.");
        }
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSigningKey).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info(e.getMessage() + " => " + e);
        }
        return null;
    }
}