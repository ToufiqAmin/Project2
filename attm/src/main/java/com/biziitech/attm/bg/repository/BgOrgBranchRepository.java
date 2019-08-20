package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.bg.model.ModelOrgBranch;


public interface BgOrgBranchRepository extends JpaRepository<ModelOrgBranch,Long>{
	
	
	@Query("select a from BG_ORG_BRANCH a where a.activeStatus=1" )
	public List<ModelOrgBranch> findOrgBranch();
	
	@Query("select a from BG_ORG_BRANCH a where a.activeStatus=1 and a.orgId.orgId=:id")
	public List<ModelOrgBranch> findBranch(@Param("id") Long id);

}
