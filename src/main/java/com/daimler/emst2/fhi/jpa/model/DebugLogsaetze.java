package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.daimler.emst2.frw.model.BaseAuditEntity;


/**
 * The persistent class for the DEBUG_LOGSAETZE database table.
 * 
 */
@Entity
@Table(name="DEBUG_LOGSAETZE")
@NamedQuery(name="DebugLogsaetze.findAll", query="SELECT d FROM DebugLogsaetze d")
public class DebugLogsaetze extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    // TODO insert Oracle Sequence Stuff
	@Id
	@Column(name="LOG_SEQ")
    @SequenceGenerator(name = "LOGSATZ_SEQ_GENERATOR", sequenceName = "LOGSATZ_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOGSATZ_SEQ_GENERATOR")
	private long logSeq;

	private String ausloeser;

	@Column(name="HIST_AUTOR")
	private String histAutor;

	@Temporal(TemporalType.DATE)
	@Column(name="HIST_DATUM")
	private Date histDatum;

	@Column(name="NACHRICHT_ID")
	private String nachrichtId;

	private String text;

	@Column(name="TRANSAKTION_SEQ")
    private Long transaktionSeq;

	@Column(name="VORGANGS_ID")
	private BigDecimal vorgangsId;

	@Temporal(TemporalType.DATE)
	private Date zeitstempel;

	public DebugLogsaetze() {
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

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}