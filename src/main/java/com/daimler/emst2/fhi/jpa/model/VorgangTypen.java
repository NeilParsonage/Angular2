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
 * The persistent class for the VORGANG_TYPEN database table.
 * 
 */
@Entity
@Table(name="VORGANG_TYPEN")
@NamedQuery(name="VorgangTypen.findAll", query="SELECT v FROM VorgangTypen v")
public class VorgangTypen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VOTY_ID")
	private long votyId;

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

    @Column(name = "VERSION")
	private BigDecimal version;

	private String vorgang;

	@Column(name="VORGANGS_ART")
	private BigDecimal vorgangsArt;

	@Column(name="VORGANGS_RF_BATCH")
	private BigDecimal vorgangsRfBatch;

	//bi-directional many-to-one association to Vorgaenge
	@OneToMany(mappedBy="vorgangTypen")
	private List<Vorgaenge> vorgaenges;

	public VorgangTypen() {
	}

	public long getVotyId() {
		return this.votyId;
	}

	public void setVotyId(long votyId) {
		this.votyId = votyId;
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

	public String getVorgang() {
		return this.vorgang;
	}

	public void setVorgang(String vorgang) {
		this.vorgang = vorgang;
	}

	public BigDecimal getVorgangsArt() {
		return this.vorgangsArt;
	}

	public void setVorgangsArt(BigDecimal vorgangsArt) {
		this.vorgangsArt = vorgangsArt;
	}

	public BigDecimal getVorgangsRfBatch() {
		return this.vorgangsRfBatch;
	}

	public void setVorgangsRfBatch(BigDecimal vorgangsRfBatch) {
		this.vorgangsRfBatch = vorgangsRfBatch;
	}

	public List<Vorgaenge> getVorgaenges() {
		return this.vorgaenges;
	}

	public void setVorgaenges(List<Vorgaenge> vorgaenges) {
		this.vorgaenges = vorgaenges;
	}

	public Vorgaenge addVorgaenge(Vorgaenge vorgaenge) {
		getVorgaenges().add(vorgaenge);
		vorgaenge.setVorgangTypen(this);

		return vorgaenge;
	}

	public Vorgaenge removeVorgaenge(Vorgaenge vorgaenge) {
		getVorgaenges().remove(vorgaenge);
		vorgaenge.setVorgangTypen(null);

		return vorgaenge;
	}

}