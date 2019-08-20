package com.biziitech.attm.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.dao.DaoAgent;
import com.biziitech.attm.model.ModelAgent;



@Service
public class DaoAgentImp implements DaoAgent{
	
	@Autowired
	private AgentRepository agentRepository;

	@Override
	public void saveATTM_Agent(ModelAgent modelAgent) {
		// TODO Auto-generated method stub
		if(modelAgent.isActive()) 
		{
			modelAgent.setActiveStatus(1);
			
		}
		else 
		{
			modelAgent.setActiveStatus(0);
			
		}
		
		if(modelAgent.isSelfFlag()) 
		{
			modelAgent.setSelfFlagStatus(1);
		}
		else {
			modelAgent.setSelfFlagStatus(0);
		}
		agentRepository.save(modelAgent);
	}

	@Override
	public List<ModelAgent> getATTM_AgentByCraiteria(String agentName, String shortCode, String remarks,
			int status) {
		// TODO Auto-generated method stub
		List<ModelAgent> resultList= agentRepository.findAgentDetails(agentName, shortCode, remarks, status);
		
		List<ModelAgent> agentList=new ArrayList<>();
		
		for(ModelAgent modelAgent: resultList) {
				if(modelAgent.getActiveStatus()==1)
				 { 
					modelAgent.setsActive("Yes");
					modelAgent.setActive(true);
				 }
				 
				 else
				 {
					 modelAgent.setsActive("NO");
					 modelAgent.setActive(false);
				     
				 }
				if(modelAgent.getSelfFlagStatus()==1) 
				{
					modelAgent.setSelfFlag(true);
					modelAgent.setsSelfFlag("Yes");
					
				}
				else 
				{
					
					modelAgent.setSelfFlag(false);
					modelAgent.setsSelfFlag("No");
				}
				
				
				 
				agentList.add(modelAgent);
		}
		
		
		
		return agentList;
	}
	
	
	

}
