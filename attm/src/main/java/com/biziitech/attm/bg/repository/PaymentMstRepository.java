package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelPaymentMst;



public interface PaymentMstRepository extends JpaRepository<ModelPaymentMst, Long>{
	
	//@Query("select a from ATTM_PAYMENT_MST a where a.parentTranId=COALESCE(:parentTranId, a.parentTranId) and a.baseTranId=COALESCE(:parentTranId, a.baseTranId)")
	//public List<ModelPaymentMst> findPaymentMstByparentTranId(@Param("parentTranId")Long parentTranId);
	

}
