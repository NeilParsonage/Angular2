package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_AUF_SPERRE")
@NamedQuery(name = "AuftragSperren.findAll", query = "SELECT s FROM AuftragSperren s")
public class AuftragSperren implements Serializable {
    private static final long serialVersionUID = 1L;

    /*Aktuell werden nicht alle Spalten eingelesen, nur die wichtigsten   */

    @Id
    @Column(name = "Vasp_Id")
    private String vaspId;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Sperrgrund")
    private String sperrgrund;

    /* Freigabetermin*/

    @Column(name = "Sperrtyp")
    private String sperrtyp;

    @Column(name = "Sperrart")
    private String sperrart;

    @Column(name = "Sperrcode")
    private String sperrcode;

    @Column(name = "Sperrcodetext")
    private String sperrcodetext;

    @Column(name = "Begruendung")
    private String begruendung;

    @Column(name = "FREIE")
    private Long freie;

    @Column(name = "HERKUNFT")
    private String herkunft;

    @Column(name = "RELEVANT_01")
    private Boolean relevant;

    @Column(name = "VF")
    private String vf;

    @Column(name = "BEREICH_FHI")
    private String bereichFHI;

    public String getVaspId() {
        return vaspId;
    }

    public void setVaspId(String vaspId) {
        this.vaspId = vaspId;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSperrgrund() {
        return sperrgrund;
    }

    public void setSperrgrund(String sperrgrund) {
        this.sperrgrund = sperrgrund;
    }

    public String getSperrtyp() {
        return sperrtyp;
    }

    public void setSperrtyp(String sperrtyp) {
        this.sperrtyp = sperrtyp;
    }

    public String getSperrart() {
        return sperrart;
    }

    public void setSperrart(String sperrart) {
        this.sperrart = sperrart;
    }

    public String getSperrcode() {
        return sperrcode;
    }

    public void setSperrcode(String sperrcode) {
        this.sperrcode = sperrcode;
    }

    public String getSperrcodetext() {
        return sperrcodetext;
    }

    public void setSperrcodetext(String sperrcodetext) {
        this.sperrcodetext = sperrcodetext;
    }

    public String getBegruendung() {
        return begruendung;
    }

    public void setBegruendung(String begruendung) {
        this.begruendung = begruendung;
    }

    public Long getFreie() {
        return freie;
    }

    public void setFreie(Long freie) {
        this.freie = freie;
    }

    public String getHerkunft() {
        return herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    public Boolean getRelevant() {
        return relevant;
    }

    public void setRelevant(Boolean relevant) {
        this.relevant = relevant;
    }

    public String getVf() {
        return vf;
    }

    public void setVf(String vf) {
        this.vf = vf;
    }

    public String getBereichFHI() {
        return bereichFHI;
    }

    public void setBereichFHI(String bereichFHI) {
        this.bereichFHI = bereichFHI;
    }

}
