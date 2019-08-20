package com.biziitech.attm.dao;

import java.util.List;

import com.biziitech.attm.model.ModelTicketRequestCancel;

public interface DaoTicketRequestCancel {
	
	public void saveTicketRequestCancel(ModelTicketRequestCancel modelTicketRequestCancel);
	public List<ModelTicketRequestCancel> getCancelById(Long ticketRequestCancelId);

}
