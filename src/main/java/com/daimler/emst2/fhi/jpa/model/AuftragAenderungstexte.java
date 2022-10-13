package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_AENDERUNGSTEXTE")
@NamedQuery(name = "AuftragAenderungstexte.findAll", query = "SELECT t FROM AuftragAenderungstexte t")
public class AuftragAenderungstexte implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "REIHENFOLGE")
    private Long reihenfolge;

    @Column(name = "QUELLE")
    private String quelle;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "LFDNR")
    private Long lfdnr;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Long getReihenfolge() {
        return reihenfolge;
    }

    public void setReihenfolge(Long reihenfolge) {
        this.reihenfolge = reihenfolge;
    }

    public String getQuelle() {
        return quelle;
    }

    public void setQuelle(String quelle) {
        this.quelle = quelle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLfdnr() {
        return lfdnr;
    }

    public void setLfdnr(Long lfdnr) {
        this.lfdnr = lfdnr;
    }

}