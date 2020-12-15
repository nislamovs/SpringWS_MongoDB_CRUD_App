package com.soap.soapserver.domain.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatNotFoundException extends RuntimeException {

    public StatNotFoundException(String msg) {
        super(msg);
    }

    public StatNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}