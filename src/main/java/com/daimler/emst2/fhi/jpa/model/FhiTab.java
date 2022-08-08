package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the LAPU database view.
 * 
 */
@Entity
@Table(name = "FHITAB")
public class FhiTab implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KEY")
    private String key;

    @Column(name = "DATEN")
    private String daten;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDaten() {
        return daten;
    }

    public void setDaten(String daten) {
        this.daten = daten;
    }



}