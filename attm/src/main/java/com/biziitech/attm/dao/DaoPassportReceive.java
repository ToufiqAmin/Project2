package com.biziitech.attm.dao;

import java.util.Date;
import java.util.List;

import com.biziitech.attm.custom.model.ModelPassportReceiveCustom;
import com.biziitech.attm.model.ModelPassportReceive;




public interface DaoPassportReceive {
	
	public void savePassportReceive_ATTM(ModelPassportReceive modelPassportReceive_ATTM);
	public List<ModelPassportReceive> getPassportReceiveListByCraiteria(Long userId, Date passportReceiveDate, int status);
	
	public List<ModelPassportReceiveCustom> getPassportReceiveUnDone(String passportNo, String passengerName, Long userId, Long serviceId, Date fromDate, Date toDate);
	public List<ModelPassportReceiveCustom> getPassportReceiveDone(String passportNo, String passengerName, Long userId, Long serviceId, Date fromDate, Date toDate);
	public List<ModelPassportReceiveCustom> getPassportReceiveById(Long passportReceiveId);
}
