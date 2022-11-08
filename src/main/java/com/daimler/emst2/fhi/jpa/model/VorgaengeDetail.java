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
 * The persistent class for the VORGAENGE_DETAILS database table.
 * 
 */
@Entity
@Table(name="VORGAENGE_DETAILS")
@NamedQuery(name="VorgaengeDetail.findAll", query="SELECT v FROM VorgaengeDetail v")
public class VorgaengeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VORGANG_DETAIL_ID")
	private long vorgangDetailId;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

    //	@Column(name="\"PARAMETER\"")
    //	private Object parameter;
    //
    //	@Column(name="PARAMETER_ERZEUGE_DETAILS")
    //	private Object parameterErzeugeDetails;

	@Column(name="PARTNER_ID")
	private BigDecimal partnerId;

	@Column(name="PROG_GRUPPE_ID")
	private BigDecimal progGruppeId;

	private String programm;

	@Temporal(TemporalType.DATE)
	@Column(name="PROGRAMM_AUSGEFUEHRT_UM")
	private Date programmAusgefuehrtUm;

	@Column(name="SCHRITT_NR")
	private BigDecimal schrittNr;

	private String status;

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

	public VorgaengeDetail() {
	}

	public long getVorgangDetailId() {
		return this.vorgangDetailId;
	}

	public void setVorgangDetailId(long vorgangDetailId) {
		this.vorgangDetailId = vorgangDetailId;
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

    //	public Object getParameter() {
    //		return this.parameter;
    //	}
    //
    //	public void setParameter(Object parameter) {
    //		this.parameter = parameter;
    //	}
    //
    //	public Object getParameterErzeugeDetails() {
    //		return this.parameterErzeugeDetails;
    //	}
    //
    //	public void setParameterErzeugeDetails(Object parameterErzeugeDetails) {
    //		this.parameterErzeugeDetails = parameterErzeugeDetails;
    //	}

	public BigDecimal getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(BigDecimal partnerId) {
		this.partnerId = partnerId;
	}

	public BigDecimal getProgGruppeId() {
		return this.progGruppeId;
	}

	public void setProgGruppeId(BigDecimal progGruppeId) {
		this.progGruppeId = progGruppeId;
	}

	public String getProgramm() {
		return this.programm;
	}

	public void setProgramm(String programm) {
		this.programm = programm;
	}

	public Date getProgrammAusgefuehrtUm() {
		return this.programmAusgefuehrtUm;
	}

	public void setProgrammAusgefuehrtUm(Date programmAusgefuehrtUm) {
		this.programmAusgefuehrtUm = programmAusgefuehrtUm;
	}

	public BigDecimal getSchrittNr() {
		return this.schrittNr;
	}

	public void setSchrittNr(BigDecimal schrittNr) {
		this.schrittNr = schrittNr;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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