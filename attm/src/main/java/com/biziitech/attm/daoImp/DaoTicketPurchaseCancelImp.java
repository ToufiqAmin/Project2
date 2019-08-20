package com.biziitech.attm.daoImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TicketPurchaseCancelRepository;
import com.biziitech.attm.dao.DaoTicketPurchaseCancel;
import com.biziitech.attm.model.ModelTicketPurchaseCancel;

@Service
public class DaoTicketPurchaseCancelImp implements DaoTicketPurchaseCancel{
	
	@Autowired
	private TicketPurchaseCancelRepository ticketPurchaseCancelRepository;

	@Override
	public void saveTicketPurchaseCancel(ModelTicketPurchaseCancel modelTicketPurchaseCancel) {
		// TODO Auto-generated method stub
		ticketPurchaseCancelRepository.save(modelTicketPurchaseCancel);
	}

}
