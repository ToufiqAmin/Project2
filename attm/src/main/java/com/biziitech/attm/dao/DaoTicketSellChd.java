package com.biziitech.attm.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.biziitech.attm.custom.model.ModelTicketSellCustom;
import com.biziitech.attm.model.ModelTicketSellChd;

@Transactional
public interface DaoTicketSellChd {
	
	public void saveTicketSellChd(ModelTicketSellChd modelTicketSellChd);
	public List<ModelTicketSellChd> getTicketSellChdByMstId(Long ticketSellMstId);
	public List<ModelTicketSellCustom> getTicketSellChdByMstId2(Long ticketSellMstId);
	public List<ModelTicketSellCustom> getTicketSellChdById(Long ticketSellChdId);
	public List<ModelTicketSellChd> getTicketSellChdByTicketPurchaseChdId(Long ticketPurchaseChdId);

}
