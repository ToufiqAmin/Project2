package com.biziitech.attm.bg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelManPowerClearance;






public interface ManPowerClearanceRepository extends JpaRepository<ModelManPowerClearance,Long>{
	
	@Query("select a from ATTM_MANPOWER_CLEARANCE a where a.modelUser.userName LIKE CONCAT('%',:userName,'%') and a.sentDate=COALESCE(:sendDate, a.sentDate) and a.receiveDate=COALESCE(:receiveDate, a.receiveDate) and a.activeStatus=:status")
	public List <ModelManPowerClearance> findManPowerClearanceDetails(@Param("userName")String userName, @Param("sendDate")Date sendDate, @Param("receiveDate")Date receiveDate, @Param("status") int status);
	
	@Query("select a from ATTM_MANPOWER_CLEARANCE a  where a.modelUser.passportNo=COALESCE(:passportNo, a.modelUser.passportNo) and a.modelUser.userName LIKE CONCAT('%',:userName,'%') and a.modelUser.userId=COALESCE(:userId, a.modelUser.userId) and a.receiveDate BETWEEN coalesce(:fromDate,a.receiveDate) AND coalesce(:toDate,a.receiveDate)")
	public List <ModelManPowerClearance> findPassportReceiveDone(@Param("passportNo")Long passportNo,@Param("userName")String userName,@Param("userId")Long userId,@Param("fromDate")Date fromDate,@Param("toDate")Date toDate);

	@Query("select a from ATTM_MANPOWER_CLEARANCE a  where a.manPowerClearanceId=COALESCE(:manPowerClearanceId, a.manPowerClearanceId) and a.activeStatus=1")
	public List <ModelManPowerClearance> findManPowerClearanceById(@Param("manPowerClearanceId")Long manPowerClearanceId);
	
	
}
