package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.daimler.emst2.fhi.sendung.constants.SperrHerkunftEnum;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;


/**
 * The persistent class for the V_AUF_SPERRE database table.
 * 
 */
@Entity
@Table(name="V_AUF_SPERRE")
@NamedQuery(name = "AuftragSperrInformation.findAll", query = "SELECT v FROM AuftragSperrInformation v")
public class AuftragSperrInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AUSGR_WT")
	private String ausgrWt;

	@Column(name="AUSP_ID")
	private BigDecimal auspId;

	private String begruendung;

	private String bereich;

	@Column(name="BEREICH_FHI")
	private String bereichFhi;

	@Column(name="BEREICH_FHI_MATERIALSPERRE")
	private String bereichFhiMaterialsperre;

	private String ersteller;

	private BigDecimal freie;

	@Column(name="FREIGABE_MANUELL")
	private BigDecimal freigabeManuell;

	@Temporal(TemporalType.DATE)
	private Date freigabetermin;

	private String herkunft;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="MATERIAL_SPERRE_01")
	private BigDecimal materialSperre01;

	@Column(name="ORDER_GROUP")
    private Integer orderGroup;

	private String pnr;

	@Column(name="PNR_VORAUSFAHRZEUG")
	private String pnrVorausfahrzeug;

    @Column(name = "RELEVANT_01", precision = 1)
    private Boolean relevantKnz;

	@Column(name="SP_TERMIN_DATUM")
    private String spTerminDatum;

	@Column(name="SP_TERMIN_ZEIT")
	private String spTerminZeit;

	private String sperrart;

	private String sperrcode;

	private String sperrcodetext;

	private String sperrgrund;

	private String sperrtyp;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="VASP_ID")
	private String vaspId;

	@Column(name="\"VERSION\"")
	private BigDecimal version;

	private String vf;

	public AuftragSperrInformation() {
	}

	public String getAusgrWt() {
		return this.ausgrWt;
	}

	public void setAusgrWt(String ausgrWt) {
		this.ausgrWt = ausgrWt;
	}

	public BigDecimal getAuspId() {
		return this.auspId;
	}

	public void setAuspId(BigDecimal auspId) {
		this.auspId = auspId;
	}

	public String getBegruendung() {
		return this.begruendung;
	}

	public void setBegruendung(String begruendung) {
		this.begruendung = begruendung;
	}

	public String getBereich() {
		return this.bereich;
	}

	public void setBereich(String bereich) {
		this.bereich = bereich;
	}

	public String getBereichFhi() {
		return this.bereichFhi;
	}

	public void setBereichFhi(String bereichFhi) {
		this.bereichFhi = bereichFhi;
	}

	public String getBereichFhiMaterialsperre() {
		return this.bereichFhiMaterialsperre;
	}

	public void setBereichFhiMaterialsperre(String bereichFhiMaterialsperre) {
		this.bereichFhiMaterialsperre = bereichFhiMaterialsperre;
	}

	public String getErsteller() {
		return this.ersteller;
	}

	public void setErsteller(String ersteller) {
		this.ersteller = ersteller;
	}

	public BigDecimal getFreie() {
		return this.freie;
	}

	public void setFreie(BigDecimal freie) {
		this.freie = freie;
	}

	public BigDecimal getFreigabeManuell() {
		return this.freigabeManuell;
	}

	public void setFreigabeManuell(BigDecimal freigabeManuell) {
		this.freigabeManuell = freigabeManuell;
	}

	public Date getFreigabetermin() {
		return this.freigabetermin;
	}

	public void setFreigabetermin(Date freigabetermin) {
		this.freigabetermin = freigabetermin;
	}

	public String getHerkunft() {
		return this.herkunft;
	}

	public void setHerkunft(String herkunft) {
		this.herkunft = herkunft;
	}

	public Date getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	public String getInsUser() {
		return this.insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}

    public BigDecimal getKnzMaterialSperre() {
		return this.materialSperre01;
	}

    public void setKnzMaterialSperre(BigDecimal materialSperre01) {
		this.materialSperre01 = materialSperre01;
	}

    public Integer getOrderGroup() {
		return this.orderGroup;
	}

    public void setOrderGroup(Integer orderGroup) {
		this.orderGroup = orderGroup;
	}

	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getPnrVorausfahrzeug() {
		return this.pnrVorausfahrzeug;
	}

	public void setPnrVorausfahrzeug(String pnrVorausfahrzeug) {
		this.pnrVorausfahrzeug = pnrVorausfahrzeug;
	}

    public Boolean getRelevantKnz() {
        return this.relevantKnz;
	}

    public void setRelevantKnz(Boolean relevantKnz) {
        this.relevantKnz = relevantKnz;
	}

	public String getSpTerminDatum() {
		return this.spTerminDatum;
	}

	public void setSpTerminDatum(String spTerminDatum) {
		this.spTerminDatum = spTerminDatum;
	}

	public String getSpTerminZeit() {
		return this.spTerminZeit;
	}

	public void setSpTerminZeit(String spTerminZeit) {
		this.spTerminZeit = spTerminZeit;
	}

	public String getSperrart() {
		return this.sperrart;
	}

	public void setSperrart(String sperrart) {
		this.sperrart = sperrart;
	}

	public String getSperrcode() {
		return this.sperrcode;
	}

	public void setSperrcode(String sperrcode) {
		this.sperrcode = sperrcode;
	}

	public String getSperrcodetext() {
		return this.sperrcodetext;
	}

	public void setSperrcodetext(String sperrcodetext) {
		this.sperrcodetext = sperrcodetext;
	}

	public String getSperrgrund() {
		return this.sperrgrund;
	}

	public void setSperrgrund(String sperrgrund) {
		this.sperrgrund = sperrgrund;
	}

	public String getSperrtyp() {
		return this.sperrtyp;
	}

	public void setSperrtyp(String sperrtyp) {
		this.sperrtyp = sperrtyp;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getVaspId() {
		return this.vaspId;
	}

	public void setVaspId(String vaspId) {
		this.vaspId = vaspId;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getVf() {
		return this.vf;
	}

	public void setVf(String vf) {
		this.vf = vf;
	}

    /* Transient-Information */
    /**
     * {@inheritDoc}
     */
    public SperrtypEnum getSperrtypEnum() {
        SperrtypEnum sperrtypEnum = SperrtypEnum.getSperrtypEnum(getSperrtyp());
        return sperrtypEnum;
    }

    /**
     * {@inheritDoc}
     */
    public SperrHerkunftEnum getSperrHerkunftEnum() {
        SperrHerkunftEnum sperrtypEnum = SperrHerkunftEnum.getSperrHerkunftEnum(getHerkunft());
        return sperrtypEnum;
    }

    public boolean isVorausfahrzeug() {
        if (getPnrVorausfahrzeug() == null) {
            return false;
        }

        return (getPnr().equalsIgnoreCase(getPnrVorausfahrzeug()));
    }

    public boolean isNachfolgefahrzeug() {
        if (getPnrVorausfahrzeug() == null) {
            return false;
        }

        return !(getPnr().equalsIgnoreCase(getPnrVorausfahrzeug()));
    }

}