package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketOwnerCompany;

public interface TicketOwnerCompanyRepository extends JpaRepository<ModelTicketOwnerCompany, Long>{
	
	@Query("select a from ATTM_TICKET_OWNER_COMPANY a where a.activeStatus=1 and a.ticketType=1 order by a.ownerCompanyName" )
	public List<ModelTicketOwnerCompany> findTicketOwnerCompanyName();
	
	@Query("select a from ATTM_TICKET_OWNER_COMPANY a where a.ownerCompanyName LIKE CONCAT('%',:ownerCompanyName,'%') "
			+ "and a.shortCode LIKE CONCAT('%',:shortCode,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') "
			+ "and a.activeStatus=:status")
	public List <ModelTicketOwnerCompany> findTicketOwnerCompanyDetails(@Param("ownerCompanyName")String ownerCompanyName,
			@Param("shortCode")String shortCode, @Param("remarks")String remarks, @Param("status") int status);

}
