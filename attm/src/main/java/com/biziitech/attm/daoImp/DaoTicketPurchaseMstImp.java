package com.biziitech.attm.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


import com.biziitech.attm.bg.repository.TicketPurchaseMstRepository;
import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.dao.DaoTicketPurchaseMst;
import com.biziitech.attm.model.ModelTicketPurchaseMst;

@Service
public class DaoTicketPurchaseMstImp implements DaoTicketPurchaseMst{
	
	@Autowired
	private TicketPurchaseMstRepository ticketPurchaseMstRepository;
	
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
	@Transactional
	public void saveTicketPurchaseMst(ModelTicketPurchaseMst modelTicketPurchaseMst) {
		// TODO Auto-generated method stub
		ticketPurchaseMstRepository.save(modelTicketPurchaseMst);
	}

	@Override
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstUnDone(Long fromAgentId,Long toAgentId, String passportNo, Long userId,
			Long fromCountryId, Long fromCityId, Long toCountryId, Long toCityId,Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.USER_ID,b.user_name,b.PASSPORT_NO,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.COUNTRY_FROM,e.country_name,a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"a.CITY_FROM,a.COUNTRY_TO,f.country_name,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.NO_OF_TICKETS,a.APPROX_AMT,\r\n" + 
				"a.AGREEMENT_AMT,a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,\r\n" + 
				"a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT\r\n" + 
				"FROM attm_ticket_request a \r\n" + 
				"LEFT OUTER JOIN bg_user b ON a.USER_ID=b.user_id\r\n" + 
				"LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID \r\n" + 
				"LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID \r\n" + 
				"LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID\r\n" + 
				"INNER JOIN bg_country e ON a.COUNTRY_FROM=e.country_id\r\n" + 
				"INNER JOIN bg_country f ON a.COUNTRY_TO=f.country_id\r\n" +
				"INNER JOIN bg_city l ON a.CITY_FROM=l.CITY_ID \r\n" + 
				"INNER JOIN bg_city m ON a.CITY_TO=m.CITY_ID\r\n"+
				"LEFT OUTER JOIN attm_ticket_purchase_mst g ON a.TICKET_REQUEST_ID=g.TICKET_REQUEST_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 and a.AUTO_PAYMENT=1\r\n" + 
				//"and b.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				//"and a.FROM_AGENT_ID=coalesce(:toAgentId,a.FROM_AGENT_ID)\r\n" + 
				//"and a.TO_AGENT_ID=coalesce(:fromAgentId,a.TO_AGENT_ID)\r\n" + 
				//"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				"and a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)\r\n" + 
				"and a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)\r\n" + 
				"and a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)\r\n" + 
				"and a.CITY_TO=coalesce(:toCityId,a.CITY_TO)\r\n" + 
				"and a.REQUEST_DATE BETWEEN\r\n"+ 
				"coalesce(:fromDate,a.REQUEST_DATE)\r\n"+
				"AND coalesce(:toDate,a.REQUEST_DATE)\r\n"+
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.USER_ID,b.user_name,b.PASSPORT_NO,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.COUNTRY_FROM,e.country_name,a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"a.CITY_FROM,a.COUNTRY_TO,f.country_name,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.NO_OF_TICKETS,a.APPROX_AMT,\r\n" + 
				"a.AGREEMENT_AMT,a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,\r\n" + 
				"a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT order by a.REQUEST_DATE DESC";
		
		/*
		 * 
		 * SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,
		   a.USER_ID,b.user_name,b.PASSPORT_NO, 
		   a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG, 
		   a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG, 
		   a.COUNTRY_FROM,e.country_name,a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
		   a.CITY_FROM,a.COUNTRY_TO,f.country_name,a.POSSIBLE_FLIGHT_DATE,
		   a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.NO_OF_TICKETS,a.APPROX_AMT,
		   a.AGREEMENT_AMT,a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,
		   a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,
		   g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT
		   FROM attm_ticket_request a
		   LEFT OUTER JOIN bg_user b ON a.USER_ID=b.user_id
		   LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID
		   LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID
		   LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID
		   INNER JOIN bg_country e ON a.COUNTRY_FROM=e.country_id
		   INNER JOIN bg_country f ON a.COUNTRY_TO=f.country_id
		   INNER JOIN bg_city l ON a.CITY_FROM=l.CITY_ID
		   INNER JOIN bg_city m ON a.CITY_TO=m.CITY_ID
		   LEFT OUTER JOIN attm_ticket_purchase_mst g ON a.TICKET_REQUEST_ID=g.TICKET_REQUEST_ID
		   WHERE a.ACTIVE_STATUS=1 and a.AUTO_PAYMENT=1
		   //and b.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')
		   //and a.FROM_AGENT_ID=coalesce(:toAgentId,a.FROM_AGENT_ID)
		   //and a.TO_AGENT_ID=coalesce(:fromAgentId,a.TO_AGENT_ID)
		   //and a.USER_ID=coalesce(:userId,a.USER_ID)
		   and a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)
		   and a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)
		   and a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)
		   and a.CITY_TO=coalesce(:toCityId,a.CITY_TO)
		   and a.REQUEST_DATE BETWEEN
		   coalesce(:fromDate,a.REQUEST_DATE)
		   AND coalesce(:toDate,a.REQUEST_DATE)
		   GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,
		   a.USER_ID,b.user_name,b.PASSPORT_NO,
		   a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,
		   a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,
		   a.COUNTRY_FROM,e.country_name,a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
		   a.CITY_FROM,a.COUNTRY_TO,f.country_name,a.POSSIBLE_FLIGHT_DATE,
		   a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.NO_OF_TICKETS,a.APPROX_AMT,
		   a.AGREEMENT_AMT,a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,
		   a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT, 
		   g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT order by a.REQUEST_DATE DESC
		 * 
		 * */
		
	
	RowMapper<ModelTicketPurchaseCustom> serviceMapper=new RowMapper<ModelTicketPurchaseCustom>() {

		@Override
		public ModelTicketPurchaseCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelTicketPurchaseCustom bn= new ModelTicketPurchaseCustom();
			Integer purChasedQTY=rs.getInt("a.NO_OF_TICKETS")-rs.getInt("g.NO_OF_TICKETS");
			
			bn.setTicketPurchaseMstId(rs.getLong("TICKET_PURCHASE_MST_ID"));
			bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
			bn.setRequestDate(rs.getDate("REQUEST_DATE"));
			bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
			bn.setFromAgentName(rs.getString("h.AGENT_NAME"));
			bn.setFromAgentSelfStatus(rs.getInt("h.SELF_FLAG"));
			bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
			bn.setToAgentName(rs.getString("c.AGENT_NAME"));
			bn.setToAgentSelfStatus(rs.getInt("c.SELF_FLAG"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			
			if(bn.getUserId()!=null || bn.getUserId()!=0) 
			{
				bn.setUserId(bn.getUserId());
				bn.setUserName(bn.getUserName());
			}
			else {
				
			if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==0) 
			{	
				bn.setUserId(0L);
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==0 && bn.getToAgentSelfStatus()==1) 
			{	
				bn.setUserId(0L);
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==1)
			{
				bn.setUserId(0L);
				bn.setUserName("");
			}
			else 
			{
				bn.setUserId(bn.getUserId());
				bn.setUserName(bn.getUserName());
			}
			
			}
			
			if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
			{
				bn.setFromAgentId(0L);
				bn.setFromAgentName("");
				bn.setToAgentId(0L);
				bn.setToAgentName("");
			}
			else 
			{
				bn.setFromAgentId(bn.getFromAgentId());
				bn.setFromAgentName(bn.getFromAgentName());
				bn.setToAgentId(bn.getToAgentId());
				bn.setToAgentName(bn.getToAgentName());
			}
			
			bn.setFromCountryId(rs.getLong("COUNTRY_FROM"));
			bn.setFromCountryName(rs.getString("e.country_name"));
			bn.setFromCityId(rs.getLong("CITY_FROM"));
			bn.setFromCityName(rs.getString("l.CITY_NAME"));
			bn.setToCountryId(rs.getLong("COUNTRY_TO"));
			bn.setTocountryName(rs.getString("f.country_name"));
			bn.setToCityId(rs.getLong("CITY_TO"));
			bn.setToCityName(rs.getString("m.CITY_NAME"));
			bn.setTicketRequestNoOfTickets(rs.getInt("a.NO_OF_TICKETS"));
			bn.setNoOfTickets(rs.getInt("g.NO_OF_TICKETS"));
			bn.setApproxAMT(rs.getDouble("APPROX_AMT"));
			bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
			bn.setTicketRequesterId(rs.getLong("REQUESTER_ID"));
			bn.setTicketRequesterName(rs.getString("REQUESTER_NAME"));
			bn.setPossibleFlightDate(rs.getDate("POSSIBLE_FLIGHT_DATE"));
			bn.setAdvPayment(rs.getDouble("ADV_PAYMENT"));
			bn.setAutoPayment(rs.getInt("AUTO_PAYMENT"));
			if(bn.getAutoPayment()==1) 
			{
				bn.setsAuotPayment("Yes");
				bn.setAutoPay(true);
			}
			else {
				bn.setsAuotPayment("No");
				bn.setAutoPay(false);
			}
			bn.setTicketOwnerCompanyId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
			bn.setOwnerCompanyName(rs.getString("OWNER_COMPANY_NAME"));
			bn.setTicketType(rs.getInt("TICKET_TYPE"));
			bn.setPurchasedQTY(purChasedQTY);
			
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("passportNo", passportNo);
      System.out.println(" in daoTicketPurchaseMstImp, passportNo:" +passportNo);
      parameters.addValue("fromAgentId", fromAgentId);
      parameters.addValue("toAgentId", toAgentId);
      System.out.println(" in daoTicketPurchaseMstImp, toAgentId:" +toAgentId);
      parameters.addValue("userId", userId);
      parameters.addValue("fromCountryId", fromCountryId);
      parameters.addValue("fromCityId", fromCityId);
      parameters.addValue("toCountryId", toCountryId);
      parameters.addValue("toCityId", toCityId);
      parameters.addValue("fromDate", fromDate);
      parameters.addValue("toDate", toDate);
      

	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstDone(Long fromAgentId,Long toAgentId, String passportNo, Long userId,
			Long fromCountryId, Long fromCityId, Long toCountryId, Long toCityId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.USER_ID,b.user_name,b.PASSPORT_NO,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"a.NO_OF_TICKETS,a.APPROX_AMT,a.AGREEMENT_AMT,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,\r\n" + 
				"a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT,g.PURCHASE_DATE,\r\n"+
				"a.COUNTRY_FROM,a.COUNTRY_TO,e.country_name,f.country_name,\r\n"+
				"a.CITY_FROM,a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.POSSIBLE_FLIGHT_DATE\r\n" + 
				"FROM attm_ticket_purchase_mst g\r\n" + 
				"LEFT OUTER JOIN attm_ticket_request a ON g.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID\r\n" + 
				"LEFT OUTER JOIN bg_user b ON a.USER_ID=b.user_id\r\n" + 
				"LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID \r\n" + 
				"LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID\r\n" + 
				"LEFT OUTER JOIN bg_country e ON a.COUNTRY_FROM=e.country_id\r\n" + 
				"LEFT OUTER JOIN bg_country f ON a.COUNTRY_TO=f.country_id\r\n" + 
				"LEFT OUTER JOIN bg_city l ON a.CITY_FROM=l.CITY_ID \r\n" + 
				"LEFT OUTER JOIN bg_city m ON a.CITY_TO=m.CITY_ID\r\n" + 
				"WHERE g.ACTIVE_STATUS=1\r\n" + 
				//"and b.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')\r\n" + 
				//"and a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID)\r\n" + 
				//"and a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID)\r\n" + 
				//"and a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				//"and a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)\r\n" + 
				//"and a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)\r\n" + 
				//"and a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)\r\n" + 
				//"and a.CITY_TO=coalesce(:toCityId,a.CITY_TO)\r\n" + 
				"and g.PURCHASE_DATE BETWEEN\r\n"+ 
				"coalesce(:fromDate,g.PURCHASE_DATE)\r\n"+
				"AND coalesce(:toDate,g.PURCHASE_DATE)\r\n"+
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.USER_ID,b.user_name,b.PASSPORT_NO,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"a.NO_OF_TICKETS,a.APPROX_AMT,a.AGREEMENT_AMT,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,\r\n" + 
				"a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT,g.PURCHASE_DATE,\r\n"+
				"a.COUNTRY_FROM,a.COUNTRY_TO,e.country_name,f.country_name,\r\n"+
				"a.CITY_FROM,a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.POSSIBLE_FLIGHT_DATE";
		
		/*
		 * 
		 * SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,
		a.USER_ID,b.user_name,b.PASSPORT_NO,
		a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG, 
		a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG, 
		a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE, 
		a.NO_OF_TICKETS,a.APPROX_AMT,a.AGREEMENT_AMT, 
		a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE, 
		a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT, 
		g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT,g.PURCHASE_DATE,
		a.COUNTRY_FROM,a.COUNTRY_TO,e.country_name,f.country_name,
		a.CITY_FROM,a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.POSSIBLE_FLIGHT_DATE
		FROM attm_ticket_purchase_mst g
		LEFT OUTER JOIN attm_ticket_request a ON g.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID
		LEFT OUTER JOIN bg_user b ON a.USER_ID=b.user_id
		LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID
		LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID
		LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID
		LEFT OUTER JOIN bg_country e ON a.COUNTRY_FROM=e.country_id 
		LEFT OUTER JOIN bg_country f ON a.COUNTRY_TO=f.country_id 
		LEFT OUTER JOIN bg_city l ON a.CITY_FROM=l.CITY_ID 
		LEFT OUTER JOIN bg_city m ON a.CITY_TO=m.CITY_ID
		WHERE g.ACTIVE_STATUS=1
		//and b.PASSPORT_NO LIKE CONCAT('%',:passportNo, '%')
		//and a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID) 
		//and a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID) 
		//and a.USER_ID=coalesce(:userId,a.USER_ID)
		//and a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)
		//and a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)
		//and a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)
		//and a.CITY_TO=coalesce(:toCityId,a.CITY_TO) 
		and g.PURCHASE_DATE BETWEEN
		coalesce(:fromDate,g.PURCHASE_DATE)
		AND coalesce(:toDate,g.PURCHASE_DATE)
		GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,
		a.USER_ID,b.user_name,b.PASSPORT_NO,
		a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,
		a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,
		a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
		a.NO_OF_TICKETS,a.APPROX_AMT,a.AGREEMENT_AMT,
		a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,d.TICKET_TYPE,
		a.ACTIVE_STATUS,a.REMARKS,g.NO_OF_TICKETS,g.TOTAL_AMOUNT,
		g.TICKET_PURCHASE_MST_ID,a.AUTO_PAYMENT,a.ADV_PAYMENT,g.PURCHASE_DATE,
		a.COUNTRY_FROM,a.COUNTRY_TO,e.country_name,f.country_name
		a.CITY_FROM,a.CITY_TO,l.CITY_NAME,m.CITY_NAME,a.POSSIBLE_FLIGHT_DATE
		 * 
		 * */
		
	
	RowMapper<ModelTicketPurchaseCustom> serviceMapper=new RowMapper<ModelTicketPurchaseCustom>() {

		@Override
		public ModelTicketPurchaseCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelTicketPurchaseCustom bn= new ModelTicketPurchaseCustom();
			Integer purChasedQTY=rs.getInt("a.NO_OF_TICKETS")-rs.getInt("g.NO_OF_TICKETS");
			
			bn.setTicketPurchaseMstId(rs.getLong("TICKET_PURCHASE_MST_ID"));
			bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
			bn.setRequestDate(rs.getDate("REQUEST_DATE"));
			bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
			bn.setFromAgentName(rs.getString("h.AGENT_NAME"));
			bn.setFromAgentSelfStatus(rs.getInt("h.SELF_FLAG"));
			bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
			bn.setToAgentName(rs.getString("c.AGENT_NAME"));
			bn.setToAgentSelfStatus(rs.getInt("c.SELF_FLAG"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			
			if(bn.getUserId()!=null || bn.getUserId()!=0) 
			{
				bn.setUserId(bn.getUserId());
				bn.setUserName(bn.getUserName());
			}
			else {
			
			if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==0) 
			{	
				bn.setUserId(0L);
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==0 && bn.getToAgentSelfStatus()==1) 
			{	
				bn.setUserId(0L);
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==1)
			{
				bn.setUserId(0L);
				bn.setUserName("");
			}
			else 
			{
				bn.setUserId(bn.getUserId());
				bn.setUserName(bn.getUserName());
			}
			
			}
			
			if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
			{
				bn.setFromAgentId(0L);
				bn.setFromAgentName("");
				bn.setToAgentId(0L);
				bn.setToAgentName("");
			}
			else 
			{
				bn.setFromAgentId(bn.getFromAgentId());
				bn.setFromAgentName(bn.getFromAgentName());
				bn.setToAgentId(bn.getToAgentId());
				bn.setToAgentName(bn.getToAgentName());
			}
			
			bn.setFromCountryId(rs.getLong("COUNTRY_FROM"));
			bn.setFromCountryName(rs.getString("e.country_name"));
			bn.setFromCityId(rs.getLong("CITY_FROM"));
			bn.setFromCityName(rs.getString("l.CITY_NAME"));
			bn.setToCountryId(rs.getLong("COUNTRY_TO"));
			bn.setTocountryName(rs.getString("f.country_name"));
			bn.setToCityId(rs.getLong("CITY_TO"));
			bn.setToCityName(rs.getString("m.CITY_NAME"));
			bn.setTicketRequestNoOfTickets(rs.getInt("a.NO_OF_TICKETS"));
			bn.setNoOfTickets(rs.getInt("g.NO_OF_TICKETS"));
			bn.setApproxAMT(rs.getDouble("APPROX_AMT"));
			bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
			bn.setTicketRequesterId(rs.getLong("REQUESTER_ID"));
			bn.setTicketRequesterName(rs.getString("REQUESTER_NAME"));
			bn.setPossibleFlightDate(rs.getDate("POSSIBLE_FLIGHT_DATE"));
			bn.setAdvPayment(rs.getDouble("ADV_PAYMENT"));
			bn.setAutoPayment(rs.getInt("AUTO_PAYMENT"));
			if(bn.getAutoPayment()==1) 
			{
				bn.setsAuotPayment("Yes");
				bn.setAutoPay(true);
			}
			else {
				bn.setsAuotPayment("No");
				bn.setAutoPay(false);
			}
			bn.setTicketOwnerCompanyId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
			bn.setOwnerCompanyName(rs.getString("OWNER_COMPANY_NAME"));
			bn.setTicketType(rs.getInt("TICKET_TYPE"));
			bn.setPurchasedQTY(purChasedQTY);
							
			return bn;
		}

	};
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	 parameters.addValue("passportNo", passportNo);
     System.out.println(" in daoTicketPurchaseMstImp, passportNo:" +passportNo);
     parameters.addValue("toAgentId", toAgentId);
     parameters.addValue("fromAgentId", fromAgentId);
     System.out.println(" in daoTicketPurchaseMstImp, fromAgentId:" +fromAgentId);
     parameters.addValue("userId", userId);
     parameters.addValue("fromCountryId", fromCountryId);
     parameters.addValue("fromCityId", fromCityId);
     parameters.addValue("toCountryId", toCountryId);
     parameters.addValue("toCityId", toCityId);
     parameters.addValue("fromDate", fromDate);
     parameters.addValue("toDate", toDate);


	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstById(Long ticketPurchaseMstId) {
		// TODO Auto-generated method stub
		
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG, \r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,\r\n" + 
				"j.TICKET_NO,j.USER_ID,b.user_name,a.AUTO_PAYMENT,a.ADV_PAYMENT,\r\n" + 
				"j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,\r\n" + 
				"j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,\r\n" + 
				"j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,\r\n" + 
				"j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,\r\n" + 
				"j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,\r\n" + 
				"j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD,\r\n" + 
				"g.TOTAL_AMOUNT,a.APPROX_AMT,a.AGREEMENT_AMT,\r\n" + 
				"a.NO_OF_TICKETS,g.NO_OF_TICKETS,g.PURCHASE_DATE,g.FLIGHT_DATE,\r\n" + 
				"a.POSSIBLE_FLIGHT_DATE,a.REQUESTER_ID,\r\n" + 
				"a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,d.TICKET_TYPE\r\n" + 
				"FROM attm_ticket_purchase_chd j\r\n" + 
				"INNER JOIN attm_ticket_purchase_mst g ON j.TICKET_PURCHASE_MST_ID=g.TICKET_PURCHASE_MST_ID\r\n" + 
				"LEFT OUTER JOIN attm_ticket_request a ON g.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID\r\n" + 
				"LEFT OUTER JOIN bg_user b ON j.USER_ID=b.user_id\r\n" + 
				"LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID\r\n" + 
				"INNER JOIN bg_country e ON j.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f ON j.TO_COUNTRY=f.country_id\r\n" + 
				"INNER JOIN bg_city l ON j.FROM_CITY=l.CITY_ID\r\n" + 
				"INNER JOIN bg_city m ON j.TO_CITY=m.CITY_ID\r\n" + 
				"WHERE j.ACTIVE_STATUS=1\r\n" + 
				"and g.TICKET_PURCHASE_MST_ID=coalesce(:ticketPurchaseMstId,g.TICKET_PURCHASE_MST_ID)\r\n" + 
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,\r\n" + 
				"j.TICKET_NO,j.USER_ID,b.user_name,a.AUTO_PAYMENT,a.ADV_PAYMENT,\r\n" + 
				"j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,\r\n" + 
				"j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,\r\n" + 
				"j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,\r\n" + 
				"j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,\r\n" + 
				"j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,\r\n" + 
				"j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD,\r\n" + 
				"g.TOTAL_AMOUNT,a.APPROX_AMT,a.AGREEMENT_AMT,\r\n" + 
				"a.NO_OF_TICKETS,g.NO_OF_TICKETS,g.PURCHASE_DATE,g.FLIGHT_DATE,\r\n" + 
				"a.POSSIBLE_FLIGHT_DATE,a.REQUESTER_ID,\r\n" + 
				"a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,d.TICKET_TYPE";
		
		/*
		SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,
a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG, 
a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,
a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,
g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,
j.TICKET_NO,j.USER_ID,b.user_name,a.AUTO_PAYMENT,a.ADV_PAYMENT,
j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,
j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,
j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,
j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,
j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,
j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD,
g.TOTAL_AMOUNT,a.APPROX_AMT,a.AGREEMENT_AMT,
a.NO_OF_TICKETS,g.NO_OF_TICKETS,g.PURCHASE_DATE,g.FLIGHT_DATE,
a.POSSIBLE_FLIGHT_DATE,a.REQUESTER_ID,
a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,d.TICKET_TYPE
FROM attm_ticket_purchase_chd j
INNER JOIN attm_ticket_purchase_mst g ON j.TICKET_PURCHASE_MST_ID=g.TICKET_PURCHASE_MST_ID
LEFT OUTER JOIN attm_ticket_request a ON g.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID
LEFT OUTER JOIN bg_user b ON j.USER_ID=b.user_id
LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID
LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID
LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID
INNER JOIN bg_country e ON j.FROM_COUNTRY=e.country_id
INNER JOIN bg_country f ON j.TO_COUNTRY=f.country_id
INNER JOIN bg_city l ON j.FROM_CITY=l.CITY_ID
INNER JOIN bg_city m ON j.TO_CITY=m.CITY_ID
WHERE j.ACTIVE_STATUS=1
and g.TICKET_PURCHASE_MST_ID=coalesce(:ticketPurchaseMstId,g.TICKET_PURCHASE_MST_ID)
GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,
a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,
a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,
a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,
g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,
j.TICKET_NO,j.USER_ID,b.user_name,a.AUTO_PAYMENT,a.ADV_PAYMENT,
j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,
j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,
j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,
j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,
j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,
j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD,
g.TOTAL_AMOUNT,a.APPROX_AMT,a.AGREEMENT_AMT,
a.NO_OF_TICKETS,g.NO_OF_TICKETS,g.PURCHASE_DATE,g.FLIGHT_DATE,
a.POSSIBLE_FLIGHT_DATE,a.REQUESTER_ID,
a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,d.TICKET_TYPE
				
				*/
	
	RowMapper<ModelTicketPurchaseCustom> serviceMapper=new RowMapper<ModelTicketPurchaseCustom>() {

		@Override
		public ModelTicketPurchaseCustom mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			ModelTicketPurchaseCustom bn= new ModelTicketPurchaseCustom();
			Integer purChasedQTY=rs.getInt("a.NO_OF_TICKETS")-rs.getInt("g.NO_OF_TICKETS");
			
			bn.setTicketPurchaseMstId(rs.getLong("TICKET_PURCHASE_MST_ID"));
			bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
			bn.setRequestDate(rs.getDate("REQUEST_DATE"));
			System.out.println("Request Date: "+bn.getRequestDate());
			bn.setFlightDate(rs.getDate("FLIGHT_DATE"));
			bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
			bn.setFromAgentName(rs.getString("h.AGENT_NAME"));
			bn.setFromAgentSelfStatus(rs.getInt("h.SELF_FLAG"));
			bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
			bn.setToAgentName(rs.getString("c.AGENT_NAME"));
			bn.setToAgentSelfStatus(rs.getInt("c.SELF_FLAG"));
			bn.setUserId(rs.getLong("USER_ID"));
			bn.setUserName(rs.getString("user_name"));
			
			if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==0) 
			{	
				bn.setUserId(0L);
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==0 && bn.getToAgentSelfStatus()==1) 
			{	
				bn.setUserId(0L);;
				bn.setUserName("DEFLT");
			}
			else if(bn.getFromAgentSelfStatus()==1 && bn.getToAgentSelfStatus()==1)
			{
				bn.setUserId(0L);
				bn.setUserName("");
			}
			else 
			{
				bn.setUserId(bn.getUserId());
				bn.setUserName(bn.getUserName());
			}
			
			if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
			{
				bn.setFromAgentId(0L);
				bn.setFromAgentName("");
				bn.setToAgentId(0L);
				bn.setToAgentName("");
			}
			else 
			{
				bn.setFromAgentId(bn.getFromAgentId());
				bn.setFromAgentName(bn.getFromAgentName());
				bn.setToAgentId(bn.getToAgentId());
				bn.setToAgentName(bn.getToAgentName());
			}
			
			//bn.setAirLineId(rs.getLong("AIRLINE_ID"));
			//bn.setAirLineName(rs.getString("AIRLINE_NAME"));
			bn.setFromCountryId(rs.getLong("FROM_COUNTRY"));
			bn.setFromCountryName(rs.getString("e.country_name"));
			bn.setFromCityId(rs.getLong("FROM_CITY"));
			bn.setFromCityName(rs.getString("l.CITY_NAME"));
			bn.setToCountryId(rs.getLong("TO_COUNTRY"));
			bn.setTocountryName(rs.getString("f.country_name"));
			bn.setToCityId(rs.getLong("TO_CITY"));
			bn.setToCityName(rs.getString("m.CITY_NAME"));
			bn.setTicketRequestNoOfTickets(rs.getInt("a.NO_OF_TICKETS"));
			bn.setNoOfTickets(rs.getInt("g.NO_OF_TICKETS"));
			bn.setApproxAMT(rs.getDouble("APPROX_AMT"));
			bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
			bn.setPurchasedQTY(purChasedQTY);
			bn.setTotalAMT(rs.getDouble("TOTAL_AMOUNT"));
			bn.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
			bn.setRemarks(rs.getString("j.REMARKS"));
			bn.setActiveStatus(rs.getInt("j.ACTIVE_STATUS"));
			bn.setTicketRequesterId(rs.getLong("REQUESTER_ID"));
			bn.setTicketRequesterName(rs.getString("REQUESTER_NAME"));
			bn.setPossibleFlightDate(rs.getDate("POSSIBLE_FLIGHT_DATE"));
			bn.setAdvPayment(rs.getDouble("ADV_PAYMENT"));
			bn.setAutoPayment(rs.getInt("AUTO_PAYMENT"));
			if(bn.getAutoPayment()==1) 
			{
				bn.setsAuotPayment("Yes");
				bn.setAutoPay(true);
			}
			else {
				bn.setsAuotPayment("No");
				bn.setAutoPay(false);
			}
			bn.setTicketOwnerCompanyId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
			bn.setOwnerCompanyName(rs.getString("OWNER_COMPANY_NAME"));
			bn.setTicketType(rs.getInt("TICKET_TYPE"));
			System.out.println("activeStatus: "+bn.getActiveStatus());
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
	 parameters.addValue("ticketPurchaseMstId", ticketPurchaseMstId);
     System.out.println(" in daoTicketPurchaseMstImp, ticketPurchaseMstId:" +ticketPurchaseMstId);
    


	System.out.println("service mapper ");
	return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketPurchaseMst> getTicketPurchaseMstByMstId(Long ticketPurchaseMstId) {
		// TODO Auto-generated method stub
		System.out.println("In daoPurchaeImp ticketPurchaseMstId: "+ticketPurchaseMstId);
		return ticketPurchaseMstRepository.getTicketPurchaseMstById(ticketPurchaseMstId);
	}
	
	

}
