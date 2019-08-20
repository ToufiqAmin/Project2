package com.biziitech.attm.bg.daoimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelObjectGroup;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.UserObjectRepository;




@Service
public class DaoUserObjectImp implements DaoUserObject{
	
	@Autowired
	private UserObjectRepository userObjectRepository;

	@Override
	public List<ModelUserObject> getUserObject(Long userId) {
		// TODO Auto-generated method stub
		return userObjectRepository.findUserObjectByUserId(userId);
	}

	@Override
	public void saveUserObject(ModelUserObject modelUserObject) {
		// TODO Auto-generated method stub
		userObjectRepository.save(modelUserObject);
	}

	@Override
	public List<ModelUserObject> getUserObjectById(Long userObjectId) {
		// TODO Auto-generated method stub
		return userObjectRepository.findUserObject(userObjectId);
	}

	@Override
	public List<ModelUserObject> getUserObjectByObjectGroup(Long userId, String objectGroup) {
		// TODO Auto-generated method stub
		return userObjectRepository.findUserObjectByUserIdObjectGroup(userId,objectGroup);
	}

	@Override
	public List<ModelObjectGroup> getObjectGroup() {
		// TODO Auto-generated method stub
		return userObjectRepository.findObjectGroup();
	}

	@Override
	public List<ModelUserObject> getUserObjectByObjectGroupId(Long objectGroupId) {
		// TODO Auto-generated method stub
		return userObjectRepository.findUserObjectByObjectGroupId(objectGroupId);
	}

}
