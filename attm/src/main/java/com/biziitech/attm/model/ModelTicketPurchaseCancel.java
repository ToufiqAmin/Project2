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

@Entity(name="ATTM_TICKET_PURCHASE_CANCEL")
public class ModelTicketPurchaseCancel {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_PURCHASE_CANCEL_ID")
	private Long ticketPurchaseCancelId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="CANCEL_DATE")
	private Date cancelDate;
	
	@ManyToOne
	@JoinColumn(name="TICKET_PURCHASE_CHD_ID")
	private ModelTicketPurchaseChd modelTicketPurchaseChd;
	
	@Column(name="QTY")
	private Integer qty;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Column(name="REMARKS")
	private String remarks;
	
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
	private String flex1;
	
	@Column(name="FLEX_FIELD2")
	private String flex2;
	
	@Column(name="FLEX_FIELD3")
	private String flex3;
	
	@Column(name="CANCEL_REASON")
	private String cancelReason;
	
	@Column(name="PENALTY_AMOUNT")
	private Double penaltyAmount;

	public Long getTicketPurchaseCancelId() {
		return ticketPurchaseCancelId;
	}

	public void setTicketPurchaseCancelId(Long ticketPurchaseCancelId) {
		this.ticketPurchaseCancelId = ticketPurchaseCancelId;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public ModelTicketPurchaseChd getModelTicketPurchaseChd() {
		return modelTicketPurchaseChd;
	}

	public void setModelTicketPurchaseChd(ModelTicketPurchaseChd modelTicketPurchaseChd) {
		this.modelTicketPurchaseChd = modelTicketPurchaseChd;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
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

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Double getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	@Override
	public String toString() {
		return "ModelTicketPurchaseCancel [ticketPurchaseCancelId=" + ticketPurchaseCancelId + ", cancelDate="
				+ cancelDate + ", modelTicketPurchaseChd=" + modelTicketPurchaseChd + ", qty=" + qty + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", active=" + active + ", sActive=" + sActive + ", enteredBy="
				+ enteredBy + ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", cancelReason="
				+ cancelReason + ", penaltyAmount=" + penaltyAmount + "]";
	}
	
	

}
