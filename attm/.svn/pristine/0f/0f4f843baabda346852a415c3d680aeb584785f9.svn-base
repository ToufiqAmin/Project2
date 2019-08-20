package com.biziitech.attm.daoImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TranTypeRepository;
import com.biziitech.attm.dao.DaoTranType;
import com.biziitech.attm.model.ModelTranType;

@Service
public class DaoTranTypeImp implements DaoTranType{
	
	@Autowired
	private TranTypeRepository tranTypeRepository;

	@Override
	public List<ModelTranType> getTranTypeByName(String typeName) {
		// TODO Auto-generated method stub
		
		return tranTypeRepository.findTranTypeByName(typeName);
	}
	
	//created by sohel rana on 25/06/2019

	@Override
	public List<ModelTranType> getAllTranType() {
		// TODO Auto-generated method stub
		return tranTypeRepository.findAllTranType();
	}
	
	

}
