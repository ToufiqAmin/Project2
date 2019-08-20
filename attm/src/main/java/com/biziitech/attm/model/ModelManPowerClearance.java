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
import org.springframework.format.annotation.DateTimeFormat;

import com.biziitech.attm.bg.model.ModelBgUser;

@Entity(name="ATTM_MANPOWER_CLEARANCE")
public class ModelManPowerClearance {
	
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="MANPOWER_CLEARANCE_ID")
	private Long manPowerClearanceId;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_SERVICE_ID")
	private ModelClientService modelClientService;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private ModelBgUser modelUser;
	
	@Column(name="EXPENSE_AMOUNT")
	private Double expenseAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="SENT_DATE")
	private Date sentDate;
	
	@Column(name="CLEARANCE_AGENT_NAME")
	private String clearanceAgentName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="RECEIVE_DATE")
	private Date receiveDate;
	
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
	
	@Transient
	private boolean manPowerClearance;
	
	@Transient
	private String sManPowerClearance;
	
	@Column(name="ENTERED_BY")
	private Long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimeStamp;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimeStamp;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="DELIVERY_DATE")
	private Date deliveryDate;

	public Long getManPowerClearanceId() {
		return manPowerClearanceId;
	}

	
	public Double getExpenseAmount() {
		return expenseAmount;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public String getClearanceAgentName() {
		return clearanceAgentName;
	}

	public Date getReceiveDate() {
		return receiveDate;
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

	public void setManPowerClearanceId(Long manPowerClearanceId) {
		this.manPowerClearanceId = manPowerClearanceId;
	}



	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public void setClearanceAgentName(String clearanceAgentName) {
		this.clearanceAgentName = clearanceAgentName;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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

	public boolean isActive() {
		return active;
	}

	public String getsActive() {
		return sActive;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setsActive(String sActive) {
		this.sActive = sActive;
	}


	public ModelClientService getModelClientService() {
		return modelClientService;
	}



	public void setModelClientService(ModelClientService modelClientService) {
		this.modelClientService = modelClientService;
	}



	public ModelBgUser getModelUser() {
		return modelUser;
	}



	public void setModelUser(ModelBgUser modelUser) {
		this.modelUser = modelUser;
	}



	public boolean isManPowerClearance() {
		return manPowerClearance;
	}



	public String getsManPowerClearance() {
		return sManPowerClearance;
	}



	public void setManPowerClearance(boolean manPowerClearance) {
		this.manPowerClearance = manPowerClearance;
	}



	public void setsManPowerClearance(String sManPowerClearance) {
		this.sManPowerClearance = sManPowerClearance;
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


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	@Override
	public String toString() {
		return "ModelManPowerClearance [manPowerClearanceId=" + manPowerClearanceId + ", modelClientService="
				+ modelClientService + ", modelUser=" + modelUser + ", expenseAmount=" + expenseAmount + ", sentDate="
				+ sentDate + ", clearanceAgentName=" + clearanceAgentName + ", receiveDate=" + receiveDate
				+ ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2
				+ ", flex3=" + flex3 + ", active=" + active + ", sActive=" + sActive + ", manPowerClearance="
				+ manPowerClearance + ", sManPowerClearance=" + sManPowerClearance + ", enteredBy=" + enteredBy
				+ ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + ", deliveryDate=" + deliveryDate + "]";
	}



	



}
