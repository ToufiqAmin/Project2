package com.biziitech.attm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="ATTM_SERVICE")
public class ModelService {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="SERVICE_ID")
	private Long serviceId;
	
	@Column(name="SERVICE_NAME")
	private String serviceName;
	
	@Column(name="SHORT_CODE")
	private String shortCode;
	
	@Column(name="SERVICE_AMT")
	private Double serviceAMT;
	
	@Column(name="APPROX_NO_OF_DAYS")
	private Integer approxNoOfDays;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="PASSPORT_REQUIRED")
	private int passportRequired;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Transient
	private boolean passport;
	
	@Transient
	private String sPassport;
	
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
	
	@Column(name="ENTERED_BY")
	private Long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimeStamp;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimeStamp;
	
	@Column(name="FLEX_FIELD1")
	private String flexField1;
	
	@Column(name="FLEX_FIELD2")
	private String flexField2;
	
	@Column(name="FLEX_FIELD3")
	private String flexField3;

	public Long getServiceId() {
		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public Double getServiceAMT() {
		return serviceAMT;
	}

	public Integer getApproxNoOfDays() {
		return approxNoOfDays;
	}

	public String getRemarks() {
		return remarks;
	}

	public int getPassportRequired() {
		return passportRequired;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public boolean isPassport() {
		return passport;
	}

	public String getsPassport() {
		return sPassport;
	}

	public boolean isActive() {
		return active;
	}

	public String getsActive() {
		return sActive;
	}

	public Long getEnteredBy() {
		return enteredBy;
	}

	public Date getEntryTimeStamp() {
		return entryTimeStamp;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public String getFlexField1() {
		return flexField1;
	}

	public String getFlexField2() {
		return flexField2;
	}

	public String getFlexField3() {
		return flexField3;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public void setServiceAMT(Double serviceAMT) {
		this.serviceAMT = serviceAMT;
	}

	public void setApproxNoOfDays(Integer approxNoOfDays) {
		this.approxNoOfDays = approxNoOfDays;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setPassportRequired(int passportRequired) {
		this.passportRequired = passportRequired;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public void setPassport(boolean passport) {
		this.passport = passport;
	}

	public void setsPassport(String sPassport) {
		this.sPassport = sPassport;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setsActive(String sActive) {
		this.sActive = sActive;
	}

	public void setEnteredBy(Long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public void setEntryTimeStamp(Date entryTimeStamp) {
		this.entryTimeStamp = entryTimeStamp;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public void setFlexField1(String flexField1) {
		this.flexField1 = flexField1;
	}

	public void setFlexField2(String flexField2) {
		this.flexField2 = flexField2;
	}

	public void setFlexField3(String flexField3) {
		this.flexField3 = flexField3;
	}

	@Override
	public String toString() {
		return "ModelService [serviceId=" + serviceId + ", serviceName=" + serviceName + ", shortCode=" + shortCode
				+ ", serviceAMT=" + serviceAMT + ", approxNoOfDays=" + approxNoOfDays + ", remarks=" + remarks
				+ ", passportRequired=" + passportRequired + ", activeStatus=" + activeStatus + ", passport=" + passport
				+ ", sPassport=" + sPassport + ", active=" + active + ", sActive=" + sActive + ", enteredBy="
				+ enteredBy + ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + ", flexField1=" + flexField1 + ", flexField2=" + flexField2 + ", flexField3="
				+ flexField3 + "]";
	}


	
	

}
