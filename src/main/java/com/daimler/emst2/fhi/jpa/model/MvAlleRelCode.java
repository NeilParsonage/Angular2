package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the MV_ALLE_REL_CODES database table.
 * 
 */
@Entity
@Table(name = "MV_ALLE_REL_CODES")
@NamedQuery(name = "MvAlleRelCode.findAll", query = "SELECT m FROM MvAlleRelCode m")
public class MvAlleRelCode extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ALLE_CODES")
    private String alleCodes;

    private BigDecimal bandnr;

    @Column(name = "CODES_BAND")
    private String codesBand;

    @Column(name = "CODES_FHI")
    private String codesFhi;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pnr")
    private Auftraege auftrag;

    public MvAlleRelCode() {
    }

    public String getAlleCodes() {
        return this.alleCodes;
    }

    public void setAlleCodes(String alleCodes) {
        this.alleCodes = alleCodes;
    }

    public BigDecimal getBandnr() {
        return this.bandnr;
    }

    public void setBandnr(BigDecimal bandnr) {
        this.bandnr = bandnr;
    }

    public String getCodesBand() {
        return this.codesBand;
    }

    public void setCodesBand(String codesBand) {
        this.codesBand = codesBand;
    }

    public String getCodesFhi() {
        return this.codesFhi;
    }

    public void setCodesFhi(String codesFhi) {
        this.codesFhi = codesFhi;
    }

    @Override
    public Long getId() {
        return null;
    }

}