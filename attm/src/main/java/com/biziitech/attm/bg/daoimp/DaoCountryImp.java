package com.biziitech.attm.bg.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.dao.DaoCountry;
import com.biziitech.attm.bg.repository.CountryRepository;

@Service
public class DaoCountryImp implements DaoCountry{
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<ModelCountry> getCountryName()  {
		return countryRepository.findAll();
	}

	@Override
	public ModelCountry getCountry(String name) throws SQLException {
		// TODO Auto-generated method stub
		return countryRepository.findCountryIdByName(name);
	}

}
