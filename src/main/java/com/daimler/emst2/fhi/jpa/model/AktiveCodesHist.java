package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the AKTIVE_CODES_HIST database table.
 * 
 */
@Entity
@Table(name="AKTIVE_CODES_HIST")
@NamedQuery(name="AktiveCodesHist.findAll", query="SELECT a FROM AktiveCodesHist a")
public class AktiveCodesHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COHI_ID")
	private long cohiId;

	@Column(name="AUF_PNR")
	private String aufPnr;

	private String codes;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	private String typ;

	private String typpnr;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

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

	public Date getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	public String getInsUser() {
		return this.insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
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

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}