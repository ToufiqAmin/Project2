package com.biziitech.attm.bg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelPaymentChd;
import com.biziitech.attm.model.ModelPaymentMst;
import com.biziitech.attm.model.ModelTran;

public interface AgentLedgerRepository   extends JpaRepository<ModelTran, Long>{
  
	@Query("select a from ATTM_TRAN a where a.tranOwnerId=COALESCE(:agentId,a.tranOwnerId) and a.tranDate BETWEEN coalesce(:startDate,a.tranDate) AND coalesce(:endDate,a.tranDate) and a.tranOwnerTypeId=1")
	public List<ModelTran> findTranDetails(@Param("agentId")Long agentId,@Param("startDate")Date startDate,@Param("endDate")Date endDate );
	
	//this query created for get amount on 25/06/2018
	
	@Query("select a from ATTM_PAYMENT_MST a where a.parentTranId=COALESCE(:parentTranId, a.parentTranId)")
    public List<ModelPaymentMst> findAmountFromMst(@Param("parentTranId")Long parentTranId);
	
	
	@Query("select a from ATTM_PAYMENT_CHD a where a.childTranId=COALESCE(:childTranId, a.childTranId)")
    public List<ModelPaymentChd> findAmountFromChd(@Param("childTranId")Long childTranId);
	
}
