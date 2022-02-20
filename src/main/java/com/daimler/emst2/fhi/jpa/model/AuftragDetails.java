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