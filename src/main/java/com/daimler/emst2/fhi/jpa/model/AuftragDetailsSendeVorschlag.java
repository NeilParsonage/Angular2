package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the V_AUFTRAG_SENDEVORSCHLAEGE database table.
 * 
 */
@Entity
@Table(name="V_AUFTRAG_SENDEVORSCHLAEGE")
@NamedQuery(name = "AuftragDetailsSendeVorschlag.findAll", query = "SELECT v FROM AuftragDetailsSendeVorschlag v")
public class AuftragDetailsSendeVorschlag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="NULLINIEN_RF")
	private BigDecimal nullinienRf;

	private String pnr;

	@Column(name="SOLLABS_FHI")
    private Integer sollabsFhi;

	@Column(name="SOLLABS_LMT")
    private Integer sollabsLmt;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

    // bi-directional many-to-one association to Auftrag
    @ManyToOne
    @JoinColumn(name = "PNR")
    private Auftraege auftrag;

	public AuftragDetailsSendeVorschlag() {
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

	public BigDecimal getNullinienRf() {
		return this.nullinienRf;
	}

	public void setNullinienRf(BigDecimal nullinienRf) {
		this.nullinienRf = nullinienRf;
	}

	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

    public Integer getSollabsFhi() {
		return this.sollabsFhi;
	}

    public void setSollabsFhi(Integer sollabsFhi) {
		this.sollabsFhi = sollabsFhi;
	}

    public Integer getSollabsLmt() {
		return this.sollabsLmt;
	}

    public void setSollabsLmt(Integer sollabsLmt) {
		this.sollabsLmt = sollabsLmt;
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

    public Auftraege getAuftrag() {
        return auftrag;
    }

    public void setAuftrag(Auftraege auftrag) {
        this.auftrag = auftrag;
    }

}