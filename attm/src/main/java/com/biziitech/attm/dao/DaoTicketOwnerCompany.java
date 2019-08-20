package com.biziitech.attm.dao;

import java.util.List;

import com.biziitech.attm.model.ModelTicketOwnerCompany;

public interface DaoTicketOwnerCompany {
	
	public void saveTicketOwnerCompany(ModelTicketOwnerCompany modelTicketOwnerCompany);
	public List<ModelTicketOwnerCompany> getTicketOwnerCompanyByCraiteria(String ownerCompanyName, String shortCode, String remarks, int  status);

}
