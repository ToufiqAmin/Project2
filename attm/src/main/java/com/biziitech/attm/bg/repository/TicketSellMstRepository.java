package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketSellMst;

public interface TicketSellMstRepository extends JpaRepository<ModelTicketSellMst, Long>{
	
	@Query("select a from ATTM_TICKET_SELL_MST a where a.activeStatus=1 and a.ticketSellMstId=COALESCE(:ticketSellMstId, a.ticketSellMstId)")
	public List<ModelTicketSellMst> findTicketSellMstById(@Param("ticketSellMstId")Long ticketSellMstId);
	
	@Query("select a from ATTM_TICKET_SELL_MST a where a.activeStatus=1 and a.modelTicketPurchaseMst.ticketPurchaseMstId=COALESCE(:ticketPurchaseMstId, a.modelTicketPurchaseMst.ticketPurchaseMstId)")
	public List<ModelTicketSellMst> findTicketSellMstByPurchaseMstId(@Param("ticketPurchaseMstId")Long ticketPurchaseMstId);
	
	
	@Query("select a from ATTM_TICKET_SELL_MST a where a.activeStatus=1 and a.modelTicketRequest.ticketRequestId=COALESCE(:ticketRequestId, a.modelTicketRequest.ticketRequestId)")
	public List<ModelTicketSellMst> findTicketSellMstByRequestId(@Param("ticketRequestId")Long ticketRequestId);
	

}
