package com.biziitech.attm.custom.model;

import java.util.Date;

public class ModelBgUserCustom {
	
	private Long userId;
	private String userName;
	private String passportNo;
	private String fatherName;
	private String motherName;
	private String remarks;
	private Date dob;
	private String userAddress;
	private String contactNo;
	
	public Long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public String getRemarks() {
		return remarks;
	}
	public Date getDob() {
		return dob;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	

}
