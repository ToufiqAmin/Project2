package com.biziitech.attm.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.biziitech.attm.bg.repository.AirLineRepository;
import com.biziitech.attm.dao.DaoAirLine;
import com.biziitech.attm.model.ModelAirLine;




@Service
public class DaoAirLineImp implements DaoAirLine{
	
	@Autowired
	private AirLineRepository airLineRepository;

	@Override
	public void saveATTM_AirLine(ModelAirLine modelAirLine) {
		// TODO Auto-generated method stub
		if(modelAirLine.isActive()) 
		{
			modelAirLine.setActiveStatus(1);
			
		}
		else 
		{
			modelAirLine.setActiveStatus(0);
			
		}
		airLineRepository.save(modelAirLine);
	}

	@Override
	public List<ModelAirLine> getATTM_AirLIneByCraiteria(String airLineName, String shortCode, String remarks,
			int status) {
		// TODO Auto-generated method stub
		List<ModelAirLine> resultList= airLineRepository.findAirLineDetails(airLineName, shortCode, remarks, status);
		
		List<ModelAirLine> airLineList=new ArrayList<>();
		
		for(ModelAirLine modelAirLine_ATTM: resultList) {
				if(modelAirLine_ATTM.getActiveStatus()==1)
				 { 
					modelAirLine_ATTM.setsActive("Yes");
					modelAirLine_ATTM.setActive(true);
				 }
				 
				 else
				 {
					 modelAirLine_ATTM.setsActive("NO");
					 modelAirLine_ATTM.setActive(false);
				     
				 }
				
				
				 
				airLineList.add(modelAirLine_ATTM);
		}
		
		
		
		return airLineList;
	}
	
	

}
