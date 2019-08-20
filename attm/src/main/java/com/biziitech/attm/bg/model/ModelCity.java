package com.biziitech.attm.bg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "BG_CITY")
public class ModelCity {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name = "CITY_ID")
	private Long cityId;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private ModelCountry modelCountry;
	
	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "ACTIVE_STATUS")
	private int activeStatus;

	@Column(name = "ENTERED_BY")
	private Long enteredBy;

	@Column(name = "ENTRY_TIMESTAMP")
	private Date entryTimestap;

	@Column(name = "UPDATED_BY")
	private Long  updatedBy;

	@Column(name = "UPDATE_TIMESTAMP", nullable=true)
	private Date updateTimestap;
	
	@Column(name = "FLEX_FIELD1")
	private String flex1;

	@Column(name = "FLEX_FIELD2")
	private String flex2;

	@Column(name = "FLEX_FIELD3")
	private String flex3;

	@Column(name = "FLEX_FIELD4")
	private String flex4;

	@Column(name = "FLEX_FIELD5")
	private String flex5;
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public ModelCountry getModelCountry() {
		return modelCountry;
	}

	public void setModelCountry(ModelCountry modelCountry) {
		this.modelCountry = modelCountry;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(Long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Date getEntryTimestap() {
		return entryTimestap;
	}

	public void setEntryTimestap(Date entryTimestap) {
		this.entryTimestap = entryTimestap;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateTimestap() {
		return updateTimestap;
	}

	public void setUpdateTimestap(Date updateTimestap) {
		this.updateTimestap = updateTimestap;
	}

	public String getFlex1() {
		return flex1;
	}

	public void setFlex1(String flex1) {
		this.flex1 = flex1;
	}

	public String getFlex2() {
		return flex2;
	}

	public void setFlex2(String flex2) {
		this.flex2 = flex2;
	}

	public String getFlex3() {
		return flex3;
	}

	public void setFlex3(String flex3) {
		this.flex3 = flex3;
	}

	public String getFlex4() {
		return flex4;
	}

	public void setFlex4(String flex4) {
		this.flex4 = flex4;
	}

	public String getFlex5() {
		return flex5;
	}

	public void setFlex5(String flex5) {
		this.flex5 = flex5;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getsActive() {
		return sActive;
	}

	public void setsActive(String sActive) {
		this.sActive = sActive;
	}
	
	

}
