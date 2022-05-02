package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_AGGREGATE")
@NamedQuery(name = "AuftragAggregate.findAll", query = "SELECT t FROM AuftragAggregate t")
public class AuftragAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AGGREGAT_ID")
    private String id;


    @Column(name = "PNR")
    private String pnr;

    @Column(name = "AGGREGAT")
    private String aggregat;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getAggregat() {
        return aggregat;
    }

    public void setAggregat(String aggregat) {
        this.aggregat = aggregat;
    }

}