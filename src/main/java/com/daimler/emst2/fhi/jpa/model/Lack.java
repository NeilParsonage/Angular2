package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_AUFT_LACK database table.
 * 
 */
@Entity
@Table(name = "V_AUFT_LACK")
@NamedQuery(name = "Lack.findAll", query = "SELECT v FROM Lack v")
public class Lack extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "AUF_PNR")
    private String aufPnr;

    @Id
    @Column(name = "AUFT_LACK_PK")
    private String auftLackPk;

    @Column(name = "LACK_LANGTEXT")
    private String lackLangtext;

    private String lackschl;

    private Integer lackschlnr;

    @Column(name = "LACKZ_LANGTEXT")
    private String lackzLangtext;

    private String lackzus;

    public Lack() {
    }

    public String getAufPnr() {
        return this.aufPnr;
    }

    public void setAufPnr(String aufPnr) {
        this.aufPnr = aufPnr;
    }

    public String getAuftLackPk() {
        return this.auftLackPk;
    }

    public void setAuftLackPk(String auftLackPk) {
        this.auftLackPk = auftLackPk;
    }

    public String getLackLangtext() {
        return this.lackLangtext;
    }

    public void setLackLangtext(String lackLangtext) {
        this.lackLangtext = lackLangtext;
    }

    public String getLackschl() {
        return this.lackschl;
    }

    public void setLackschl(String lackschl) {
        this.lackschl = lackschl;
    }

    public Integer getLackschlnr() {
        return this.lackschlnr;
    }

    public void setLackschlnr(Integer lackschlnr) {
        this.lackschlnr = lackschlnr;
    }

    public String getLackzLangtext() {
        return this.lackzLangtext;
    }

    public void setLackzLangtext(String lackzLangtext) {
        this.lackzLangtext = lackzLangtext;
    }

    public String getLackzus() {
        return this.lackzus;
    }

    public void setLackzus(String lackzus) {
        this.lackzus = lackzus;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}