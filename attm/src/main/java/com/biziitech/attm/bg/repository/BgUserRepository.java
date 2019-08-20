package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelBgUser;







public interface BgUserRepository extends JpaRepository<ModelBgUser, Long>{
	
	
	@Query("select a from BG_USER a  where a.userName LIKE CONCAT('%',:userName,'%') and a.titleName LIKE CONCAT('%',:titleName,'%') and a.passportNo LIKE CONCAT('%',:passportNo,'%') and a.activeStatus=:status order by a.userName")
	public List <ModelBgUser> findUserDetails(@Param("userName")String userName, @Param("titleName")String titleName, @Param("passportNo")String passportNo, @Param("status") int status);
	
	@Query("select a from BG_USER a where a.activeStatus=1 and a.passportNo LIKE CONCAT('%',:passportNo,'%') order by a.userName" )
	public List<ModelBgUser> getUserName(@Param("passportNo")String passportNo);
	
	@Query("select a from BG_USER a where a.activeStatus=1 and a.modelUserType.userTypeId=20190406511 order by a.userName" )
	public List<ModelBgUser> getUserNameInOrder();
	
	@Query("select a from BG_USER a where a.activeStatus=1 and a.userId=COALESCE(:userId, a.userId)" )
	public List<ModelBgUser> getUserDetailsById(@Param("userId")Long userId);

}
