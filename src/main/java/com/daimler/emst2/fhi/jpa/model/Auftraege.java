package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.daimler.emst2.fhi.util.BasisCollectionUtils;
import com.daimler.emst2.frw.model.BaseAuditEntity;


/**
 * The persistent class for the WERTELISTEN database table.
 * 
 */
@Entity
@Table(name = "Auftrag")
@NamedQuery(name = "Auftraege.findAll", query = "SELECT a FROM Auftraege a")
public class Auftraege extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Lfd_Nr_Ges")
    private Long lfdNrGes;

    @Column(name = "Lfd_Nr_Fhi")
    private Long lfdNrFhi;

    @Column(name = "Lfd_Nr_Band")
    private Long lfdNrLmt;

    @Column(name = "Lfd_Nr_Ubm")
    private Long lfdNrUbm;

    @Column(name = "Lfd_Nr_Rhm")
    private Long lfdNrRhm;

    @Column(name = "Fzgartneu")
    private String fzgArt;

    @Column(name = "Fhsbm")
    private String fhsBaumuster;

    @Column(name = "Fzgbm")
    private String fzgBaumuster;

    @Column(name = "Taktkl")
    private String fzgTaktklasse;

    @Column(name = "Fhsmap")
    private String fhsTaktklasse;

    @Column(name = "Aufnr")
    private String anr;

    @Column(name = "Verkbez")
    private String verkBez;

    @Column(name = "BANDNR")
    private Integer bandNr;

    @Column(name = "Bandnr_Alt_Rt2")
    private Long bandNrRt2;
    
    @Column(name = "Bandnr_Alt_Rt5")
    private Long bandNrRt5;

    @Column(name = "FGSTENDNR")
    private String fgstendnr;

    @Column(name = "FBM")
    private String fbm;

    @Column(name = "LENKUNG")
    private String lenkung;

    @Column(name = "WKZ")
    private String wkz;

    @Column(name = "ORT")
    private String ortOrginal;

    @Column(name = "ORT_RHM")
    private String ortRhm;

    // Mv_Alle_Rel_Codes
    //@Column(name = "CODES_BAND", length = 2000)
    //private String codesBand;

    @Column(name = "FHISENDUNG", length = 1)
    private String fhiSendung;

    @Column(name = "LMTSENDUNG", length = 1)
    private String lmtSendung;

    @Column(name = "RHMSENDUNG", length = 1)
    private String rhmSendung;

    @Column(name = "UBMSENDUNG", length = 1)
    private String ubmSendung;

    @Column(name = "FHI_SEND_STATUS", length = 1)
    private String fhiSendStatus;

    @Column(name = "LMT_SEND_STATUS", length = 1)
    private String lmtSendStatus;

    @Column(name = "RHM_SEND_STATUS", length = 1)
    private String rhmSendStatus;

    @Column(name = "UBM_SEND_STATUS", length = 1)
    private String ubmSendStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "SENDETERMIN_FHI")
    private Date sendeterminFhi;

    @Temporal(TemporalType.DATE)
    @Column(name = "SENDETERMIN_KOMPLETT")
    private Date sendetermin;

    @Column(name = "ZUGEBUNDEN", length = 1)
    private String zugebunden;

    @Transient
    private Date sendeterminRhm;

    @Transient
    private Date sendeterminUbm;

    //bi-directional many-to-one association to AufSp
    @OneToMany(mappedBy = "auftrag", fetch = FetchType.LAZY)
    private List<AuftragDetailsSendeVorschlag> auftragDetailsSendeVorschlagList;

    @OneToMany(mappedBy = "auftrag", fetch = FetchType.LAZY)
    private List<AuftragSperrInformation> sperrInformationen;

    @OneToMany(mappedBy = "auftrag", fetch = FetchType.LAZY)
    private List<MvAlleRelCode> alleRelCode;

    @Transient
    private AuftragDetailsSendeVorschlag transSendeVorschlagDetails;

    @Transient
    public final AuftragMeta meta;

    public Auftraege() {
        this.meta = AuftragMeta.create(this);
    }

    public String getFzgArt() {
        return fzgArt;
    }

    public void setFzgArt(String fzgArt) {
        this.fzgArt = fzgArt;
    }

    public Integer getBandNr() {
        return bandNr;
    }

    public void setBandNr(Integer bandNr) {
        this.bandNr = bandNr;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Long getLfdNrGes() {
        return lfdNrGes;
    }

    public void setLfdNrGes(Long lfdNrGes) {
        this.lfdNrGes = lfdNrGes;
    }

    public Long getLfdNrFhi() {
        return lfdNrFhi;
    }

    public void setLfdNrFhi(Long lfdNrFhi) {
        this.lfdNrFhi = lfdNrFhi;
    }

    public Long getLfdNrLmt() {
        return lfdNrLmt;
    }

    public void setLfdNrLmt(Long lfdNrLmt) {
        this.lfdNrLmt = lfdNrLmt;
    }

    public Long getLfdNrUbm() {
        return lfdNrUbm;
    }

    public void setLfdNrUbm(Long lfdNrUbm) {
        this.lfdNrUbm = lfdNrUbm;
    }

    public String getFhsBaumuster() {
        return fhsBaumuster;
    }

    public void setFhsBaumuster(String fhsBaumuster) {
        this.fhsBaumuster = fhsBaumuster;
    }

    public String getAnr() {
        return anr;
    }

    public void setAnr(String anr) {
        this.anr = anr;
    }

    public String getVerkBez() {
        return verkBez;
    }

    public void setVerkBez(String verkBez) {
        this.verkBez = verkBez;
    }

    public Long getBandNrRt2() {
        return bandNrRt2;
    }

    public void setBandNrRt2(Long bandNrRt2) {
        this.bandNrRt2 = bandNrRt2;
    }

    public Long getBandNrRt5() {
        return bandNrRt5;
    }

    public void setBandNrRt5(Long bandNrRt5) {
        this.bandNrRt5 = bandNrRt5;
    }

    public String getFzgTaktklasse() {
        return fzgTaktklasse;
    }

    public void setFzgTaktklasse(String fzgTaktklasse) {
        this.fzgTaktklasse = fzgTaktklasse;
    }

    public String getFhsTaktklasse() {
        return fhsTaktklasse;
    }

    public void setFhsTaktklasse(String fhsTaktklasse) {
        this.fhsTaktklasse = fhsTaktklasse;
    }

    public String getFzgBaumuster() {
        return fzgBaumuster;
    }

    public void setFzgBaumuster(String fzgBaumuster) {
        this.fzgBaumuster = fzgBaumuster;
    }

    public Long getLfdNrRhm() {
        return lfdNrRhm;
    }

    public void setLfdNrRhm(Long lfdNrRhm) {
        this.lfdNrRhm = lfdNrRhm;
    }

    public String getFgstendnr() {
        return fgstendnr;
    }

    public void setFgstendnr(String fgstendnr) {
        this.fgstendnr = fgstendnr;
    }

    public String getWkz() {
        return wkz;
    }

    public void setWkz(String wkz) {
        this.wkz = wkz;
    }

    public String getFbm() {
        return fbm;
    }

    public void setFbm(String fbm) {
        this.fbm = fbm;
    }

    public String getLenkung() {
        return lenkung;
    }

    public void setLenkung(String lenkung) {
        this.lenkung = lenkung;
    }

    public String getOrtOrginal() {
        return ortOrginal;
    }

    public void setOrtOrginal(String ortOrginal) {
        this.ortOrginal = ortOrginal;
    }

    public String getOrtRhm() {
        return ortRhm;
    }

    public void setOrtRhm(String ortRhm) {
        this.ortRhm = ortRhm;
    }

    //    public String getCodesBand() {
    //        return codesBand;
    //    }
    //
    //    public void setCodesBand(String codesBand) {
    //        this.codesBand = codesBand;
    //    }

    public String getFhiSendung() {
        return fhiSendung;
    }

    public void setFhiSendung(String fhiSendung) {
        this.fhiSendung = fhiSendung;
    }

    public String getLmtSendung() {
        return lmtSendung;
    }

    public void setLmtSendung(String lmtSendung) {
        this.lmtSendung = lmtSendung;
    }

    public String getRhmSendung() {
        return rhmSendung;
    }

    public void setRhmSendung(String rhmSendung) {
        this.rhmSendung = rhmSendung;
    }

    public String getUbmSendung() {
        return ubmSendung;
    }

    public void setUbmSendung(String ubmSendung) {
        this.ubmSendung = ubmSendung;
    }

    public String getFhiSendStatus() {
        return fhiSendStatus;
    }

    public void setFhiSendStatus(String fhiSendStatus) {
        this.fhiSendStatus = fhiSendStatus;
    }

    public String getLmtSendStatus() {
        return lmtSendStatus;
    }

    public void setLmtSendStatus(String lmtSendStatus) {
        this.lmtSendStatus = lmtSendStatus;
    }

    public String getRhmSendStatus() {
        return rhmSendStatus;
    }

    public void setRhmSendStatus(String rhmSendStatus) {
        this.rhmSendStatus = rhmSendStatus;
    }

    public String getUbmSendStatus() {
        return ubmSendStatus;
    }

    public void setUbmSendStatus(String ubmSendStatus) {
        this.ubmSendStatus = ubmSendStatus;
    }

    public Date getSendeterminFhi() {
        return sendeterminFhi;
    }

    public void setSendeterminFhi(Date sendeterminFhi) {
        this.sendeterminFhi = sendeterminFhi;
    }

    public Date getSendetermin() {
        return sendetermin;
    }

    public void setSendetermin(Date sendetermin) {
        this.sendetermin = sendetermin;
    }

    public Date getSendeterminRhm() {
        return sendeterminRhm;
    }

    public void setSendeterminRhm(Date sendeterminRhm) {
        this.sendeterminRhm = sendeterminRhm;
    }

    public Date getSendeterminUbm() {
        return sendeterminUbm;
    }

    public void setSendeterminUbm(Date sendeterminUbm) {
        this.sendeterminUbm = sendeterminUbm;
    }

    public AuftragDetailsSendeVorschlag getSendeVorschlagDetails() {
        if (getTransSendeVorschlagDetails() == null) {
            if (BasisCollectionUtils.isEmptyOrNull(getAuftragDetailsSendeVorschlagList())) {
                setTransSendeVorschlagDetails(new AuftragDetailsSendeVorschlag());
            }
            else {
                List<AuftragDetailsSendeVorschlag> myDetailsList = getAuftragDetailsSendeVorschlagList();
                AuftragDetailsSendeVorschlag myDetails = myDetailsList.get(0);
                setTransSendeVorschlagDetails(myDetails);
            }
        }
        return getTransSendeVorschlagDetails();
    }

    public List<AuftragDetailsSendeVorschlag> getAuftragDetailsSendeVorschlagList() {
        return auftragDetailsSendeVorschlagList;
    }

    public void
            setAuftragDetailsSendeVorschlagList(List<AuftragDetailsSendeVorschlag> auftragDetailsSendeVorschlagList) {
        this.auftragDetailsSendeVorschlagList = auftragDetailsSendeVorschlagList;
    }

    public AuftragDetailsSendeVorschlag getTransSendeVorschlagDetails() {
        return transSendeVorschlagDetails;
    }

    public void setTransSendeVorschlagDetails(AuftragDetailsSendeVorschlag transSendeVorschlagDetails) {
        this.transSendeVorschlagDetails = transSendeVorschlagDetails;
    }

    public String getZugebunden() {
        return zugebunden;
    }

    public void setZugebunden(String zugebunden) {
        this.zugebunden = zugebunden;
    }

    public Integer getBandnr() {
        return bandNr;
    }

    public void setBandnr(Integer bandnr) {
        this.bandNr = bandnr;
    }

    public List<AuftragSperrInformation> getSperrInformationen() {
        return sperrInformationen;
    }

    public void setSperrInformationen(List<AuftragSperrInformation> sperrInformationen) {
        this.sperrInformationen = sperrInformationen;
    }

    public List<MvAlleRelCode> getAlleRelCode() {
        return alleRelCode;
    }

    public void setAlleRelCode(List<MvAlleRelCode> alleRelCode) {
        this.alleRelCode = alleRelCode;
    }

    @Override
    public Long getId() {
        return null;
    }

}