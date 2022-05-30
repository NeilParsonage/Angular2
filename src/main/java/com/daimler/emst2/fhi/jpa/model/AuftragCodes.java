package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "V_E2_CODES")
public class AuftragCodes implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODES_ID")
    private String id;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "LANGTEXT")
    private String text;

    @Column(name = "CODE")
    private String code;

    @Column(name = "BEREICH")
    private String bereich;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBereich() {
        return bereich;
    }

    public void setBereich(String bereich) {
        bereich = bereich;
    }

    public Boolean getRelevant() {
        return relevant;
    }

    public void setRelevant(Boolean relevant) {
        this.relevant = relevant;
    }

}