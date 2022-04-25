package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_AUFT_KRIT database table.
 * 
 */
@Entity
@Table(name = "V_AUFT_KRIT")
@NamedQuery(name = "KriteriumRelevant.findAll", query = "SELECT v FROM KriteriumRelevant v")
public class KriteriumRelevant extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bereich;

    private String pnr;

    @Column(name = "REST_ID")
    private Long restId;

    @Id
    @Column(name = "V_AUFT_KRIT_PK")
    private String vAuftKritPk;

    public KriteriumRelevant() {
    }

    public String getBereich() {
        return this.bereich;
    }

    public void setBereich(String bereich) {
        this.bereich = bereich;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Long getRestId() {
        return this.restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getVAuftKritPk() {
        return this.vAuftKritPk;
    }

    public void setVAuftKritPk(String vAuftKritPk) {
        this.vAuftKritPk = vAuftKritPk;
    }

    @Override
    public Long getId() {
        return null;
    }

}
