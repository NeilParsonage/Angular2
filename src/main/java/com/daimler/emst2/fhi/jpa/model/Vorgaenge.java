package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the VORGAENGE database table.
 * 
 */
@Entity
@NamedQuery(name="Vorgaenge.findAll", query="SELECT v FROM Vorgaenge v")
public class Vorgaenge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VORGANG_ID")
	private long vorgangId;

	private String aga;

	@Temporal(TemporalType.DATE)
	@Column(name="ENDE_DATE")
	private Date endeDate;

	private String ersteller;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	private String pnr;

	@Column(name="SCHRITT_NR")
	private BigDecimal schrittNr;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private BigDecimal status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

    @Column(name = "VERSION")
	private BigDecimal version;

	//bi-directional many-to-one association to Vorgaenge
	@ManyToOne
	@JoinColumn(name="MASTER_ID")
	private Vorgaenge vorgaenge;

	//bi-directional many-to-one association to Vorgaenge
	@OneToMany(mappedBy="vorgaenge")
	private List<Vorgaenge> vorgaenges;

	//bi-directional many-to-one association to VorgangTypen
	@ManyToOne
	@JoinColumn(name="VORGANG_TYP_ID")
	private VorgangTypen vorgangTypen;

	//bi-directional many-to-one association to Zentrum
    //	@ManyToOne
    //	@JoinColumn(name="ZENTRUM_KEY")
    //	private Zentrum zentrum;

	//bi-directional many-to-one association to VorgaengeDetail
	@OneToMany(mappedBy="vorgaenge")
	private List<VorgaengeDetail> vorgaengeDetails;

	//bi-directional many-to-one association to VorgaengeMeldungen
	@OneToMany(mappedBy="vorgaenge")
	private List<VorgaengeMeldungen> vorgaengeMeldungens;

	//bi-directional many-to-one association to VorgaengeWiederaufsetzen
	@OneToMany(mappedBy="vorgaenge")
	private List<VorgaengeWiederaufsetzen> vorgaengeWiederaufsetzens;

	public Vorgaenge() {
	}

	public long getVorgangId() {
		return this.vorgangId;
	}

	public void setVorgangId(long vorgangId) {
		this.vorgangId = vorgangId;
	}

	public String getAga() {
		return this.aga;
	}

	public void setAga(String aga) {
		this.aga = aga;
	}

	public Date getEndeDate() {
		return this.endeDate;
	}

	public void setEndeDate(Date endeDate) {
		this.endeDate = endeDate;
	}

	public String getErsteller() {
		return this.ersteller;
	}

	public void setErsteller(String ersteller) {
		this.ersteller = ersteller;
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

	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public BigDecimal getSchrittNr() {
		return this.schrittNr;
	}

	public void setSchrittNr(BigDecimal schrittNr) {
		this.schrittNr = schrittNr;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
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

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public Vorgaenge getVorgaenge() {
		return this.vorgaenge;
	}

	public void setVorgaenge(Vorgaenge vorgaenge) {
		this.vorgaenge = vorgaenge;
	}

	public List<Vorgaenge> getVorgaenges() {
		return this.vorgaenges;
	}

	public void setVorgaenges(List<Vorgaenge> vorgaenges) {
		this.vorgaenges = vorgaenges;
	}

	public Vorgaenge addVorgaenge(Vorgaenge vorgaenge) {
		getVorgaenges().add(vorgaenge);
		vorgaenge.setVorgaenge(this);

		return vorgaenge;
	}

	public Vorgaenge removeVorgaenge(Vorgaenge vorgaenge) {
		getVorgaenges().remove(vorgaenge);
		vorgaenge.setVorgaenge(null);

		return vorgaenge;
	}

	public VorgangTypen getVorgangTypen() {
		return this.vorgangTypen;
	}

	public void setVorgangTypen(VorgangTypen vorgangTypen) {
		this.vorgangTypen = vorgangTypen;
	}

    //	public Zentrum getZentrum() {
    //		return this.zentrum;
    //	}
    //
    //	public void setZentrum(Zentrum zentrum) {
    //		this.zentrum = zentrum;
    //	}

	public List<VorgaengeDetail> getVorgaengeDetails() {
		return this.vorgaengeDetails;
	}

	public void setVorgaengeDetails(List<VorgaengeDetail> vorgaengeDetails) {
		this.vorgaengeDetails = vorgaengeDetails;
	}

	public VorgaengeDetail addVorgaengeDetail(VorgaengeDetail vorgaengeDetail) {
		getVorgaengeDetails().add(vorgaengeDetail);
		vorgaengeDetail.setVorgaenge(this);

		return vorgaengeDetail;
	}

	public VorgaengeDetail removeVorgaengeDetail(VorgaengeDetail vorgaengeDetail) {
		getVorgaengeDetails().remove(vorgaengeDetail);
		vorgaengeDetail.setVorgaenge(null);

		return vorgaengeDetail;
	}

	public List<VorgaengeMeldungen> getVorgaengeMeldungens() {
		return this.vorgaengeMeldungens;
	}

	public void setVorgaengeMeldungens(List<VorgaengeMeldungen> vorgaengeMeldungens) {
		this.vorgaengeMeldungens = vorgaengeMeldungens;
	}

	public VorgaengeMeldungen addVorgaengeMeldungen(VorgaengeMeldungen vorgaengeMeldungen) {
		getVorgaengeMeldungens().add(vorgaengeMeldungen);
		vorgaengeMeldungen.setVorgaenge(this);

		return vorgaengeMeldungen;
	}

	public VorgaengeMeldungen removeVorgaengeMeldungen(VorgaengeMeldungen vorgaengeMeldungen) {
		getVorgaengeMeldungens().remove(vorgaengeMeldungen);
		vorgaengeMeldungen.setVorgaenge(null);

		return vorgaengeMeldungen;
	}

	public List<VorgaengeWiederaufsetzen> getVorgaengeWiederaufsetzens() {
		return this.vorgaengeWiederaufsetzens;
	}

	public void setVorgaengeWiederaufsetzens(List<VorgaengeWiederaufsetzen> vorgaengeWiederaufsetzens) {
		this.vorgaengeWiederaufsetzens = vorgaengeWiederaufsetzens;
	}

	public VorgaengeWiederaufsetzen addVorgaengeWiederaufsetzen(VorgaengeWiederaufsetzen vorgaengeWiederaufsetzen) {
		getVorgaengeWiederaufsetzens().add(vorgaengeWiederaufsetzen);
		vorgaengeWiederaufsetzen.setVorgaenge(this);

		return vorgaengeWiederaufsetzen;
	}

	public VorgaengeWiederaufsetzen removeVorgaengeWiederaufsetzen(VorgaengeWiederaufsetzen vorgaengeWiederaufsetzen) {
		getVorgaengeWiederaufsetzens().remove(vorgaengeWiederaufsetzen);
		vorgaengeWiederaufsetzen.setVorgaenge(null);

		return vorgaengeWiederaufsetzen;
	}

}