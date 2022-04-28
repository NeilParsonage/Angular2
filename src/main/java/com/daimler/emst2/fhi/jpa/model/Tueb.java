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
 * The persistent class for the TUEB database table.
 * 
 */
@Entity
@NamedQuery(name="Tueb.findAll", query="SELECT t FROM Tueb t")
public class Tueb extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="HIST_AUTOR")
	private String histAutor;

	@Temporal(TemporalType.DATE)
	@Column(name="HIST_DATUM")
	private Date histDatum;

	private String progname;

    @Column(name = "SYSTEM")
	private String system;

	private String tlae;

	private String tname;

	private String tsprache;

	private String ttext;


	public Tueb() {
	}

	@Override
    public Long getId() {
        return id;
	}

    public void setId(Long id) {
        this.id = id;
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

	public String getProgname() {
		return this.progname;
	}

	public void setProgname(String progname) {
		this.progname = progname;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getTlae() {
		return this.tlae;
	}

	public void setTlae(String tlae) {
		this.tlae = tlae;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsprache() {
		return this.tsprache;
	}

	public void setTsprache(String tsprache) {
		this.tsprache = tsprache;
	}

	public String getTtext() {
		return this.ttext;
	}

	public void setTtext(String ttext) {
		this.ttext = ttext;
	}

}