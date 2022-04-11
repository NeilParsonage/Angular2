package com.daimler.emst2.fhi.model;

import java.util.Date;

import org.springframework.util.Assert;

public class TaktTelegram {

    private static String MELDEKLASSE_TOALLG = "T0ALLG";

    private static String STATUS_NEU = "0";

    private static String EMPFAENGER_TOL3SEND = "T0L3send";

    private String meldeKlasse = MELDEKLASSE_TOALLG; // max 6 characters

    private Date timestamp;

    private String status = STATUS_NEU; // nur "0" oder "1"

    private String empfaenger = EMPFAENGER_TOL3SEND; // max 16 characters

    private String message; // max 255 characters

    public TaktTelegram() {
        super();
    }

    /**
     * XXX THB - hier koennte eine telegramm-spezifische Validierung der Telegramm-Nachricht sinnvoll sein...
     */
    public void validate() {
        // simpler check - keine 'null' Substrings im Telegramm
        Assert.isTrue(!message.contains("null"), "found 'null' in telegram message '" + message + "'");
    }

    public String getMeldeKlasse() {
        return meldeKlasse;
    }

    public void setMeldeKlasse(String pMeldeKlasse) {
        meldeKlasse = pMeldeKlasse;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date pTimestamp) {
        timestamp = pTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String pStatus) {
        status = pStatus;
    }

    public String getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(String pEmpfaenger) {
        empfaenger = pEmpfaenger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String pMessage) {
        message = pMessage;
    }

}
