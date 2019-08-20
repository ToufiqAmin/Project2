package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelUser;


public interface LogonRepository extends JpaRepository<ModelUser, Long>{
	
	@Query("select a from BG_USER a where upper(a.userName)=upper(:userName) and a.securityCode=:password")
	public ModelUser getValidUserPassword(@Param("userName") String userName,@Param("password") String password);
	
	
	@Query("select a from BG_USER a where UPPER((a.loginUser)) in (UPPER(:userName)) and a.securityCode=:password")
	public List <ModelUser> getUserValidatiion(@Param("userName") String userName,@Param("password") String password);
	
	@Query("select a from BG_USER a where a.userId=:id")
	ModelUser getLogonUserName(@Param("id")Long id);
	

}
