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

@Entity(name="ATTM_TICKET_SELL_MST")
public class ModelTicketSellMst {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_SELL_MST_ID")
	private Long ticketSellMstId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="SELL_DATE")
	private Date sellDate;
	
	@ManyToOne
	@JoinColumn(name="TICKET_PURCHASE_MST_ID")
	private ModelTicketPurchaseMst modelTicketPurchaseMst;
	
	@Column(name="SELL_QTY")
	private Integer sellQty;
	
	@ManyToOne
	@JoinColumn(name="TO_AGENT_ID")
	private ModelAgent modelAgent;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
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
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
	
	@ManyToOne
	@JoinColumn(name="TICKET_REQUEST_ID")
	private ModelTicketRequest modelTicketRequest;
	
	@Column(name="FLEX_FIELD1")
	private String flex1;
	
	@Column(name="FLEX_FIELD2")
	private String flex2;
	
	@Column(name="FLEX_FIELD3")
	private String flex3;

	public Long getTicketSellMstId() {
		return ticketSellMstId;
	}

	public void setTicketSellMstId(Long ticketSellMstId) {
		this.ticketSellMstId = ticketSellMstId;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public ModelTicketPurchaseMst getModelTicketPurchaseMst() {
		return modelTicketPurchaseMst;
	}

	public void setModelTicketPurchaseMst(ModelTicketPurchaseMst modelTicketPurchaseMst) {
		this.modelTicketPurchaseMst = modelTicketPurchaseMst;
	}

	public Integer getSellQty() {
		return sellQty;
	}

	public void setSellQty(Integer sellQty) {
		this.sellQty = sellQty;
	}

	public ModelAgent getModelAgent() {
		return modelAgent;
	}

	public void setModelAgent(ModelAgent modelAgent) {
		this.modelAgent = modelAgent;
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

	public ModelTicketRequest getModelTicketRequest() {
		return modelTicketRequest;
	}

	public void setModelTicketRequest(ModelTicketRequest modelTicketRequest) {
		this.modelTicketRequest = modelTicketRequest;
	}

	@Override
	public String toString() {
		return "ModelTicketSellMst [ticketSellMstId=" + ticketSellMstId + ", sellDate=" + sellDate
				+ ", modelTicketPurchaseMst=" + modelTicketPurchaseMst + ", sellQty=" + sellQty + ", modelAgent="
				+ modelAgent + ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", enteredBy=" + enteredBy
				+ ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + ", active=" + active + ", sActive=" + sActive + ", modelTicketRequest="
				+ modelTicketRequest + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + "]";
	}

	
	
	


}
