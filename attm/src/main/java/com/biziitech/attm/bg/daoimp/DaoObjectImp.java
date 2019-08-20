package com.biziitech.attm.bg.daoimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.model.ModelModule;
import com.biziitech.attm.bg.model.ModelObject;
import com.biziitech.attm.bg.dao.DaoObject;
import com.biziitech.attm.bg.repository.ModuleRepository;
import com.biziitech.attm.bg.repository.ObjectRepository;


@Service
public class DaoObjectImp implements DaoObject {

	@Autowired
	private ObjectRepository objectRepository;
	
	private List<ModelObject> objectList;
	public List<ModelObject> getObjectList() {
		return objectList;
	}
	public void setObjectList(List<ModelObject> objectList) {
		this.objectList = objectList;
	}
	
	@Override
	public void saveObject(ModelObject object) {
		if (object.isActive()) {
			object.setActiveStatus(1);
		}
		
		else
		{
			object.setActiveStatus(0);
		}
		
		
		objectRepository.save(object);
		
	}

	@Override
	public List<ModelObject> getObjectFromModule(Long id) {
		System.out.println("moduleId: "+id);
		List<ModelObject> objectListModule=objectRepository.findModuleFromSystem(id);
		setObjectList(objectListModule);
		return objectListModule;
	}
	@Override
	public List<ModelObject> getAllObjectList() {
		
		return objectRepository.findAll();
	}

}
