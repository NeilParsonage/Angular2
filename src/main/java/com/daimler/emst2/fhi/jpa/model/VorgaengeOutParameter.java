package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the VORGAENGE_OUT_PARAMETER database table.
 * 
 */
@Entity
@Table(name="VORGAENGE_OUT_PARAMETER")
@NamedQuery(name="VorgaengeOutParameter.findAll", query="SELECT v FROM VorgaengeOutParameter v")
public class VorgaengeOutParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="INS_DATE")
	private Date insDate;

	@Column(name="INS_USER")
	private String insUser;

	@Column(name="PARAMETER_NAME")
	private String parameterName;

    //	@Column(name="PARAMETER_VALUE")
    //	private Object parameterValue;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

    @Column(name = "VERSION")
	private BigDecimal version;

	@Column(name="VORGANG_ID")
	private BigDecimal vorgangId;

    @Id
	@Column(name="VORGANG_PARAMETER_ID")
	private BigDecimal vorgangParameterId;

	public VorgaengeOutParameter() {
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

	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

    //	public Object getParameterValue() {
    //		return this.parameterValue;
    //	}
    //
    //	public void setParameterValue(Object parameterValue) {
    //		this.parameterValue = parameterValue;
    //	}

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

	public BigDecimal getVorgangId() {
		return this.vorgangId;
	}

	public void setVorgangId(BigDecimal vorgangId) {
		this.vorgangId = vorgangId;
	}

	public BigDecimal getVorgangParameterId() {
		return this.vorgangParameterId;
	}

	public void setVorgangParameterId(BigDecimal vorgangParameterId) {
		this.vorgangParameterId = vorgangParameterId;
	}

}