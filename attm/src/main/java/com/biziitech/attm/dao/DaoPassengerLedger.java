package com.biziitech.attm.dao;

import java.util.Date;
import java.util.List;

import com.biziitech.attm.custom.model.ModelTransactionCustom;

public interface DaoPassengerLedger {
  
	public List<ModelTransactionCustom>searchTranDetails(Long userId,Long tranTypeId,Date fromDate,Date toDate);
}
