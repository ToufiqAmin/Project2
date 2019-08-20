package com.biziitech.attm.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.ClientServiceRepository;
import com.biziitech.attm.custom.model.ModelClientServiceCustom;
import com.biziitech.attm.dao.DaoClientService;
import com.biziitech.attm.model.ModelClientService;


@Service
public class DaoClientServiceImp implements DaoClientService{
	
	@Autowired
	private ClientServiceRepository clientServiceRepository;
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
    
	
	
    public void setNamedDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
          }
	

	@Override
	public void saveClientService(ModelClientService modelClientService) {
		// TODO Auto-generated method stub
		
		if(modelClientService.isActive()) 
		{
			modelClientService.setActiveStatus(1);
			
		}
		else 
		{
			modelClientService.setActiveStatus(0);
			
		}
		clientServiceRepository.save(modelClientService);
		
	}

	@Override
	public List<ModelClientServiceCustom> getClientServiceOfAgent(Long clientTypeId, String clientName,
			String remarks, int status) {
		// TODO Auto-generated method stub
			
			String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
					"a.CLIENT_ID,c.AGENT_NAME,a.SERVICE_ID,d.SERVICE_NAME, \r\n" + 
					"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
					"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,g.CITY_NAME,\r\n" + 
					"a.TO_COUNTRY,f.country_name,a.TO_CITY,h.CITY_NAME,\r\n" + 
					"a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS\r\n" + 
					"FROM attm_client_service a \r\n" + 
					"INNER JOIN attm_client_type b on a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
					"INNER JOIN attm_agent c on a.CLIENT_ID=c.AGENT_ID\r\n" + 
					"INNER JOIN attm_service d on a.SERVICE_ID=d.SERVICE_ID\r\n" + 
					"INNER JOIN bg_country e on a.FROM_COUNTRY=e.country_id \r\n" + 
					"INNER JOIN bg_country f on a.TO_COUNTRY=f.country_id\r\n" + 
					"LEFT OUTER JOIN bg_city g ON a.FROM_CITY=g.CITY_ID\r\n" + 
					"LEFT OUTER JOIN bg_city h ON a.TO_CITY=h.CITY_ID\r\n" + 
					"where a.CLIENT_TYPE_ID=coalesce(:clientTypeId,a.CLIENT_TYPE_ID)\r\n" + 
					"and a.ACTIVE_STATUS=coalesce(:status,a.ACTIVE_STATUS)\r\n" + 
					"and c.AGENT_NAME LIKE CONCAT('%',:clientName, '%')\r\n" + 
					"and a.REMARKS LIKE CONCAT('%',:remarks, '%')\r\n" + 
					"group by a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
					"a.CLIENT_ID,c.AGENT_NAME,a.SERVICE_ID,d.SERVICE_NAME, \r\n" + 
					"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
					"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,g.CITY_NAME,\r\n" + 
					"a.TO_COUNTRY,f.country_name,a.TO_CITY,h.CITY_NAME,\r\n" + 
					"a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS";
		
		RowMapper<ModelClientServiceCustom> serviceMapper=new RowMapper<ModelClientServiceCustom>() {

			@Override
			public ModelClientServiceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelClientServiceCustom bn= new ModelClientServiceCustom();
							
				
				bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
				bn.setClientTypeId(rs.getLong("CLIENT_TYPE_ID"));
				bn.setClientTypeName(rs.getString("TYPE_NAME"));
				bn.setClientId(rs.getLong("CLIENT_ID"));
				bn.setClientName(rs.getString("AGENT_NAME"));
				bn.setServiceId(rs.getLong("SERVICE_ID"));
				bn.setServiceName(rs.getString("SERVICE_NAME"));
				bn.setCareOfName(rs.getString("CARE_OF_NAME"));
				bn.setCareOfContactNo(rs.getString("CARE_OF_CONTACT_NO"));
				bn.setFromCountryId(rs.getLong("FROM_COUNTRY"));
				bn.setFromCountryName(rs.getString("country_name"));
				bn.setFromCityId(rs.getLong("FROM_CITY"));
				bn.setFromCityName(rs.getString("g.CITY_NAME"));
				bn.setToCountryId(rs.getLong("TO_COUNTRY"));
				bn.setToCountryName(rs.getString("f.country_name"));
				bn.setToCity(rs.getLong("TO_CITY"));
				bn.setToCityName(rs.getString("h.CITY_NAME"));
				bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
				bn.setqTY(rs.getInt("QTY"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}else
				{
					bn.setActive(false);
					bn.setsActive("No");
					
				}
								
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("clientTypeId", clientTypeId);
	      System.out.println(" in daoClientServiceImp, clientTypeId:" +clientTypeId);
	      parameters.addValue("clientName", clientName);
	      System.out.println(" in daoClientServiceImp, clientName:" +clientName);
	      parameters.addValue("remarks", remarks);
	      parameters.addValue("status", status);

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
		
		
		
		
	}


	@Override
	public List<ModelClientServiceCustom> getClientServiceOfIndividual(Long clientTypeId, String clientName,
			String remarks, int status) {
		// TODO Auto-generated method stub
		String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"a.CLIENT_ID,c.user_name,a.SERVICE_ID,d.SERVICE_NAME,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,g.CITY_NAME,\r\n" + 
				"a.TO_COUNTRY,f.country_name,a.TO_CITY,h.CITY_NAME,\r\n" + 
				"a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS \r\n" + 
				"FROM attm_client_service a\r\n" + 
				"INNER JOIN attm_client_type b on a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c on a.CLIENT_ID=c.user_id\r\n" + 
				"INNER JOIN attm_service d on a.SERVICE_ID=d.SERVICE_ID\r\n" + 
				"INNER JOIN bg_country e on a.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f on a.TO_COUNTRY=f.country_id\r\n" + 
				"LEFT OUTER JOIN bg_city g ON a.FROM_CITY=g.CITY_ID\r\n" + 
				"LEFT OUTER JOIN bg_city h ON a.TO_CITY=h.CITY_ID\r\n" + 
				"where a.CLIENT_TYPE_ID=coalesce(:clientTypeId,a.CLIENT_TYPE_ID)\r\n" + 
				"and a.ACTIVE_STATUS=coalesce(:status,a.ACTIVE_STATUS)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:clientName, '%')\r\n" + 
				"and a.REMARKS LIKE CONCAT('%',:remarks, '%')\r\n" + 
				"group by a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"a.CLIENT_ID,c.user_name,a.SERVICE_ID,d.SERVICE_NAME,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,g.CITY_NAME,\r\n" + 
				"a.TO_COUNTRY,f.country_name,a.TO_CITY,h.CITY_NAME,\r\n" + 
				"a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS";
		
		RowMapper<ModelClientServiceCustom> serviceMapper=new RowMapper<ModelClientServiceCustom>() {

			@Override
			public ModelClientServiceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelClientServiceCustom bn= new ModelClientServiceCustom();
				
				
				bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
				bn.setClientTypeId(rs.getLong("CLIENT_TYPE_ID"));
				bn.setClientTypeName(rs.getString("TYPE_NAME"));
				bn.setClientId(rs.getLong("CLIENT_ID"));
				bn.setClientName(rs.getString("user_name"));
				bn.setServiceId(rs.getLong("SERVICE_ID"));
				bn.setServiceName(rs.getString("SERVICE_NAME"));
				bn.setCareOfName(rs.getString("CARE_OF_NAME"));
				bn.setCareOfContactNo(rs.getString("CARE_OF_CONTACT_NO"));
				bn.setFromCountryId(rs.getLong("FROM_COUNTRY"));
				bn.setFromCountryName(rs.getString("country_name"));
				bn.setFromCityId(rs.getLong("FROM_CITY"));
				bn.setFromCityName(rs.getString("g.CITY_NAME"));
				bn.setToCountryId(rs.getLong("TO_COUNTRY"));
				bn.setToCountryName(rs.getString("f.country_name"));
				bn.setToCity(rs.getLong("TO_CITY"));
				bn.setToCityName(rs.getString("h.CITY_NAME"));
				bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
				bn.setqTY(rs.getInt("QTY"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}else
				{
					bn.setActive(false);
					bn.setsActive("No");
					
				}
				
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("clientTypeId", clientTypeId);
	      System.out.println(" in daoClientServiceImp, clientTypeId:" +clientTypeId);
	      parameters.addValue("clientName", clientName);
	      System.out.println(" in daoClientServiceImp, clientName:" +clientName);
	      parameters.addValue("remarks", remarks);
	      parameters.addValue("status", status);

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}
	
	
	
	

}
