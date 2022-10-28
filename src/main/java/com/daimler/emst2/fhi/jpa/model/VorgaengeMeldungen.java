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
 * The persistent class for the VORGAENGE_MELDUNGEN database table.
 * 
 */
@Entity
@Table(name="VORGAENGE_MELDUNGEN")
@NamedQuery(name="VorgaengeMeldungen.findAll", query="SELECT v FROM VorgaengeMeldungen v")
public class VorgaengeMeldungen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VOME_ID")
	private long vomeId;

	@Column(name="IGNORIERT_01")
	private BigDecimal ignoriert01;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	private String meldung;

	@Column(name="MELDUNG_NR")
	private BigDecimal meldungNr;

	@Column(name="MELDUNG_TYP")
	private String meldungTyp;

	private String paramstring;

	@Column(name="TUEB_PROGNAME")
	private String tuebProgname;

	@Column(name="TUEB_SYSTEM")
	private String tuebSystem;

	@Column(name="TUEB_TNAME")
	private String tuebTname;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

    @Column(name = "VERSION")
	private BigDecimal version;

	//bi-directional many-to-one association to Vorgaenge
	@ManyToOne
	@JoinColumn(name="VORGANGS_ID")
	private Vorgaenge vorgaenge;

	public VorgaengeMeldungen() {
	}

	public long getVomeId() {
		return this.vomeId;
	}

	public void setVomeId(long vomeId) {
		this.vomeId = vomeId;
	}

	public BigDecimal getIgnoriert01() {
		return this.ignoriert01;
	}

	public void setIgnoriert01(BigDecimal ignoriert01) {
		this.ignoriert01 = ignoriert01;
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

	public String getMeldung() {
		return this.meldung;
	}

	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}

	public BigDecimal getMeldungNr() {
		return this.meldungNr;
	}

	public void setMeldungNr(BigDecimal meldungNr) {
		this.meldungNr = meldungNr;
	}

	public String getMeldungTyp() {
		return this.meldungTyp;
	}

	public void setMeldungTyp(String meldungTyp) {
		this.meldungTyp = meldungTyp;
	}

	public String getParamstring() {
		return this.paramstring;
	}

	public void setParamstring(String paramstring) {
		this.paramstring = paramstring;
	}

	public String getTuebProgname() {
		return this.tuebProgname;
	}

	public void setTuebProgname(String tuebProgname) {
		this.tuebProgname = tuebProgname;
	}

	public String getTuebSystem() {
		return this.tuebSystem;
	}

	public void setTuebSystem(String tuebSystem) {
		this.tuebSystem = tuebSystem;
	}

	public String getTuebTname() {
		return this.tuebTname;
	}

	public void setTuebTname(String tuebTname) {
		this.tuebTname = tuebTname;
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