package com.biziitech.attm.daoImp;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import com.biziitech.attm.bg.repository.PassportReceiveRepository;
import com.biziitech.attm.custom.model.ModelPassportReceiveCustom;
import com.biziitech.attm.dao.DaoPassportReceive;
import com.biziitech.attm.model.ModelPassportReceive;



@Service
public class DaoPassportReceiveImp implements DaoPassportReceive{
	
	@Autowired
	private PassportReceiveRepository passportReceiveRepository;
	
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
	public void savePassportReceive_ATTM(ModelPassportReceive modelPassportReceive) {
		// TODO Auto-generated method stub
	
		
		
		passportReceiveRepository.save(modelPassportReceive);
	}

	@Override
	public List<ModelPassportReceive> getPassportReceiveListByCraiteria(Long userId,
			Date passportReceiveDate, int status) {
		// TODO Auto-generated method stub
		List<ModelPassportReceive> resultList= passportReceiveRepository.findPassportReceiveDetails(userId, passportReceiveDate, status);
		
		List<ModelPassportReceive> passportReceiveList=new ArrayList<>();
		
		for(ModelPassportReceive passportReceive: resultList) {
				if(passportReceive.getActiveStatus()==1)
				 { 
					passportReceive.setsActive("Yes");
					passportReceive.setActive(true);
				 }
				 
				 else
				 {
					 passportReceive.setsActive("NO");
					 passportReceive.setActive(false);
				     
				 }
				
				
				 
				passportReceiveList.add(passportReceive);
		}
		
		
		
		return passportReceiveList;
	}


	@Override
	public List<ModelPassportReceiveCustom> getPassportReceiveUnDone(String passportNo, String passengerName, Long userId,
			Long serviceId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS\r\n" + 
				"FROM attm_client_service a \r\n" + 
				"INNER JOIN attm_client_type b ON a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c ON a.CLIENT_ID=c.user_id\r\n" + 
				"INNER JOIN attm_service d ON a.SERVICE_ID=d.SERVICE_ID\r\n" + 
				"LEFT OUTER JOIN attm_passport_receive e ON a.CLIENT_SERVICE_ID=e.CLIENT_SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 and d.PASSPORT_REQUIRED=1 AND a.CLIENT_TYPE_ID=2\r\n" + 
				"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%') \r\n" + 
				"and a.CLIENT_ID=coalesce(:userId,a.CLIENT_ID)\r\n" + 
				"and a.SERVICE_ID=coalesce(:serviceId,a.SERVICE_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:passengerName, '%')\r\n" + 
				"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP) AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"GROUP BY a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS ORDER BY c.user_name";
	
	RowMapper<ModelPassportReceiveCustom> serviceMapper=new RowMapper<ModelPassportReceiveCustom>() {

		@Override
		public ModelPassportReceiveCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelPassportReceiveCustom bn= new ModelPassportReceiveCustom();
			
//			java.util.Date date;
//			Date dateTime = rs.getDate("RECEIVE_DATE");
//			if (dateTime != null)
//			    date = new java.util.Date(dateTime.getTime());
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setReceiveDate(rs.getDate("RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("CLIENT_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setRemarks(rs.getString("e.REMARKS"));
			bn.setActiveStatus(rs.getInt("e.ACTIVE_STATUS"));
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
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoPassportReceiveImp, passportNo:" +passportNo);
      parameters.addValue("passengerName", passengerName);
      System.out.println(" in daoPassportReceiveImp, passengerName:" +passengerName);
      parameters.addValue("serviceId", serviceId);
      System.out.println(" in daoPassportReceiveImp, serviceId:" +serviceId);
      parameters.addValue("userId", userId);
      System.out.println(" in daoPassportReceiveImp, userId:" +userId);
      parameters.addValue("fromDate", fromDate);
      System.out.println(" in daoPassportReceiveImp, fromDate:" +fromDate);
      parameters.addValue("toDate", toDate);
      System.out.println(" in daoPassportReceiveImp, toDate:" +toDate);
	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}


	@Override
	public List<ModelPassportReceiveCustom> getPassportReceiveDone(String passportNo, String passengerName, Long userId,
			Long serviceId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
//		List<ModelPassportReceive> resultList= passportReceiveRepository.findPassportReceiveDone(passportNo, passengerName, userId, serviceId, fromDate, toDate);
//		
//		List<ModelPassportReceive> passportReceiveList=new ArrayList<>();
//		
//		for(ModelPassportReceive modelPassportReceive: resultList) {
//				if(modelPassportReceive.getActiveStatus()==1)
//				 { 
//					modelPassportReceive.setsActive("Yes");
//					modelPassportReceive.setActive(true);
//				 }
//				 
//				 else
//				 {
//					 modelPassportReceive.setsActive("NO");
//					 modelPassportReceive.setActive(false);
//				     
//				 }
//				
//				
//				 
//				passportReceiveList.add(modelPassportReceive);
//		}
//		
//		
//		
//		return passportReceiveList;
		
		
		String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS\r\n" + 
				"FROM attm_passport_receive e\r\n" + 
				"INNER JOIN attm_client_service a ON e.CLIENT_SERVICE_ID=a.CLIENT_SERVICE_ID\r\n" + 
				"INNER JOIN attm_client_type b ON a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c ON a.CLIENT_ID=c.user_id\r\n" + 
				"INNER JOIN attm_service d ON a.SERVICE_ID=d.SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 and d.PASSPORT_REQUIRED=1 AND a.CLIENT_TYPE_ID=2\r\n" + 
				"and c.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%') \r\n" + 
				"and a.CLIENT_ID=coalesce(:userId,a.CLIENT_ID)\r\n" + 
				"and a.SERVICE_ID=coalesce(:serviceId,a.SERVICE_ID)\r\n" + 
				"and c.user_name LIKE CONCAT('%',:passengerName, '%')\r\n" + 
				"and a.ENTRY_TIMESTAMP BETWEEN coalesce(:fromDate,a.ENTRY_TIMESTAMP) AND coalesce(:toDate,a.ENTRY_TIMESTAMP)\r\n" + 
				"GROUP BY a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS ORDER BY c.user_name";
	
	RowMapper<ModelPassportReceiveCustom> serviceMapper=new RowMapper<ModelPassportReceiveCustom>() {

		@Override
		public ModelPassportReceiveCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelPassportReceiveCustom bn= new ModelPassportReceiveCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setReceiveDate(rs.getDate("RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("CLIENT_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setRemarks(rs.getString("e.REMARKS"));
			bn.setActiveStatus(rs.getInt("e.ACTIVE_STATUS"));
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
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoPassportReceiveImp, passportNo:" +passportNo);
      parameters.addValue("passengerName", passengerName);
      System.out.println(" in daoPassportReceiveImp, passengerName:" +passengerName);
      parameters.addValue("serviceId", serviceId);
      System.out.println(" in daoPassportReceiveImp, serviceId:" +serviceId);
      parameters.addValue("userId", userId);
      System.out.println(" in daoPassportReceiveImp, userId:" +userId);
      parameters.addValue("fromDate", fromDate);
      System.out.println(" in daoPassportReceiveImp, fromDate:" +fromDate);
      parameters.addValue("toDate", toDate);
      System.out.println(" in daoPassportReceiveImp, toDate:" +toDate);
	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	
	}


	@Override
	public List<ModelPassportReceiveCustom> getPassportReceiveById(Long passportReceiveId) {
		// TODO Auto-generated method stub
//		List<ModelPassportReceive> resultList= passportReceiveRepository.findPassportReceiveById(passportReceiveId);
//		
//		List<ModelPassportReceive> passportReceiveList=new ArrayList<>();
//		
//		for(ModelPassportReceive passportReceive: resultList) {
//				if(passportReceive.getActiveStatus()==1)
//				 { 
//					passportReceive.setsActive("Yes");
//					passportReceive.setActive(true);
//				 }
//				 
//				 else
//				 {
//					 passportReceive.setsActive("NO");
//					 passportReceive.setActive(false);
//				     
//				 }
//				
//				
//				 
//				passportReceiveList.add(passportReceive);
//		}
//		
//		
//		
//		return passportReceiveList;
		
		
		String qry="SELECT a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS\r\n" + 
				"FROM attm_passport_receive e\r\n" + 
				"INNER JOIN attm_client_service a ON e.CLIENT_SERVICE_ID=a.CLIENT_SERVICE_ID\r\n" + 
				"INNER JOIN attm_client_type b ON a.CLIENT_TYPE_ID=b.CLIENT_TYPE_ID\r\n" + 
				"INNER JOIN bg_user c ON a.CLIENT_ID=c.user_id\r\n" + 
				"INNER JOIN attm_service d ON a.SERVICE_ID=d.SERVICE_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 and d.PASSPORT_REQUIRED=1 AND a.CLIENT_TYPE_ID=2\r\n" + 
				"and e.PASSPORT_RECEIVE_ID=coalesce(:passportReceiveId,e.PASSPORT_RECEIVE_ID)\r\n" + 
				"GROUP BY a.CLIENT_SERVICE_ID,a.CLIENT_TYPE_ID,b.TYPE_NAME,a.CLIENT_ID,c.user_name,c.PASSPORT_NO,\r\n" + 
				"a.SERVICE_ID,d.SERVICE_NAME,d.PASSPORT_REQUIRED,\r\n" + 
				"a.CARE_OF_NAME,a.CARE_OF_CONTACT_NO,a.FROM_COUNTRY,a.FROM_CITY,\r\n" + 
				"a.TO_COUNTRY,a.TO_CITY,a.AGREEMENT_AMT,a.QTY,a.ACTIVE_STATUS,a.REMARKS,a.ENTRY_TIMESTAMP,\r\n" + 
				"e.PASSPORT_RECEIVE_ID,e.RECEIVE_DATE,e.REMARKS,e.ACTIVE_STATUS ORDER BY c.user_name";
	
	RowMapper<ModelPassportReceiveCustom> serviceMapper=new RowMapper<ModelPassportReceiveCustom>() {

		@Override
		public ModelPassportReceiveCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelPassportReceiveCustom bn= new ModelPassportReceiveCustom();
						
			bn.setPassportReceiveId(rs.getLong("PASSPORT_RECEIVE_ID"));
			bn.setReceiveDate(rs.getDate("RECEIVE_DATE"));
			bn.setClientServiceId(rs.getLong("CLIENT_SERVICE_ID"));
			bn.setClientServiceEntryDate(rs.getDate("ENTRY_TIMESTAMP"));
			bn.setUserId(rs.getLong("CLIENT_ID"));
			bn.setUserName(rs.getString("user_name"));
			bn.setServiceId(rs.getLong("SERVICE_ID"));
			bn.setServiceName(rs.getString("SERVICE_NAME"));
			bn.setRemarks(rs.getString("e.REMARKS"));
			bn.setActiveStatus(rs.getInt("e.ACTIVE_STATUS"));
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
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportReceiveId", passportReceiveId);
      System.out.println(" in daoPassportReceiveImp, passportReceiveId:" +passportReceiveId);
     
	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);

		
		
	}

}
