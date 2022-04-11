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
import javax.persistence.Transient;


/**
 * The persistent class for the V_AUFT_HIST database table.
 * 
 */
@Entity
@Table(name="V_AUFT_HIST")
@NamedQuery(name = "AuftragHistorie.findAll", query = "SELECT v FROM AuftragHistorie v")
public class AuftragHistorie implements Serializable {
	private static final long serialVersionUID = 1L;

    @Transient
    public static final int MAX_LEN_MELDUNG = 40;

    @Id
	@Column(name="AUF_HIST_ID")
	private BigDecimal aufHistId;

	@Column(name="AUF_PNR")
	private String aufPnr;

	@Column(name="AUFH_ID")
	private BigDecimal aufhId;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	private String melder;

	private String meldkenn;

	private String meldung;

	@Temporal(TemporalType.DATE)
	private Date sendetermin;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	@Temporal(TemporalType.DATE)
	private Date zeit;

	public AuftragHistorie() {
	}

	public BigDecimal getAufHistId() {
		return this.aufHistId;
	}

	public void setAufHistId(BigDecimal aufHistId) {
		this.aufHistId = aufHistId;
	}

	public String getAufPnr() {
		return this.aufPnr;
	}

	public void setAufPnr(String aufPnr) {
		this.aufPnr = aufPnr;
	}

	public BigDecimal getAufhId() {
		return this.aufhId;
	}

	public void setAufhId(BigDecimal aufhId) {
		this.aufhId = aufhId;
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

	public String getMelder() {
		return this.melder;
	}

	public void setMelder(String melder) {
		this.melder = melder;
	}

	public String getMeldkenn() {
		return this.meldkenn;
	}

	public void setMeldkenn(String meldkenn) {
		this.meldkenn = meldkenn;
	}

	public String getMeldung() {
		return this.meldung;
	}

	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}

	public Date getSendetermin() {
		return this.sendetermin;
	}

	public void setSendetermin(Date sendetermin) {
		this.sendetermin = sendetermin;
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

	public Date getZeit() {
		return this.zeit;
	}

	public void setZeit(Date zeit) {
		this.zeit = zeit;
	}

}