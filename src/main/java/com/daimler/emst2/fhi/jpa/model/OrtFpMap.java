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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ORT_FP_MAP database table.
 * 
 */
@Entity
@Table(name="ORT_FP_MAP")
@NamedQuery(name="OrtFpMap.findAll", query="SELECT o FROM OrtFpMap o")
public class OrtFpMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORT_FP_MAP_ID")
	private long ortFpMapId;

	@Column(name="AUFTRAG_ORTS_TYP")
	private String auftragOrtsTyp;

	private String fp;

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

	//bi-directional many-to-one association to OrtReihenfolge
	@ManyToOne
	@JoinColumn(name="ORT")
	private OrtReihenfolge ortReihenfolge;

	public OrtFpMap() {
	}

	public long getOrtFpMapId() {
		return this.ortFpMapId;
	}

	public void setOrtFpMapId(long ortFpMapId) {
		this.ortFpMapId = ortFpMapId;
	}

	public String getAuftragOrtsTyp() {
		return this.auftragOrtsTyp;
	}

	public void setAuftragOrtsTyp(String auftragOrtsTyp) {
		this.auftragOrtsTyp = auftragOrtsTyp;
	}

	public String getFp() {
		return this.fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
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

	public OrtReihenfolge getOrtReihenfolge() {
		return this.ortReihenfolge;
	}

	public void setOrtReihenfolge(OrtReihenfolge ortReihenfolge) {
		this.ortReihenfolge = ortReihenfolge;
	}

}