package com.biziitech.attm.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TicketRequestCancelRepository;
import com.biziitech.attm.dao.DaoTicketRequestCancel;
import com.biziitech.attm.model.ModelTicketRequestCancel;

@Service
public class DaoTicketRequestCancelImp implements DaoTicketRequestCancel{
	
	@Autowired
	private TicketRequestCancelRepository tivcketRequestCancelReposiotry;

	@Override
	public void saveTicketRequestCancel(ModelTicketRequestCancel modelTicketRequestCancel) {
		// TODO Auto-generated method stub
		if(modelTicketRequestCancel.isActive()) 
		{
			modelTicketRequestCancel.setActiveStatus(1);
		}
		else 
		{
			modelTicketRequestCancel.setActiveStatus(0);
		}
		
		tivcketRequestCancelReposiotry.save(modelTicketRequestCancel);
		
	}

	@Override
	public List<ModelTicketRequestCancel> getCancelById(Long ticketRequestCancelId) {
		// TODO Auto-generated method stub
		List<ModelTicketRequestCancel> resultList= tivcketRequestCancelReposiotry.getCancelById(ticketRequestCancelId);
		
		List<ModelTicketRequestCancel> ticketRequestCancelList=new ArrayList<>();
		
		for(ModelTicketRequestCancel modelTicketRequestCancel: resultList) {
				if(modelTicketRequestCancel.getActiveStatus()==1)
				 { 
					modelTicketRequestCancel.setsActive("Yes");
					modelTicketRequestCancel.setActive(true);
				 }
				 
				 else
				 {
					 modelTicketRequestCancel.setsActive("NO");
					 modelTicketRequestCancel.setActive(false);
				     
				 }
				
				
				 
				ticketRequestCancelList.add(modelTicketRequestCancel);
		}
		
		
		
		return ticketRequestCancelList;
	}

}
