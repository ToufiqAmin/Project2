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

import com.biziitech.attm.bg.model.ModelUser;

@Entity(name="ATTM_TICKET_SELL_CHD")
public class ModelTicketSellChd {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_SELL_CHD_ID")
	private Long ticketSellChdId;
	
	@ManyToOne
	@JoinColumn(name="TICKET_SELL_MST_ID")
	private ModelTicketSellMst modelTicketSellMst;
	
	@ManyToOne
	@JoinColumn(name="TICKET_PURCHASE_CHD_ID")
	private ModelTicketPurchaseChd modelTicketPurChaseChd;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private ModelUser modelUser;
	
	@Column(name="PNR")
	private String pNR;
	
	@Column(name="AGENT_PASSENGER_NAME")
	private String agentPassengerName;
	
	@Column(name="TICKET_AMT_USD")
	private Double ticketAMTUSD;
	
	@Column(name="TICKET_AMT_BDT")
	private Double ticketAMTBDT;
	
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
	
	@Column(name="FLEX_FIELD1")
	private String flex1;
	
	@Column(name="FLEX_FIELD2")
	private String flex2;
	
	@Column(name="FLEX_FIELD3")
	private String flex3;
	
	@Column(name="QTY")
	private int qty;

	public Long getTicketSellChdId() {
		return ticketSellChdId;
	}

	public void setTicketSellChdId(Long ticketSellChdId) {
		this.ticketSellChdId = ticketSellChdId;
	}

	public ModelTicketSellMst getModelTicketSellMst() {
		return modelTicketSellMst;
	}

	public void setModelTicketSellMst(ModelTicketSellMst modelTicketSellMst) {
		this.modelTicketSellMst = modelTicketSellMst;
	}

	public ModelTicketPurchaseChd getModelTicketPurChaseChd() {
		return modelTicketPurChaseChd;
	}

	public void setModelTicketPurChaseChd(ModelTicketPurchaseChd modelTicketPurChaseChd) {
		this.modelTicketPurChaseChd = modelTicketPurChaseChd;
	}

	public ModelUser getModelUser() {
		return modelUser;
	}

	public void setModelUser(ModelUser modelUser) {
		this.modelUser = modelUser;
	}

	public String getpNR() {
		return pNR;
	}

	public void setpNR(String pNR) {
		this.pNR = pNR;
	}

	public String getAgentPassengerName() {
		return agentPassengerName;
	}

	public void setAgentPassengerName(String agentPassengerName) {
		this.agentPassengerName = agentPassengerName;
	}

	public Double getTicketAMTUSD() {
		return ticketAMTUSD;
	}

	public void setTicketAMTUSD(Double ticketAMTUSD) {
		this.ticketAMTUSD = ticketAMTUSD;
	}

	public Double getTicketAMTBDT() {
		return ticketAMTBDT;
	}

	public void setTicketAMTBDT(Double ticketAMTBDT) {
		this.ticketAMTBDT = ticketAMTBDT;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "ModelTicketSellChd [ticketSellChdId=" + ticketSellChdId + ", modelTicketSellMst=" + modelTicketSellMst
				+ ", modelTicketPurChaseChd=" + modelTicketPurChaseChd + ", modelUser=" + modelUser + ", pNR=" + pNR
				+ ", agentPassengerName=" + agentPassengerName + ", ticketAMTUSD=" + ticketAMTUSD + ", ticketAMTBDT="
				+ ticketAMTBDT + ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", enteredBy=" + enteredBy
				+ ", entryTimeStamp=" + entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp="
				+ updateTimeStamp + ", active=" + active + ", sActive=" + sActive + ", flex1=" + flex1 + ", flex2="
				+ flex2 + ", flex3=" + flex3 + ", qty=" + qty + "]";
	}

	
	
	

}
