package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTranType;

public interface TranTypeRepository extends JpaRepository<ModelTranType, Long>{
	
	@Query("select a from ATTM_TRAN_TYPE a where a.typeName LIKE CONCAT('%',:typeName,'%')")
	public List<ModelTranType> findTranTypeByName(@Param("typeName")String typeName);
	
	//created by sohel rana on 25/06/2019
	
	@Query("select a from ATTM_TRAN_TYPE a where a.activeStatus=1 order by typeName")
	public List<ModelTranType> findAllTranType();

}
