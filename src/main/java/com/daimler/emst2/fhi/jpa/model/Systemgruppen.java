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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the SYSTEMGRUPPEN database table.
 * 
 */
@Entity
@NamedQuery(name="Systemgruppen.findAll", query="SELECT s FROM Systemgruppen s")
public class Systemgruppen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GRUPPE_ID")
	private long gruppeId;

	@Column(name="GRUPPE_NAME")
	private String gruppeName;

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

	//bi-directional many-to-one association to Systemgruppenzuo
	@OneToMany(mappedBy="systemgruppen")
	private List<Systemgruppenzuo> systemgruppenzuos;

	public Systemgruppen() {
	}

	public long getGruppeId() {
		return this.gruppeId;
	}

	public void setGruppeId(long gruppeId) {
		this.gruppeId = gruppeId;
	}

	public String getGruppeName() {
		return this.gruppeName;
	}

	public void setGruppeName(String gruppeName) {
		this.gruppeName = gruppeName;
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

	public List<Systemgruppenzuo> getSystemgruppenzuos() {
		return this.systemgruppenzuos;
	}

	public void setSystemgruppenzuos(List<Systemgruppenzuo> systemgruppenzuos) {
		this.systemgruppenzuos = systemgruppenzuos;
	}

	public Systemgruppenzuo addSystemgruppenzuo(Systemgruppenzuo systemgruppenzuo) {
		getSystemgruppenzuos().add(systemgruppenzuo);
		systemgruppenzuo.setSystemgruppen(this);

		return systemgruppenzuo;
	}

	public Systemgruppenzuo removeSystemgruppenzuo(Systemgruppenzuo systemgruppenzuo) {
		getSystemgruppenzuos().remove(systemgruppenzuo);
		systemgruppenzuo.setSystemgruppen(null);

		return systemgruppenzuo;
	}

}