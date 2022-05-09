package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the ORT_CHECKS database table.
 * 
 */
@Entity
@Table(name = "ORT_CHECKS")
@NamedQuery(name = "OrtCheck.findAll", query = "SELECT o FROM OrtCheck o")
public class OrtCheck extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ORT_CHECKS_ID")
    private long ortChecksId;

    @Column(name = "AUFTRAG_ORTS_TYP")
    private String auftragOrtsTyp;

    @Column(name = "ORT", length = 4, insertable = false, updatable = false)
    private String ort;

    @Column(name = "ORT_CHECK")
    private String ortCheck;

    //bi-directional many-to-one association to OrtReihenfolge
    @ManyToOne
    @JoinColumn(name = "ORT", referencedColumnName = "ORT")
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

    public String getOrtCheck() {
        return this.ortCheck;
    }

    public void setOrtCheck(String ortCheck) {
        this.ortCheck = ortCheck;
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

    @Override
    public Long getId() {
        return ortChecksId;
    }

}