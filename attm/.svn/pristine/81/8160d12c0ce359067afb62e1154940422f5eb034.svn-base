package com.biziitech.attm.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.model.ModelTicketPurchaseChd;


@Transactional
public interface DaoTicketPurchaseChd {
	
	public void saveTicketPurchaseChd(ModelTicketPurchaseChd modelTicketPurchaseChd);
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdByMstId(Long ticketPurchaseMstId);
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdById(Long ticketPurchaseChdId);
	public List<ModelTicketPurchaseCustom> getTicketPurchaseChdByMst(Long ticketPurchaseMstId);

}
