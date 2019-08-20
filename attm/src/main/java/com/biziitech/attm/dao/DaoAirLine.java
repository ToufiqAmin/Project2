package com.biziitech.attm.dao;


import java.util.List;

import com.biziitech.attm.model.ModelAirLine;


public interface DaoAirLine {
	
	public void saveATTM_AirLine(ModelAirLine modelAirLine_ATTM);
	public List<ModelAirLine> getATTM_AirLIneByCraiteria(String airLineName, String shortCode, String remarks, int  status);

}
