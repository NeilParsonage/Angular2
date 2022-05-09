package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the SYSTEMGRUPPENZUO database table.
 * 
 */
@Entity
@NamedQuery(name="Systemgruppenzuo.findAll", query="SELECT s FROM Systemgruppenzuo s")
public class Systemgruppenzuo extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SGZU_ID")
	private long sgzuId;

	//bi-directional many-to-one association to Systemgruppen
	@ManyToOne
	@JoinColumn(name="GRUPPE_ID")
	private Systemgruppen systemgruppen;

	//bi-directional many-to-one association to Systemwerte
	@ManyToOne
	@JoinColumn(name="WERT_ID")
	private Systemwerte systemwerte;

	public Systemgruppenzuo() {
	}

	public long getSgzuId() {
		return this.sgzuId;
	}

	public void setSgzuId(long sgzuId) {
		this.sgzuId = sgzuId;
	}

	public Systemgruppen getSystemgruppen() {
		return this.systemgruppen;
	}

	public void setSystemgruppen(Systemgruppen systemgruppen) {
		this.systemgruppen = systemgruppen;
	}

	public Systemwerte getSystemwerte() {
		return this.systemwerte;
	}

	public void setSystemwerte(Systemwerte systemwerte) {
		this.systemwerte = systemwerte;
	}

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return sgzuId;
    }

}