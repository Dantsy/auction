package ru.otus.spring.project.gatewayservice.exeptions;

import javax.security.sasl.AuthenticationException;

public class InvalidJwtTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public InvalidJwtTokenException(String msg) {
        super(msg);
    }
}
