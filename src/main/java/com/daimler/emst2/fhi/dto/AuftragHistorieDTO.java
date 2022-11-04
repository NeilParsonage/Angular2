package com.daimler.emst2.fhi.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AuftragHistorieDTO {

    public String pnr;
    public BigDecimal aufhId;
    public String quelle;
    public String meldkenn;
    public String aktion;
    public Date sendetermin;
    public Date zeit;
    public Long bandnr;
    public String fzgbm;
    public String ort;
    public Long lfdNrGes;
    public Long lfdNrFhi;
    public Long lfdNrLmt;
    public Long lfdNrRhm;
    public Long lfdNrUbm;
    public Date pat;
    public Long gesLfdSoll;
    public Long bdLfdSoll;

}


