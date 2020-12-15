package com.soap.soapserver.domain.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SkillNotFoundException extends RuntimeException {

    public SkillNotFoundException(String msg) {
        super(msg);
    }

    public SkillNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}