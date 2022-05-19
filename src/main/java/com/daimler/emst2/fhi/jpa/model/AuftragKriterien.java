package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "V_E2_KRITS")
public class AuftragKriterien implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KRITERIEN_ID")
    private String id;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "LANGTEXT")
    private String text;

    @Column(name = "KRITERIUM")
    private String kriterium;

    @Column(name = "KURZTEXT")
    private String Kurztext;

    @Column(name = "BEREICH")
    private String Bereich;

    @Column(name = "SOLLABS_FENSTER")
    private Long Dichte;

    @Column(name = "SOLLABS_INTERVALL")
    private Long Intervall;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKriterium() {
        return kriterium;
    }

    public void setKriterium(String kriterium) {
        this.kriterium = kriterium;
    }

    public String getKurztext() {
        return Kurztext;
    }

    public void setKurztext(String kurztext) {
        Kurztext = kurztext;
    }

    public String getBereich() {
        return Bereich;
    }

    public void setBereich(String bereich) {
        Bereich = bereich;
    }

    public Long getDichte() {
        return Dichte;
    }

    public void setDichte(Long dichte) {
        Dichte = dichte;
    }

    public Long getIntervall() {
        return Intervall;
    }

    public void setIntervall(Long intervall) {
        Intervall = intervall;
    }

}