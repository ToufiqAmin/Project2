package com.biziitech.attm.bg.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.dao.DaoUserType_attm;
import com.biziitech.attm.bg.model.ModelUserType;
import com.biziitech.attm.bg.repository.BgUserTypeRepository;




@Service
public class DaoUserTypeImp_attm implements DaoUserType_attm{
	
	@Autowired
	private BgUserTypeRepository attm_BgUserTypeRepository;

	@Override
	public void saveUserType_ATTM(ModelUserType modelUserType_ATTM) {
		// TODO Auto-generated method stub
		if (modelUserType_ATTM.isActive()) {
			modelUserType_ATTM.setActiveStatus(1);
		}
		
		else
		{
			modelUserType_ATTM.setActiveStatus(0);
		}
		
		attm_BgUserTypeRepository.save(modelUserType_ATTM);
	}

	@Override
	public List<ModelUserType> getUserTypeListByCraiteria(String userTypeName, String shortCode, String remarks,
			int status) {
		// TODO Auto-generated method stub
		List<ModelUserType> resultList = attm_BgUserTypeRepository.findUserTypeDetails(userTypeName, shortCode, remarks, status);
		List<ModelUserType> userTypeList= new ArrayList<>();
		
		for(ModelUserType modelUserType_ATTM: resultList) {
			if(modelUserType_ATTM.getActiveStatus()==1)
				if(modelUserType_ATTM.getActiveStatus()==1)
				 { modelUserType_ATTM.setsActive("Yes");
				 modelUserType_ATTM.setActive(true);
				 }
				 
				 else
				 {	 modelUserType_ATTM.setsActive("NO");
				 modelUserType_ATTM.setActive(false);
				     
				 }
				 
			userTypeList.add(modelUserType_ATTM);
		}
		
		
		
		return userTypeList;
	}

	@Override
	public List<ModelUserType> getUserTypeListInOrder() {
		// TODO Auto-generated method stub
		return attm_BgUserTypeRepository.findUserTypeListInOrder();
	}
	
	

}
