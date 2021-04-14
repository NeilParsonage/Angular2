package com.daimler.emst2.frw.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    @JsonFormat
    String error;

    @JsonFormat
    String exception;

    public static final ErrorResponse createError(String msg, String rawMsg) {
        ErrorResponse err = new ErrorResponse();
        err.error = msg;
        err.exception = rawMsg;
        return err;
    }

    /*public static final String createError(String msg, String rawMsg) {
        return msg;
    }*/
}
