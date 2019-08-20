package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelPaymentChd;

public interface PaymentChdRepository extends JpaRepository<ModelPaymentChd, Long>{
	
	@Query("select a from ATTM_PAYMENT_CHD a where a.childTranId=COALESCE(:childTranId, a.childTranId)")
	public List<ModelPaymentChd> findPaymentChdByChildTranId(@Param("childTranId")Long childTranId);

}
