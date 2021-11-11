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


}