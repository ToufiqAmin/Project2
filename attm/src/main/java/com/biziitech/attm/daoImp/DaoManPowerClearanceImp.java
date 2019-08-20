package com.biziitech.attm.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.biziitech.attm.bg.repository.ManPowerClearanceRepository;
import com.biziitech.attm.custom.model.ModelManPowerClearanceCustom;
import com.biziitech.attm.dao.DaoManPowerClearance;
import com.biziitech.attm.model.ModelManPowerClearance;



@Service
public class DaoManPowerClearanceImp implements DaoManPowerClearance{
	
	@Autowired
	private ManPowerClearanceRepository manPowerClearanceRepository;
	
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
	public void saveATTM_ManPowerClearance(ModelManPowerClearance modelManPowerClearance) {
		// TODO Auto-generated method stub
		
		manPowerClearanceRepository.save(modelManPowerClearance);
		
	}

	@Override
	public List<ModelManPowerClearance> getManPower_ATTMByCraiteria(String userName, Date sendDate,
			Date receiveDate, int status) {
		// TODO Auto-generated method stub
		List<ModelManPowerClearance> resultList= manPowerClearanceRepository.findManPowerClearanceDetails(userName, sendDate, receiveDate, status);		
		List<ModelManPowerClearance> manPowerClearanceList=new ArrayList<>();		
		for(ModelManPowerClearance manPowerClearance: resultList) {
				if(manPowerClearance.getActiveStatus()==1)
				 { 
					manPowerClearance.setsActive("Yes");
					manPowerClearance.setActive(true);
				 }				 
				 else
				 {
					 manPowerClearance.setsActive("NO");
					 manPowerClearance.setActive(false);				     
				 }
				 
				manPowerClearanceList.add(manPowerClearance);
		}
				
		return manPowerClearanceList;
	}

	@Override
	public List<ModelManPowerClearanceCustom> getMCUnDoneWithPR(String passportNo, String userName, Long userId,
			Date fromDate, Date toDate, String clearanceAgentName) {
		// TODO Auto-generated method stub
		String qry="SELECT h.PASSPORT_RECEIVE_ID,h.RECEIVE_DATE,a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"h.USER_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO, \r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name, \r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,h.ACTIVE_STATUS, \r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME,\r\n" + 
				"g.RECEIVE_DATE,g.DELIVERY_DATE,a.SERVICE_ID,i.SERVICE_NAME,i.PASSPORT_REQUIRED\r\n" + 
				"FROM attm_passport_receive h\r\n" + 
				"INNER JOIN attm_client_service a ON h.CLIENT_SERVICE_ID=a.CLIENT_SERVICE_ID\r\n" + 
				"INNER JOIN attm_client_type b on a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c on h.USER_ID=c.user_id\r\n" + 
				"INNER JOIN bg_country e on a.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f on a.TO_COUNTRY=f.country_id\r\n" + 
				"INNER JOIN attm_service i ON a.SERVICE_ID=i.SERVICE_ID\r\n" + 
				"LEFT OUTER JOIN attm_manpower_clearance g on h.CLIENT_SERVICE_ID=g.CLIENT_SERVICE_ID\r\n" + 
				"WHERE c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				"and h.USER_ID=coalesce(:userId,h.USER_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" + 
				//"and g.CLEARANCE_AGENT_NAME LIKE CONCAT('%',:clearanceAgentName, '%')\r\n" + 
				"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"and h.ACTIVE_STATUS=1 AND a.CLIENT_TYPE_ID=2\r\n" + 
				"GROUP BY h.PASSPORT_RECEIVE_ID,h.RECEIVE_DATE,a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"h.USER_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO, \r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name, \r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,h.ACTIVE_STATUS, \r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME,\r\n" + 
				"g.RECEIVE_DATE,g.DELIVERY_DATE,a.SERVICE_ID,i.SERVICE_NAME,i.PASSPORT_REQUIRED \r\n" + 
				"order by c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("h.RECEIVE_DATE"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("g.RECEIVE_DATE"));
			bn.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			System.out.println("MC ID: "+bn.getManPowerClearanceId());
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
			bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoMCImp, passportNo:" +passportNo);
      parameters.addValue("userName", userName);
      System.out.println(" in daoMCImp, userName:" +userName);
      parameters.addValue("userId", userId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);
      parameters.addValue("clearanceAgentName", clearanceAgentName);


	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

/*	@Override
	public List<ModelManPowerClearanceCustom> getMCUnDoneWithoutPR(String passportNo, String userName, Long userId,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"a.CLIENT_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO, \r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name, \r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,a.ACTIVE_STATUS, \r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME, \r\n" + 
				"g.RECEIVE_DATE,a.SERVICE_ID,h.SERVICE_NAME,h.PASSPORT_REQUIRED,\r\n" + 
				"i.PASSPORT_RECEIVE_ID,i.RECEIVE_DATE\r\n" + 
				"FROM attm_client_service a\r\n" + 
				"INNER JOIN attm_client_type b on a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c on a.CLIENT_ID=c.user_id\r\n" + 
				"INNER JOIN bg_country e on a.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f on a.TO_COUNTRY=f.country_id\r\n" + 
				"INNER JOIN attm_service h ON a.SERVICE_ID=h.SERVICE_ID\r\n" + 
				"LEFT OUTER JOIN attm_manpower_clearance g on a.CLIENT_SERVICE_ID=g.CLIENT_SERVICE_ID\r\n" + 
				"LEFT OUTER JOIN attm_passport_receive i ON a.CLIENT_SERVICE_ID=i.CLIENT_SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 and a.CLIENT_TYPE_ID=2 and h.PASSPORT_REQUIRED=1\r\n" + 
				"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				"and a.CLIENT_ID=coalesce(:userId,a.CLIENT_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" + 
				"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"GROUP BY a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"a.CLIENT_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO, \r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name, \r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,a.ACTIVE_STATUS, \r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME, \r\n" + 
				"g.RECEIVE_DATE,a.SERVICE_ID,h.SERVICE_NAME,h.PASSPORT_REQUIRED,\r\n" + 
				"i.PASSPORT_RECEIVE_ID,i.RECEIVE_DATE ORDER BY c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("i.RECEIVE_DATE"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("g.RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("CLIENT_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			System.out.println("Manpower Clearance Id: "+bn.getManPowerClearanceId());
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoMCImp, passportNo:" +passportNo);
      parameters.addValue("userName", userName);
      System.out.println(" in daoMCImp, userName:" +userName);
      parameters.addValue("userId", userId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);

	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}
*/
	@Override
	public List<ModelManPowerClearanceCustom> getMCDoneWithPR(String passportNo, String userName, Long userId, Date fromDate,
			Date toDate, String clearanceAgentName) {
		// TODO Auto-generated method stub
		String qry="SELECT a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
				"a.SENT_DATE,a.RECEIVE_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
				"a.CLEARANCE_AGENT_NAME,a.REMARKS,\r\n" + 
				"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
				"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME\r\n" + 
				"FROM attm_manpower_clearance a  \r\n" + 
				"INNER JOIN attm_client_service b ON a.CLIENT_SERVICE_ID=b.CLIENT_SERVICE_ID \r\n" + 
				"INNER JOIN bg_user c ON a.USER_ID=c.user_id \r\n" + 
				"INNER JOIN attm_passport_receive d ON a.CLIENT_SERVICE_ID=d.CLIENT_SERVICE_ID \r\n" + 
				"INNER JOIN attm_service e ON b.SERVICE_ID=e.SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1\r\n" + 
				"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" +
				"and a.CLEARANCE_AGENT_NAME LIKE CONCAT('%',:clearanceAgentName, '%')\r\n" +
				"and a.RECEIVE_DATE BETWEEN coalesce(:fromDate,a.RECEIVE_DATE)\r\n" + 
				"AND coalesce(:toDate,a.RECEIVE_DATE)\r\n" + 
				"GROUP BY a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
				"a.SENT_DATE,a.RECEIVE_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
				"a.CLEARANCE_AGENT_NAME,a.REMARKS,\r\n" + 
				"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
				"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME \r\n" + 
				"ORDER BY c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("d.RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("a.RECEIVE_DATE"));
			bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
			bn.setActiveStatus(rs.getInt("a.ACTIVE_STATUS"));
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
			bn.setClearanceAgentName(rs.getString("CLEARANCE_AGENT_NAME"));
			if(bn.getActiveStatus()==1) 
			{
				bn.setActive(true);
				bn.setsActive("Yes");
			}
			else 
			{
				bn.setActive(false);
				bn.setsActive("No");
			}
			bn.setRemarks(rs.getString("a.REMARKS"));
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoMCImp, passportNo:" +passportNo);
      parameters.addValue("userName", userName);
      System.out.println(" in daoMCImp, userName:" +userName);
      parameters.addValue("userId", userId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);
      parameters.addValue("clearanceAgentName", clearanceAgentName);


	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}
	
/*	@Override
	public List<ModelManPowerClearanceCustom> getMCDoneWithoutPR(String passportNo, String userName, Long userId,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
				"a.SENT_DATE,a.RECEIVE_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
				"a.CLEARANCE_AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,\r\n" + 
				"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
				"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME\r\n" + 
				"FROM attm_manpower_clearance a  \r\n" + 
				"INNER JOIN attm_client_service b ON a.CLIENT_SERVICE_ID=b.CLIENT_SERVICE_ID \r\n" + 
				"INNER JOIN bg_user c ON a.USER_ID=c.user_id \r\n" + 
				"LEFT OUTER JOIN attm_passport_receive d ON a.CLIENT_SERVICE_ID=d.CLIENT_SERVICE_ID \r\n" + 
				"INNER JOIN attm_service e ON b.SERVICE_ID=e.SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1\r\n" + 
				"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" + 
				"and a.RECEIVE_DATE BETWEEN coalesce(:fromDate,a.RECEIVE_DATE)\r\n" + 
				"AND coalesce(:toDate,a.RECEIVE_DATE)\r\n" + 
				"GROUP BY a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
				"a.SENT_DATE,a.RECEIVE_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
				"a.CLEARANCE_AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,\r\n" + 
				"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
				"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME \r\n" + 
				"ORDER BY c.user_name";
		
		RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

			@Override
			public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
							
				bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
				bn.setPassportReceiveDate(rs.getDate("d.RECEIVE_DATE"));
				bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
				bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				bn.setServiceId(rs.getLong("SERVICE_ID"));
				bn.setServiceName(rs.getString("SERVICE_NAME"));
				bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
				bn.setSentDate(rs.getDate("SENT_DATE"));
				bn.setReceiveDate(rs.getDate("a.RECEIVE_DATE"));
				bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
				bn.setActiveStatus(rs.getInt("a.ACTIVE_STATUS"));
				if(bn.getManPowerClearanceId()!=0) 
				{
					bn.setManPowerClearance(true);
					bn.setsManPowerClearance("Yes");
					
					
				}
				else 
				{
					bn.setManPowerClearance(false);
					bn.setsManPowerClearance("No");
				}
				
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else 
				{
					bn.setActive(false);
					bn.setsActive("No");
				}
				bn.setClearanceAgentName(rs.getString("CLEARANCE_AGENT_NAME"));
				bn.setRemarks(rs.getString("a.REMARKS"));				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("passportNo", passportNo);
	      System.out.println(" in daoMCImp, passportNo:" +passportNo);
	      parameters.addValue("userName", userName);
	      System.out.println(" in daoMCImp, userName:" +userName);
	      parameters.addValue("userId", userId);
	      parameters.addValue("fromDate", fromDate);
	      parameters.addValue("toDate", toDate);

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}
*/
	@Override
	public List<ModelManPowerClearanceCustom> getMCById(Long manPowerClearanceId) {
		// TODO Auto-generated method stub
		String qry="SELECT h.PASSPORT_RECEIVE_ID,h.RECEIVE_DATE,a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"h.USER_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name,\r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,h.ACTIVE_STATUS,\r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME,\r\n" + 
				"g.RECEIVE_DATE,g.DELIVERY_DATE,a.SERVICE_ID,i.SERVICE_NAME,i.PASSPORT_REQUIRED,g.ACTIVE_STATUS,\r\n" + 
				"g.REMARKS,g.CLIENT_SERVICE_ID\r\n" + 
				"FROM attm_manpower_clearance g\r\n" + 
				"LEFT OUTER JOIN attm_passport_receive h ON g.CLIENT_SERVICE_ID=h.CLIENT_SERVICE_ID\r\n" + 
				"LEFT OUTER JOIN attm_client_service a ON h.CLIENT_SERVICE_ID=a.CLIENT_SERVICE_ID\r\n" + 
				"INNER JOIN attm_client_type b on a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c on h.USER_ID=c.user_id\r\n" + 
				"INNER JOIN bg_country e on a.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f on a.TO_COUNTRY=f.country_id\r\n" + 
				"INNER JOIN attm_service i ON a.SERVICE_ID=i.SERVICE_ID\r\n" + 
				"WHERE g.ACTIVE_STATUS=1\r\n" + 
				"and g.MANPOWER_CLEARANCE_ID=coalesce(:manPowerClearanceId,g.MANPOWER_CLEARANCE_ID)\r\n" + 
				"GROUP BY h.PASSPORT_RECEIVE_ID,h.RECEIVE_DATE,a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,\r\n" + 
				"h.USER_ID,c.user_name,c.PASSPORT_NO,a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,\r\n" + 
				"a.FROM_COUNTRY,e.country_name,a.FROM_CITY,a.TO_COUNTRY,f.country_name,\r\n" + 
				"a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ENTRY_TIMESTAMP,h.ACTIVE_STATUS,\r\n" + 
				"g.MANPOWER_CLEARANCE_ID,g.EXPENSE_AMOUNT,g.SENT_DATE,g.CLEARANCE_AGENT_NAME,\r\n" + 
				"g.RECEIVE_DATE,g.DELIVERY_DATE,a.SERVICE_ID,i.SERVICE_NAME,i.PASSPORT_REQUIRED,g.ACTIVE_STATUS,\r\n" + 
				"g.REMARKS,g.CLIENT_SERVICE_ID order by c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("h.RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("g.RECEIVE_DATE"));
			bn.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
			bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
			bn.setActiveStatus(rs.getInt("g.ACTIVE_STATUS"));
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
			
			if(bn.getActiveStatus()==1) 
			{
				bn.setActive(true);
				bn.setsActive("Yes");
			}
			else 
			{
				bn.setActive(false);
				bn.setsActive("No");
			}
			bn.setClearanceAgentName(rs.getString("CLEARANCE_AGENT_NAME"));
			bn.setRemarks(rs.getString("g.REMARKS"));				
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("manPowerClearanceId", manPowerClearanceId);
      System.out.println(" in daoMCImp, manPowerClearanceId:" +manPowerClearanceId);
      

	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}


@Override
public List<ModelManPowerClearanceCustom> getMCDoneWithoutReceiveDate(String passportNo, String userName, Long userId,
		Date fromDate, Date toDate, String clearanceAgentName) {
	// TODO Auto-generated method stub
	String qry="SELECT a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
			"a.SENT_DATE,a.RECEIVE_DATE,a.DELIVERY_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
			"a.CLEARANCE_AGENT_NAME,a.REMARKS,\r\n" + 
			"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO,a.ENTRY_TIMESTAMP, \r\n" + 
			"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME\r\n" + 
			"FROM attm_manpower_clearance a  \r\n" + 
			"INNER JOIN attm_client_service b ON a.CLIENT_SERVICE_ID=b.CLIENT_SERVICE_ID \r\n" + 
			"INNER JOIN bg_user c ON a.USER_ID=c.user_id \r\n" + 
			"LEFT OUTER JOIN attm_passport_receive d ON a.CLIENT_SERVICE_ID=d.CLIENT_SERVICE_ID \r\n" + 
			"INNER JOIN attm_service e ON b.SERVICE_ID=e.SERVICE_ID\r\n" + 
			"WHERE a.ACTIVE_STATUS=1 AND a.RECEIVE_DATE IS NULL\r\n" + 
			"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
			"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
			"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" +
			"and a.CLEARANCE_AGENT_NAME LIKE CONCAT('%',:clearanceAgentName, '%')\r\n" +
			"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP)\r\n" + 
			"AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
			"GROUP BY a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
			"a.SENT_DATE,a.RECEIVE_DATE,a.DELIVERY_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
			"a.CLEARANCE_AGENT_NAME,a.REMARKS,\r\n" + 
			"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO,a.ENTRY_TIMESTAMP, \r\n" + 
			"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME \r\n" + 
			"ORDER BY c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("d.RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("a.RECEIVE_DATE"));
			bn.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
			bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
			bn.setActiveStatus(rs.getInt("a.ACTIVE_STATUS"));
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
			
			if(bn.getActiveStatus()==1) 
			{
				bn.setActive(true);
				bn.setsActive("Yes");
			}
			else 
			{
				bn.setActive(false);
				bn.setsActive("No");
			}
			bn.setClearanceAgentName(rs.getString("CLEARANCE_AGENT_NAME"));
			bn.setRemarks(rs.getString("a.REMARKS"));				
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoMCImp, passportNo:" +passportNo);
      parameters.addValue("userName", userName);
      System.out.println(" in daoMCImp, userName:" +userName);
      parameters.addValue("userId", userId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);
      parameters.addValue("clearanceAgentName", clearanceAgentName);

	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
}


@Override
public List<ModelManPowerClearanceCustom> getMCDoneWithReceiveDate(String passportNo, String userName, Long userId,
		Date fromDate, Date toDate, String clearanceAgentName) {
	// TODO Auto-generated method stub
	String qry="SELECT a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
			"a.SENT_DATE,a.RECEIVE_DATE,a.DELIVERY_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
			"a.CLEARANCE_AGENT_NAME,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
			"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
			"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME\r\n" + 
			"FROM attm_manpower_clearance a  \r\n" + 
			"INNER JOIN attm_client_service b ON a.CLIENT_SERVICE_ID=b.CLIENT_SERVICE_ID \r\n" + 
			"INNER JOIN bg_user c ON a.USER_ID=c.user_id \r\n" + 
			"INNER JOIN attm_passport_receive d ON a.CLIENT_SERVICE_ID=d.CLIENT_SERVICE_ID \r\n" + 
			"INNER JOIN attm_service e ON b.SERVICE_ID=e.SERVICE_ID\r\n" + 
			"WHERE a.ACTIVE_STATUS=1 AND a.RECEIVE_DATE IS NOT NULL\r\n" + 
			"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
			"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
			"and c.user_name LIKE CONCAT('%',:userName, '%')\r\n" +
			"and a.CLEARANCE_AGENT_NAME LIKE CONCAT('%',:clearanceAgentName, '%')\r\n" +
			"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP)\r\n" + 
			"AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
			"GROUP BY a.MANPOWER_CLEARANCE_ID,a.CLIENT_SERVICE_ID,a.USER_ID, \r\n" + 
			"a.SENT_DATE,a.RECEIVE_DATE,a.DELIVERY_DATE,a.ACTIVE_STATUS,a.EXPENSE_AMOUNT,\r\n" + 
			"a.CLEARANCE_AGENT_NAME,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
			"b.ENTRY_TIMESTAMP,c.user_name,c.PASSPORT_NO, \r\n" + 
			"d.PASSPORT_RECEIVE_ID,d.RECEIVE_DATE,b.SERVICE_ID,e.SERVICE_NAME \r\n" + 
			"ORDER BY c.user_name";
	
	RowMapper<ModelManPowerClearanceCustom> serviceMapper=new RowMapper<ModelManPowerClearanceCustom>() {

		@Override
		public ModelManPowerClearanceCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelManPowerClearanceCustom bn= new ModelManPowerClearanceCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setPassportReceiveDate(rs.getDate("d.RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setManPowerClearanceId(rs.getLong("MANPOWER_CLEARANCE_ID"));
			bn.setSentDate(rs.getDate("SENT_DATE"));
			bn.setReceiveDate(rs.getDate("a.RECEIVE_DATE"));
			bn.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
			bn.setExpenseAMT(rs.getDouble("EXPENSE_AMOUNT"));
			bn.setActiveStatus(rs.getInt("a.ACTIVE_STATUS"));
			if(bn.getManPowerClearanceId()!=0) 
			{
				bn.setManPowerClearance(true);
				bn.setsManPowerClearance("Yes");
				
				
			}
			else 
			{
				bn.setManPowerClearance(false);
				bn.setsManPowerClearance("No");
			}
			
			if(bn.getActiveStatus()==1) 
			{
				bn.setActive(true);
				bn.setsActive("Yes");
			}
			else 
			{
				bn.setActive(false);
				bn.setsActive("No");
			}
			bn.setClearanceAgentName(rs.getString("CLEARANCE_AGENT_NAME"));
			bn.setRemarks(rs.getString("a.REMARKS"));				
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoMCImp, passportNo:" +passportNo);
      parameters.addValue("userName", userName);
      System.out.println(" in daoMCImp, userName:" +userName);
      parameters.addValue("userId", userId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);
      parameters.addValue("clearanceAgentName", clearanceAgentName);

	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
}


	
	
	
	

}
