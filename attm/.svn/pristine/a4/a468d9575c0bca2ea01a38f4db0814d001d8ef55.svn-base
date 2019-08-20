package com.biziitech.attm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="ATTM_TICKET_OWNER_COMPANY")
public class ModelTicketOwnerCompany {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_OWNER_COMPANY_ID")
	private Long ticketOwnerCompanyId;
	
	@Column(name="OWNER_COMPANY_NAME")
	private String ownerCompanyName;
	
	@Column(name="SHORT_CODE")
	private String shortCode;
	
	@Column(name="TICKET_TYPE")
	private Integer ticketType;
	
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
	
	@Transient
	private boolean ticketTypeBus;
	
	@Transient
	private boolean ticketTypeTrain;
	
	@Transient
	private boolean ticketTypeAir;
	
	@Transient
	private String sTicketType;
	
	@Column(name="ENTERED_BY")
	private Long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimeStamp;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimeStamp;

	public Long getTicketOwnerCompanyId() {
		return ticketOwnerCompanyId;
	}

	public void setTicketOwnerCompanyId(Long ticketOwnerCompanyId) {
		this.ticketOwnerCompanyId = ticketOwnerCompanyId;
	}

	public String getOwnerCompanyName() {
		return ownerCompanyName;
	}

	public void setOwnerCompanyName(String ownerCompanyName) {
		this.ownerCompanyName = ownerCompanyName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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

	public Long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(Long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Date getEntryTimeStamp() {
		return entryTimeStamp;
	}

	public void setEntryTimeStamp(Date entryTimeStamp) {
		this.entryTimeStamp = entryTimeStamp;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public boolean isTicketTypeBus() {
		return ticketTypeBus;
	}

	public void setTicketTypeBus(boolean ticketTypeBus) {
		this.ticketTypeBus = ticketTypeBus;
	}

	public boolean isTicketTypeTrain() {
		return ticketTypeTrain;
	}

	public void setTicketTypeTrain(boolean ticketTypeTrain) {
		this.ticketTypeTrain = ticketTypeTrain;
	}

	public boolean isTicketTypeAir() {
		return ticketTypeAir;
	}

	public void setTicketTypeAir(boolean ticketTypeAir) {
		this.ticketTypeAir = ticketTypeAir;
	}

	public String getsTicketType() {
		return sTicketType;
	}

	public void setsTicketType(String sTicketType) {
		this.sTicketType = sTicketType;
	}

	public Integer getTicketType() {
		return ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "ModelTicketOwnerCompany [ticketOwnerCompanyId=" + ticketOwnerCompanyId + ", ownerCompanyName="
				+ ownerCompanyName + ", shortCode=" + shortCode + ", ticketType=" + ticketType + ", contactName="
				+ contactName + ", contactNo=" + contactNo + ", activeStatus=" + activeStatus + ", remarks=" + remarks
				+ ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", active=" + active + ", sActive="
				+ sActive + ", ticketTypeBus=" + ticketTypeBus + ", ticketTypeTrain=" + ticketTypeTrain
				+ ", ticketTypeAir=" + ticketTypeAir + ", sTicketType=" + sTicketType + ", enteredBy=" + enteredBy
				+ ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + "]";
	}


	

	

}
