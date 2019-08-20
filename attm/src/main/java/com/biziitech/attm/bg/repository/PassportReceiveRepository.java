package com.biziitech.attm.bg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelPassportReceive;






public interface PassportReceiveRepository extends JpaRepository<ModelPassportReceive,Long>{
	
	@Query("select a from ATTM_PASSPORT_RECEIVE a  where a.modelUser.userId=COALESCE(:userId, a.modelUser.userId) and a.receiveDate=COALESCE(:receiveDate, a.receiveDate) and a.activeStatus=:status")
	public List <ModelPassportReceive> findPassportReceiveDetails(@Param("userId")Long userId, @Param("receiveDate")Date receiveDate, @Param("status") int status);
	
	@Query("select a from ATTM_PASSPORT_RECEIVE a  where a.modelUser.passportNo LIKE CONCAT('%',:passportNo,'%') and a.modelUser.userName LIKE CONCAT('%',:passengerName,'%') and a.modelUser.userId=COALESCE(:userId, a.modelUser.userId) and a.modelClientService.modelService.serviceId=COALESCE(:serviceId, a.modelClientService.modelService.serviceId) and a.receiveDate BETWEEN coalesce(:fromDate,a.receiveDate) AND coalesce(:toDate,a.receiveDate)")
	public List <ModelPassportReceive> findPassportReceiveDone(@Param("passportNo")String passportNo,@Param("passengerName")String passengerName,@Param("userId")Long userId,@Param("serviceId")Long serviceId,@Param("fromDate")Date fromDate,@Param("toDate")Date toDate);

	@Query("select a from ATTM_PASSPORT_RECEIVE a  where a.passportReceiveId=COALESCE(:passportReceiveId, a.passportReceiveId) and a.activeStatus=1")
	public List <ModelPassportReceive> findPassportReceiveById(@Param("passportReceiveId")Long passportReceiveId);
	
	
	
}
