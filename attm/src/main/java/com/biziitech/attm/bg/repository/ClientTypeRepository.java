package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biziitech.attm.model.ModelClientType;



public interface ClientTypeRepository extends JpaRepository<ModelClientType, Long>{
	
	@Query("select b from ATTM_CLIENT_TYPE b where b.activeStatus=1 ORDER BY b.typeName desc")
	public List <ModelClientType> findClientTypeInOrder();

}
