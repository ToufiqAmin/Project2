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
import com.biziitech.attm.bg.model.ModelUser;

@Entity(name="ATTM_TRAN")
public class ModelTran {
	
	
	@Id @GenericGenerator(name = "custom_sequence", strategy = 
			"com.biziitech.attm.IdGenerator")
	@GeneratedValue(generator = "custom_sequence")
	@Column(name="TRAN_ID")
	private Long tranId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="TRAN_DATE")
	private Date tranDate;
	
	@ManyToOne
	@JoinColumn(name="TRAN_TYPE_ID")
	private ModelTranType modelTranType;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private ModelUser modelUser;
	
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private ModelAgent modelAgent;
	
	@ManyToOne
	@JoinColumn(name="AIRLINE_ID")
	private ModelAirLine modelAirLine;
	
	@Column(name="BASE_TRAN_ID")
	private Long baseTranId;
	
	@Column(name="BASE_TRAN_TABLE")
	private String baseTranTable;
	
	@Column(name="TRAN_AMT")
	private Double tranAMT;
	
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private ModelCurrency modelCurrency;
	
	@Column(name="PARENT_TRAN_ID")
	private Long parentTranId;
	
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
	
	@Column(name="TRAN_OWNER_TYPE_ID")
	private Integer tranOwnerTypeId;
	
	@Column(name="TRAN_OWNER_ID")
	private Long tranOwnerId;
	
	@Column(name="OTHER_OWNER_TYPE_ID")
	private Integer otherOwnerTypeId;
	
	@Column(name="OTHER_OWNER_ID")
	private Long otherOwnerId;
	
	@Column(name="PAIR_TRAN_ID")
	private Long pairTranId;
	

	
	@Transient
	private boolean active;
	
	@Transient
	private String sActive;

	public Long getTranId() {
		return tranId;
	}

	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public ModelTranType getModelTranType() {
		return modelTranType;
	}

	public void setModelTranType(ModelTranType modelTranType) {
		this.modelTranType = modelTranType;
	}

	public ModelUser getModelUser() {
		return modelUser;
	}

	public void setModelUser(ModelUser modelUser) {
		this.modelUser = modelUser;
	}

	public ModelAgent getModelAgent() {
		return modelAgent;
	}

	public void setModelAgent(ModelAgent modelAgent) {
		this.modelAgent = modelAgent;
	}

	public ModelAirLine getModelAirLine() {
		return modelAirLine;
	}

	public void setModelAirLine(ModelAirLine modelAirLine) {
		this.modelAirLine = modelAirLine;
	}

	public Long getBaseTranId() {
		return baseTranId;
	}

	public void setBaseTranId(Long baseTranId) {
		this.baseTranId = baseTranId;
	}

	public String getBaseTranTable() {
		return baseTranTable;
	}

	public void setBaseTranTable(String baseTranTable) {
		this.baseTranTable = baseTranTable;
	}

	public Double getTranAMT() {
		return tranAMT;
	}

	public void setTranAMT(Double tranAMT) {
		this.tranAMT = tranAMT;
	}

	public ModelCurrency getModelCurrency() {
		return modelCurrency;
	}

	public void setModelCurrency(ModelCurrency modelCurrency) {
		this.modelCurrency = modelCurrency;
	}

	public Long getParentTranId() {
		return parentTranId;
	}

	public void setParentTranId(Long parentTranId) {
		this.parentTranId = parentTranId;
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

	public Integer getTranOwnerTypeId() {
		return tranOwnerTypeId;
	}

	public void setTranOwnerTypeId(Integer tranOwnerTypeId) {
		this.tranOwnerTypeId = tranOwnerTypeId;
	}

	public Long getTranOwnerId() {
		return tranOwnerId;
	}

	public void setTranOwnerId(Long tranOwnerId) {
		this.tranOwnerId = tranOwnerId;
	}

	public Integer getOtherOwnerTypeId() {
		return otherOwnerTypeId;
	}

	public void setOtherOwnerTypeId(Integer otherOwnerTypeId) {
		this.otherOwnerTypeId = otherOwnerTypeId;
	}

	public Long getOtherOwnerId() {
		return otherOwnerId;
	}

	public void setOtherOwnerId(Long otherOwnerId) {
		this.otherOwnerId = otherOwnerId;
	}

	public Long getPairTranId() {
		return pairTranId;
	}

	public void setPairTranId(Long pairTranId) {
		this.pairTranId = pairTranId;
	}

	@Override
	public String toString() {
		return "ModelTran [tranId=" + tranId + ", tranDate=" + tranDate + ", modelTranType=" + modelTranType
				+ ", modelUser=" + modelUser + ", modelAgent=" + modelAgent + ", modelAirLine=" + modelAirLine
				+ ", baseTranId=" + baseTranId + ", baseTranTable=" + baseTranTable + ", tranAMT=" + tranAMT
				+ ", modelCurrency=" + modelCurrency + ", parentTranId=" + parentTranId + ", activeStatus="
				+ activeStatus + ", remarks=" + remarks + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3
				+ ", tranOwnerTypeId=" + tranOwnerTypeId + ", tranOwnerId=" + tranOwnerId + ", otherOwnerTypeId="
				+ otherOwnerTypeId + ", otherOwnerId=" + otherOwnerId + ", pairTranId=" + pairTranId + ", active="
				+ active + ", sActive=" + sActive + "]";
	}

	
	
	

}
