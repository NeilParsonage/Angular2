package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_DETAILS")
@NamedQuery(name = "AuftragDetails.findAll", query = "SELECT t FROM AuftragDetails t")
public class AuftragDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Aufaentext")
    private String aufaenText;

    @Column(name = "Autor")
    private String autor;

    @Column(name = "Bemerkung")
    private String bemerkung;

    @Column(name = "Bemerkung_alt")
    private String bemerkungAlt;

    @Column(name = "LAND_CODE")
    private String landesCode;

    @Column(name = "Land_Langtext")
    private String land;

    @Column(name = "Gesamtlaenge")
    private String gesamtLaenge;

    @Column(name = "Radstand")
    private String radStand;

    @Column(name = "Alle_Codes")
    private String alleCodes;

    @Column(name = "Fhi_Rel_Codes")
    private String fhiRelCodes;

    @Column(name = "Band_Rel_Codes")
    private String bandRelCodes;

    @Column(name = "Alle_Krits")
    private String alleKrits;

    @Column(name = "Fhi_Rel_Krits")
    private String fhiRelKrits;

    @Column(name = "Band_Rel_Krits")
    private String bandRelKrits;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAlleCodes() {
        return alleCodes;
    }

    public void setAlleCodes(String alleCodes) {
        this.alleCodes = alleCodes;
    }

    public String getFhiRelCodes() {
        return fhiRelCodes;
    }

    public void setFhiRelCodes(String fhiRelCodes) {
        this.fhiRelCodes = fhiRelCodes;
    }

    public String getBandRelCodes() {
        return bandRelCodes;
    }

    public void setBandRelCodes(String bandRelCodes) {
        this.bandRelCodes = bandRelCodes;
    }

    public String getAlleKrits() {
        return alleKrits;
    }

    public void setAlleKrits(String alleKrits) {
        this.alleKrits = alleKrits;
    }

    public String getFhiRelKrits() {
        return fhiRelKrits;
    }

    public void setFhiRelKrits(String fhiRelKrits) {
        this.fhiRelKrits = fhiRelKrits;
    }

    public String getBandRelKrits() {
        return bandRelKrits;
    }

    public void setBandRelKrits(String bandRelKrits) {
        this.bandRelKrits = bandRelKrits;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getAufaenText() {
        return aufaenText;
    }

    public void setAufaenText(String aufaenText) {
        this.aufaenText = aufaenText;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public String getBemerkungAlt() {
        return bemerkungAlt;
    }

    public void setBemerkungAlt(String bemerkungAlt) {
        this.bemerkungAlt = bemerkungAlt;
    }

    public String getLandesCode() {
        return landesCode;
    }

    public void setLandesCode(String landesCode) {
        this.landesCode = landesCode;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getGesamtLaenge() {
        return gesamtLaenge;
    }

    public void setGesamtLaenge(String gesamtLaenge) {
        this.gesamtLaenge = gesamtLaenge;
    }

    public String getRadStand() {
        return radStand;
    }

    public void setRadStand(String radStand) {
        this.radStand = radStand;
    }

}