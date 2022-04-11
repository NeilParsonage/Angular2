package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.daimler.emst2.frw.model.BaseAuditEntity;

/**
 * The persistent class for the V_AKTIVE_RESTRIKTION database table.
 * 
 */
@Entity
@Table(name = "V_AKTIVE_RESTRIKTION")
@NamedQuery(name = "AktiveRestriktion.findAll", query = "SELECT v FROM AktiveRestriktion v")
public class AktiveRestriktion extends BaseAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bereich;

    @Column(name = "CODE1_LANG")
    private String code1Lang;

    @Column(name = "CODE2_LANG")
    private String code2Lang;

    @Column(name = "CODE3_LANG")
    private String code3Lang;

    @Column(name = "HINTERGUND_FARBE")
    private String hintergundFarbe;

    @Column(name = "ISTABS_FENSTER")
    private Long istabsFenster;

    @Column(name = "ISTABS_INTERVALL")
    private String istabsIntervall;

    @Column(name = "KRIT_LANG")
    private String kritLang;

    @Column(name = "KRIT_REL_CODE1")
    private String code1;

    @Column(name = "KRIT_REL_CODE2")
    private String code2;

    @Column(name = "KRIT_REL_CODE3")
    private String code3;

    private String kriterium;

    @Temporal(TemporalType.DATE)
    @Column(name = "LETZTE_AKTUALISIERUNG")
    private Date letzteAktualisierung;

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

    @Column(name = "STERN_POS2")
    private Long sternPos2;

    @Column(name = "TAB_ANZEIGE_01")
    private Integer knzCodeAnzeige;

    @Id
    @Column(name = "V_AKRE_PK")
    private Long vAkrePk;

    private Long verletzung;

    @Column(name = "VORABS")
    private Long abstand;

    public AktiveRestriktion() {
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

    public Long getIstabsFenster() {
        return this.istabsFenster;
    }

    public void setIstabsFenster(Long istabsFenster) {
        this.istabsFenster = istabsFenster;
    }

    public String getIstabsIntervall() {
        return this.istabsIntervall;
    }

    public void setIstabsIntervall(String istabsIntervall) {
        this.istabsIntervall = istabsIntervall;
    }

    public String getKritLang() {
        return this.kritLang;
    }

    public void setKritLang(String kritLang) {
        this.kritLang = kritLang;
    }

    public String getKriterium() {
        return this.kriterium;
    }

    public void setKriterium(String kriterium) {
        this.kriterium = kriterium;
    }

    public Date getLetzteAktualisierung() {
        return this.letzteAktualisierung;
    }

    public void setLetzteAktualisierung(Date letzteAktualisierung) {
        this.letzteAktualisierung = letzteAktualisierung;
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

    public Long getSternPos2() {
        return this.sternPos2;
    }

    public void setSternPos2(Long sternPos2) {
        this.sternPos2 = sternPos2;
    }

    public Long getVAkrePk() {
        return this.vAkrePk;
    }

    public void setVAkrePk(Long vAkrePk) {
        this.vAkrePk = vAkrePk;
    }

    public Long getVerletzung() {
        return this.verletzung;
    }

    public void setVerletzung(Long verletzung) {
        this.verletzung = verletzung;
    }

    public Long getAbstand() {
        return abstand;
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

    public Integer getKnzCodeAnzeige() {
        return knzCodeAnzeige;
    }

    public void setKnzCodeAnzeige(Integer knzCodeAnzeige) {
        this.knzCodeAnzeige = knzCodeAnzeige;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return getVAkrePk();
    }

}
