package com.biziitech.attm.daoImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.TranRepository;
import com.biziitech.attm.dao.DaoTran;
import com.biziitech.attm.model.ModelTran;

@Service
public class DaoTranImp implements DaoTran{
	
	@Autowired
	private TranRepository tranRepository;

	@Override
	@Transactional
	public void saveTran(ModelTran modelTran) {
		// TODO Auto-generated method stub
		tranRepository.save(modelTran);
	}

	@Override
	public List<ModelTran> getTranByBaseTranId(Long baseTranId, String baseTranTable) {
		// TODO Auto-generated method stub
		return tranRepository.findTranById(baseTranId, baseTranTable);
	}

	@Override
	public List<ModelTran> getTranByTranId(Long tranId) {
		// TODO Auto-generated method stub
		return tranRepository.findTranByTranId(tranId);
	}

	@Override
	public List<ModelTran> getTranByParentTranId(Long parentTranId) {
		// TODO Auto-generated method stub
		return tranRepository.findTranByParentTranId(parentTranId);
	}

	@Override
	public List<ModelTran> getTranByBaseTranTable(String baseTranTable) {
		// TODO Auto-generated method stub
		return tranRepository.findTranByBaseTranTable(baseTranTable);
	}

	@Override
	public List<ModelTran> getTranByBaseTranId(Long baseTranId) {
		// TODO Auto-generated method stub
		return tranRepository.findTranByBaseTranId(baseTranId);
	}

}
