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

import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.model.ModelUser;

@Entity(name="ATTM_TICKET_PURCHASE_CHD")
public class ModelTicketPurchaseChd {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TICKET_PURCHASE_CHD_ID")
	private Long ticketPurchaseChdId;
	
	@ManyToOne
	@JoinColumn(name="TICKET_PURCHASE_MST_ID")
	private ModelTicketPurchaseMst modelTicketPurchaseMst;
	
	@Column(name="TICKET_NO")
	private String ticketNo;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private ModelUser modelUser;
	
	@Column(name="PNR")
	private String pNR;
	
	@Column(name="TICKET_AMT_USD")
	private Double ticketAMTUSD;
	
	@Column(name="TICKET_AMT_BDT")
	private Double ticketAMTBDT;
	
	@ManyToOne
	@JoinColumn(name="FROM_COUNTRY")
	private ModelCountry countryFrom;
	
	@ManyToOne
	@JoinColumn(name="TO_COUNTRY")
	private ModelCountry countryTo;
	
	@ManyToOne
	@JoinColumn(name="FROM_CITY")
	private ModelCity cityFrom;
		
	@ManyToOne
	@JoinColumn(name="TO_CITY")
	private ModelCity cityTo;
	
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
	
	@Column(name="AGENT_PASSENGER_NAME")
	private String agentPassengerName;
	
	@Column(name="SELLING_PRICE_USD")
	private Double sellingPriceUSD;
	
	@Column(name="SELLING_PRICE_BDT")
	private Double sellingPriceBDT;
	
	@Column(name="PURCHASE_PRICE_USD")
	private Double purchasePriceUSD;
	
	@Column(name="PURCHASE_PRICE_BDT")
	private Double purchasePriceBDT;

	public Long getTicketPurchaseChdId() {
		return ticketPurchaseChdId;
	}

	public ModelTicketPurchaseMst getModelTicketPurchaseMst() {
		return modelTicketPurchaseMst;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public ModelUser getModelUser() {
		return modelUser;
	}

	public String getpNR() {
		return pNR;
	}

	public Double getTicketAMTUSD() {
		return ticketAMTUSD;
	}

	public Double getTicketAMTBDT() {
		return ticketAMTBDT;
	}

	public ModelCountry getCountryFrom() {
		return countryFrom;
	}

	public ModelCountry getCountryTo() {
		return countryTo;
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

	public void setTicketPurchaseChdId(Long ticketPurchaseChdId) {
		this.ticketPurchaseChdId = ticketPurchaseChdId;
	}

	public void setModelTicketPurchaseMst(ModelTicketPurchaseMst modelTicketPurchaseMst) {
		this.modelTicketPurchaseMst = modelTicketPurchaseMst;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public void setModelUser(ModelUser modelUser) {
		this.modelUser = modelUser;
	}

	public void setpNR(String pNR) {
		this.pNR = pNR;
	}

	public void setTicketAMTUSD(Double ticketAMTUSD) {
		this.ticketAMTUSD = ticketAMTUSD;
	}

	public void setTicketAMTBDT(Double ticketAMTBDT) {
		this.ticketAMTBDT = ticketAMTBDT;
	}

	public void setCountryFrom(ModelCountry countryFrom) {
		this.countryFrom = countryFrom;
	}

	public void setCountryTo(ModelCountry countryTo) {
		this.countryTo = countryTo;
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

	public String getAgentPassengerName() {
		return agentPassengerName;
	}

	public void setAgentPassengerName(String agentPassengerName) {
		this.agentPassengerName = agentPassengerName;
	}

	public ModelCity getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(ModelCity cityFrom) {
		this.cityFrom = cityFrom;
	}

	public ModelCity getCityTo() {
		return cityTo;
	}

	public void setCityTo(ModelCity cityTo) {
		this.cityTo = cityTo;
	}

	public Double getSellingPriceUSD() {
		return sellingPriceUSD;
	}

	public void setSellingPriceUSD(Double sellingPriceUSD) {
		this.sellingPriceUSD = sellingPriceUSD;
	}

	public Double getSellingPriceBDT() {
		return sellingPriceBDT;
	}

	public void setSellingPriceBDT(Double sellingPriceBDT) {
		this.sellingPriceBDT = sellingPriceBDT;
	}

	public Double getPurchasePriceUSD() {
		return purchasePriceUSD;
	}

	public void setPurchasePriceUSD(Double purchasePriceUSD) {
		this.purchasePriceUSD = purchasePriceUSD;
	}

	public Double getPurchasePriceBDT() {
		return purchasePriceBDT;
	}

	public void setPurchasePriceBDT(Double purchasePriceBDT) {
		this.purchasePriceBDT = purchasePriceBDT;
	}

	@Override
	public String toString() {
		return "ModelTicketPurchaseChd [ticketPurchaseChdId=" + ticketPurchaseChdId + ", modelTicketPurchaseMst="
				+ modelTicketPurchaseMst + ", ticketNo=" + ticketNo + ", modelUser=" + modelUser + ", pNR=" + pNR
				+ ", ticketAMTUSD=" + ticketAMTUSD + ", ticketAMTBDT=" + ticketAMTBDT + ", countryFrom=" + countryFrom
				+ ", countryTo=" + countryTo + ", cityFrom=" + cityFrom + ", cityTo=" + cityTo + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3
				+ ", active=" + active + ", sActive=" + sActive + ", enteredBy=" + enteredBy + ", entryTimeStamp="
				+ entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp=" + updateTimeStamp
				+ ", agentPassengerName=" + agentPassengerName + ", sellingPriceUSD=" + sellingPriceUSD
				+ ", sellingPriceBDT=" + sellingPriceBDT + ", purchasePriceUSD=" + purchasePriceUSD
				+ ", purchasePriceBDT=" + purchasePriceBDT + "]";
	}





	

}
