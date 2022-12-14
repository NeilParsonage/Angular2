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
 * The persistent class for the SYSTEMWERTE database table.
 * 
 */
@Entity
@Table(name = "Systemwerte")
@NamedQuery(name = "Systemwert.findAll", query = "SELECT s FROM Systemwert s")
public class Systemwert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WERT_ID")
	private long wertId;

	@Column(name="HIST_AUTOR")
	private String histAutor;

	@Temporal(TemporalType.DATE)
	@Column(name="HIST_DATUM")
	private Date histDatum;

	@Column(name="KNZ_SYSTEM_01")
	private BigDecimal knzSystem01;

	private String kommentar;

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
    @OneToMany(mappedBy = "systemwert")
	private List<Systemgruppenzuo> systemgruppenzuos;

	public Systemwert() {
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
		systemgruppenzuo.setSystemwert(this);

		return systemgruppenzuo;
	}

	public Systemgruppenzuo removeSystemgruppenzuo(Systemgruppenzuo systemgruppenzuo) {
		getSystemgruppenzuos().remove(systemgruppenzuo);
		systemgruppenzuo.setSystemwert(null);

		return systemgruppenzuo;
	}

}