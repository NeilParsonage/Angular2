package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the AUFTRAG_TERMIN database table.
 * 
 */
@Entity
@Table(name = "AUFTRAG_TERMIN")
public class AuftragTermin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "LADISPO_PLAN")
    private Date ladispoPlan;

    public String getPnr() {
        return pnr;
    }



    public void setPnr(String pnr) {
        this.pnr = pnr;
    }



    public Date getLadispoPlan() {
        return ladispoPlan;
    }



    public void setLadispoPlan(Date ladispoPlan) {
        this.ladispoPlan = ladispoPlan;
    }





}