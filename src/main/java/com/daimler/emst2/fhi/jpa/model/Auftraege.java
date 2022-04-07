package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the WERTELISTEN database table.
 * 
 */
@Entity
@Table(name = "Auftrag")
@NamedQuery(name = "Auftraege.findAll", query = "SELECT a FROM Auftraege a")
public class Auftraege implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Lfd_Nr_Ges")
    private Long lfdNrGes;

    @Column(name = "Lfd_Nr_Fhi")
    private Long lfdNrFhi;

    @Column(name = "Lfd_Nr_Band")
    private Long lfdNrLmt;

    @Column(name = "Lfd_Nr_Ubm")
    private Long lfdNrUbm;

    @Column(name = "Fzgartneu")
    private String fzgArt;

    @Column(name = "Bandnr")
    private Long bandNr;

    @Column(name = "Fhsbm")
    private String fhsBaumuster;

    @Column(name = "Fzgbm")
    private String fzgBaumuster;

    @Column(name = "Taktkl")
    private String fzgTaktklasse;

    @Column(name = "Fhsmap")
    private String fhsTaktklasse;

    @Column(name = "Aufnr")
    private String anr;

    @Column(name = "Verkbez")
    private String verkBez;

    @Column(name = "Bandnr_Alt_Rt2")
    private Long bandNrRt2;
    
    @Column(name = "Bandnr_Alt_Rt5")
    private Long bandNrRt5;

    @Column(name = "Fin")
    private String fin;

    @Column(name = "Vin")
    private String vin;

    /* Sendestatus    */
    
    /* Audit */

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Column(name = "Hrk_Audit")
    private Long hrkAudit;

    @Column(name = "Fhi_Audit")
    private Long fhiAudit;

    public Long getHrkAudit() {
        return hrkAudit;
    }

    public void setHrkAudit(Long hrkAudit) {
        this.hrkAudit = hrkAudit;
    }

    public Long getFhiAudit() {
        return fhiAudit;
    }

    public void setFhiAudit(Long fhiAudit) {
        this.fhiAudit = fhiAudit;
    }


    public String getFzgArt() {
        return fzgArt;
    }

    public void setFzgArt(String fzgArt) {
        this.fzgArt = fzgArt;
    }

    public Long getBandNr() {
        return bandNr;
    }

    public void setBandNr(Long bandNr) {
        this.bandNr = bandNr;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Long getLfdNrGes() {
        return lfdNrGes;
    }

    public void setLfdNrGes(Long lfdNrGes) {
        this.lfdNrGes = lfdNrGes;
    }

    public Long getLfdNrFhi() {
        return lfdNrFhi;
    }

    public void setLfdNrFhi(Long lfdNrFhi) {
        this.lfdNrFhi = lfdNrFhi;
    }

    public Long getLfdNrLmt() {
        return lfdNrLmt;
    }

    public void setLfdNrLmt(Long lfdNrLmt) {
        this.lfdNrLmt = lfdNrLmt;
    }

    public Long getLfdNrUbm() {
        return lfdNrUbm;
    }

    public void setLfdNrUbm(Long lfdNrUbm) {
        this.lfdNrUbm = lfdNrUbm;
    }

    public String getFhsBaumuster() {
        return fhsBaumuster;
    }

    public void setFhsBaumuster(String fhsBaumuster) {
        this.fhsBaumuster = fhsBaumuster;
    }



    public String getAnr() {
        return anr;
    }

    public void setAnr(String anr) {
        this.anr = anr;
    }

    public String getVerkBez() {
        return verkBez;
    }

    public void setVerkBez(String verkBez) {
        this.verkBez = verkBez;
    }

    public Long getBandNrRt2() {
        return bandNrRt2;
    }

    public void setBandNrRt2(Long bandNrRt2) {
        this.bandNrRt2 = bandNrRt2;
    }

    public Long getBandNrRt5() {
        return bandNrRt5;
    }

    public void setBandNrRt5(Long bandNrRt5) {
        this.bandNrRt5 = bandNrRt5;
    }

    public String getFzgTaktklasse() {
        return fzgTaktklasse;
    }

    public void setFzgTaktklasse(String fzgTaktklasse) {
        this.fzgTaktklasse = fzgTaktklasse;
    }

    public String getFhsTaktklasse() {
        return fhsTaktklasse;
    }

    public void setFhsTaktklasse(String fhsTaktklasse) {
        this.fhsTaktklasse = fhsTaktklasse;
    }

    public String getFzgBaumuster() {
        return fzgBaumuster;
    }

    public void setFzgBaumuster(String fzgBaumuster) {
        this.fzgBaumuster = fzgBaumuster;
    }

}