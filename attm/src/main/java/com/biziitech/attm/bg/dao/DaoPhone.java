package com.biziitech.attm.bg.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.biziitech.attm.bg.model.ModelPhone;



public interface DaoPhone {
	public long getPhoneId() throws SQLException;//for generating  Id (Primary key);
	public List<ModelPhone> getUserPhoneData(Long userId);
	public List<ModelPhone> getUserPhoneDataByUserId(Long userId);
	public void savePhone(ModelPhone p);
	public void updatePhone(ModelPhone p);
	public long getPhoneId(Long userId) throws SQLException; // phone number of specific user
	public Optional<ModelPhone> getPhoneById(Long phoneId);
}
