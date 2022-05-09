package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_AUFTRAG_SENDEVORSCHLAEGE database table.
 * 
 */
@Entity
@Table(name="V_AUFTRAG_SENDEVORSCHLAEGE")
@NamedQuery(name = "AuftragDetailsSendeVorschlag.findAll", query = "SELECT v FROM AuftragDetailsSendeVorschlag v")
public class AuftragDetailsSendeVorschlag extends BaseAuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="NULLINIEN_RF")
	private BigDecimal nullinienRf;

    /*@Id
    @Column(name = "PNR", updatable = false, insertable = false)
    private String pnr;*/

	@Column(name="SOLLABS_FHI")
    private Integer sollabsFhi;

	@Column(name="SOLLABS_LMT")
    private Integer sollabsLmt;


    // bi-directional many-to-one association to Auftrag
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PNR", referencedColumnName = "PNR")
    private Auftraege auftrag;

	public AuftragDetailsSendeVorschlag() {
	}

	public BigDecimal getNullinienRf() {
		return this.nullinienRf;
	}

	public void setNullinienRf(BigDecimal nullinienRf) {
		this.nullinienRf = nullinienRf;
	}

    public Integer getSollabsFhi() {
		return this.sollabsFhi;
	}

    public void setSollabsFhi(Integer sollabsFhi) {
		this.sollabsFhi = sollabsFhi;
	}

    public Integer getSollabsLmt() {
		return this.sollabsLmt;
	}

    public void setSollabsLmt(Integer sollabsLmt) {
		this.sollabsLmt = sollabsLmt;
	}

    public Auftraege getAuftrag() {
        return auftrag;
    }

    public void setAuftrag(Auftraege auftrag) {
        this.auftrag = auftrag;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}