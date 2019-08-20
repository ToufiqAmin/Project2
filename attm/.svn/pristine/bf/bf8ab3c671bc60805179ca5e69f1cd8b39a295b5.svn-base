package com.biziitech.attm.bg.model;

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

@Entity(name="BG_USER")
public class ModelUser {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_DESIGNATION_ID")
	private int userDesignationId;
	
	@Column(name="LOGIN_USER")
	private String loginUser;
	
	@Column(name="SECURITY_CODE")
	private String securityCode;
	
	@Column(name="ORG_BRANCH_ID")
	private Long orgBranchId;
	
	@Column(name="BRANCH_UNIT_ID")
	private Long branchUnitId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="GENDER")
	private Integer genderStatus;
	
	@Column(name="BLOOD_GROUP")
	private String bloodGroup;
	
	@Column(name="SMS_FLAG")
	private String smsFlag;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FATHER_NAME")
	private String fatherName;
	
	@Column(name="MOTHER_NAME")
	private String motherName;
	
	@Column(name="PROFESSION")
	private String profession;
	
	@Column(name="USER_ADDRESS")
	private String userAddress;
	
	@Column(name="USER_POSTCODE")
	private Integer userPostcode;
	
	@Column(name="USER_CITY")
	private String userCity;
	
	@Column(name="USER_POLICE_STATION")
	private String userPoliceStation;
	
	@Column(name="USER_UPAZILLA")
	private String userUpazilla;
	
	@Column(name="USER_DISTRICT")
	private String userDistrict;
	
	@Column(name="USER_DIVISION")
	private String userDivision;
	
	@Column(name="NATIONAL_ID")
	private String nationalId;
	
	@ManyToOne
	@JoinColumn(name="USER_TYPE_ID")
	private ModelUserType modelUserType;
	
	@Column(name="USER_DEFINED_ID")
	private String userDefinedId;
	
	@Column(name="USER_GROUP")
	private String userGroup;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="SPOUSE_NAME")
	private String spouseName;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private ModelCountry modelCountry;
	
	@Column(name="ORG_ID")
	private Long orgId;
	
	@Column(name="TITLE_NAME")
	private String titleName;
	
	@Column(name="USER_IMAGE")
	private String userImage;
	
	@Column(name="ACTIVE_STATUS")
	private int activeStatus;
	
	@Column(name="USER_REMARKS")
	private String userRemarks;
	
	@Column(name="ORG_TYPE_REMARKS")
	private String orgTypeRemarks;
	
	@Column(name="ENTERED_BY")
	private Long enteredBy;
	
	@Column(name="ENTRY_TIMESTAMP")
	private Date entryTimestamp;
	
	@Column(name="UPDATED_BY")
	//@ColumnDefault("0")
	private Long updatedBy;
	
	@Column(name="UPDATE_TIMESTAMP" , nullable=true)
	private Date updateTimestap;
	
	@Column(name="FLEX_FIELD1")
	private String flex1  ;
	
	@Column(name="FLEX_FIELD2")
	private String flex2  ;
	
	@Column(name="FLEX_FIELD3")
	private String flex3  ;
	
	@Column(name="FLEX_FIELD4")
	private String flex4  ;
	
	@Column(name="FLEX_FIELD5")
	private String flex5  ;
	
	@Column(name="PASSPORT_NO")
	private String passportNo;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;
	
	@Transient
	private boolean gender;
	
	@Transient
	private String sGender;
	
    public ModelUser() {
    	
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

	public int getUserDesignationId() {
		return userDesignationId;
	}

	public void setUserDesignationId(int userDesignationId) {
		this.userDesignationId = userDesignationId;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}



	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserPoliceStation() {
		return userPoliceStation;
	}

	public void setUserPoliceStation(String userPoliceStation) {
		this.userPoliceStation = userPoliceStation;
	}

	public String getUserUpazilla() {
		return userUpazilla;
	}

	public void setUserUpazilla(String userUpazilla) {
		this.userUpazilla = userUpazilla;
	}

	public String getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}


	public String getUserDefinedId() {
		return userDefinedId;
	}

	public void setUserDefinedId(String userDefinedId) {
		this.userDefinedId = userDefinedId;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}



	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	

	public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public String getOrgTypeRemarks() {
		return orgTypeRemarks;
	}

	public void setOrgTypeRemarks(String orgTypeRemarks) {
		this.orgTypeRemarks = orgTypeRemarks;
	}



	public Date getEntryTimestamp() {
		return entryTimestamp;
	}

	public void setEntryTimestamp(Date entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	

	public Date getUpdateTimestap() {
		return updateTimestap;
	}

	public void setUpdateTimestap(Date updateTimestap) {
		this.updateTimestap = updateTimestap;
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

	public String getFlex4() {
		return flex4;
	}

	public void setFlex4(String flex4) {
		this.flex4 = flex4;
	}

	public String getFlex5() {
		return flex5;
	}

	public void setFlex5(String flex5) {
		this.flex5 = flex5;
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


	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Long getEnteredBy() {
		return enteredBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setEnteredBy(Long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getBranchUnitId() {
		return branchUnitId;
	}

	public void setBranchUnitId(Long branchUnitId) {
		this.branchUnitId = branchUnitId;
	}


	public boolean isGender() {
		return gender;
	}

	public String getsGender() {
		return sGender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setsGender(String sGender) {
		this.sGender = sGender;
	}


	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public ModelUserType getModelUserType() {
		return modelUserType;
	}

	public void setModelUserType(ModelUserType modelUserType) {
		this.modelUserType = modelUserType;
	}

	public ModelCountry getModelCountry() {
		return modelCountry;
	}

	public void setModelCountry(ModelCountry modelCountry) {
		this.modelCountry = modelCountry;
	}

	public Long getOrgBranchId() {
		return orgBranchId;
	}

	public Integer getUserPostcode() {
		return userPostcode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgBranchId(Long orgBranchId) {
		this.orgBranchId = orgBranchId;
	}

	public void setUserPostcode(Integer userPostcode) {
		this.userPostcode = userPostcode;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Integer getGenderStatus() {
		return genderStatus;
	}

	public void setGenderStatus(Integer genderStatus) {
		this.genderStatus = genderStatus;
	}

	



	
}
