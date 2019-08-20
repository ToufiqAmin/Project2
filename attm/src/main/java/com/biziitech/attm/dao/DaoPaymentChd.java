package com.biziitech.attm.dao;

import java.util.List;

import com.biziitech.attm.model.ModelPaymentChd;

public interface DaoPaymentChd {

	public void savePaymentChd(ModelPaymentChd modelPaymentChd);
	
	public List<ModelPaymentChd> getPaymentChdIdByChilTran(Long childTranId);
	
}
