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


@Entity(name="ATTM_TICKET_PURCHASE_MST")
public class ModelTicketPurchaseMst {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_PURCHASE_MST_ID")
	private Long ticketPurchaseMstId;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name="FLIGHT_DATE")
	private Date flightDate;
	
	@ManyToOne
	@JoinColumn(name="TICKET_REQUEST_ID")
	private ModelTicketRequest modelTicketRequest;
	
	@ManyToOne
	@JoinColumn(name="AIRLINE_ID")
	private ModelAirLine modelAirLine;
	
	@Column(name="NO_OF_TICKETS")
	private Integer noOfTickets;
	
	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;
	
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
	
	@ManyToOne
	@JoinColumn(name="TICKET_OWNER_COMPANY_ID")
	private ModelTicketOwnerCompany modelTicketOwnerCompany;
	
	@ManyToOne
	@JoinColumn(name="PURCHASE_FROM_TICKET_COMPANY_ID")
	private ModelTicketOwnerCompany modelTicketOwnerCompany2;
	
	@ManyToOne
	@JoinColumn(name="PURCHASE_FROM_AGENT_ID")
	private ModelAgent modelAgent;

	public Long getTicketPurchaseMstId() {
		return ticketPurchaseMstId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public ModelTicketRequest getModelTicketRequest() {
		return modelTicketRequest;
	}

	public ModelAirLine getModelAirLine() {
		return modelAirLine;
	}

	public Integer getNoOfTickets() {
		return noOfTickets;
	}

	public Double getTotalAmount() {
		return totalAmount;
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

	public void setTicketPurchaseMstId(Long ticketPurchaseMstId) {
		this.ticketPurchaseMstId = ticketPurchaseMstId;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setModelTicketRequest(ModelTicketRequest modelTicketRequest) {
		this.modelTicketRequest = modelTicketRequest;
	}

	public void setModelAirLine(ModelAirLine modelAirLine) {
		this.modelAirLine = modelAirLine;
	}

	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public ModelTicketOwnerCompany getModelTicketOwnerCompany() {
		return modelTicketOwnerCompany;
	}

	public void setModelTicketOwnerCompany(ModelTicketOwnerCompany modelTicketOwnerCompany) {
		this.modelTicketOwnerCompany = modelTicketOwnerCompany;
	}

	public ModelTicketOwnerCompany getModelTicketOwnerCompany2() {
		return modelTicketOwnerCompany2;
	}

	public void setModelTicketOwnerCompany2(ModelTicketOwnerCompany modelTicketOwnerCompany2) {
		this.modelTicketOwnerCompany2 = modelTicketOwnerCompany2;
	}

	public ModelAgent getModelAgent() {
		return modelAgent;
	}

	public void setModelAgent(ModelAgent modelAgent) {
		this.modelAgent = modelAgent;
	}

	@Override
	public String toString() {
		return "ModelTicketPurchaseMst [ticketPurchaseMstId=" + ticketPurchaseMstId + ", purchaseDate=" + purchaseDate
				+ ", flightDate=" + flightDate + ", modelTicketRequest=" + modelTicketRequest + ", modelAirLine="
				+ modelAirLine + ", noOfTickets=" + noOfTickets + ", totalAmount=" + totalAmount + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3
				+ ", active=" + active + ", sActive=" + sActive + ", enteredBy=" + enteredBy + ", entryTimeStamp="
				+ entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp=" + updateTimeStamp
				+ ", modelTicketOwnerCompany=" + modelTicketOwnerCompany + ", modelTicketOwnerCompany2="
				+ modelTicketOwnerCompany2 + ", modelAgent=" + modelAgent + "]";
	}



	
	
	

}
