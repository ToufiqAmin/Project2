package com.biziitech.attm.bg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biziitech.attm.model.ModelAgent;




public interface AgentRepository extends JpaRepository<ModelAgent, Long>{
	
	@Query("select a from ATTM_AGENT a  where a.agentName LIKE CONCAT('%',:agentName,'%') and a.shortCode LIKE CONCAT('%',:shortCode,'%') and a.remarks LIKE CONCAT('%',:remarks,'%') and a.activeStatus=:status")
	public List <ModelAgent> findAgentDetails(@Param("agentName")String agentName, @Param("shortCode")String shortCode, @Param("remarks")String remarks, @Param("status") int status);

	@Query("select a from ATTM_AGENT a where a.activeStatus=1 order by a.agentName" )
	public List<ModelAgent> getAgentName();
	
	//select a from ATTM_AGENT a where a.activeStatus=1 order by a.agentName,a.selfFlagStatus
	
	@Query("select a from ATTM_AGENT a where a.activeStatus=1 and a.selfFlagStatus=1 order by a.agentName" )
	public List<ModelAgent> findAgentNameBySelfFalg();

}
