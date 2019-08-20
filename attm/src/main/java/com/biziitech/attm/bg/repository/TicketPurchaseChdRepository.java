package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketPurchaseChd;


public interface TicketPurchaseChdRepository extends JpaRepository<ModelTicketPurchaseChd, Long>{
	
	@Query("select a from ATTM_TICKET_PURCHASE_CHD a where a.activeStatus=1 and a.ticketPurchaseChdId=COALESCE(:ticketPurchaseChdId, a.ticketPurchaseChdId)")
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdById(@Param("ticketPurchaseChdId")Long ticketPurchaseChdId);
	
	@Query("select a from ATTM_TICKET_PURCHASE_CHD a where a.activeStatus=1 and a.modelTicketPurchaseMst.ticketPurchaseMstId=COALESCE(:ticketPurchaseMstId, a.modelTicketPurchaseMst.ticketPurchaseMstId)")
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdByMstId(@Param("ticketPurchaseMstId")Long ticketPurchaseMstId);

}
