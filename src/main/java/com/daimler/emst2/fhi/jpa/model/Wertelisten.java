package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.daimler.emst2.frw.model.BaseAuditEntity;


/**
 * The persistent class for the WERTELISTEN database table.
 * 
 */
@Entity
@NamedQuery(name="Wertelisten.findAll", query="SELECT w FROM Wertelisten w")
public class Wertelisten extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WERT_ID")
	private long wertId;

	@Column(name="HIST_AUTOR")
	private String histAutor;

	@Temporal(TemporalType.DATE)
	@Column(name="HIST_DATUM")
	private Date histDatum;

	private String liste;

	@Column(name="STATUS_BEM")
	private String statusBem;

	@Column(name="TUEB_PROGNAME")
	private String tuebProgname;

	@Column(name="TUEB_SYSTEM")
	private String tuebSystem;

	@Column(name="TUEB_TNAME")
	private String tuebTname;

	private String wert;

	public Wertelisten() {
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

	public String getListe() {
		return this.liste;
	}

	public void setListe(String liste) {
		this.liste = liste;
	}

	public String getStatusBem() {
		return this.statusBem;
	}

	public void setStatusBem(String statusBem) {
		this.statusBem = statusBem;
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


	public String getWert() {
		return this.wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

    @Override
    public Long getId() {
        return getWertId();
    }

}