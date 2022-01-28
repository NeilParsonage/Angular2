package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_TERMINE")
@NamedQuery(name = "AuftragTermin.findAll", query = "SELECT t FROM AuftragTermine t")
public class AuftragTermine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "SAT")
    private Date sat;

    @Column(name = "SAT_JUL")
    private String satJul;

    @Column(name = "PAT")
    private Date pat;

    @Column(name = "PAT_JUL")
    private String patJul;

    @Column(name = "IBSPERRE")
    private Date ibSperre;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Date getSat() {
        return sat;
    }

    public void setSat(Date sat) {
        this.sat = sat;
    }

    public String getSatJul() {
        return satJul;
    }

    public void setSatJul(String satJul) {
        this.satJul = satJul;
    }

    public Date getPat() {
        return pat;
    }

    public void setPat(Date pat) {
        this.pat = pat;
    }

    public String getPatJul() {
        return patJul;
    }

    public void setPatJul(String patJul) {
        this.patJul = patJul;
    }

    public Date getIbsperre() {
        return ibSperre;
    }

    public void setIbsperre(Date ibSperre) {
        this.ibSperre = ibSperre;
    }


}