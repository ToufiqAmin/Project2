package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelAirLine;




public interface AirLineRepository extends JpaRepository<ModelAirLine, Long>{
	
	
	@Query("select a from ATTM_AIRLINE a  where a.airLineName LIKE CONCAT('%',:airLineName,'%') and a.shortCode LIKE CONCAT('%',:shortCode,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') and a.activeStatus=:status")
	public List <ModelAirLine> findAirLineDetails(@Param("airLineName")String airLineName, @Param("shortCode")String shortCode, @Param("remarks")String remarks, @Param("status") int status);

	@Query("select a from ATTM_AIRLINE a where a.activeStatus=1 order by a.airLineName" )
	public List<ModelAirLine> getAirLineName();

}
