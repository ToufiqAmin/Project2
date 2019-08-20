package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelTran;

public interface TranRepository extends JpaRepository<ModelTran, Long>{
	
	@Query("select a from ATTM_TRAN a where a.baseTranId=COALESCE(:baseTranId,a.baseTranId) and a.baseTranTable LIKE CONCAT('%',:baseTranTable,'%')")
	public List<ModelTran> findTranById(@Param("baseTranId")Long baseTranId,@Param("baseTranTable")String baseTranTable );
	
	@Query("select a from ATTM_TRAN a where a.tranId=COALESCE(:tranId,a.tranId)")
	public List<ModelTran> findTranByTranId(@Param("tranId")Long tranId );
	
	//@Query("select a from ATTM_TRAN a where a.baseTranId=COALESCE(:baseTranId,a.baseTranId) and a.baseTranTable LIKE CONCAT('%',:baseTranTable,'%')")
	//public List<ModelTran> findTranByBaseTranId(@Param("baseTranId")Long baseTranId,@Param("baseTranTable")String baseTranTable );
	
	@Query("select a from ATTM_TRAN a where a.parentTranId=COALESCE(:parentTranId, a.parentTranId)")
	public List<ModelTran> findTranByParentTranId(@Param("parentTranId")Long parentTranId);
	
	@Query("select a from ATTM_TRAN a where a.baseTranTable LIKE CONCAT('%',:baseTranTable,'%')")
	public List<ModelTran> findTranByBaseTranTable(@Param("baseTranTable")String baseTranTable);
	
	@Query("select a from ATTM_TRAN a where a.baseTranId=COALESCE(:baseTranId,a.baseTranId)")
	public List<ModelTran> findTranByBaseTranId(@Param("baseTranId")Long baseTranId);


}
