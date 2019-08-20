package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biziitech.attm.bg.model.ModelCountry;

public interface CountryRepository extends JpaRepository<ModelCountry,Long>{
	@Query("select b.countryName,b.countryId from BG_COUNTRY b where b.countryName=:countryName")
	public ModelCountry findCountryIdByName(String countryName);
	
	@Query("select b from BG_COUNTRY b where b.activeStatus=1 ORDER BY b.countryName")
	public List <ModelCountry> findCountryInOrder();

}
