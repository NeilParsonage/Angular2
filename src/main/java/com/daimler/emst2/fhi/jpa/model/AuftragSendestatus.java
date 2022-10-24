package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the V_E2_SENDESTATUS database table.
 * 
 */
@Entity
@Table(name = "V_E2_SENDESTATUS")
@NamedQuery(name = "AuftragSendestatus.findAll", query = "SELECT v FROM AuftragSendestatus v")
public class AuftragSendestatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ANZAHL_ANKUENDIGUNGEN")
    private BigDecimal anzahlAnkuendigungen;

    @Column(name = "ANZAHL_SPERREN")
    private BigDecimal anzahlSperren;

    @Column(name = "FHI_SEND_STATUS")
    private String fhiSendStatus;

    private String fhisendung;

    @Column(name = "FP_FHS")
    private String fpFhs;

    @Column(name = "FP_FHS_BENENNUNG")
    private String fpFhsBenennung;

    @Temporal(TemporalType.DATE)
    @Column(name = "FP_FHS_DATUM")
    private Date fpFhsDatum;

    @Column(name = "FP_LMT")
    private String fpLmt;

    @Column(name = "FP_LMT_BENENNUNG")
    private String fpLmtBenennung;

    @Temporal(TemporalType.DATE)
    @Column(name = "FP_LMT_DATUM")
    private Date fpLmtDatum;

    @Column(name = "FP_RHM")
    private String fpRhm;

    @Column(name = "FP_RHM_BENENNUNG")
    private String fpRhmBenennung;

    @Temporal(TemporalType.DATE)
    @Column(name = "FP_RHM_DATUM")
    private Date fpRhmDatum;

    private String hrknr;

    @Column(name = "IN_WARTESCHLANGE_01")
    private BigDecimal inWarteschlange01;

    @Column(name = "IN_WARTESCHLANGE_POS")
    private BigDecimal inWarteschlangePos;

    @Column(name = "IN_WARTESCHLANGE_TYP")
    private String inWarteschlangeTyp;

    @Column(name = "LMT_SEND_STATUS")
    private String lmtSendStatus;

    private String lmtsendung;

    private String ort;

    @Id
    private String pnr;

    @Column(name = "RHM_SEND_STATUS")
    private String rhmSendStatus;

    private String rhmsendung;

    private String sendbar;

    @Column(name = "SKID_NR")
    private BigDecimal skidNr;

    @Column(name = "UBM_SEND_STATUS")
    private String ubmSendStatus;

    private String ubmsendung;

    @Column(name = "ZIEL_LAPU")
    private String zielLapu;

    @Column(name = "ZIEL_SEPU")
    private String zielSepu;

    private String zugebunden;



    public BigDecimal getAnzahlAnkuendigungen() {
        return this.anzahlAnkuendigungen;
    }

    public void setAnzahlAnkuendigungen(BigDecimal anzahlAnkuendigungen) {
        this.anzahlAnkuendigungen = anzahlAnkuendigungen;
    }

    public BigDecimal getAnzahlSperren() {
        return this.anzahlSperren;
    }

    public void setAnzahlSperren(BigDecimal anzahlSperren) {
        this.anzahlSperren = anzahlSperren;
    }

    public String getFhiSendStatus() {
        return this.fhiSendStatus;
    }

    public void setFhiSendStatus(String fhiSendStatus) {
        this.fhiSendStatus = fhiSendStatus;
    }

    public String getFhisendung() {
        return this.fhisendung;
    }

    public void setFhisendung(String fhisendung) {
        this.fhisendung = fhisendung;
    }

    public String getFpFhs() {
        return this.fpFhs;
    }

    public void setFpFhs(String fpFhs) {
        this.fpFhs = fpFhs;
    }

    public String getFpFhsBenennung() {
        return this.fpFhsBenennung;
    }

    public void setFpFhsBenennung(String fpFhsBenennung) {
        this.fpFhsBenennung = fpFhsBenennung;
    }

    public Date getFpFhsDatum() {
        return this.fpFhsDatum;
    }

    public void setFpFhsDatum(Date fpFhsDatum) {
        this.fpFhsDatum = fpFhsDatum;
    }

    public String getFpLmt() {
        return this.fpLmt;
    }

    public void setFpLmt(String fpLmt) {
        this.fpLmt = fpLmt;
    }

    public String getFpLmtBenennung() {
        return this.fpLmtBenennung;
    }

    public void setFpLmtBenennung(String fpLmtBenennung) {
        this.fpLmtBenennung = fpLmtBenennung;
    }

    public Date getFpLmtDatum() {
        return this.fpLmtDatum;
    }

    public void setFpLmtDatum(Date fpLmtDatum) {
        this.fpLmtDatum = fpLmtDatum;
    }

    public String getFpRhm() {
        return this.fpRhm;
    }

    public void setFpRhm(String fpRhm) {
        this.fpRhm = fpRhm;
    }

    public String getFpRhmBenennung() {
        return this.fpRhmBenennung;
    }

    public void setFpRhmBenennung(String fpRhmBenennung) {
        this.fpRhmBenennung = fpRhmBenennung;
    }

    public Date getFpRhmDatum() {
        return this.fpRhmDatum;
    }

    public void setFpRhmDatum(Date fpRhmDatum) {
        this.fpRhmDatum = fpRhmDatum;
    }

    public String getHrknr() {
        return this.hrknr;
    }

    public void setHrknr(String hrknr) {
        this.hrknr = hrknr;
    }

    public BigDecimal getInWarteschlange01() {
        return this.inWarteschlange01;
    }

    public void setInWarteschlange01(BigDecimal inWarteschlange01) {
        this.inWarteschlange01 = inWarteschlange01;
    }

    public BigDecimal getInWarteschlangePos() {
        return this.inWarteschlangePos;
    }

    public void setInWarteschlangePos(BigDecimal inWarteschlangePos) {
        this.inWarteschlangePos = inWarteschlangePos;
    }

    public String getInWarteschlangeTyp() {
        return this.inWarteschlangeTyp;
    }

    public void setInWarteschlangeTyp(String inWarteschlangeTyp) {
        this.inWarteschlangeTyp = inWarteschlangeTyp;
    }

    public String getLmtSendStatus() {
        return this.lmtSendStatus;
    }

    public void setLmtSendStatus(String lmtSendStatus) {
        this.lmtSendStatus = lmtSendStatus;
    }

    public String getLmtsendung() {
        return this.lmtsendung;
    }

    public void setLmtsendung(String lmtsendung) {
        this.lmtsendung = lmtsendung;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getRhmSendStatus() {
        return this.rhmSendStatus;
    }

    public void setRhmSendStatus(String rhmSendStatus) {
        this.rhmSendStatus = rhmSendStatus;
    }

    public String getRhmsendung() {
        return this.rhmsendung;
    }

    public void setRhmsendung(String rhmsendung) {
        this.rhmsendung = rhmsendung;
    }



    public BigDecimal getSkidNr() {
        return this.skidNr;
    }

    public void setSkidNr(BigDecimal skidNr) {
        this.skidNr = skidNr;
    }

    public String getUbmSendStatus() {
        return this.ubmSendStatus;
    }

    public void setUbmSendStatus(String ubmSendStatus) {
        this.ubmSendStatus = ubmSendStatus;
    }

    public String getUbmsendung() {
        return this.ubmsendung;
    }

    public void setUbmsendung(String ubmsendung) {
        this.ubmsendung = ubmsendung;
    }

    public String getZielLapu() {
        return this.zielLapu;
    }

    public void setZielLapu(String zielLapu) {
        this.zielLapu = zielLapu;
    }

    public String getZielSepu() {
        return this.zielSepu;
    }

    public void setZielSepu(String zielSepu) {
        this.zielSepu = zielSepu;
    }

    public String getSendbar() {
        return sendbar;
    }

    public void setSendbar(String sendbar) {
        this.sendbar = sendbar;
    }

    public String getZugebunden() {
        return zugebunden;
    }

    public void setZugebunden(String zugebunden) {
        this.zugebunden = zugebunden;
    }


}