package com.biziitech.attm.dao;


import java.util.List;

import javax.transaction.Transactional;

import com.biziitech.attm.model.ModelTran;

@Transactional
public interface DaoTran {
	
	public void saveTran(ModelTran modelTran);
	public List<ModelTran> getTranByParentTranId(Long parentTranId);
	public List<ModelTran> getTranByBaseTranId(Long baseTranId, String baseTranTable);
	public List<ModelTran> getTranByBaseTranId(Long baseTranId);
	public List<ModelTran> getTranByTranId(Long tranId);
	public List<ModelTran> getTranByBaseTranTable(String baseTranTable);

}
