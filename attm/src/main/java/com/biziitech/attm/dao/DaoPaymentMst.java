package com.biziitech.attm.dao;

import java.util.List;

import com.biziitech.attm.model.ModelPaymentMst;

public interface DaoPaymentMst {
	
	public void savePaymentMst(ModelPaymentMst modelPaymentMst);
	public List<ModelPaymentMst> getPaymentMstByParentTranId(Long parentTranId);

}
