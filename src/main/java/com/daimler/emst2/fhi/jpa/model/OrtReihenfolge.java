package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ORT_REIHENFOLGE database table.
 * 
 */
@Entity
@Table(name="ORT_REIHENFOLGE")
@NamedQuery(name="OrtReihenfolge.findAll", query="SELECT o FROM OrtReihenfolge o")
public class OrtReihenfolge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ort;

	@Column(name="GRUPPE_SENDUNG_ORT")
	private BigDecimal gruppeSendungOrt;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="ORT_RF_FABRIK")
	private BigDecimal ortRfFabrik;

	@Column(name="REPORT_ORT_GRUPPE")
	private String reportOrtGruppe;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	@Column(name="WEGEZEIT_BEI_VORSENDUNG")
	private BigDecimal wegezeitBeiVorsendung;

	//bi-directional many-to-one association to OrtCheck
	@OneToMany(mappedBy="ortReihenfolge")
	private List<OrtCheck> ortChecks;

	//bi-directional many-to-one association to OrtFpMap
	@OneToMany(mappedBy="ortReihenfolge")
	private List<OrtFpMap> ortFpMaps;

	public OrtReihenfolge() {
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public BigDecimal getGruppeSendungOrt() {
		return this.gruppeSendungOrt;
	}

	public void setGruppeSendungOrt(BigDecimal gruppeSendungOrt) {
		this.gruppeSendungOrt = gruppeSendungOrt;
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

	public BigDecimal getOrtRfFabrik() {
		return this.ortRfFabrik;
	}

	public void setOrtRfFabrik(BigDecimal ortRfFabrik) {
		this.ortRfFabrik = ortRfFabrik;
	}

	public String getReportOrtGruppe() {
		return this.reportOrtGruppe;
	}

	public void setReportOrtGruppe(String reportOrtGruppe) {
		this.reportOrtGruppe = reportOrtGruppe;
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

	public BigDecimal getWegezeitBeiVorsendung() {
		return this.wegezeitBeiVorsendung;
	}

	public void setWegezeitBeiVorsendung(BigDecimal wegezeitBeiVorsendung) {
		this.wegezeitBeiVorsendung = wegezeitBeiVorsendung;
	}

	public List<OrtCheck> getOrtChecks() {
		return this.ortChecks;
	}

	public void setOrtChecks(List<OrtCheck> ortChecks) {
		this.ortChecks = ortChecks;
	}

	public OrtCheck addOrtCheck(OrtCheck ortCheck) {
		getOrtChecks().add(ortCheck);
		ortCheck.setOrtReihenfolge(this);

		return ortCheck;
	}

	public OrtCheck removeOrtCheck(OrtCheck ortCheck) {
		getOrtChecks().remove(ortCheck);
		ortCheck.setOrtReihenfolge(null);

		return ortCheck;
	}

	public List<OrtFpMap> getOrtFpMaps() {
		return this.ortFpMaps;
	}

	public void setOrtFpMaps(List<OrtFpMap> ortFpMaps) {
		this.ortFpMaps = ortFpMaps;
	}

	public OrtFpMap addOrtFpMap(OrtFpMap ortFpMap) {
		getOrtFpMaps().add(ortFpMap);
		ortFpMap.setOrtReihenfolge(this);

		return ortFpMap;
	}

	public OrtFpMap removeOrtFpMap(OrtFpMap ortFpMap) {
		getOrtFpMaps().remove(ortFpMap);
		ortFpMap.setOrtReihenfolge(null);

		return ortFpMap;
	}

}