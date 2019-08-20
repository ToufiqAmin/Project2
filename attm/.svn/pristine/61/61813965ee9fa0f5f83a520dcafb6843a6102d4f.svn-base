package com.biziitech.attm.bg.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.dao.DaoUser;
import com.biziitech.attm.bg.repository.UserRepository;

@Service
public class DaoUserImp implements DaoUser{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<ModelUser> getAllUSerName() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<ModelUser> getAllUSerNameInOrder() {
		// TODO Auto-generated method stub
		List<ModelUser> resultList = userRepository.getUserNameInOrder();
		List<ModelUser> userList= new ArrayList<>();
		for(ModelUser type: resultList) {
			if(type.getActiveStatus()==1)
				{
				type.setActive(true);
				type.setsActive("Yes");
				}
			else
			{
				type.setActive(true);

				type.setsActive("No");
			}
			userList.add(type);
		}
		
		
		
		return userList;
		
	}

	@Override
	public void save(ModelUser modelUser) {
		// TODO Auto-generated method stub
		System.out.println("Security Code: "+modelUser.getSecurityCode());
		
	/*	
		if(modelUser.isActive())
		{
			modelUser.setActiveStatus(1);
			
		}
		else
		{
			modelUser.setActiveStatus(0);
			
		}
	*/	
		
		userRepository.save(modelUser);
	}

	@Override
	public List<ModelUser> getUserListByCraiteria(String userName, String titleName, String passportNo,
			int status) {
		// TODO Auto-generated method stub
		List<ModelUser> resultList= userRepository.findUserDetails(userName, titleName, passportNo, status);
		
		List<ModelUser> userList=new ArrayList<>();
		
		for(ModelUser user: resultList) {
				if(user.getActiveStatus()==1)
				 { 
					user.setsActive("Yes");
					user.setActive(true);
				 }
				 
				 else
				 {
					 user.setsActive("NO");
					 user.setActive(false);
				     
				 }
				
				if(user.getGenderStatus()==1) 
				{
					user.setsGender("Male");
					
				}
				else if(user.getGenderStatus()==2) 
				{
					user.setsGender("Female");
					
				}
				else if(user.getGenderStatus()==3) 
				{
					user.setsGender("Others");
				}
				 
				userList.add(user);
		}
		
		
		
		return userList;
	}

}
