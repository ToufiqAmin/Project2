package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelUserType;




public interface BgUserTypeRepository extends JpaRepository<ModelUserType, Long>{
	
	@Query("select a from BG_USER_TYPE a  where a.userTypeName LIKE CONCAT('%',:userTypeName,'%') and a.shortCode LIKE CONCAT('%',:shortCode,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') and a.activeStatus=:status")
	public List <ModelUserType> findUserTypeDetails(@Param("userTypeName")String userTypeName, @Param("shortCode")String shortCode, @Param("remarks")String remarks, @Param("status") int status);

	@Query("select a from BG_USER_TYPE a where a.activeStatus=1 order by a.userTypeName" )
	public List<ModelUserType> findUserTypeListInOrder();
}
