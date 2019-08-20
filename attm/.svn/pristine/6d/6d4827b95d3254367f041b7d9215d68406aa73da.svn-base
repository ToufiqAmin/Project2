package com.biziitech.attm.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.custom.model.ModelTicketSellCustom;
import com.biziitech.attm.model.ModelTicketSellMst;

@Transactional
public interface DaoTicketSellMst {
	
	public void saveTicketSellMst(ModelTicketSellMst modelTicketSellMst);
	public List<ModelTicketSellMst> getTicketSellMstById(Long ticketSellMstId);
	public List<ModelTicketSellMst> getTicketSellMstByPurchaseMstId(Long ticketPurchaseMstId);
	public List<ModelTicketSellCustom> getTicketSellMstByRequestId(Long ticketRequestId);
	public List<ModelTicketSellCustom> getTicketSellMstByMstId(Long ticketSellMstId);
	
	public List<ModelTicketPurchaseCustom> getSelfTicketPurchase();
	
	public List<ModelTicketSellCustom> getTicketSellUnDoneChdDetails(Long userId,
			Long fromCountryId,Long fromCityId,Long toCountryId,Long toCityId,
			Long fromAgentId,Long toAgentId,Date fromDate, Date toDate);
	
	public List<ModelTicketSellCustom> getTicketSellDoneChdDetails(Long userId,
			Long fromCountryId,Long fromCityId,Long toCountryId,Long toCityId,
			Long fromAgentId,Long toAgentId,Date fromDate, Date toDate);

}
