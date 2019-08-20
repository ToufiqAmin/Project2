package com.biziitech.attm.dao;

import javax.transaction.Transactional;

import com.biziitech.attm.model.ModelTicketSellCancel;

@Transactional
public interface DaoTicketSellCancel {
	
	public void saveTicketSellCancel(ModelTicketSellCancel modelTicketSellCancel);

}
