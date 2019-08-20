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



@Entity(name="ATTM_TICKET_REQUEST_CANCEL")
public class ModelTicketRequestCancel {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_REQUEST_CANCEL_ID")
	private Long ticketRequestCancelId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="CANCEL_DATE_DATE")
	private Date cancelDate;
	
	@ManyToOne
	@JoinColumn(name="TICKET_REQUEST_ID")
	private ModelTicketRequest modelTicketRequest;
	
	@Column(name="CANCEL_QTY")
	private Double cancelQty;
	
	@Column(name="CANCEL_REASON")
	private String cancelReason;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
	
	@Column(name="REMARKS")
	private String remarks;
	
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

	public Long getTicketRequestCancelId() {
		return ticketRequestCancelId;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public ModelTicketRequest getModelTicketRequest() {
		return modelTicketRequest;
	}

	public Double getCancelQty() {
		return cancelQty;
	}

	public String getCancelReason() {
		return cancelReason;
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

	public String getRemarks() {
		return remarks;
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

	public void setTicketRequestCancelId(Long ticketRequestCancelId) {
		this.ticketRequestCancelId = ticketRequestCancelId;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public void setModelTicketRequest(ModelTicketRequest modelTicketRequest) {
		this.modelTicketRequest = modelTicketRequest;
	}

	public void setCancelQty(Double cancelQty) {
		this.cancelQty = cancelQty;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "ModelTicketRequestCancel [ticketRequestCancelId=" + ticketRequestCancelId + ", cancelDate=" + cancelDate
				+ ", modelTicketRequest=" + modelTicketRequest + ", cancelQty=" + cancelQty + ", cancelReason="
				+ cancelReason + ", activeStatus=" + activeStatus + ", active=" + active + ", sActive=" + sActive
				+ ", remarks=" + remarks + ", enteredBy=" + enteredBy + ", entryTimeStamp=" + entryTimeStamp
				+ ", updatedBy=" + updatedBy + ", updateTimeStamp=" + updateTimeStamp + ", flexField1=" + flexField1
				+ ", flexField2=" + flexField2 + ", flexField3=" + flexField3 + "]";
	}
	
	
	
}
