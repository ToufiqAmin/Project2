package com.biziitech.attm.bg.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketRequest;






public interface TicketRequestRepository extends JpaRepository<ModelTicketRequest, Long>{
	
	@Query("select a from ATTM_TICKET_REQUEST a where a.requesterName LIKE CONCAT('%',:requesterName,'%') "
			+ "and a.requesterContactPhone LIKE CONCAT('%',:requesterContactPhone,'%') "
			+ "and a.modelUser_ATTM.userName LIKE CONCAT('%',:userName,'%') "
			+ "and a.fromAgent.agentName LIKE CONCAT('%',:fromAgentName,'%') "
			+ "and a.toAgent.agentName LIKE CONCAT('%',:toAgentName,'%') "
			+ "and a.modelAirLine.airLineName LIKE CONCAT('%',:airLineName,'%') "
			+ "and a.possibleFlightDate BETWEEN coalesce(:fromDate,a.possibleFlightDate) "
			+ "AND coalesce(:toDate,a.possibleFlightDate) "
			+ "and a.activeStatus=:status")
	public List <ModelTicketRequest> findTicketRequestDetails(@Param("requesterName")String requesterName,@Param("requesterContactPhone")String requesterContactPhone,@Param("userName")String userName, @Param("fromAgentName")String fromAgentName, @Param("toAgentName")String toAgentName, @Param("airLineName")String airLineName,@Param("fromDate")Date fromDate,@Param("toDate")Date toDate, @Param("status") int status);

	@Query("select a from ATTM_TICKET_REQUEST a where a.ticketRequestId=COALESCE(:ticketRequestId, a.ticketRequestId)")
	public List <ModelTicketRequest> findTicketRequestById(@Param("ticketRequestId")Long ticketRequestId);
	
	
}
