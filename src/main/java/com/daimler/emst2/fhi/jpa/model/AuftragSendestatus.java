package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_SENDESTATUS")
@NamedQuery(name = "AuftragSendestatus.findAll", query = "SELECT s FROM AuftragSendestatus s")
public class AuftragSendestatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Ziel_Lapu")
    private String zielLapu;

    @Column(name = "Ziel_Sepu")
    private String zielSepu;

    @Column(name = "Skid_Nr")
    private Number skidNr;

    @Column(name = "Ort")
    private String ort;

    @Column(name = "Hrknr")
    private String hrknr;
    
    @Column(name = "Fhi_Send_Status")
    private String fhiSendStatus;
    
    @Column(name = "Fhisendung")
    private String fhisendung;
    
    @Column(name = "Rhm_Send_Status")
    private String rhmSendStatus;
    
    @Column(name = "Rhmsendung")
    private String rhmsendung;
    
    @Column(name = "Lmt_Send_Status")
    private String lmtSendStatus;
    
    @Column(name = "Lmtsendung")
    private String lmtsendung;
    
    @Column(name = "Ubm_Send_Status")
    private String ubmSendStatus;
    
    @Column(name = "Ubmsendung")
    private String ubmsendung;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPnr() {
        return pnr;
    }

    public String getZielLapu() {
        return zielLapu;
    }

    public String getZielSepu() {
        return zielSepu;
    }

    public Number getSkidNr() {
        return skidNr;
    }

    public String getOrt() {
        return ort;
    }

    public String getHrknr() {
        return hrknr;
    }

    public String getFhiSendStatus() {
        return fhiSendStatus;
    }

    public String getFhisendung() {
        return fhisendung;
    }

    public String getRhmSendStatus() {
        return rhmSendStatus;
    }

    public String getRhmsendung() {
        return rhmsendung;
    }

    public String getLmtSendStatus() {
        return lmtSendStatus;
    }

    public String getLmtsendung() {
        return lmtsendung;
    }

    public String getUbmSendStatus() {
        return ubmSendStatus;
    }

    public String getUbmsendung() {
        return ubmsendung;
    }
    /* 
    @Column(name = "In_Warteschlange_01")
    private Number inWarteschlange01;
    
    @Column(name = "In_Warteschlange_Typ")
    private String inWarteschlangeTyp;
    
    @Column(name = "In_Warteschlange_Pos")
    private Number inWarteschlangePos;    
    
    @Column(name = "Anzahl_Ankuendigungen")
    private Number anzahlAnkuendigungen;
    
    @Column(name = "Anzahl_Sperren")
    private Number anzahlSperren;*/

    @Column(name = "Fp_Lmt")
    private String fpLmt;

    /*Fp_Lmt_Datum   */
    @Column(name = "Fp_Lmt_Benennung")
    private String fpLmtBenennung;
    
    @Column(name = "Fp_Fhs")
    private String fpFhs;

    /*Fp_Fhs_Datum  */
    @Column(name = "Fp_Fhs_Benennung")
    private String fpfhsBenennung;
    
    @Column(name = "Fp_Rhm")
    private String fpRhm;

    /*Fp_Rhm_Datum  */
    @Column(name = "Fp_Rhm_Benennung")
    private String fpRhmBenennung;
    
    @Column(name = "Sendbar")
    private String sendbar;
    
    @Column(name = "Zugebunden")
    private String zugebunden;




}
