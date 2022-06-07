package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "V_E2_KRITERIEN")
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
    private String kurztext;

    @Column(name = "BEREICH")
    private String bereich;

    @Column(name = "SOLLABS_FENSTER")
    private Long dichte;

    @Column(name = "SOLLABS_INTERVALL")
    private Long intervall;

    @Column(name = "RELEVANT")
    private Boolean relevant;

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
        return kurztext;
    }

    public void setKurztext(String kurztext) {
        this.kurztext = kurztext;
    }

    public String getBereich() {
        return bereich;
    }

    public void setBereich(String bereich) {
        this.bereich = bereich;
    }

    public Long getDichte() {
        return dichte;
    }

    public void setDichte(Long dichte) {
        this.dichte = dichte;
    }

    public Long getIntervall() {
        return intervall;
    }

    public void setIntervall(Long intervall) {
        this.intervall = intervall;
    }

    public Boolean getRelevant() {
        return relevant;
    }

    public void setRelevant(Boolean relevant) {
        this.relevant = relevant;
    }


}