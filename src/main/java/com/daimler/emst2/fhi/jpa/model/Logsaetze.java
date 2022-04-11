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
 * The persistent class for the LOGSAETZE database table.
 * 
 */
@Entity
@NamedQuery(name="Logsaetze.findAll", query="SELECT l FROM Logsaetze l")
public class Logsaetze implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOG_SEQ")
	private long logSeq;

	private String ausloeser;

	@Column(name="HIST_AUTOR")
	private String histAutor;

	@Temporal(TemporalType.DATE)
	@Column(name="HIST_DATUM")
	private Date histDatum;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="NACHRICHT_ID")
	private String nachrichtId;

	private String text;

	@Column(name="TRANSAKTION_SEQ")
    private Long transaktionSeq;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	@Column(name="VORGANGS_ID")
	private BigDecimal vorgangsId;

	@Temporal(TemporalType.DATE)
	private Date zeitstempel;

	public Logsaetze() {
	}

	public long getLogSeq() {
		return this.logSeq;
	}

	public void setLogSeq(long logSeq) {
		this.logSeq = logSeq;
	}

	public String getAusloeser() {
		return this.ausloeser;
	}

	public void setAusloeser(String ausloeser) {
		this.ausloeser = ausloeser;
	}

	public String getHistAutor() {
		return this.histAutor;
	}

	public void setHistAutor(String histAutor) {
		this.histAutor = histAutor;
	}

	public Date getHistDatum() {
		return this.histDatum;
	}

	public void setHistDatum(Date histDatum) {
		this.histDatum = histDatum;
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

	public String getNachrichtId() {
		return this.nachrichtId;
	}

	public void setNachrichtId(String nachrichtId) {
		this.nachrichtId = nachrichtId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public Long getTransaktionSeq() {
		return this.transaktionSeq;
	}

    public void setTransaktionSeq(Long transaktionSeq) {
		this.transaktionSeq = transaktionSeq;
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

	public BigDecimal getVorgangsId() {
		return this.vorgangsId;
	}

	public void setVorgangsId(BigDecimal vorgangsId) {
		this.vorgangsId = vorgangsId;
	}

	public Date getZeitstempel() {
		return this.zeitstempel;
	}

	public void setZeitstempel(Date zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

}