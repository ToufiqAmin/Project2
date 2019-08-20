package com.biziitech.attm.daoImp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TicketSellCancelRepository;
import com.biziitech.attm.dao.DaoTicketSellCancel;
import com.biziitech.attm.model.ModelTicketSellCancel;

@Service
public class DaoTicketSellCancelImp implements DaoTicketSellCancel{

	@Autowired
	private TicketSellCancelRepository ticketSellCancelRepository;
	
	
	@Override
	@Transactional
	public void saveTicketSellCancel(ModelTicketSellCancel modelTicketSellCancel) {
		// TODO Auto-generated method stub
		ticketSellCancelRepository.save(modelTicketSellCancel);
	}

}
