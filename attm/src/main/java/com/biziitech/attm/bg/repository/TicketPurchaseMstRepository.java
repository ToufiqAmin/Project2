package com.biziitech.attm.bg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketPurchaseMst;


public interface TicketPurchaseMstRepository extends JpaRepository<ModelTicketPurchaseMst, Long>{
	
	@Query("select a from ATTM_TICKET_PURCHASE_MST a where a.activeStatus=1 and a.ticketPurchaseMstId=COALESCE(:ticketPurchaseMstId, a.ticketPurchaseMstId)")
	public List<ModelTicketPurchaseMst> getTicketPurchaseMstById(@Param("ticketPurchaseMstId")Long ticketPurchaseMstId);
	
	

}
