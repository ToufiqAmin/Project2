package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelUser;





public interface UserRepository extends JpaRepository<ModelUser,Long> {
	
	@Query("select c.userName,c.userId from BG_USER c where c.activeStatus=1")
	public List<ModelUser> getUserName();
	
	@Query("select c from BG_USER c where c.activeStatus=1 ORDER BY c.userName")
	public List<ModelUser> getUserNameInOrder();
	
	@Query("select a from BG_USER a  where a.userName LIKE CONCAT('%',:userName,'%') and a.titleName LIKE CONCAT('%',:titleName,'%') and a.passportNo LIKE CONCAT('%',:passportNo,'%') and a.activeStatus=:status order by a.userName")
	public List <ModelUser> findUserDetails(@Param("userName")String userName, @Param("titleName")String titleName, @Param("passportNo")String passportNo, @Param("status") int status);
	
	@Query("select a from BG_USER a where a.userId=COALESCE(:userId, a.userId)")
	public List<ModelUser> findUserById(@Param("userId")Long userId);
	

}
