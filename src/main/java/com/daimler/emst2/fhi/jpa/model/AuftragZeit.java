package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_AUFT_ZEIT database table.
 * 
 */
@Entity
@Table(name = "V_AUFT_ZEIT")
public class AuftragZeit extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AUFT_IST_PK")
    private String auftLackPk;

    @Column(name = "BEREICH")
    private String bereich;

    @Column(name = "BAND1_ZEIT")
    private Date band1Zeit;

    @Column(name = "BAND2_ZEIT")
    private Date band2Zeit;

    @Column(name = "BAND3_ZEIT")
    private Date band3Zeit;

    @Column(name = "FHI_ZEIT")
    private Date fhiZeit;

    public String getBereich() {
        return bereich;
    }


    public void setBereich(String bereich) {
        this.bereich = bereich;
    }



    public Date getBand1Zeit() {
        return band1Zeit;
    }

    public void setBand1Zeit(Date band1Zeit) {
        this.band1Zeit = band1Zeit;
    }

    public Date getBand2Zeit() {
        return band2Zeit;
    }

    public void setBand2Zeit(Date band2Zeit) {
        this.band2Zeit = band2Zeit;
    }

    public Date getBand3Zeit() {
        return band3Zeit;
    }


    public void setBand3Zeit(Date band3Zeit) {
        this.band3Zeit = band3Zeit;
    }



    public Date getFhiZeit() {
        return fhiZeit;
    }



    public void setFhiZeit(Date fhiZeit) {
        this.fhiZeit = fhiZeit;
    }



    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }


}