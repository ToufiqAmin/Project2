package com.biziitech.attm.dao;

import java.util.List;

import com.biziitech.attm.model.ModelTranType;

public interface DaoTranType {
	
	public List<ModelTranType> getTranTypeByName(String typeName);
	
	//created by sohel rana on 25/06/2019
	
	public List<ModelTranType> getAllTranType();

}
