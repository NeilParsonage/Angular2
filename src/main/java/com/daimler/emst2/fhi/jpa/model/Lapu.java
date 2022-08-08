package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the LAPU database view.
 * 
 */
@Entity
@Table(name = "LAPU")
@IdClass(LapuId.class)
public class Lapu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String platz;

    @Id
    private String reihe;
    
    public String getPlatz() {
        return platz;
    }

    public void setPlatz(String platz) {
        this.platz = platz;
    }

    public String getReihe() {
        return reihe;
    }

    public void setReihe(String reihe) {
        this.reihe = reihe;
    }

    @Column(name = "PNR_IST")
    private String pnr_ist;

    public String getPnr_ist() {
        return pnr_ist;
    }

    public void setPnr_ist(String pnr_ist) {
        this.pnr_ist = pnr_ist;
    }


}
