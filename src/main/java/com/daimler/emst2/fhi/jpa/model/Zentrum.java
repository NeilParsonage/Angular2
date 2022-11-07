//package com.daimler.emst2.fhi.jpa.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQuery;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//
///**
// * The persistent class for the ZENTRUM database table.
// * 
// */
//@Entity
//@NamedQuery(name="Zentrum.findAll", query="SELECT z FROM Zentrum z")
//public class Zentrum implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name="ZENTRUM_KEY")
//	private String zentrumKey;
//
//	private String bereich;
//
//	@Column(name="FERT_IMPULS")
//	private String fertImpuls;
//
//	@Column(name="FOLGE_ZENTRUM_KEY")
//	private String folgeZentrumKey;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="INS_DATE")
//	private Date insDate;
//
//	@Column(name="INS_USER")
//	private String insUser;
//
//	@Column(name="KZ_PLANBILDUNG")
//	private String kzPlanbildung;
//
//	@Column(name="MAX_ARBEITSPLAN_GROESSE")
//	private BigDecimal maxArbeitsplanGroesse;
//
//	@Column(name="PUNKT_ID")
//	private String punktId;
//
//	@Column(name="RF_NR")
//	private BigDecimal rfNr;
//
//	@Column(name="SENDEN_AN_TAKT_01")
//	private BigDecimal sendenAnTakt01;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="UPD_DATE")
//	private Date updDate;
//
//	@Column(name="UPD_USER")
//	private String updUser;
//
//	@Column(name="VERSION")
//	private BigDecimal version;
//
//	private String zentrum;
//
//	@Column(name="ZENTRUM_GANZ_KURZ")
//	private String zentrumGanzKurz;
//
//	@Column(name="ZENTRUM_NAME")
//	private String zentrumName;
//
//	//bi-directional many-to-one association to Vorgaenge
//    //	@OneToMany(mappedBy="zentrum")
//    //	private List<Vorgaenge> vorgaenges;
//
//	public Zentrum() {
//	}
//
//	public String getZentrumKey() {
//		return this.zentrumKey;
//	}
//
//	public void setZentrumKey(String zentrumKey) {
//		this.zentrumKey = zentrumKey;
//	}
//
//	public String getBereich() {
//		return this.bereich;
//	}
//
//	public void setBereich(String bereich) {
//		this.bereich = bereich;
//	}
//
//	public String getFertImpuls() {
//		return this.fertImpuls;
//	}
//
//	public void setFertImpuls(String fertImpuls) {
//		this.fertImpuls = fertImpuls;
//	}
//
//	public String getFolgeZentrumKey() {
//		return this.folgeZentrumKey;
//	}
//
//	public void setFolgeZentrumKey(String folgeZentrumKey) {
//		this.folgeZentrumKey = folgeZentrumKey;
//	}
//
//	public Date getInsDate() {
//		return this.insDate;
//	}
//
//	public void setInsDate(Date insDate) {
//		this.insDate = insDate;
//	}
//
//	public String getInsUser() {
//		return this.insUser;
//	}
//
//	public void setInsUser(String insUser) {
//		this.insUser = insUser;
//	}
//
//	public String getKzPlanbildung() {
//		return this.kzPlanbildung;
//	}
//
//	public void setKzPlanbildung(String kzPlanbildung) {
//		this.kzPlanbildung = kzPlanbildung;
//	}
//
//	public BigDecimal getMaxArbeitsplanGroesse() {
//		return this.maxArbeitsplanGroesse;
//	}
//
//	public void setMaxArbeitsplanGroesse(BigDecimal maxArbeitsplanGroesse) {
//		this.maxArbeitsplanGroesse = maxArbeitsplanGroesse;
//	}
//
//	public String getPunktId() {
//		return this.punktId;
//	}
//
//	public void setPunktId(String punktId) {
//		this.punktId = punktId;
//	}
//
//	public BigDecimal getRfNr() {
//		return this.rfNr;
//	}
//
//	public void setRfNr(BigDecimal rfNr) {
//		this.rfNr = rfNr;
//	}
//
//	public BigDecimal getSendenAnTakt01() {
//		return this.sendenAnTakt01;
//	}
//
//	public void setSendenAnTakt01(BigDecimal sendenAnTakt01) {
//		this.sendenAnTakt01 = sendenAnTakt01;
//	}
//
//	public Date getUpdDate() {
//		return this.updDate;
//	}
//
//	public void setUpdDate(Date updDate) {
//		this.updDate = updDate;
//	}
//
//	public String getUpdUser() {
//		return this.updUser;
//	}
//
//	public void setUpdUser(String updUser) {
//		this.updUser = updUser;
//	}
//
//	public BigDecimal getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(BigDecimal version) {
//		this.version = version;
//	}
//
//	public String getZentrum() {
//		return this.zentrum;
//	}
//
//	public void setZentrum(String zentrum) {
//		this.zentrum = zentrum;
//	}
//
//	public String getZentrumGanzKurz() {
//		return this.zentrumGanzKurz;
//	}
//
//	public void setZentrumGanzKurz(String zentrumGanzKurz) {
//		this.zentrumGanzKurz = zentrumGanzKurz;
//	}
//
//	public String getZentrumName() {
//		return this.zentrumName;
//	}
//
//	public void setZentrumName(String zentrumName) {
//		this.zentrumName = zentrumName;
//	}
//
//    //	public List<Vorgaenge> getVorgaenges() {
////		return this.vorgaenges;
////	}
////
////	public void setVorgaenges(List<Vorgaenge> vorgaenges) {
////		this.vorgaenges = vorgaenges;
////	}
//
//    //	public Vorgaenge addVorgaenge(Vorgaenge vorgaenge) {
//    //		getVorgaenges().add(vorgaenge);
//    //		vorgaenge.setZentrum(this);
//    //
//    //		return vorgaenge;
//    //	}
//    //
//    //	public Vorgaenge removeVorgaenge(Vorgaenge vorgaenge) {
//    //		getVorgaenges().remove(vorgaenge);
//    //		vorgaenge.setZentrum(null);
//    //
//    //		return vorgaenge;
//    //	}
//
//}