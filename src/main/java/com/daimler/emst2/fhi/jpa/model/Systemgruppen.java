package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the SYSTEMGRUPPEN database table.
 * 
 */
@Entity
@NamedQuery(name="Systemgruppen.findAll", query="SELECT s FROM Systemgruppen s")
public class Systemgruppen extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GRUPPE_ID")
	private long gruppeId;

	@Column(name="GRUPPE_NAME")
	private String gruppeName;

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

    @Override
    public Long getId() {
        return gruppeId;
    }

}