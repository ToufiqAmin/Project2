package com.biziitech.attm.bg.dao;

import java.util.List;

import com.biziitech.attm.bg.model.ModelUserType;




public interface DaoUserType_attm {
	
	public void saveUserType_ATTM(ModelUserType modelUserType_ATTM);
	public List<ModelUserType> getUserTypeListByCraiteria(String userTypeName, String shortCode, String remarks, int  status);
	public List<ModelUserType> getUserTypeListInOrder();

}
