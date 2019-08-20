package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelCity;

public interface CityRepository extends JpaRepository<ModelCity, Long>{
	
	@Query("select b from BG_CITY b where b.activeStatus=1 ORDER BY b.cityName")
	public List <ModelCity> findCityInOrder();
	
	@Query("select b from BG_CITY b where b.activeStatus=1 and b.modelCountry.countryId=COALESCE(:countryId, b.modelCountry.countryId) ORDER BY b.cityName")
	public List <ModelCity> findCityByCountryId(@Param("countryId")Long countryId);
	
	

}
