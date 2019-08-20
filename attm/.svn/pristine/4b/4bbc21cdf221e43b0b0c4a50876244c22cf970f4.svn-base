package com.biziitech.attm.daoImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.PaymentMstRepository;
import com.biziitech.attm.dao.DaoPaymentMst;
import com.biziitech.attm.model.ModelPaymentMst;

@Service
public class DaoPaymentMstImp implements DaoPaymentMst{
	
	
	@Autowired
	private PaymentMstRepository paymentMstRepository;

	@Override
	public void savePaymentMst(ModelPaymentMst modelPaymentMst) {
		// TODO Auto-generated method stub
		paymentMstRepository.save(modelPaymentMst);
	}

	@Override
	public List<ModelPaymentMst> getPaymentMstByParentTranId(Long parentTranId) {
		// TODO Auto-generated method stub
		//return paymentMstRepository.findPaymentMstByparentTranId(parentTranId);
		return null;
	}

}
