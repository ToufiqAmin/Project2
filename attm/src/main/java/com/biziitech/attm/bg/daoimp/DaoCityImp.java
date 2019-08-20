package com.biziitech.attm.bg.daoimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.dao.DaoCity;
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.repository.CityRepository;

@Service
public class DaoCityImp implements DaoCity{
	
	@Autowired
	private CityRepository cityRepository;
	

	@Override
	public List<ModelCity> getCityInOrder() {
		// TODO Auto-generated method stub
		return cityRepository.findCityInOrder();
	}

	@Override
	public List<ModelCity> getCityByCountryId(Long countryId) {
		// TODO Auto-generated method stub
		return cityRepository.findCityByCountryId(countryId);
	}
	
	
	

}
