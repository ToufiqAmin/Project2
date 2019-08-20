package com.biziitech.attm.dao;

import javax.transaction.Transactional;

import com.biziitech.attm.model.ModelTicketPurchaseCancel;

@Transactional
public interface DaoTicketPurchaseCancel {
	
	public void saveTicketPurchaseCancel(ModelTicketPurchaseCancel modelTicketPurchaseCancel);

}
