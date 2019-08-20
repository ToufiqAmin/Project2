package com.biziitech.attm.custom.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


import com.biziitech.attm.bg.model.ModelCountry;


public class ModelClientServiceCustom {
	
	
	private Long clientServiceId;
	private Long clientTypeId;
	private String clientTypeName;
	private Long clientId;
	private String clientName;
	private Long serviceId;
	private String serviceName;
	private String careOfName;
	private String careOfContactNo;
	private Long fromCountryId;
	private String fromCountryName;
	private Long fromCityId;
	private String fromCityName;
	private Long toCountryId;
	private String toCountryName;
	private Long toCity;
	private String toCityName;
	private Double agreementAMT;
	private Integer qTY;
	private int activeStatus;
	private String remarks;
	private boolean active;
	private String sActive;
	
	
	public Long getClientServiceId() {
		return clientServiceId;
	}
	public Long getClientTypeId() {
		return clientTypeId;
	}
	public String getClientTypeName() {
		return clientTypeName;
	}
	public Long getClientId() {
		return clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getCareOfName() {
		return careOfName;
	}
	public String getCareOfContactNo() {
		return careOfContactNo;
	}
	public Long getFromCountryId() {
		return fromCountryId;
	}
	public String getFromCountryName() {
		return fromCountryName;
	}
	public Long getFromCityId() {
		return fromCityId;
	}
	public String getFromCityName() {
		return fromCityName;
	}
	public Long getToCountryId() {
		return toCountryId;
	}
	public String getToCountryName() {
		return toCountryName;
	}
	public Long getToCity() {
		return toCity;
	}
	public String getToCityName() {
		return toCityName;
	}
	public Double getAgreementAMT() {
		return agreementAMT;
	}
	public Integer getqTY() {
		return qTY;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public boolean isActive() {
		return active;
	}
	public String getsActive() {
		return sActive;
	}
	public void setClientServiceId(Long clientServiceId) {
		this.clientServiceId = clientServiceId;
	}
	public void setClientTypeId(Long clientTypeId) {
		this.clientTypeId = clientTypeId;
	}
	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setCareOfName(String careOfName) {
		this.careOfName = careOfName;
	}
	public void setCareOfContactNo(String careOfContactNo) {
		this.careOfContactNo = careOfContactNo;
	}
	public void setFromCountryId(Long fromCountryId) {
		this.fromCountryId = fromCountryId;
	}
	public void setFromCountryName(String fromCountryName) {
		this.fromCountryName = fromCountryName;
	}
	public void setFromCityId(Long fromCityId) {
		this.fromCityId = fromCityId;
	}
	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}
	public void setToCountryId(Long toCountryId) {
		this.toCountryId = toCountryId;
	}
	public void setToCountryName(String toCountryName) {
		this.toCountryName = toCountryName;
	}
	public void setToCity(Long toCity) {
		this.toCity = toCity;
	}
	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}
	public void setAgreementAMT(Double agreementAMT) {
		this.agreementAMT = agreementAMT;
	}
	public void setqTY(Integer qTY) {
		this.qTY = qTY;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setsActive(String sActive) {
		this.sActive = sActive;
	}

}
