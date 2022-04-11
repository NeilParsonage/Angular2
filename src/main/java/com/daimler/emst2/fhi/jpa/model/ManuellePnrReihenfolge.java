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
 * The persistent class for the MAN_RF database table.
 * 
 */
@Entity
@Table(name="MAN_RF")
@NamedQuery(name="ManRf.findAll", query="SELECT m FROM ManRf m")
public class ManuellePnrReihenfolge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String pnr;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="RF_NR")
	private BigDecimal rfNr;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	public ManuellePnrReihenfolge() {
	}

	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
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

	public BigDecimal getRfNr() {
		return this.rfNr;
	}

	public void setRfNr(BigDecimal rfNr) {
		this.rfNr = rfNr;
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