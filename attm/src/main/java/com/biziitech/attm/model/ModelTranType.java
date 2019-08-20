package com.biziitech.attm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="ATTM_TRAN_TYPE")
public class ModelTranType {
	
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TRAN_TYPE_ID")
	private Long tranTypeId;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	
	@Column(name="SHORT_CODE")
	private String shortCode;
	
	@Column(name="TRAN_NATURE")
	private String tranNature;
	
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

	public Long getTranTypeId() {
		return tranTypeId;
	}

	public void setTranTypeId(Long tranTypeId) {
		this.tranTypeId = tranTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getTranNature() {
		return tranNature;
	}

	public void setTranNature(String tranNature) {
		this.tranNature = tranNature;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Override
	public String toString() {
		return "ModelTranType [tranTypeId=" + tranTypeId + ", typeName=" + typeName + ", shortCode=" + shortCode
				+ ", tranNature=" + tranNature + ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", flex1="
				+ flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", active=" + active + ", sActive=" + sActive + "]";
	}
	
	

}
