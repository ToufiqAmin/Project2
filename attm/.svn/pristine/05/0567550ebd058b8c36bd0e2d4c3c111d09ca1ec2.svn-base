package com.biziitech.attm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="ATTM_AIRLINE")
public class ModelAirLine {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="AIRLINE_ID")
	private Long airLineId;
	
	@Column(name="AIRLINE_NAME")
	private String airLineName;
			
	@Column(name="SHORT_CODE")
	private String shortCode;
	
	@Column(name="CONTACT_NAME")
	private String contactName;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="FLEX_FIELD1")
	private String flex1;
	
	@Column(name="FLEX_FIELD2")
	private String flex2;
	
	@Column(name="FLEX_FIELD3")
	private String flex3;
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;

	public Long getAirLineId() {
		return airLineId;
	}

	public String getAirLineName() {
		return airLineName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getFlex1() {
		return flex1;
	}

	public String getFlex2() {
		return flex2;
	}

	public String getFlex3() {
		return flex3;
	}

	public boolean isActive() {
		return active;
	}

	public String getsActive() {
		return sActive;
	}

	public void setAirLineId(Long airLineId) {
		this.airLineId = airLineId;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setFlex1(String flex1) {
		this.flex1 = flex1;
	}

	public void setFlex2(String flex2) {
		this.flex2 = flex2;
	}

	public void setFlex3(String flex3) {
		this.flex3 = flex3;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setsActive(String sActive) {
		this.sActive = sActive;
	}

	@Override
	public String toString() {
		return "ModelAirLine_ATTM [airLineId=" + airLineId + ", airLineName=" + airLineName + ", shortCode=" + shortCode
				+ ", contactName=" + contactName + ", contactNo=" + contactNo + ", activeStatus=" + activeStatus
				+ ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", active="
				+ active + ", sActive=" + sActive + "]";
	}
	
	
}
