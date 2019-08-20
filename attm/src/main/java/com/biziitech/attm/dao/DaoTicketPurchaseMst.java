package com.biziitech.attm.dao;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.model.ModelTicketPurchaseMst;

@Transactional
public interface DaoTicketPurchaseMst {
	
	public void saveTicketPurchaseMst(ModelTicketPurchaseMst modelTicketPurchaseMst);
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstUnDone(Long fromAgentId,Long toAgentId, String passportNo, 
			Long userId, Long fromCountryId, Long fromCityId, Long toCountryId, Long toCityId,Date fromDate, Date toDate);
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstDone(Long fromAgentId,Long toAgentId, String passportNo,
			Long userId, Long fromCountryId, Long fromCityId, Long toCountryId, Long toCityId,Date fromDate, Date toDate);
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstById(Long ticketPurchaseMstId);
	
	public List<ModelTicketPurchaseMst> getTicketPurchaseMstByMstId(Long ticketPurchaseMstId);

}
