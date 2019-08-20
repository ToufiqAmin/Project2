package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelService;





public interface ServiceRepository extends JpaRepository<ModelService, Long>{
	
	@Query("select a from ATTM_SERVICE a  where a.serviceName LIKE CONCAT('%',:serviceName,'%') and a.shortCode LIKE CONCAT('%',:shortCode,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') and a.activeStatus=:status and a.passportRequired=:passportStatus")
	public List <ModelService> findServiceDetails(@Param("serviceName")String serviceName, @Param("shortCode")String shortCode, @Param("remarks")String remarks, @Param("status") int status, @Param("passportStatus") int passportStatus);


	@Query("select a from ATTM_SERVICE a where a.activeStatus=1 order by a.serviceName" )
	public List<ModelService> getServiceInOrder();
	
}
