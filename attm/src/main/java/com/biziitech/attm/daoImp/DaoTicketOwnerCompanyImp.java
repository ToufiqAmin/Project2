package com.biziitech.attm.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TicketOwnerCompanyRepository;
import com.biziitech.attm.dao.DaoTicketOwnerCompany;
import com.biziitech.attm.model.ModelTicketOwnerCompany;

@Service
public class DaoTicketOwnerCompanyImp implements DaoTicketOwnerCompany{
	
	@Autowired
	private TicketOwnerCompanyRepository ticketOwnerCompanyRepository;

	@Override
	public void saveTicketOwnerCompany(ModelTicketOwnerCompany modelTicketOwnerCompany) {
		// TODO Auto-generated method stub
		
	/*	
		if(modelTicketOwnerCompany.isActive()) 
		{
			modelTicketOwnerCompany.setActiveStatus(1);
		}
		else {
			modelTicketOwnerCompany.setActiveStatus(0);
		}
	*/
	
		/*
		if(modelTicketOwnerCompany.isTicketTypeAir()) 
		{
			modelTicketOwnerCompany.setTicketType(1);
		}
		else if(modelTicketOwnerCompany.isTicketTypeTrain()) 
		{
			modelTicketOwnerCompany.setTicketType(2);
		}
		else 
		{
			modelTicketOwnerCompany.setTicketType(3);
		}
	*/	
		
		ticketOwnerCompanyRepository.save(modelTicketOwnerCompany);
	}

	@Override
	public List<ModelTicketOwnerCompany> getTicketOwnerCompanyByCraiteria(String ownerCompanyName, String shortCode,
			String remarks, int status) {
		// TODO Auto-generated method stub
		List<ModelTicketOwnerCompany> resultList= ticketOwnerCompanyRepository.findTicketOwnerCompanyDetails(ownerCompanyName, shortCode, remarks, status);
		
		List<ModelTicketOwnerCompany> ticketCompanyList=new ArrayList<>();
		
		for(ModelTicketOwnerCompany modelTicketOwnerCompany: resultList) {
				if(modelTicketOwnerCompany.getActiveStatus()==1)
				 { 
					modelTicketOwnerCompany.setsActive("Yes");
					modelTicketOwnerCompany.setActive(true);
				 }
				 
				 else
				 {
					 modelTicketOwnerCompany.setsActive("NO");
					 modelTicketOwnerCompany.setActive(false);
				     
				 }
				if(modelTicketOwnerCompany.getTicketType()==1) 
				{
					modelTicketOwnerCompany.setsTicketType("Air Ticket");
				}
				else if(modelTicketOwnerCompany.getTicketType()==2) 
				{
					modelTicketOwnerCompany.setsTicketType("Train Ticket");
				}
				else 
				{
					modelTicketOwnerCompany.setsTicketType("Bus Ticket");
				}
				
				
				 
				ticketCompanyList.add(modelTicketOwnerCompany);
		}
		
		
		
		return ticketCompanyList;
	}

}
