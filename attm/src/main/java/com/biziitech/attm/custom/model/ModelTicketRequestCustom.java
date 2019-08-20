package com.biziitech.attm.custom.model;

import java.util.Date;

public class ModelTicketRequestCustom {
	
	private Long ticketRequestId;
	private Date ticketRequestDate;
	private Long userId;
	private String userName;
	private String userContactNo;
	private Date possibleFlightDate;
	private Long countryFromId;
	private String countryFromName;
	private Long fromCityId;
	private String fromCityName;
	private Long countryToId;
	private String countryToName;
	private Long toCityId;
	private String toCityName;
	private Integer noOfTickets;
	private Double approxAMT;
	private Double agreementAMT;
	private Double advPayment;
	private Long airLineId;
	private String airLineName;
	private int activeStatus;
	private String sActive;
	private boolean active;
	private int autoPayment;
	private String sAutoPayment;
	private boolean autoPay;
	private String remarks;
	private int pOFlag;
	private String pORemarks;
	private Long requesterId;
	private String requesterName;
	private String requesterContactPhone;
	private Long fromAgentId;
	private String fromAgentName;
	private Long toAgentId;
	private String toAgentName;
	private Long ticketOwnerCompanyId;
	private String ownerCompanyName;
	
	
	public Long getTicketRequestId() {
		return ticketRequestId;
	}
	public void setTicketRequestId(Long ticketRequestId) {
		this.ticketRequestId = ticketRequestId;
	}
	public Date getTicketRequestDate() {
		return ticketRequestDate;
	}
	public void setTicketRequestDate(Date ticketRequestDate) {
		this.ticketRequestDate = ticketRequestDate;
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
	public Date getPossibleFlightDate() {
		return possibleFlightDate;
	}
	public void setPossibleFlightDate(Date possibleFlightDate) {
		this.possibleFlightDate = possibleFlightDate;
	}
	public Long getCountryFromId() {
		return countryFromId;
	}
	public void setCountryFromId(Long countryFromId) {
		this.countryFromId = countryFromId;
	}
	public String getCountryFromName() {
		return countryFromName;
	}
	public void setCountryFromName(String countryFromName) {
		this.countryFromName = countryFromName;
	}
	public Long getCountryToId() {
		return countryToId;
	}
	public void setCountryToId(Long countryToId) {
		this.countryToId = countryToId;
	}
	public String getCountryToName() {
		return countryToName;
	}
	public void setCountryToName(String countryToName) {
		this.countryToName = countryToName;
	}
	public Integer getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public Double getApproxAMT() {
		return approxAMT;
	}
	public void setApproxAMT(Double approxAMT) {
		this.approxAMT = approxAMT;
	}
	public Double getAgreementAMT() {
		return agreementAMT;
	}
	public void setAgreementAMT(Double agreementAMT) {
		this.agreementAMT = agreementAMT;
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
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getpOFlag() {
		return pOFlag;
	}
	public void setpOFlag(int pOFlag) {
		this.pOFlag = pOFlag;
	}
	public String getpORemarks() {
		return pORemarks;
	}
	public void setpORemarks(String pORemarks) {
		this.pORemarks = pORemarks;
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
	public Long getFromCityId() {
		return fromCityId;
	}
	public void setFromCityId(Long fromCityId) {
		this.fromCityId = fromCityId;
	}
	public String getFromCityName() {
		return fromCityName;
	}
	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}
	public Long getToCityId() {
		return toCityId;
	}
	public void setToCityId(Long toCityId) {
		this.toCityId = toCityId;
	}
	public String getToCityName() {
		return toCityName;
	}
	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}
	public Double getAdvPayment() {
		return advPayment;
	}
	public void setAdvPayment(Double advPayment) {
		this.advPayment = advPayment;
	}
	public int getAutoPayment() {
		return autoPayment;
	}
	public void setAutoPayment(int autoPayment) {
		this.autoPayment = autoPayment;
	}
	public String getsAutoPayment() {
		return sAutoPayment;
	}
	public void setsAutoPayment(String sAutoPayment) {
		this.sAutoPayment = sAutoPayment;
	}
	public boolean isAutoPay() {
		return autoPay;
	}
	public void setAutoPay(boolean autoPay) {
		this.autoPay = autoPay;
	}
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
	public String getUserContactNo() {
		return userContactNo;
	}
	public void setUserContactNo(String userContactNo) {
		this.userContactNo = userContactNo;
	}
	
	

}
