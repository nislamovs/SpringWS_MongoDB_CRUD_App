package com.soap.soapclient.domain.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestNotFoundException extends RuntimeException {

    public QuestNotFoundException(String msg) {
        super(msg);
    }

    public QuestNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}