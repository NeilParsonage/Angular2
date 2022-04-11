package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the W73RIMPO database table.
 * 
 */
@Entity
@NamedQuery(name="W73rimpo.findAll", query="SELECT w FROM W73rimpo w")
public class W73rimpo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IMPO_PK")
	private long impoPk;

	@Column(name="IMPO_EINGANGSIMPULS_ID")
	private String impoEingangsimpulsId;

	@Column(name="IMPO_ID")
	private BigDecimal impoId;

	@Column(name="IMPO_PNR")
	private String impoPnr;

	@Column(name="IMPO_QUELLE_SYSTEM")
	private String impoQuelleSystem;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPO_RUECKMELDE_ZEITPUNKT")
	private Date impoRueckmeldeZeitpunkt;

	@Column(name="IMPO_STATUS")
	private String impoStatus;

	@Column(name="IMPO_SYSTEM_ID")
	private String impoSystemId;

	@Column(name="IMPO_VARIABLE_DATEN")
	private String impoVariableDaten;

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

	public W73rimpo() {
	}

	public long getImpoPk() {
		return this.impoPk;
	}

	public void setImpoPk(long impoPk) {
		this.impoPk = impoPk;
	}

	public String getImpoEingangsimpulsId() {
		return this.impoEingangsimpulsId;
	}

	public void setImpoEingangsimpulsId(String impoEingangsimpulsId) {
		this.impoEingangsimpulsId = impoEingangsimpulsId;
	}

	public BigDecimal getImpoId() {
		return this.impoId;
	}

	public void setImpoId(BigDecimal impoId) {
		this.impoId = impoId;
	}

	public String getImpoPnr() {
		return this.impoPnr;
	}

	public void setImpoPnr(String impoPnr) {
		this.impoPnr = impoPnr;
	}

	public String getImpoQuelleSystem() {
		return this.impoQuelleSystem;
	}

	public void setImpoQuelleSystem(String impoQuelleSystem) {
		this.impoQuelleSystem = impoQuelleSystem;
	}

	public Date getImpoRueckmeldeZeitpunkt() {
		return this.impoRueckmeldeZeitpunkt;
	}

	public void setImpoRueckmeldeZeitpunkt(Date impoRueckmeldeZeitpunkt) {
		this.impoRueckmeldeZeitpunkt = impoRueckmeldeZeitpunkt;
	}

	public String getImpoStatus() {
		return this.impoStatus;
	}

	public void setImpoStatus(String impoStatus) {
		this.impoStatus = impoStatus;
	}

	public String getImpoSystemId() {
		return this.impoSystemId;
	}

	public void setImpoSystemId(String impoSystemId) {
		this.impoSystemId = impoSystemId;
	}

	public String getImpoVariableDaten() {
		return this.impoVariableDaten;
	}

	public void setImpoVariableDaten(String impoVariableDaten) {
		this.impoVariableDaten = impoVariableDaten;
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

}