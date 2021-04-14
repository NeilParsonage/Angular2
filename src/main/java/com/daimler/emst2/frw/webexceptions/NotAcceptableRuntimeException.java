package com.daimler.emst2.frw.webexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableRuntimeException extends RuntimeException {

    public NotAcceptableRuntimeException() {
    }

    public NotAcceptableRuntimeException(String msg) {
        super(msg);
    }

    public NotAcceptableRuntimeException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
