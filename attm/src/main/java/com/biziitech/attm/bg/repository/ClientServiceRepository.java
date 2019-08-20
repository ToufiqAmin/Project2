package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.model.ModelClientService;



public interface ClientServiceRepository extends JpaRepository<ModelClientService, Long>{
	
	//@Query("select a from ATTM_CLIENT_SERVICE a  where a.modelClientType.typeName LIKE CONCAT('%',:clientTypeName,'%') and a.modelUser.userName LIKE CONCAT('%',:clientName,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') and a.activeStatus=:status")
	//public List <ModelClientService> findClientServiceDetails(@Param("clientTypeName")String clientTypeName, @Param("clientName")String clientName, @Param("remarks")String remarks, @Param("status") int status);

	
	//@Query("select a from ATTM_CLIENT_SERVICE a where a.activeStatus=1 and a.passportNo=COALESCE(:passportNo, a.passportNo) order by a.userName" )
	//public List<ModelClientService> getUserName(@Param("passportNo")Long passportNo);

}
