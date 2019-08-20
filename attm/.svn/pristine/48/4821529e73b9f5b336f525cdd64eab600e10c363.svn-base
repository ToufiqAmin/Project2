package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelPhone;

public interface PhoneRepository extends JpaRepository <ModelPhone,Long> {
	@Query("select a from BG_PHONE a  ")
	public List<ModelPhone> findPhones(@Param("id")Long id);
	
	@Query("select a from BG_PHONE a where a.modelUser.userId=:id ")
	public List<ModelPhone> findPhonesByUserId(@Param("id")Long id);
	
}
