package com.biziitech.attm.bg.dao;

import java.util.List;
import java.util.Optional;

import com.biziitech.attm.bg.model.ModelOrgGroup;



public interface DaoOrgGroup {
	
	public void saveOrgGroup(ModelOrgGroup modelOrgGroup);
	public List<ModelOrgGroup> getOrgGroupListByCraiteria(String groupName,String groupCode,String orgGroupRemarks,int status);
	public Optional<ModelOrgGroup> getGroupById(Long id);
	public List<ModelOrgGroup> findOrgGroup();
	public List<ModelOrgGroup> getOrgGroupName();
}
