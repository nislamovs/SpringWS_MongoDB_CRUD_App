package com.soap.soapserver.domain.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}