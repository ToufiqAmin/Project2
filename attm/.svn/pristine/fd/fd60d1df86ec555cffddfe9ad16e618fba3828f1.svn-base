package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTicketRequestCancel;

public interface TicketRequestCancelRepository extends JpaRepository<ModelTicketRequestCancel, Long>{
	
	
	@Query("select a from ATTM_TICKET_REQUEST_CANCEL a where a.activeStatus=1 and a.ticketRequestCancelId=COALESCE(:ticketRequestCancelId, a.ticketRequestCancelId)")
	public List<ModelTicketRequestCancel> getCancelById(@Param("ticketRequestCancelId")Long ticketRequestCancelId);

}
