package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_E2_HISTORIE database table.
 * 
 */
@Entity
@Table(name = "V_E2_HISTORIE")
@NamedQuery(name = "AuftragHistorie.findAll", query = "SELECT v FROM AuftragHistorie v")
public class AuftragHistorieReadOnly extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Transient
    public static final int MAX_LEN_MELDUNG = 40;

    @Id
    @Column(name = "AUF_HIST_ID")
    @SequenceGenerator(name = "SEQ_AUF_HIST_ID_GENERATOR", sequenceName = "SEQ_AUF_HIST_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUF_HIST_ID_GENERATOR")
    private Long aufHistId;

    @Column(name = "AUF_PNR")
    private String aufPnr;

    @Column(name = "AUFH_ID")
    private BigDecimal aufhId;

    private String melder;

    private String meldkenn;

    private String meldung;

    @Temporal(TemporalType.DATE)
    private Date sendetermin;

    @Temporal(TemporalType.DATE)
    private Date zeit;

    private Long bandnr;

    private String fzgbm;

    private String ort;

    public AuftragHistorieReadOnly() {
    }

    public Long getAufHistId() {
        return this.aufHistId;
    }

    public void setAufHistId(Long aufHistId) {
        this.aufHistId = aufHistId;
    }

    public String getAufPnr() {
        return this.aufPnr;
    }

    public void setAufPnr(String aufPnr) {
        this.aufPnr = aufPnr;
    }

    public BigDecimal getAufhId() {
        return this.aufhId;
    }

    public void setAufhId(BigDecimal aufhId) {
        this.aufhId = aufhId;
    }

    public String getMelder() {
        return this.melder;
    }

    public void setMelder(String melder) {
        this.melder = melder;
    }

    public String getMeldkenn() {
        return this.meldkenn;
    }

    public void setMeldkenn(String meldkenn) {
        this.meldkenn = meldkenn;
    }

    public String getMeldung() {
        return this.meldung;
    }

    public void setMeldung(String meldung) {
        this.meldung = meldung;
    }

    public Date getSendetermin() {
        return this.sendetermin;
    }

    public void setSendetermin(Date sendetermin) {
        this.sendetermin = sendetermin;
    }

    public Date getZeit() {
        return this.zeit;
    }

    public void setZeit(Date zeit) {
        this.zeit = zeit;
    }

    public Long getBandnr() {
        return bandnr;
    }

    public void setBandnr(Long bandnr) {
        this.bandnr = bandnr;
    }

    public String getFzgbm() {
        return fzgbm;
    }

    public void setFzgbm(String fzgbm) {
        this.fzgbm = fzgbm;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public Long getId() {
        return aufHistId;
    }

}