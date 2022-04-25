package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;


/**
 * The persistent class for the AKTIVE_CODES_HIST database table.
 * 
 */
@Entity
@Table(name="AKTIVE_CODES_HIST")
@NamedQuery(name="AktiveCodesHist.findAll", query="SELECT a FROM AktiveCodesHist a")
public class AktiveCodesHist extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COHI_ID")
	private long cohiId;

	@Column(name="AUF_PNR")
	private String aufPnr;

	private String codes;

	private String typ;

	private String typpnr;

	public AktiveCodesHist() {
	}

	public long getCohiId() {
		return this.cohiId;
	}

	public void setCohiId(long cohiId) {
		this.cohiId = cohiId;
	}

	public String getAufPnr() {
		return this.aufPnr;
	}

	public void setAufPnr(String aufPnr) {
		this.aufPnr = aufPnr;
	}

	public String getCodes() {
		return this.codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getTyppnr() {
		return this.typpnr;
	}

	public void setTyppnr(String typpnr) {
		this.typpnr = typpnr;
	}

    @Override
    public Long getId() {
        return cohiId;
    }

}