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

@Entity(name="ATTM_PASSPORT_RECEIVE")
public class ModelPassportReceive {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name= "PASSPORT_RECEIVE_ID")
	private Long passportReceiveId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="RECEIVE_DATE")
	private Date receiveDate;	
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private ModelBgUser modelUser;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_SERVICE_ID")
	private ModelClientService modelClientService;
		
	@Column(name="FLEX_FIELD1")
	private String flex1;
	
	@Column(name="FLEX_FIELD2")
	private String flex2;
	
	@Column(name="FLEX_FIELD3")
	private String flex3;
	
	@Column(name= "REMARKS")
	private String remarks;
	
	@Column(name= "ACTIVE_STATUS")
	private int activeStatus;	
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
		
	public Long getPassportReceiveId() {
		return passportReceiveId;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}	

	public ModelBgUser getModelUser() {
		return modelUser;
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

	public String getRemarks() {
		return remarks;
	}

	public int getActiveStatus() {
		return activeStatus;
	}


	public boolean isActive() {
		return active;
	}

	public String getsActive() {
		return sActive;
	}

	public void setPassportReceiveId(Long passportReceiveId) {
		this.passportReceiveId = passportReceiveId;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setModelUser(ModelBgUser modelUser) {
		this.modelUser = modelUser;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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

	@Override
	public String toString() {
		return "ModelPassportReceive [passportReceiveId=" + passportReceiveId + ", receiveDate=" + receiveDate
				+ ", modelUser=" + modelUser + ", modelClientService=" + modelClientService + ", flex1=" + flex1
				+ ", flex2=" + flex2 + ", flex3=" + flex3 + ", remarks=" + remarks + ", activeStatus=" + activeStatus
				+ ", active=" + active + ", sActive=" + sActive + "]";
	}


	

}
