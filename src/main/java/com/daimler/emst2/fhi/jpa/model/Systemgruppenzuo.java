package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the SYSTEMGRUPPENZUO database table.
 * 
 */
@Entity
@NamedQuery(name="Systemgruppenzuo.findAll", query="SELECT s FROM Systemgruppenzuo s")
public class Systemgruppenzuo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SGZU_ID")
	private long sgzuId;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	//bi-directional many-to-one association to Systemgruppen
	@ManyToOne
	@JoinColumn(name="GRUPPE_ID")
	private Systemgruppen systemgruppen;

	//bi-directional many-to-one association to Systemwerte
	@ManyToOne
	@JoinColumn(name="WERT_ID")
	private Systemwerte systemwerte;

	public Systemgruppenzuo() {
	}

	public long getSgzuId() {
		return this.sgzuId;
	}

	public void setSgzuId(long sgzuId) {
		this.sgzuId = sgzuId;
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

	public Systemgruppen getSystemgruppen() {
		return this.systemgruppen;
	}

	public void setSystemgruppen(Systemgruppen systemgruppen) {
		this.systemgruppen = systemgruppen;
	}

	public Systemwerte getSystemwerte() {
		return this.systemwerte;
	}

	public void setSystemwerte(Systemwerte systemwerte) {
		this.systemwerte = systemwerte;
	}

}