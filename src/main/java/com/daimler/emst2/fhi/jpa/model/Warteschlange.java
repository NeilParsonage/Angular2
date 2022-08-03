package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the WARTESCHLANGE database table.
 * 
 */
@Entity
@Table(name = "WARTESCHLANGE")
public class Warteschlange extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "SENDUNGS_TYP")
    private String sendungsTyp;

    @Column(name = "POSITION")
    private Long position;

    public String getSendungsTyp() {
        return sendungsTyp;
    }

    public void setSendungsTyp(String sendungsTyp) {
        this.sendungsTyp = sendungsTyp;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getPnr() {
        return pnr;
    }


    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
