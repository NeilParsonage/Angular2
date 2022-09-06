package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_UML")
@NamedQuery(name = "UmlaufWerte.findAll", query = "SELECT u FROM UmlaufWerte u")
public class UmlaufWerte implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BANDNR")
    private Long bandNr;

    @Column(name = "ANZAHL_BAND")
    private Long anzahlBand;

    @Column(name = "ANZAHL_FHI")
    private Long anzahlFhi;

    @Column(name = "UML")
    private Long uml;

    public Long getBand() {
        return bandNr;
    }

    public void setBand(Long bandNr) {
        this.bandNr = bandNr;
    }

    public Long getAnzahlBand() {
        return anzahlBand;
    }

    public void setAnzahlBand(Long anzahlBand) {
        this.anzahlBand = anzahlBand;
    }

    public Long getAnzahlFhi() {
        return anzahlFhi;
    }

    public void setAnzahlFhi(Long anzahlFhi) {
        this.anzahlFhi = anzahlFhi;
    }

    public Long getUml() {
        return uml;
    }

    public void setUml(Long uml) {
        this.uml = uml;
    }


}