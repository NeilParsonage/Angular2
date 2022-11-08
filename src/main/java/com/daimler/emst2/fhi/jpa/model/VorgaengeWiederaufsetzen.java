package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the VORGAENGE_WIEDERAUFSETZEN database table.
 * 
 */
@Entity
@Table(name="VORGAENGE_WIEDERAUFSETZEN")
@NamedQuery(name="VorgaengeWiederaufsetzen.findAll", query="SELECT v FROM VorgaengeWiederaufsetzen v")
public class VorgaengeWiederaufsetzen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VOWI_ID")
	private long vowiId;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Lob
	@Column(name="SQL_SKRIPT")
	private String sqlSkript;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

    @Column(name = "VERSION")
	private BigDecimal version;

	//bi-directional many-to-one association to Vorgaenge
	@ManyToOne
	@JoinColumn(name="VORGANG_ID")
	private Vorgaenge vorgaenge;

	public VorgaengeWiederaufsetzen() {
	}

	public long getVowiId() {
		return this.vowiId;
	}

	public void setVowiId(long vowiId) {
		this.vowiId = vowiId;
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

	public String getSqlSkript() {
		return this.sqlSkript;
	}

	public void setSqlSkript(String sqlSkript) {
		this.sqlSkript = sqlSkript;
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

	public Vorgaenge getVorgaenge() {
		return this.vorgaenge;
	}

	public void setVorgaenge(Vorgaenge vorgaenge) {
		this.vorgaenge = vorgaenge;
	}

}