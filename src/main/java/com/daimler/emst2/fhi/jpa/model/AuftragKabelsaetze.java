package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_DETAILS")
@NamedQuery(name = "AuftragKabelsaetze.findAll", query = "SELECT t FROM AuftragKabelsaetze t")
public class AuftragKabelsaetze implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KABELSATZ_ID")
    private String id;


    @Column(name = "PNR")
    private String pnr;

    @Column(name = "KABELSATZ")
    private String kabelsatz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getKabelsatz() {
        return kabelsatz;
    }

    public void setKabelsatz(String kabelsatz) {
        this.kabelsatz = kabelsatz;
    }

}