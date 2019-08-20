package com.biziitech.attm.bg.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.model.ModelObjectGroup;
import com.biziitech.attm.bg.dao.DaoObjectGroup;

import com.biziitech.attm.bg.repository.ObjectGroupRepository;

@Service
public class DaoObjectGroupImp implements DaoObjectGroup {
	
	@Autowired
	private ObjectGroupRepository objectGroupRepository;

	@Override
	public void saveObjectGroup(ModelObjectGroup objectGroup) {
			if (objectGroup.isActive()) {
				objectGroup.setActiveStatus(1);
			}
			
			else
			{
				objectGroup.setActiveStatus(0);
			}
			
			
			objectGroupRepository.save(objectGroup);
			
		}

	@Override
	public List<ModelObjectGroup> getObjectGroupListByCraiteria(String groupName, String shortCode, String remarks,
			int status) {
		  
		List<ModelObjectGroup> resultList = objectGroupRepository.findObjectGroupDetails(groupName, shortCode, remarks, status);
		List<ModelObjectGroup> objectGroupList= new ArrayList<>();
		
		for(ModelObjectGroup objectGroup: resultList) {
			if(objectGroup.getActiveStatus()==1)
				if(objectGroup.getActiveStatus()==1)
				 { objectGroup.setsActive("Yes");
				   objectGroup.setActive(true);
				 }
				 
				 else
				 {	 objectGroup.setsActive("NO");
				     objectGroup.setActive(false);
				     
				 }
				 
			objectGroupList.add(objectGroup);
		}
		
		
		
		return objectGroupList;
	}

	@Override
	public List<ModelObjectGroup> getObjectGroupList() {
		
		return objectGroupRepository.findAll();
	}
	

}
