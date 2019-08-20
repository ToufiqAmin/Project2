package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketSellChd;

public interface TicketSellChdRepository extends JpaRepository<ModelTicketSellChd, Long>{
	
	
	@Query("select a from ATTM_TICKET_SELL_CHD a where a.activeStatus=1 "
			+ "and a.modelTicketPurChaseChd.ticketPurchaseChdId=COALESCE(:ticketPurchaseChdId, a.modelTicketPurChaseChd.ticketPurchaseChdId)")
	public List<ModelTicketSellChd> findTicketSellChdByTicketPurchaseChdId(@Param("ticketPurchaseChdId")Long ticketPurchaseChdId);
	
	@Query("select a from ATTM_TICKET_SELL_CHD a where a.activeStatus=1 "
			+ "and a.modelTicketSellMst.ticketSellMstId=COALESCE(:ticketSellMstId, a.modelTicketSellMst.ticketSellMstId)")
	public List<ModelTicketSellChd> findTicketSellChdByTicketSellMstId(@Param("ticketSellMstId")Long ticketSellMstId);
	
	@Query("select a from ATTM_TICKET_SELL_CHD a where a.activeStatus=1 "
			+ "and a.ticketSellChdId=COALESCE(:ticketSellChdId, a.ticketSellChdId)")
	public List<ModelTicketSellChd> findTicketSellChdById(@Param("ticketSellChdId")Long ticketSellChdId);

}
