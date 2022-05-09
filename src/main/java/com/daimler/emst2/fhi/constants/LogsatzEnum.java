package com.daimler.emst2.fhi.constants;

public enum LogsatzEnum {
    DEBUG("D"), INFORMATION("I"), WARNING("W"), ERROR("E"), STOERUNG("S");

    private String nachrichtId;

    LogsatzEnum(String logLevel) {
        this.nachrichtId = logLevel;
    }

    public String getNachrichtId() {
        return nachrichtId;
    }

}
