package com.biziitech.attm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;

@Entity(name="ATTM_CLIENT_SERVICE")
public class ModelClientService {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="CLIENT_SERVICE_ID")
	private Long clientServiceId;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_TYPE_ID")
	private ModelClientType modelClientType;
	
	@Column(name="CLIENT_ID")
	private Long clientId;
	
	@ManyToOne
	@JoinColumn(name="SERVICE_ID")
	private ModelService modelService;
	
	@Column(name="CARE_OF_NAME")
	private String careOfName;
	
	@Column(name="CARE_OF_CONTACT_NO")
	private String careOfContactNo;
	
	@ManyToOne
	@JoinColumn(name="FROM_COUNTRY")
	private ModelCountry modelCountry;
	
	@ManyToOne
	@JoinColumn(name="FROM_City")
	private ModelCity fromCity;
	
	@ManyToOne
	@JoinColumn(name="TO_COUNTRY")
	private ModelCountry modelCountry2;
	
	@ManyToOne
	@JoinColumn(name="TO_CITY")
	private ModelCity toCity;
	
	@Column(name="AGREEMENT_AMT")
	private Double agreementAMT;
	
	@Column(name="QTY")
	private Integer qTY;
	
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
	
	@Column(name="ENTERED_BY")
	private Long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimeStamp;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimeStamp;

	public Long getClientServiceId() {
		return clientServiceId;
	}

	public ModelClientType getModelClientType() {
		return modelClientType;
	}

	

	public ModelService getModelService() {
		return modelService;
	}

	public String getCareOfName() {
		return careOfName;
	}

	public String getCareOfContactNo() {
		return careOfContactNo;
	}

	public ModelCountry getModelCountry() {
		return modelCountry;
	}

	public Double getAgreementAMT() {
		return agreementAMT;
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

	public void setClientServiceId(Long clientServiceId) {
		this.clientServiceId = clientServiceId;
	}

	public void setModelClientType(ModelClientType modelClientType) {
		this.modelClientType = modelClientType;
	}

	

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setCareOfName(String careOfName) {
		this.careOfName = careOfName;
	}

	public void setCareOfContactNo(String careOfContactNo) {
		this.careOfContactNo = careOfContactNo;
	}

	public void setModelCountry(ModelCountry modelCountry) {
		this.modelCountry = modelCountry;
	}


	public void setAgreementAMT(Double agreementAMT) {
		this.agreementAMT = agreementAMT;
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

	public ModelCountry getModelCountry2() {
		return modelCountry2;
	}

	public void setModelCountry2(ModelCountry modelCountry2) {
		this.modelCountry2 = modelCountry2;
	}

	public Integer getqTY() {
		return qTY;
	}

	public void setqTY(Integer qTY) {
		this.qTY = qTY;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public ModelCity getFromCity() {
		return fromCity;
	}

	public void setFromCity(ModelCity fromCity) {
		this.fromCity = fromCity;
	}

	public ModelCity getToCity() {
		return toCity;
	}

	public void setToCity(ModelCity toCity) {
		this.toCity = toCity;
	}

	@Override
	public String toString() {
		return "ModelClientService [clientServiceId=" + clientServiceId + ", modelClientType=" + modelClientType
				+ ", clientId=" + clientId + ", modelService=" + modelService + ", careOfName=" + careOfName
				+ ", careOfContactNo=" + careOfContactNo + ", modelCountry=" + modelCountry + ", fromCity=" + fromCity
				+ ", modelCountry2=" + modelCountry2 + ", toCity=" + toCity + ", agreementAMT=" + agreementAMT
				+ ", qTY=" + qTY + ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", flex1=" + flex1
				+ ", flex2=" + flex2 + ", flex3=" + flex3 + ", active=" + active + ", sActive=" + sActive
				+ ", enteredBy=" + enteredBy + ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy
				+ ", updateTimeStamp=" + updateTimeStamp + "]";
	}




	

}
