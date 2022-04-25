package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ORT_CHECKS database table.
 * 
 */
@Entity
@Table(name = "ORT_CHECKS")
@NamedQuery(name = "OrtCheck.findAll", query = "SELECT o FROM OrtCheck o")
public class OrtCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ORT_CHECKS_ID")
    private long ortChecksId;

    @Column(name = "AUFTRAG_ORTS_TYP")
    private String auftragOrtsTyp;

    @Column(name = "ORT", length = 4)
    private String ort;

    @Column(name = "ORT", length = 4, updatable = false, insertable = false)
    private String ort2;

    @Temporal(TemporalType.DATE)
    @Column(name = "INS_DATE")
    private Date insDate;

    @Column(name = "INS_USER")
    private String insUser;

    @Column(name = "ORT_CHECK")
    private String ortCheck;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPD_DATE")
    private Date updDate;

    @Column(name = "UPD_USER")
    private String updUser;

    @Column(name = "\"VERSION\"")
    private BigDecimal version;

    //bi-directional many-to-one association to OrtReihenfolge
    @ManyToOne
    @JoinColumn(name = "ORT2")
    private OrtReihenfolge ortReihenfolge;

    public OrtCheck() {
    }

    public long getOrtChecksId() {
        return this.ortChecksId;
    }

    public void setOrtChecksId(long ortChecksId) {
        this.ortChecksId = ortChecksId;
    }

    public String getAuftragOrtsTyp() {
        return this.auftragOrtsTyp;
    }

    public void setAuftragOrtsTyp(String auftragOrtsTyp) {
        this.auftragOrtsTyp = auftragOrtsTyp;
    }

    public Date getInsDate() {
        return this.insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getInsUser() {
        return this.insUser;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }

    public String getOrtCheck() {
        return this.ortCheck;
    }

    public void setOrtCheck(String ortCheck) {
        this.ortCheck = ortCheck;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdUser() {
        return this.updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public BigDecimal getVersion() {
        return this.version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public OrtReihenfolge getOrtReihenfolge() {
        return this.ortReihenfolge;
    }

    public void setOrtReihenfolge(OrtReihenfolge ortReihenfolge) {
        this.ortReihenfolge = ortReihenfolge;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

}