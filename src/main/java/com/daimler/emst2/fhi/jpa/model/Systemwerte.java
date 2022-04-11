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
 * The persistent class for the SYSTEMWERTE database table.
 * 
 */
@Entity
@NamedQuery(name="Systemwerte.findAll", query="SELECT s FROM Systemwerte s")
public class Systemwerte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WERT_ID")
	private long wertId;

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

	@Column(name="KNZ_SYSTEM_01")
	private BigDecimal knzSystem01;

	private String kommentar;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	@Column(name="WERT_CHAR")
	private String wertChar;

	@Temporal(TemporalType.DATE)
	@Column(name="WERT_DATUM")
	private Date wertDatum;

	@Column(name="WERT_DOUBLE")
	private BigDecimal wertDouble;

	@Column(name="WERT_NAME")
	private String wertName;

	@Column(name="WERT_NUM")
    private Long wertNum;

	//bi-directional many-to-one association to Systemgruppenzuo
	@OneToMany(mappedBy="systemwerte")
	private List<Systemgruppenzuo> systemgruppenzuos;

	public Systemwerte() {
	}

	public long getWertId() {
		return this.wertId;
	}

	public void setWertId(long wertId) {
		this.wertId = wertId;
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

	public BigDecimal getKnzSystem01() {
		return this.knzSystem01;
	}

	public void setKnzSystem01(BigDecimal knzSystem01) {
		this.knzSystem01 = knzSystem01;
	}

	public String getKommentar() {
		return this.kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
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

	public String getWertChar() {
		return this.wertChar;
	}

	public void setWertChar(String wertChar) {
		this.wertChar = wertChar;
	}

	public Date getWertDatum() {
		return this.wertDatum;
	}

	public void setWertDatum(Date wertDatum) {
		this.wertDatum = wertDatum;
	}

	public BigDecimal getWertDouble() {
		return this.wertDouble;
	}

	public void setWertDouble(BigDecimal wertDouble) {
		this.wertDouble = wertDouble;
	}

	public String getWertName() {
		return this.wertName;
	}

	public void setWertName(String wertName) {
		this.wertName = wertName;
	}

    public Long getWertNum() {
		return this.wertNum;
	}

    public void setWertNum(Long wertNum) {
		this.wertNum = wertNum;
	}

	public List<Systemgruppenzuo> getSystemgruppenzuos() {
		return this.systemgruppenzuos;
	}

	public void setSystemgruppenzuos(List<Systemgruppenzuo> systemgruppenzuos) {
		this.systemgruppenzuos = systemgruppenzuos;
	}

	public Systemgruppenzuo addSystemgruppenzuo(Systemgruppenzuo systemgruppenzuo) {
		getSystemgruppenzuos().add(systemgruppenzuo);
		systemgruppenzuo.setSystemwerte(this);

		return systemgruppenzuo;
	}

	public Systemgruppenzuo removeSystemgruppenzuo(Systemgruppenzuo systemgruppenzuo) {
		getSystemgruppenzuos().remove(systemgruppenzuo);
		systemgruppenzuo.setSystemwerte(null);

		return systemgruppenzuo;
	}

}