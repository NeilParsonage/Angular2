package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_AUFTRAG_TERMINE_TAB")
@NamedQuery(name = "AuftragTermineDetails.findAll", query = "SELECT t FROM AuftragTermineDetails t")
public class AuftragTermineDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "PNR")
    private String pnr;


    @Column(name = "REIHENFOLGE")
    private Long reihenfolge;

    @Column(name = "GEWERK")
    private String gewerk;

    @Column(name = "BEGINN_TERMIN")
    private Date beginnTermin;

    @Column(name = "BEGINN_TYP")
    private String beginnTyp;

    @Column(name = "IST_SEQUENZ_TERMIN")
    private Date istSequenzTermin;

    @Column(name = "IST_SEQUENZ_TYP")
    private String istSequenzTyp;

    @Column(name = "TEILSENDUNG_TERMIN")
    private Date teilsendungTermin;

    @Column(name = "TEILSENDUNG_TYP")
    private String teilsendungTyp;

    @Column(name = "STORNO_TERMIN")
    private Date stornoTermin;

    public String getPnr() {
        return pnr;
    }

    public Long getReihenfolge() {
        return reihenfolge;
    }

    public void setReihenfolge(Long reihenfolge) {
        this.reihenfolge = reihenfolge;
    }

    public String getGewerk() {
        return gewerk;
    }

    public void setGewerk(String gewerk) {
        this.gewerk = gewerk;
    }

    public Date getBeginnTermin() {
        return beginnTermin;
    }

    public void setBeginnTermin(Date beginnTermin) {
        this.beginnTermin = beginnTermin;
    }

    public String getBeginnTyp() {
        return beginnTyp;
    }

    public void setBeginnTyp(String beginnTyp) {
        this.beginnTyp = beginnTyp;
    }

    public Date getIstSequenzTermin() {
        return istSequenzTermin;
    }

    public void setIstSequenzTermin(Date istSequenzTermin) {
        this.istSequenzTermin = istSequenzTermin;
    }

    public String getIstSequenzTyp() {
        return istSequenzTyp;
    }

    public void setIstSequenzTyp(String istSequenzTyp) {
        this.istSequenzTyp = istSequenzTyp;
    }

    public Date getTeilsendungTermin() {
        return teilsendungTermin;
    }

    public void setTeilsendungTermin(Date teilsendungTermin) {
        this.teilsendungTermin = teilsendungTermin;
    }

    public String getTeilsendungTyp() {
        return teilsendungTyp;
    }

    public void setTeilsendungTyp(String teilsendungTyp) {
        this.teilsendungTyp = teilsendungTyp;
    }

    public Date getStornoTermin() {
        return stornoTermin;
    }

    public void setStornoTermin(Date stornoTermin) {
        this.stornoTermin = stornoTermin;
    }

}