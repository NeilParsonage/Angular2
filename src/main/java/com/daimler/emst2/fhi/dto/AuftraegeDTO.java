package com.daimler.emst2.fhi.dto;

import org.springframework.stereotype.Component;

@Component
public class AuftraegeDTO {
    public String pnr;

    public Long lfdNrGes;

    public Long lfdNrFhi;

    public Long lfdNrLmt;

    public Long lfdNrUbm;

    public String fzgArt;

    public Long bandNr;

    public Long bandNrRt2;

    public Long bandNrRt5;

    public String fhsBaumuster;

    public String fzgBaumuster;

    public String fhsTaktklasse;

    public String fzgTaktklasse;

    public String anr;

    public String verkBez;

    public String fin;

    public String vin;

    /* Details*/

    public String aufaenText;

    public String bemerkung;

    public String autor;

    public String bemerkungAlt;

    public String landesCode;

    public String land;

    public String gesamtLaenge;

    public String radStand;

    public String alleCodes;

    public String fhiRelCodes;

    public String bandRelCodes;

    public String alleKrits;

    public String fhiRelKrits;

    public String bandRelKrits;
    /* Sendestatus*/
    public String zielLapu;

    public String zielSepu;

    public Number skidNr;

    public String ort;

    public String hrknr;
    
    public String fhiSendStatus;
    
    public String fhisendung;
    
    public String rhmSendStatus;
    
    public String rhmsendung;
    
    public String lmtSendStatus;
    
    public String lmtsendung;
    
    public String ubmSendStatus;
    
    public String ubmsendung;
    
    public Number inWarteschlange01;
    
    public String inWarteschlangeTyp;
    
    public Number inWarteschlangePos;
    
    public Number anzahlAnkuendigungen;
    
    public Number anzahlSperren;
    
    public String fpLmt;

    /*Fp_Lmt_Datum   */
    public String fpLmtBenennung;
    
    public String fpFhs;

    /*Fp_Fhs_Datum  */
    public String fpfhsBenennung;
    
    public String fpRhm;

    /*Fp_Rhm_Datum  */
    public String fpRhmBenennung;
    
    public String sendbar;
    
    public String zugebunden;
    /* Audit*/

    public Long hrkAudit;

    public Long fhiAudit;

}


