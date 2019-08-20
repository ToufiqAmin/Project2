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

import com.biziitech.attm.bg.model.ModelCurrency;

@Entity(name="ATTM_PAYMENT_MST")
public class ModelPaymentMst {
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="PAYMENT_MST_ID")
	private Long paymentMstId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="PAYMENT_DATE")
	private Date paymentDate;
	
	@Column(name="PARENT_TRAN_ID")
	private Long parentTranId;
	
	@Column(name="PAYMENT_AMOUNT")
	private Double paymentAmount;
	
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private ModelCurrency modelCurrency;
	
	@Column(name="PAYMENT_TYPE")
	private Integer paymentType;
	
	@Column(name="PAYMENT_MODE_ID")
	private Long paymentModelId;
	
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

	public Long getPaymentMstId() {
		return paymentMstId;
	}

	public void setPaymentMstId(Long paymentMstId) {
		this.paymentMstId = paymentMstId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public ModelCurrency getModelCurrency() {
		return modelCurrency;
	}

	public void setModelCurrency(ModelCurrency modelCurrency) {
		this.modelCurrency = modelCurrency;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Long getPaymentModelId() {
		return paymentModelId;
	}

	public void setPaymentModelId(Long paymentModelId) {
		this.paymentModelId = paymentModelId;
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

	public Long getParentTranId() {
		return parentTranId;
	}

	public void setParentTranId(Long parentTranId) {
		this.parentTranId = parentTranId;
	}

	@Override
	public String toString() {
		return "ModelPaymentMst [paymentMstId=" + paymentMstId + ", paymentDate=" + paymentDate + ", parentTranId="
				+ parentTranId + ", paymentAmount=" + paymentAmount + ", modelCurrency=" + modelCurrency
				+ ", paymentType=" + paymentType + ", paymentModelId=" + paymentModelId + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", enteredBy=" + enteredBy + ", entryTimeStamp="
				+ entryTimeStamp + ", updatedBy=" + updatedBy + ", updateTimeStamp=" + updateTimeStamp + ", active="
				+ active + ", sActive=" + sActive + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3 + "]";
	}

	
	
	

}
