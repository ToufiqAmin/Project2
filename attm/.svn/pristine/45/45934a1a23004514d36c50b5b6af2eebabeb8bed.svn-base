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
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;

@Entity(name="ATTM_TICKET_REQUEST")
public class ModelTicketRequest {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_REQUEST_ID")
	private Long ticketRequestId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=true)
	private ModelBgUser modelUser_ATTM;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="POSSIBLE_FLIGHT_DATE")
	private Date possibleFlightDate;
		
	@ManyToOne
	@JoinColumn(name="COUNTRY_FROM")
	private ModelCountry countryFrom;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_TO")
	private ModelCountry countryTo;
	
	@ManyToOne
	@JoinColumn(name="CITY_FROM")
	private ModelCity fromCity;
		
	@ManyToOne
	@JoinColumn(name="CITY_TO")
	private ModelCity toCity;
	
	@Column(name="NO_OF_TICKETS")
	private Integer noOfTickets;
	
	@Column(name="APPROX_AMT")
	private Double approxAMT;
	
	@Column(name="AGREEMENT_AMT")
	private Double agreementAMT;
	
	@ManyToOne
	@JoinColumn(name="AIRLINE_ID")
	private ModelAirLine modelAirLine;
	
	@ManyToOne
	@JoinColumn(name="FROM_AGENT_ID", nullable=true)
	//@MapsId
	private ModelAgent fromAgent;
	
	@ManyToOne
	@JoinColumn(name="TO_AGENT_ID", nullable=true)
	//@MapsId
	private ModelAgent toAgent;
	
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private ModelAgent modelAgent;
	
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
	
	@Column(name="PO_REMARKS")
	private String pORemarks;
	
	@Column(name="PO_FLAG")
	private int pOFlagStatus;
	
	@Transient
	private boolean pOFlag;
	
	@Transient
	private String sPOFlag;
	
	@Transient
	private boolean autoPay;
	
	@Transient
	private String sAutoPayment;
	
	@Column(name="REQUESTER_ID")
	private Long requesterId;
	
	@Column(name="REQUESTER_NAME")
	private String requesterName;
	
	@Column(name="REQUESTER_CONTACT_PHONE")
	private String requesterContactPhone;
	
	@ManyToOne
	@JoinColumn(name="TICKET_OWNER_COMPANY_ID")
	private ModelTicketOwnerCompany modelTicketOwnerCompany;
	
	@Column(name="AUTO_PAYMENT")
	private int autoPayment;
	
	@Column(name="ADV_PAYMENT")
	private Double advPayment;
	
	public Long getTicketRequestId() {
		return ticketRequestId;
	}	

	public ModelBgUser getModelUser_ATTM() {
		return modelUser_ATTM;
	}

	public Date getPossibleFlightDate() {
		return possibleFlightDate;
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

	public void setTicketRequestId(Long ticketRequestId) {
		this.ticketRequestId = ticketRequestId;
	}

	public void setModelUser_ATTM(ModelBgUser modelUser_ATTM) {
		this.modelUser_ATTM = modelUser_ATTM;
	}

	public void setPossibleFlightDate(Date possibleFlightDate) {
		this.possibleFlightDate = possibleFlightDate;
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getNoOfTickets() {
		return noOfTickets;
	}

	public Double getApproxAMT() {
		return approxAMT;
	}

	public Double getAgreementAMT() {
		return agreementAMT;
	}

	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public void setApproxAMT(Double approxAMT) {
		this.approxAMT = approxAMT;
	}

	public void setAgreementAMT(Double agreementAMT) {
		this.agreementAMT = agreementAMT;
	}

	public ModelAirLine getModelAirLine() {
		return modelAirLine;
	}

	public ModelAgent getModelAgent() {
		return modelAgent;
	}

	public void setModelAirLine(ModelAirLine modelAirLine) {
		this.modelAirLine = modelAirLine;
	}

	public void setModelAgent(ModelAgent modelAgent) {
		this.modelAgent = modelAgent;
	}

	public String getpORemarks() {
		return pORemarks;
	}

	public int getpOFlagStatus() {
		return pOFlagStatus;
	}

	public boolean ispOFlag() {
		return pOFlag;
	}

	public String getsPOFlag() {
		return sPOFlag;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public String getRequesterContactPhone() {
		return requesterContactPhone;
	}

	public void setpORemarks(String pORemarks) {
		this.pORemarks = pORemarks;
	}

	public void setpOFlagStatus(int pOFlagStatus) {
		this.pOFlagStatus = pOFlagStatus;
	}

	public void setpOFlag(boolean pOFlag) {
		this.pOFlag = pOFlag;
	}

	public void setsPOFlag(String sPOFlag) {
		this.sPOFlag = sPOFlag;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public void setRequesterContactPhone(String requesterContactPhone) {
		this.requesterContactPhone = requesterContactPhone;
	}

	public ModelCountry getCountryFrom() {
		return countryFrom;
	}

	public ModelCountry getCountryTo() {
		return countryTo;
	}

	public void setCountryFrom(ModelCountry countryFrom) {
		this.countryFrom = countryFrom;
	}

	public void setCountryTo(ModelCountry countryTo) {
		this.countryTo = countryTo;
	}

	public ModelCity getFromCity() {
		return fromCity;
	}

	public void setFromCity(ModelCity fromCity) {
		this.fromCity = fromCity;
	}

	public ModelCity getToCity() {
		return toCity;
	}

	public void setToCity(ModelCity toCity) {
		this.toCity = toCity;
	}

	public ModelAgent getFromAgent() {
		return fromAgent;
	}

	public void setFromAgent(ModelAgent fromAgent) {
		this.fromAgent = fromAgent;
	}

	public ModelAgent getToAgent() {
		return toAgent;
	}

	public void setToAgent(ModelAgent toAgent) {
		this.toAgent = toAgent;
	}

	public ModelTicketOwnerCompany getModelTicketOwnerCompany() {
		return modelTicketOwnerCompany;
	}

	public void setModelTicketOwnerCompany(ModelTicketOwnerCompany modelTicketOwnerCompany) {
		this.modelTicketOwnerCompany = modelTicketOwnerCompany;
	}

	public int getAutoPayment() {
		return autoPayment;
	}

	public void setAutoPayment(int autoPayment) {
		this.autoPayment = autoPayment;
	}

	public Double getAdvPayment() {
		return advPayment;
	}

	public void setAdvPayment(Double advPayment) {
		this.advPayment = advPayment;
	}

	public boolean isAutoPay() {
		return autoPay;
	}

	public void setAutoPay(boolean autoPay) {
		this.autoPay = autoPay;
	}

	public String getsAutoPayment() {
		return sAutoPayment;
	}

	public void setsAutoPayment(String sAutoPayment) {
		this.sAutoPayment = sAutoPayment;
	}

	@Override
	public String toString() {
		return "ModelTicketRequest [ticketRequestId=" + ticketRequestId + ", requestDate=" + requestDate
				+ ", modelUser_ATTM=" + modelUser_ATTM + ", possibleFlightDate=" + possibleFlightDate + ", countryFrom="
				+ countryFrom + ", countryTo=" + countryTo + ", fromCity=" + fromCity + ", toCity=" + toCity
				+ ", noOfTickets=" + noOfTickets + ", approxAMT=" + approxAMT + ", agreementAMT=" + agreementAMT
				+ ", modelAirLine=" + modelAirLine + ", fromAgent=" + fromAgent + ", toAgent=" + toAgent
				+ ", modelAgent=" + modelAgent + ", activeStatus=" + activeStatus + ", remarks=" + remarks + ", flex1="
				+ flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + ", active=" + active + ", sActive=" + sActive
				+ ", pORemarks=" + pORemarks + ", pOFlagStatus=" + pOFlagStatus + ", pOFlag=" + pOFlag + ", sPOFlag="
				+ sPOFlag + ", autoPay=" + autoPay + ", sAutoPayment=" + sAutoPayment + ", requesterId=" + requesterId
				+ ", requesterName=" + requesterName + ", requesterContactPhone=" + requesterContactPhone
				+ ", modelTicketOwnerCompany=" + modelTicketOwnerCompany + ", autoPayment=" + autoPayment
				+ ", advPayment=" + advPayment + "]";
	}








}
