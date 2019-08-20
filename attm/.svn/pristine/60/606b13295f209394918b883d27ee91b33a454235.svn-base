package com.biziitech.attm.bg.dao;

import java.util.List;

import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.custom.model.ModelBgUserCustom;



public interface DaoUser_attm {
	
	public void saveUser_ATTM(ModelBgUser modelBgUser);
	public List<ModelBgUser> getUserListByCraiteria(String userName, String titleName, String passportNo, int  status);
	public List<ModelBgUserCustom> getUserNameById(Long userId);
	public List<ModelBgUserCustom> getUserByPassportNo(String passportNo);
	public boolean hasLoginUser(String loginUser);
}
