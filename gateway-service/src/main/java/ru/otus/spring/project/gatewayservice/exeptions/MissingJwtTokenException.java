package ru.otus.spring.project.gatewayservice.exeptions;

import javax.security.sasl.AuthenticationException;

public class MissingJwtTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public MissingJwtTokenException(String msg) {
        super(msg);
    }
}