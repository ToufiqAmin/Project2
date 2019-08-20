package com.biziitech.attm.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.biziitech.attm.bg.repository.ServiceRepository;
import com.biziitech.attm.dao.DaoService;
import com.biziitech.attm.model.ModelService;

@Service
public class DaoServiceImp implements DaoService{
	
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public void saveService(ModelService modelService) {
		// TODO Auto-generated method stub
		if(modelService.isActive()) 
		{
			modelService.setActiveStatus(1);
			
		}
		else 
		{
			modelService.setActiveStatus(0);
			
		}
		if(modelService.isPassport()) 
		{
			modelService.setPassportRequired(1);
		}
		else 
		{			
			modelService.setPassportRequired(0);
		}
		
		serviceRepository.save(modelService);
	}

	@Override
	public List<ModelService> getServiceByCraiteria(String serviceName, String shortCode, String remarks, int status, int passportStatus) {
		// TODO Auto-generated method stub
		List<ModelService> resultList= serviceRepository.findServiceDetails(serviceName, shortCode, remarks, status, passportStatus);
		
		List<ModelService> serviceList=new ArrayList<>();
		
		for(ModelService modelService: resultList) {
				if(modelService.getActiveStatus()==1)
				 { 
					modelService.setsActive("Yes");
					modelService.setActive(true);
				 }
				 
				 else
				 {
					 modelService.setsActive("NO");
					 modelService.setActive(false);
				     
				 }
				if(modelService.getPassportRequired()==1) 
				{
					modelService.setsPassport("Yes");
					modelService.setPassport(true);
				}
				else 
				{
					modelService.setsPassport("NO");
					modelService.setPassport(false);
				}
				
				 
				serviceList.add(modelService);
		}
		
		
		
		return serviceList;
	
	}

}
