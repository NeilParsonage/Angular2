package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the ORT_FP_MAP database table.
 * 
 */
@Entity
@Table(name="ORT_FP_MAP")
@NamedQuery(name="OrtFpMap.findAll", query="SELECT o FROM OrtFpMap o")
public class OrtFpMap extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORT_FP_MAP_ID")
	private long ortFpMapId;

	@Column(name="AUFTRAG_ORTS_TYP")
	private String auftragOrtsTyp;

	private String fp;

	//bi-directional many-to-one association to OrtReihenfolge
	@ManyToOne
	@JoinColumn(name="ORT")
	private OrtReihenfolge ortReihenfolge;

	public OrtFpMap() {
	}

	public long getOrtFpMapId() {
		return this.ortFpMapId;
	}

	public void setOrtFpMapId(long ortFpMapId) {
		this.ortFpMapId = ortFpMapId;
	}

	public String getAuftragOrtsTyp() {
		return this.auftragOrtsTyp;
	}

	public void setAuftragOrtsTyp(String auftragOrtsTyp) {
		this.auftragOrtsTyp = auftragOrtsTyp;
	}

	public String getFp() {
		return this.fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public OrtReihenfolge getOrtReihenfolge() {
		return this.ortReihenfolge;
	}

	public void setOrtReihenfolge(OrtReihenfolge ortReihenfolge) {
		this.ortReihenfolge = ortReihenfolge;
	}

    @Override
    public Long getId() {
        return ortFpMapId;
    }

}