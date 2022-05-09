package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the MAN_RF database table.
 * 
 */
@Entity
@Table(name="MAN_RF")
@NamedQuery(name = "ManuellePnrReihenfolge.findAll", query = "SELECT m FROM ManuellePnrReihenfolge m")
public class ManuellePnrReihenfolge extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String pnr;

	@Column(name="RF_NR")
	private BigDecimal rfNr;

	public ManuellePnrReihenfolge() {
	}

	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public BigDecimal getRfNr() {
		return this.rfNr;
	}

	public void setRfNr(BigDecimal rfNr) {
		this.rfNr = rfNr;
	}

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}