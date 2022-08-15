package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

/**
 * The primaryKey class for the LAPU database entity.
 * 
 */
//@Embeddable
public class LapuId implements Serializable {

    private String platz;
    private String reihe;

    public LapuId(String platz, String reihe) {
        this.platz = platz;
        this.reihe = reihe;
    }

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


}