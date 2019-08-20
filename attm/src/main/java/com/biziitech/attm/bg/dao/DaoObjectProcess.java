package com.biziitech.attm.bg.dao;

import java.util.List;

import com.biziitech.attm.bg.model.ModelObjectProcess;

public interface DaoObjectProcess {

	public void saveObjectProcess(ModelObjectProcess objectProcess);
	public List<ModelObjectProcess> getObjectProcessFromObject(Long id);
}
