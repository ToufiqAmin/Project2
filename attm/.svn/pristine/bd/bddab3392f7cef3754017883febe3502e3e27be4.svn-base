package com.biziitech.attm.dao;

import java.util.Date;
import java.util.List;

import com.biziitech.attm.custom.model.ModelManPowerClearanceCustom;
import com.biziitech.attm.model.ModelManPowerClearance;


public interface DaoManPowerClearance {
	
	
	public void saveATTM_ManPowerClearance(ModelManPowerClearance modelManPowerClearance_ATTM);
	public List<ModelManPowerClearance> getManPower_ATTMByCraiteria(String userName, Date sendDate, Date receiveDate, int  status);	
	public List<ModelManPowerClearanceCustom> getMCUnDoneWithPR(String passportNo, String userName, Long userId, Date fromDate, Date toDate, String clearanceAgentName);
	//public List<ModelManPowerClearanceCustom> getMCUnDoneWithoutPR(String passportNo, String userName, Long userId, Date fromDate, Date toDate);
	public List<ModelManPowerClearanceCustom> getMCDoneWithPR(String passportNo, String userName, Long userId, Date fromDate, Date toDate, String clearanceAgentName);
	//public List<ModelManPowerClearanceCustom> getMCDoneWithoutPR(String passportNo, String userName, Long userId, Date fromDate, Date toDate);
	public List<ModelManPowerClearanceCustom> getMCById(Long manPowerClearanceId);
	public List<ModelManPowerClearanceCustom> getMCDoneWithoutReceiveDate(String passportNo, String userName, Long userId, Date fromDate, Date toDate, String clearanceAgentName);
	public List<ModelManPowerClearanceCustom> getMCDoneWithReceiveDate(String passportNo, String userName, Long userId, Date fromDate, Date toDate, String clearanceAgentName);

}
