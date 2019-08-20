package com.biziitech.attm.bg.dao;

import java.util.List;
import java.util.Optional;

import com.biziitech.attm.bg.model.ModelOrgType;


public interface DaoOrgType {


	public void saveOrgType(ModelOrgType modelOrgType);

	public List<ModelOrgType> getOrgTypeName();
	public List<ModelOrgType> getOrgTypeListByCraiteria(String orgTypeName, String shortCode, String orgTypeRemarks, int status);
}
