package com.biziitech.attm.bg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="BG_ORG_TYPE")
public class ModelOrgType {
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="ORG_TYPE_ID")
	private Long orgTypeId;
	
	@Column(name="ORG_TYPE_NAME")
	private String orgTypeName;
	
	@Column(name="SHORT_CODE")
	private String shortCode;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Column(name="ORG_TYPE_REMARKS")
	private String orgTypeRemarks;
	
	@Column(name="ENTERED_BY")
	private long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimestamp;
	
	@Column(name="UPDATED_BY")
	@ColumnDefault("0")
	private long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP" , nullable=true)
	private Date updateTimestap;
	
	@Column(name="FLEX_FIELD1")
	private String flex1  ;
	
	@Column(name="FLEX_FIELD2")
	private String flex2  ;
	
	@Column(name="FLEX_FIELD3")
	private String flex3  ;
	
	@Column(name="FLEX_FIELD4")
	private String flex4  ;
	
	@Column(name="FLEX_FIELD5")
	private String flex5  ;
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
	
    public ModelOrgType() {
    	
    }

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName.trim();
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode.trim();
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getOrgTypeRemarks() {
		return orgTypeRemarks;
	}

	public void setOrgTypeRemarks(String orgTypeRemarks) {
		this.orgTypeRemarks = orgTypeRemarks.trim();
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Date getEntryTimestamp() {
		return entryTimestamp;
	}

	public void setEntryTimestamp(Date entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
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

//	@Override
//	public String toString() {
//		return "ModelOrgType [orgTypeId=" + orgTypeId + ", orgTypeName=" + orgTypeName + ", shortCode=" + shortCode
//				+ ", activeStatus=" + activeStatus + ", orgTypeRemarks=" + orgTypeRemarks + ", enteredBy=" + enteredBy
//				+ ", entryTimestamp=" + entryTimestamp + ", updatedBy=" + updatedBy + ", updateTimestap="
//				+ updateTimestap + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", flex4=" + flex4
//				+ ", flex5=" + flex5 + ", active=" + active + ", sActive=" + sActive + "]";
//	}

	
}
