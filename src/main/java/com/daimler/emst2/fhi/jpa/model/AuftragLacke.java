package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_Lacke")
@NamedQuery(name = "AuftragLacke.findAll", query = "SELECT t FROM AuftragLacke t")
public class AuftragLacke implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LACK_ID")
    private String id;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "LACKSCHL")
    private String lackschl;

    @Column(name = "LACKSCHLNR")
    private Long lackSchlNr;

    @Column(name = "LACK_LANGTEXT")
    private String lackLangText;

    @Column(name = "LACKZ_LANGTEXT")
    private String lackzLangText;

    @Column(name = "LACKZUS")
    private String lackzus;

    @Column(name = "LACK_VERWENDUNG")
    private String lackVerwendung;

    @Column(name = "FHS_REIHENFOLGE")
    private Long fhsReihenfolge;

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

    public String getLackschl() {
        return lackschl;
    }

    public void setLackschl(String lackschl) {
        this.lackschl = lackschl;
    }

    public Long getLackSchlNr() {
        return lackSchlNr;
    }

    public void setLackSchlNr(Long lackSchlNr) {
        this.lackSchlNr = lackSchlNr;
    }

    public String getLackLangText() {
        return lackLangText;
    }

    public void setLackLangText(String lackLangText) {
        this.lackLangText = lackLangText;
    }

    public String getLackzLangText() {
        return lackzLangText;
    }

    public void setLackzLangText(String lackzLangText) {
        this.lackzLangText = lackzLangText;
    }

    public String getLackzus() {
        return lackzus;
    }

    public void setLackzus(String lackzus) {
        this.lackzus = lackzus;
    }

}