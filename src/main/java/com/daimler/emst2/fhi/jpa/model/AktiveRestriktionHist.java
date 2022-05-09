package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the AKTIVE_RESTRIKTION_HIST database table.
 * 
 */
@Entity
@Table(name = "AKTIVE_RESTRIKTION_HIST")
@NamedQuery(name = "AktiveRestriktionHist.findAll", query = "SELECT a FROM AktiveRestriktionHist a")
public class AktiveRestriktionHist extends BaseAuditEntity implements Serializable { // old name : HistRestriktion
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ARHI_ID")
    private long arhiId;

    @Column(name = "AUF_PNR")
    private String aufPnr;

    private String bereich;

    @Column(name = "CODE1_LANG")
    private String code1Lang;

    @Column(name = "CODE2_LANG")
    private String code2Lang;

    @Column(name = "CODE3_LANG")
    private String code3Lang;

    @Column(name = "HINTERGUND_FARBE")
    private String hintergundFarbe;

    @Column(name = "KRIT_REL_CODE1")
    private String code1;

    @Column(name = "KRIT_REL_CODE2")
    private String code2;

    @Column(name = "KRIT_REL_CODE3")
    private String code3;

    private String kriterium;

    @Column(name = "REST_ID")
    private Long restId;

    @Column(name = "SOLLABS_FENSTER")
    private Long sollabsFenster;

    @Column(name = "SOLLABS_INTERVALL")
    private Long sollabsIntervall;

    @Column(name = "STERN_ANZEIGE_01")
    private Integer knzSternAnzeige;

    @Column(name = "STERN_MARKIERUNG_01")
    private Integer knzSternMarkierung;

    @Column(name = "STERN_POS")
    private Long sternPos;

    @Column(name = "TAB_ANZEIGE_01")
    private Integer knzCodeAnzeige;

    @Column(name = "VORABS")
    private Long abstand;

    public AktiveRestriktionHist() {
    }

    public long getArhiId() {
        return this.arhiId;
    }

    public void setArhiId(long arhiId) {
        this.arhiId = arhiId;
    }

    public String getAufPnr() {
        return this.aufPnr;
    }

    public void setAufPnr(String aufPnr) {
        this.aufPnr = aufPnr;
    }

    public String getBereich() {
        return this.bereich;
    }

    public void setBereich(String bereich) {
        this.bereich = bereich;
    }

    public String getCode1Lang() {
        return this.code1Lang;
    }

    public void setCode1Lang(String code1Lang) {
        this.code1Lang = code1Lang;
    }

    public String getCode2Lang() {
        return this.code2Lang;
    }

    public void setCode2Lang(String code2Lang) {
        this.code2Lang = code2Lang;
    }

    public String getCode3Lang() {
        return this.code3Lang;
    }

    public void setCode3Lang(String code3Lang) {
        this.code3Lang = code3Lang;
    }

    public String getHintergundFarbe() {
        return this.hintergundFarbe;
    }

    public void setHintergundFarbe(String hintergundFarbe) {
        this.hintergundFarbe = hintergundFarbe;
    }

    public String getKriterium() {
        return this.kriterium;
    }

    public void setKriterium(String kriterium) {
        this.kriterium = kriterium;
    }

    public Long getRestId() {
        return this.restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public Long getSollabsFenster() {
        return this.sollabsFenster;
    }

    public void setSollabsFenster(Long sollabsFenster) {
        this.sollabsFenster = sollabsFenster;
    }

    public Long getSollabsIntervall() {
        return this.sollabsIntervall;
    }

    public void setSollabsIntervall(Long sollabsIntervall) {
        this.sollabsIntervall = sollabsIntervall;
    }

    public Long getSternPos() {
        return this.sternPos;
    }

    public void setSternPos(Long sternPos) {
        this.sternPos = sternPos;
    }

    public Long getAbstand() {
        return this.abstand;
    }

    public void setAbstand(Long abstand) {
        this.abstand = abstand;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public Integer getKnzCodeAnzeige() {
        return knzCodeAnzeige;
    }

    public void setKnzCodeAnzeige(Integer knzCodeAnzeige) {
        this.knzCodeAnzeige = knzCodeAnzeige;
    }

    public Integer getKnzSternAnzeige() {
        return knzSternAnzeige;
    }

    public void setKnzSternAnzeige(Integer knzSternAnzeige) {
        this.knzSternAnzeige = knzSternAnzeige;
    }

    public Integer getKnzSternMarkierung() {
        return knzSternMarkierung;
    }

    public void setKnzSternMarkierung(Integer knzSternMarkierung) {
        this.knzSternMarkierung = knzSternMarkierung;
    }

    @Override
    public Long getId() {
        return arhiId;
    }

}