package com.daimler.emst2.frw.webexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.daimler.emst2.fhi.dto.ResponseMessagesDTO;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableRuntimeException extends RuntimeException {

    private ResponseMessagesDTO responseMessage;

    public NotAcceptableRuntimeException() {
    }

    public NotAcceptableRuntimeException(ResponseMessagesDTO messages) {
        super();
        this.responseMessage = messages;
    }

    public NotAcceptableRuntimeException(String msg) {
        super(msg);
    }

    public NotAcceptableRuntimeException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public boolean hasMessages() {
        return this.responseMessage != null;
    }

    public ResponseMessagesDTO getResponseMessage() {
        return responseMessage;
    }
}
