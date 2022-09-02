package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "V_E2_HEBERHAUS")
public class AuftragHeberhaus implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "BANDNR")
    private Long bandNr;

    @Column(name = "TERMIN_ZHE")
    private Date terminZhe;

    @Column(name = "TERMIN_ZHE_TYP")
    private String terminZheTyp;

    @Column(name = "IST_PNR")
    private String istPnr;

    @Column(name = "IST_LFD_NR_LMT")
    private Long istLfdNrLmt;

    @Column(name = "IST_TERMIN")
    private Date istTermin;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Long getBandNr() {
        return bandNr;
    }

    public void setBandNr(Long bandNr) {
        this.bandNr = bandNr;
    }

    public Date getTerminZhe() {
        return terminZhe;
    }

    public void setTerminZhe(Date terminZhe) {
        this.terminZhe = terminZhe;
    }

    public String getTerminZheTyp() {
        return terminZheTyp;
    }

    public void setTerminZheTyp(String terminZheTyp) {
        this.terminZheTyp = terminZheTyp;
    }

    public String getIstPnr() {
        return istPnr;
    }

    public void setIstPnr(String istPnr) {
        istPnr = istPnr;
    }

    public Long getIstLfdNrLmt() {
        return istLfdNrLmt;
    }

    public void setIstLfdNrLmt(Long istLfdNrLmt) {
        this.istLfdNrLmt = istLfdNrLmt;
    }

    public Date getIstTermin() {
        return istTermin;
    }

    public void setIstTermin(Date istTermin) {
        this.istTermin = istTermin;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}