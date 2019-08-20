package com.biziitech.attm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="ATTM_AGENT")
public class ModelAgent {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="AGENT_ID")
	private Long agentId;
	
	@Column(name="AGENT_NAME")
	private String agentName;
	
	@Column(name="MAIL_ID")
	private String mailId;
			
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
	
	@Column(name="SELF_FLAG")
	private int selfFlagStatus;
	
	@Transient
	private boolean selfFlag;
	
	@Transient
	private String sSelfFlag;

	public Long getAgentId() {
		return agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getMailId() {
		return mailId;
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

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
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

	public boolean isSelfFlag() {
		return selfFlag;
	}

	public void setSelfFlag(boolean selfFlag) {
		this.selfFlag = selfFlag;
	}

	public String getsSelfFlag() {
		return sSelfFlag;
	}

	public void setsSelfFlag(String sSelfFlag) {
		this.sSelfFlag = sSelfFlag;
	}

	public int getSelfFlagStatus() {
		return selfFlagStatus;
	}

	public void setSelfFlagStatus(int selfFlagStatus) {
		this.selfFlagStatus = selfFlagStatus;
	}

	@Override
	public String toString() {
		return "ModelAgent [agentId=" + agentId + ", agentName=" + agentName + ", mailId=" + mailId + ", shortCode="
				+ shortCode + ", contactName=" + contactName + ", contactNo=" + contactNo + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3
				+ ", active=" + active + ", sActive=" + sActive + ", selfFlagStatus=" + selfFlagStatus + ", selfFlag="
				+ selfFlag + ", sSelfFlag=" + sSelfFlag + "]";
	}





	
	

}
