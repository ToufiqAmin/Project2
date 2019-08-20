package com.biziitech.attm.daoImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.PaymentChdRepository;
import com.biziitech.attm.dao.DaoPaymentChd;
import com.biziitech.attm.model.ModelPaymentChd;

@Service
public class DaoPaymentChdImp implements DaoPaymentChd{
	
	
	@Autowired
	private PaymentChdRepository paymentChdRepository;

	@Override
	public void savePaymentChd(ModelPaymentChd modelPaymentChd) {
		// TODO Auto-generated method stub
		paymentChdRepository.save(modelPaymentChd);
	}

	@Override
	public List<ModelPaymentChd> getPaymentChdIdByChilTran(Long childTranId) {
		// TODO Auto-generated method stub
		return paymentChdRepository.findPaymentChdByChildTranId(childTranId);
	}
	
	

}
