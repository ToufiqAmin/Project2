package com.biziitech.attm.custom.model;

import java.util.Date;

public class ModelTicketSellCustom {

	private Long ticketSellMstId;
	private Long ticketSellChdId;
	private Date sellDate;
	private Long ticketRequestId;
	private Integer sellQty;
	private Long toAgentId;
	private String toAgentName;
	private Long fromAgentId;
	private String fromAgentName;
	private String remarks;
	private int activeStatus;
	private String sActive;
	private boolean active;
	private Double agreementAMT;
	private Double advancePayment;
	private Long userId;
	private String userName;
	private Long fromCountryId;
	private String fromCountryName;
	private Long toCountryId;
	private String toCountryName;
	private Double ticketAMTUSD;
	private Double ticketAMTBDT;
	private Integer autoPayment;
	private Integer sellAgentSelfStatus;
	private Integer noOfTickets;
	private Integer soldQty;
	private Long airLineId;
	private String airLineName;
	private Date requestDate;
	private Long requesterId;
	private String requesterName;
	private String requesterContactPhone;
	private Long ticketPurchaseChdId;
	private String pNR;
	private String agentPassengerName;
	
	
	
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
	
	public Integer getSellQty() {
		return sellQty;
	}
	public void setSellQty(Integer sellQty) {
		this.sellQty = sellQty;
	}
	public Long getToAgentId() {
		return toAgentId;
	}
	public void setToAgentId(Long toAgentId) {
		this.toAgentId = toAgentId;
	}
	public String getToAgentName() {
		return toAgentName;
	}
	public void setToAgentName(String toAgentName) {
		this.toAgentName = toAgentName;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getsActive() {
		return sActive;
	}
	public void setsActive(String sActive) {
		this.sActive = sActive;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public Integer getSellAgentSelfStatus() {
		return sellAgentSelfStatus;
	}
	public void setSellAgentSelfStatus(Integer sellAgentSelfStatus) {
		this.sellAgentSelfStatus = sellAgentSelfStatus;
	}
	
	public Integer getSoldQty() {
		return soldQty;
	}
	public void setSoldQty(Integer soldQty) {
		this.soldQty = soldQty;
	}
	public Long getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(Long airLineId) {
		this.airLineId = airLineId;
	}
	public String getAirLineName() {
		return airLineName;
	}
	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	public Long getTicketRequestId() {
		return ticketRequestId;
	}
	public void setTicketRequestId(Long ticketRequestId) {
		this.ticketRequestId = ticketRequestId;
	}
	public Long getFromAgentId() {
		return fromAgentId;
	}
	public void setFromAgentId(Long fromAgentId) {
		this.fromAgentId = fromAgentId;
	}
	public String getFromAgentName() {
		return fromAgentName;
	}
	public void setFromAgentName(String fromAgentName) {
		this.fromAgentName = fromAgentName;
	}
	public Double getAgreementAMT() {
		return agreementAMT;
	}
	public void setAgreementAMT(Double agreementAMT) {
		this.agreementAMT = agreementAMT;
	}

	public Long getFromCountryId() {
		return fromCountryId;
	}
	public void setFromCountryId(Long fromCountryId) {
		this.fromCountryId = fromCountryId;
	}
	public String getFromCountryName() {
		return fromCountryName;
	}
	public void setFromCountryName(String fromCountryName) {
		this.fromCountryName = fromCountryName;
	}
	public Long getToCountryId() {
		return toCountryId;
	}
	public void setToCountryId(Long toCountryId) {
		this.toCountryId = toCountryId;
	}
	public String getToCountryName() {
		return toCountryName;
	}
	public void setToCountryName(String toCountryName) {
		this.toCountryName = toCountryName;
	}
	public Integer getAutoPayment() {
		return autoPayment;
	}
	public void setAutoPayment(Integer autoPayment) {
		this.autoPayment = autoPayment;
	}
	public Integer getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public Double getAdvancePayment() {
		return advancePayment;
	}
	public void setAdvancePayment(Double advancePayment) {
		this.advancePayment = advancePayment;
	}
	public Long getTicketSellChdId() {
		return ticketSellChdId;
	}
	public void setTicketSellChdId(Long ticketSellChdId) {
		this.ticketSellChdId = ticketSellChdId;
	}
	public Long getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getRequesterContactPhone() {
		return requesterContactPhone;
	}
	public void setRequesterContactPhone(String requesterContactPhone) {
		this.requesterContactPhone = requesterContactPhone;
	}
	public Long getTicketPurchaseChdId() {
		return ticketPurchaseChdId;
	}
	public void setTicketPurchaseChdId(Long ticketPurchaseChdId) {
		this.ticketPurchaseChdId = ticketPurchaseChdId;
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
	
	
	
	
}
