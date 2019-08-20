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


import com.biziitech.attm.bg.repository.TicketSellMstRepository;
import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.custom.model.ModelTicketSellCustom;
import com.biziitech.attm.dao.DaoTicketSellMst;
import com.biziitech.attm.model.ModelTicketSellMst;

@Service
public class DaoTicketSellMstImp implements DaoTicketSellMst{
	
	@Autowired
	private TicketSellMstRepository ticketSellMstRepository;
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void saveTicketSellMst(ModelTicketSellMst modelTicketSellMst) {
		// TODO Auto-generated method stub
	 ticketSellMstRepository.save(modelTicketSellMst);
	}

	@Override
	public List<ModelTicketSellMst> getTicketSellMstById(Long ticketSellMstId) {
		// TODO Auto-generated method stub
		return ticketSellMstRepository.findTicketSellMstById(ticketSellMstId);
	}

	@Override
	public List<ModelTicketSellCustom> getTicketSellUnDoneChdDetails(Long userId,Long fromCountryId,Long fromCityId,Long toCountryId,Long toCityId,
			Long fromAgentId,Long toAgentId,Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n" + 
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY\r\n" + 
				"FROM attm_ticket_request a\r\n" + 
				"INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id\r\n" + 
				"INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID\r\n" + 
				"INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id\r\n" + 
				"INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id\r\n" + 
				"LEFT OUTER JOIN attm_ticket_sell_mst i ON a.TICKET_REQUEST_ID=i.TICKET_REQUEST_ID\r\n" + 
				"INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0\r\n" + 
				//"AND a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				//"AND a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID)\r\n" + 
				//"AND a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID)\r\n" + 
				"AND a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)\r\n" + 
				"AND a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)\r\n" + 
				"AND a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)\r\n" + 
				"AND a.CITY_TO=coalesce(:toCityId,a.CITY_TO)\r\n" + 
				"AND a.REQUEST_DATE BETWEEN coalesce(:fromDate,a.REQUEST_DATE) \r\n" + 
				"AND coalesce(:toDate,a.REQUEST_DATE)\r\n" + 
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n" + 
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY";
		
		/*
		 * SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
FROM attm_ticket_request a
INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id
INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID
INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id
INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID
LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID
LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID
LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id
LEFT OUTER JOIN attm_ticket_sell_mst i ON a.TICKET_REQUEST_ID=i.TICKET_REQUEST_ID
INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID
WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0
AND a.USER_ID=coalesce(:userId,a.USER_ID)
AND a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID)
AND a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID)
AND a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)
AND a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)
AND a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)
AND a.CITY_TO=coalesce(:toCityId,a.CITY_TO)
AND a.REQUEST_DATE BETWEEN coalesce(:fromDate,a.REQUEST_DATE) 
AND coalesce(:toDate,a.REQUEST_DATE)
GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
		 * */
		
		
		RowMapper<ModelTicketSellCustom> serviceMapper=new RowMapper<ModelTicketSellCustom>() {

			@Override
			public ModelTicketSellCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketSellCustom bn= new ModelTicketSellCustom();
				
				bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
				bn.setTicketSellMstId(rs.getLong("TICKET_SELL_MST_ID"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else {
					bn.setActive(false);
					bn.setsActive("No");
				}
				
				bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
				bn.setFromAgentName(rs.getString("g.AGENT_NAME"));
				
				bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
				bn.setToAgentName(rs.getString("f.AGENT_NAME"));
				//bn.setSellAgentSelfStatus(rs.getInt("e.SELF_FLAG"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				
				if(bn.getUserId()!=null|| bn.getUserId()!=0) 
				{
					bn.setUserId(bn.getUserId());
					bn.setUserName(bn.getUserName());
				}
				else {
				
					bn.setUserId(0L);
					bn.setUserName("DEFLT");	
								
				}
				
				if(bn.getToAgentId()==null||bn.getToAgentId()==0) 
				{
					bn.setToAgentId(0L);
					bn.setToAgentName("DEFLT");
				}
				else 
				{
				
					bn.setToAgentId(bn.getToAgentId());
					bn.setToAgentName(bn.getToAgentName());
				}
				
				if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
				{
					bn.setFromAgentId(0L);
					bn.setFromAgentName("DEFLT");
				}
				else 
				{
				
					bn.setFromAgentId(bn.getFromAgentId());
					bn.setFromAgentName(bn.getFromAgentName());
				}
				
				bn.setAirLineId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
				bn.setAirLineName(rs.getString("OWNER_COMPANY_NAME"));
				bn.setSellDate(rs.getDate("SELL_DATE"));
				bn.setSellQty(rs.getInt("SELL_QTY"));
				bn.setRequestDate(rs.getDate("REQUEST_DATE"));
				bn.setNoOfTickets(rs.getInt("NO_OF_TICKETS"));
				bn.setSoldQty(rs.getInt("SELL_QTY"));
				bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
				bn.setAdvancePayment(rs.getDouble("ADV_PAYMENT"));
				bn.setRequesterId(rs.getLong("REQUESTER_ID"));
				bn.setRequesterName(rs.getString("REQUESTER_NAME"));
				bn.setRequesterContactPhone(rs.getString("REQUESTER_CONTACT_PHONE"));
				//bn.setPurchasedQTY(purChasedQTY);
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("userId", userId);
	      System.out.println(" in daoTicketSellMstImp, userId:" +userId);
	      parameters.addValue("fromDate", fromDate);
	      parameters.addValue("toDate", toDate);
	      parameters.addValue("fromCountryId", fromCountryId);
	      parameters.addValue("fromCityId", fromCityId);
	      parameters.addValue("toCountryId", toCountryId);
	      parameters.addValue("toCityId", toCityId);
	      parameters.addValue("fromAgentId", fromAgentId);
	      parameters.addValue("toAgentId", toAgentId);
	      

	      

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketSellCustom> getTicketSellDoneChdDetails(Long userId,Long fromCountryId,Long fromCityId,Long toCountryId,Long toCityId,
			Long fromAgentId,Long toAgentId,Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n"+
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY\r\n" + 
				"FROM attm_ticket_sell_mst i\r\n" + 
				"INNER JOIN attm_ticket_request a ON i.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID\r\n" + 
				"INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id\r\n" + 
				"INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID\r\n" + 
				"INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id\r\n" + 
				"INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id\r\n" + 
				"INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0\r\n" + 
				//"AND a.USER_ID=coalesce(:userId,a.USER_ID)\r\n" + 
				//"AND a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID)\r\n" + 
				//"AND a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID)\r\n" + 
				"AND a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)\r\n" + 
				"AND a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)\r\n" + 
				"AND a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)\r\n" + 
				"AND a.CITY_TO=coalesce(:toCityId,a.CITY_TO)\r\n" + 
				"AND a.REQUEST_DATE BETWEEN coalesce(:fromDate,a.REQUEST_DATE) \r\n" + 
				"AND coalesce(:toDate,a.REQUEST_DATE)\r\n" + 
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n"+
				"a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY";
		
		/*
		 * 
		 * SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
FROM attm_ticket_sell_mst i
INNER JOIN attm_ticket_request a ON i.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID
INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id
INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID
INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id
INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID
LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID
LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID
LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id
INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID
WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0
AND a.USER_ID=coalesce(:userId,a.USER_ID)
AND a.FROM_AGENT_ID=coalesce(:fromAgentId,a.FROM_AGENT_ID)
AND a.TO_AGENT_ID=coalesce(:toAgentId,a.TO_AGENT_ID)
AND a.COUNTRY_FROM=coalesce(:fromCountryId,a.COUNTRY_FROM)
AND a.CITY_FROM=coalesce(:fromCityId,a.CITY_FROM)
AND a.COUNTRY_TO=coalesce(:toCountryId,a.COUNTRY_TO)
AND a.CITY_TO=coalesce(:toCityId,a.CITY_TO)
AND a.REQUEST_DATE BETWEEN coalesce(:fromDate,a.REQUEST_DATE) 
AND coalesce(:toDate,a.REQUEST_DATE)
GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
a.REQUESTER_ID,a.REQUESTER_NAME,a.REQUESTER_CONTACT_PHONE,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
		 * */
		
//String qry_count_chd="SELECT COUNT(h.TICKET_SELL_CHD_ID) AS Id FROM attm_ticket_sell_chd h where h.TICKET_SELL_MST_ID=?";
		
		RowMapper<ModelTicketSellCustom> serviceMapper=new RowMapper<ModelTicketSellCustom>() {

			@Override
			public ModelTicketSellCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketSellCustom bn= new ModelTicketSellCustom();
				
				bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
				bn.setTicketSellMstId(rs.getLong("TICKET_SELL_MST_ID"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else {
					bn.setActive(false);
					bn.setsActive("No");
				}
				
				bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
				bn.setFromAgentName(rs.getString("g.AGENT_NAME"));
				
				bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
				bn.setToAgentName(rs.getString("f.AGENT_NAME"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				
				if(bn.getUserId()!=null|| bn.getUserId()!=0) 
				{
					bn.setUserId(bn.getUserId());
					bn.setUserName(bn.getUserName());
				}
				else {
				
					bn.setUserId(0L);
					bn.setUserName("DEFLT");	
								
				}
				
				if(bn.getToAgentId()==null||bn.getToAgentId()==0) 
				{
					bn.setToAgentId(0L);
					bn.setToAgentName("DEFLT");
				}
				else 
				{
				
					bn.setToAgentId(bn.getToAgentId());
					bn.setToAgentName(bn.getToAgentName());
				}
				
				if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
				{
					bn.setFromAgentId(0L);
					bn.setFromAgentName("DEFLT");
				}
				else 
				{
				
					bn.setFromAgentId(bn.getFromAgentId());
					bn.setFromAgentName(bn.getFromAgentName());
				}
				
				bn.setAirLineId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
				bn.setAirLineName(rs.getString("OWNER_COMPANY_NAME"));
				bn.setSellDate(rs.getDate("SELL_DATE"));
				bn.setSellQty(rs.getInt("SELL_QTY"));
				bn.setRequestDate(rs.getDate("REQUEST_DATE"));
				bn.setNoOfTickets(rs.getInt("NO_OF_TICKETS"));
				bn.setSoldQty(rs.getInt("SELL_QTY"));
				bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
				bn.setAdvancePayment(rs.getDouble("ADV_PAYMENT"));
				bn.setRequesterId(rs.getLong("REQUESTER_ID"));
				bn.setRequesterName(rs.getString("REQUESTER_NAME"));
				bn.setRequesterContactPhone(rs.getString("REQUESTER_CONTACT_PHONE"));
				
				 
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
	      System.out.println(" in daoTicketSellMstImp, userId:" +userId);
	      parameters.addValue("fromDate", fromDate);
	      parameters.addValue("toDate", toDate);
	      parameters.addValue("fromCountryId", fromCountryId);
	      parameters.addValue("fromCityId", fromCityId);
	      parameters.addValue("toCountryId", toCountryId);
	      parameters.addValue("toCityId", toCityId);
	      parameters.addValue("fromAgentId", fromAgentId);
	      parameters.addValue("toAgentId", toAgentId);

	      

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketSellCustom> getTicketSellMstByMstId(Long ticketSellMstId) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY\r\n" + 
				"FROM attm_ticket_sell_mst i\r\n" + 
				"INNER JOIN attm_ticket_request a ON i.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID\r\n" + 
				"INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id\r\n" + 
				"INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID\r\n" + 
				"INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id\r\n" + 
				"INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id\r\n" + 
				"INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID\r\n" + 
				"WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0\r\n" + 
				"AND i.TICKET_SELL_MST_ID=coalesce(:ticketSellMstId,i.TICKET_SELL_MST_ID)\r\n" + 
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,\r\n" + 
				"a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,\r\n" + 
				"a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,\r\n" + 
				"a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,\r\n" + 
				"a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,\r\n" + 
				"a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,\r\n" + 
				"i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY";
		
		
		/*
		 *  SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
FROM attm_ticket_sell_mst i
INNER JOIN attm_ticket_request a ON i.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID
INNER JOIN bg_country b ON a.COUNTRY_FROM = b.country_id
INNER JOIN bg_city c ON a.CITY_FROM=c.CITY_ID
INNER JOIN bg_country d ON a.COUNTRY_TO=d.country_id
INNER JOIN bg_city e ON a.CITY_TO=e.CITY_ID
LEFT OUTER JOIN attm_agent f ON a.FROM_AGENT_ID=f.AGENT_ID
LEFT OUTER JOIN attm_agent g ON a.TO_AGENT_ID=g.AGENT_ID
LEFT OUTER JOIN bg_user h ON a.USER_ID=h.user_id
INNER JOIN attm_ticket_owner_company j ON a.TICKET_OWNER_COMPANY_ID=j.TICKET_OWNER_COMPANY_ID
WHERE a.ACTIVE_STATUS=1 AND a.AUTO_PAYMENT=0
AND i.TICKET_SELL_MST_ID=coalesce(:ticketSellMstId,i.TICKET_SELL_MST_ID)
GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,a.POSSIBLE_FLIGHT_DATE,
a.COUNTRY_FROM,b.country_name,a.CITY_FROM,c.CITY_NAME,
a.COUNTRY_TO,d.country_name,a.CITY_TO,e.CITY_NAME,a.NO_OF_TICKETS,
a.AGREEMENT_AMT,a.FROM_AGENT_ID,f.AGENT_NAME,
a.TO_AGENT_ID,g.AGENT_NAME,a.USER_ID,h.user_name,
a.TICKET_OWNER_COMPANY_ID,j.OWNER_COMPANY_NAME,a.AUTO_PAYMENT,
a.ADV_PAYMENT,a.ACTIVE_STATUS,a.REMARKS,
i.TICKET_SELL_MST_ID,i.SELL_DATE,i.SELL_QTY
		 * */
		
		RowMapper<ModelTicketSellCustom> serviceMapper=new RowMapper<ModelTicketSellCustom>() {

			@Override
			public ModelTicketSellCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketSellCustom bn= new ModelTicketSellCustom();
				
				bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
				bn.setTicketSellMstId(rs.getLong("TICKET_SELL_MST_ID"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else {
					bn.setActive(false);
					bn.setsActive("No");
				}
				
				bn.setFromAgentId(rs.getLong("TO_AGENT_ID"));
				bn.setFromAgentName(rs.getString("g.AGENT_NAME"));
				
				bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
				bn.setToAgentName(rs.getString("f.AGENT_NAME"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				
				if(bn.getUserId()!=null|| bn.getUserId()!=0) 
				{
					bn.setUserId(bn.getUserId());
					bn.setUserName(bn.getUserName());
				}
				else {
				
					bn.setUserId(0L);
					bn.setUserName("DEFLT");	
								
				}
				
				if(bn.getToAgentId()==null||bn.getToAgentId()==0) 
				{
					bn.setToAgentId(0L);
					bn.setToAgentName("DEFLT");
				}
				else 
				{
				
					bn.setToAgentId(bn.getToAgentId());
					bn.setToAgentName(bn.getToAgentName());
				}
				
				if(bn.getFromAgentId()==null||bn.getFromAgentId()==0) 
				{
					bn.setFromAgentId(0L);
					bn.setFromAgentName("DEFLT");
				}
				else 
				{
				
					bn.setFromAgentId(bn.getFromAgentId());
					bn.setFromAgentName(bn.getFromAgentName());
				}
				
				bn.setAirLineId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
				bn.setAirLineName(rs.getString("OWNER_COMPANY_NAME"));
				bn.setSellDate(rs.getDate("SELL_DATE"));
				bn.setSellQty(rs.getInt("SELL_QTY"));
				bn.setRequestDate(rs.getDate("REQUEST_DATE"));
				bn.setNoOfTickets(rs.getInt("NO_OF_TICKETS"));
				bn.setSoldQty(rs.getInt("SELL_QTY"));
				bn.setAgreementAMT(rs.getDouble("AGREEMENT_AMT"));
				bn.setAdvancePayment(rs.getDouble("ADV_PAYMENT"));
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("ticketSellMstId", ticketSellMstId);
	      System.out.println(" in daoTicketSellMstImp, ticketSellMstId:" +ticketSellMstId);
	      
	      

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketSellMst> getTicketSellMstByPurchaseMstId(Long ticketPurchaseMstId) {
		// TODO Auto-generated method stub
		return ticketSellMstRepository.findTicketSellMstByPurchaseMstId(ticketPurchaseMstId);
	}

	@Override
	public List<ModelTicketPurchaseCustom> getSelfTicketPurchase() {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_PURCHASE_CHD_ID,a.TICKET_PURCHASE_MST_ID,\r\n" + 
				"a.TICKET_NO,a.USER_ID,c.user_name,a.PNR,a.TICKET_AMT_USD,a.TICKET_AMT_BDT,\r\n" + 
				"a.FROM_COUNTRY,d.country_name,a.FROM_CITY,f.CITY_NAME,\r\n" + 
				"a.TO_COUNTRY,e.country_name,a.TO_CITY,g.CITY_NAME,\r\n" + 
				"a.REMARKS,a.ACTIVE_STATUS,a.AGENT_PASSENGER_NAME,\r\n" + 
				"a.SELLING_PRICE_USD,a.SELLING_PRICE_BDT,a.QTY,b.TICKET_REQUEST_ID,\r\n" + 
				"b.PURCHASE_DATE,b.PURCHASE_FROM_TICKET_COMPANY_ID,h.OWNER_COMPANY_NAME,\r\n" + 
				"b.PURCHASE_FROM_AGENT_ID,i.AGENT_NAME\r\n" + 
				"FROM attm_ticket_purchase_chd a \r\n" + 
				"INNER JOIN attm_ticket_purchase_mst b ON a.TICKET_PURCHASE_MST_ID=b.TICKET_PURCHASE_MST_ID\r\n" + 
				"LEFT OUTER JOIN bg_user c ON a.USER_ID=c.user_id\r\n" + 
				"INNER JOIN bg_country d ON a.FROM_COUNTRY = d.country_id\r\n" + 
				"INNER JOIN bg_country e ON a.TO_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_city f ON a.FROM_CITY=f.CITY_ID\r\n" + 
				"INNER JOIN bg_city g ON a.TO_CITY=g.CITY_ID\r\n" + 
				"LEFT OUTER JOIN attm_ticket_owner_company h ON b.PURCHASE_FROM_TICKET_COMPANY_ID=h.TICKET_OWNER_COMPANY_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent i ON b.PURCHASE_FROM_AGENT_ID=i.AGENT_ID\r\n" + 
				"WHERE NOT EXISTS(SELECT * FROM attm_ticket_sell_chd j \r\n" + 
				"WHERE j.TICKET_PURCHASE_CHD_ID=a.TICKET_PURCHASE_CHD_ID) \r\n" + 
				"AND b.TICKET_REQUEST_ID is null\r\n" + 
				"GROUP BY a.TICKET_PURCHASE_CHD_ID,a.TICKET_PURCHASE_MST_ID,\r\n" + 
				"a.TICKET_NO,a.USER_ID,c.user_name,a.PNR,a.TICKET_AMT_USD,a.TICKET_AMT_BDT,\r\n" + 
				"a.FROM_COUNTRY,d.country_name,a.FROM_CITY,f.CITY_NAME,\r\n" + 
				"a.TO_COUNTRY,e.country_name,a.TO_CITY,g.CITY_NAME,\r\n" + 
				"a.REMARKS,a.ACTIVE_STATUS,a.AGENT_PASSENGER_NAME,\r\n" + 
				"a.SELLING_PRICE_USD,a.SELLING_PRICE_BDT,a.QTY,b.TICKET_REQUEST_ID,\r\n" + 
				"b.PURCHASE_DATE,b.PURCHASE_FROM_TICKET_COMPANY_ID,h.OWNER_COMPANY_NAME,\r\n" + 
				"b.PURCHASE_FROM_AGENT_ID,i.AGENT_NAME";

		
		/*
		 * SELECT a.TICKET_PURCHASE_CHD_ID,a.TICKET_PURCHASE_MST_ID,
a.TICKET_NO,a.USER_ID,c.user_name,a.PNR,a.TICKET_AMT_USD,a.TICKET_AMT_BDT,
a.FROM_COUNTRY,d.country_name,a.FROM_CITY,f.CITY_NAME,
a.TO_COUNTRY,e.country_name,a.TO_CITY,g.CITY_NAME,
a.REMARKS,a.ACTIVE_STATUS,a.AGENT_PASSENGER_NAME,
a.SELLING_PRICE_USD,a.SELLING_PRICE_BDT,a.QTY,b.TICKET_REQUEST_ID,
b.PURCHASE_DATE,b.PURCHASE_FROM_TICKET_COMPANY_ID,h.OWNER_COMPANY_NAME,
b.PURCHASE_FROM_AGENT_ID,i.AGENT_NAME
FROM attm_ticket_purchase_chd a 
INNER JOIN attm_ticket_purchase_mst b ON a.TICKET_PURCHASE_MST_ID=b.TICKET_PURCHASE_MST_ID
LEFT OUTER JOIN bg_user c ON a.USER_ID=c.user_id
INNER JOIN bg_country d ON a.FROM_COUNTRY = d.country_id
INNER JOIN bg_country e ON a.TO_COUNTRY=e.country_id
INNER JOIN bg_city f ON a.FROM_CITY=f.CITY_ID
INNER JOIN bg_city g ON a.TO_CITY=g.CITY_ID
LEFT OUTER JOIN attm_ticket_owner_company h ON b.PURCHASE_FROM_TICKET_COMPANY_ID=h.TICKET_OWNER_COMPANY_ID
LEFT OUTER JOIN attm_agent i ON b.PURCHASE_FROM_AGENT_ID=i.AGENT_ID
WHERE NOT EXISTS(SELECT * FROM attm_ticket_sell_chd j 
WHERE j.TICKET_PURCHASE_CHD_ID=a.TICKET_PURCHASE_CHD_ID) 
AND b.TICKET_REQUEST_ID is null
GROUP BY a.TICKET_PURCHASE_CHD_ID,a.TICKET_PURCHASE_MST_ID,
a.TICKET_NO,a.USER_ID,c.user_name,a.PNR,a.TICKET_AMT_USD,a.TICKET_AMT_BDT,
a.FROM_COUNTRY,d.country_name,a.FROM_CITY,f.CITY_NAME,
a.TO_COUNTRY,e.country_name,a.TO_CITY,g.CITY_NAME,
a.REMARKS,a.ACTIVE_STATUS,a.AGENT_PASSENGER_NAME,
a.SELLING_PRICE_USD,a.SELLING_PRICE_BDT,a.QTY,b.TICKET_REQUEST_ID,
b.PURCHASE_DATE,b.PURCHASE_FROM_TICKET_COMPANY_ID,h.OWNER_COMPANY_NAME,
b.PURCHASE_FROM_AGENT_ID,i.AGENT_NAME
		 * 
		 * */
		
		
		
		RowMapper<ModelTicketPurchaseCustom> serviceMapper=new RowMapper<ModelTicketPurchaseCustom>() {

			@Override
			public ModelTicketPurchaseCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketPurchaseCustom bn= new ModelTicketPurchaseCustom();
				
				bn.setTicketPurchaseMstId(rs.getLong("TICKET_PURCHASE_MST_ID"));
				bn.setTicketPurchaseChdId(rs.getLong("TICKET_PURCHASE_CHD_ID"));
				bn.setTicketNo(rs.getString("TICKET_NO"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				bn.setpNR(rs.getString("PNR"));
				bn.setTicketAMTBDT(rs.getDouble("TICKET_AMT_BDT"));
				bn.setTicketAMTUSD(rs.getDouble("TICKET_AMT_USD"));
				bn.setFromCountryId(rs.getLong("FROM_COUNTRY"));
				bn.setFromCountryName(rs.getString("d.country_name"));
				bn.setFromCityId(rs.getLong("FROM_CITY"));
				bn.setFromCityName(rs.getString("f.CITY_NAME"));
				bn.setToCountryId(rs.getLong("TO_COUNTRY"));
				bn.setTocountryName(rs.getString("e.country_name"));
				bn.setToCityId(rs.getLong("TO_CITY"));
				bn.setToCityName(rs.getString("g.CITY_NAME"));
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else {
					bn.setActive(false);
					bn.setsActive("No");
				}
				bn.setAgentPassengerName(rs.getString("AGENT_PASSENGER_NAME"));
				bn.setSellingPriceBDT(rs.getDouble("SELLING_PRICE_BDT"));
				bn.setSellingPriceUSD(rs.getDouble("SELLING_PRICE_USD"));
				bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
				bn.setPurchasedQTY(rs.getInt("QTY"));
				bn.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
				bn.setFromAgentId(rs.getLong("PURCHASE_FROM_AGENT_ID"));
				bn.setFromAgentName(rs.getString("AGENT_NAME"));
				bn.setTicketOwnerCompanyId(rs.getLong("PURCHASE_FROM_TICKET_COMPANY_ID"));
				bn.setOwnerCompanyName(rs.getString("OWNER_COMPANY_NAME"));
				
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    //  parameters.addValue("ticketSellMstId", ticketSellMstId);
	    //  System.out.println(" in daoTicketPurchaseMstImp, ticketSellMstId:" +ticketSellMstId);
	      
	      

	      System.out.println("service mapper ");
			return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	@Override
	public List<ModelTicketSellCustom> getTicketSellMstByRequestId(Long ticketRequestId) {
		// TODO Auto-generated method stub
		String qry="SELECT a.TICKET_SELL_MST_ID,a.SELL_DATE,a.SELL_QTY,\r\n" + 
				"a.TO_AGENT_ID,b.AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,\r\n" + 
				"a.TICKET_REQUEST_ID \r\n" + 
				"FROM attm_ticket_sell_mst a\r\n" + 
				"LEFT OUTER JOIN attm_agent b ON a.TO_AGENT_ID=b.AGENT_ID\r\n" + 
				"WHERE a.TICKET_REQUEST_ID=coalesce(:ticketRequestId,a.TICKET_REQUEST_ID)\r\n" + 
				"GROUP BY a.TICKET_SELL_MST_ID,a.SELL_DATE,a.SELL_QTY,\r\n" + 
				"a.TO_AGENT_ID,b.AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,\r\n" + 
				"a.TICKET_REQUEST_ID";
		
		/*
		 * SELECT a.TICKET_SELL_MST_ID,a.SELL_DATE,a.SELL_QTY,
a.TO_AGENT_ID,b.AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,
a.TICKET_REQUEST_ID 
FROM attm_ticket_sell_mst a
LEFT OUTER JOIN attm_agent b ON a.TO_AGENT_ID=b.AGENT_ID
WHERE a.TICKET_REQUEST_ID=coalesce(:ticketRequestId,a.TICKET_REQUEST_ID)
GROUP BY a.TICKET_SELL_MST_ID,a.SELL_DATE,a.SELL_QTY,
a.TO_AGENT_ID,b.AGENT_NAME,a.REMARKS,a.ACTIVE_STATUS,
a.TICKET_REQUEST_ID
		 * */	
		
		RowMapper<ModelTicketSellCustom> serviceMapper=new RowMapper<ModelTicketSellCustom>() {

			@Override
			public ModelTicketSellCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketSellCustom bn= new ModelTicketSellCustom();
				
				bn.setTicketSellMstId(rs.getLong("TICKET_SELL_MST_ID"));
				bn.setTicketRequestId(rs.getLong("TICKET_REQUEST_ID"));
				bn.setSellDate(rs.getDate("SELL_DATE"));
				bn.setSellQty(rs.getInt("SELL_QTY"));
				bn.setToAgentId(rs.getLong("TO_AGENT_ID"));
				bn.setToAgentName(rs.getString("AGENT_NAME"));
				
				bn.setRemarks(rs.getString("REMARKS"));
				bn.setActiveStatus(rs.getInt("ACTIVE_STATUS"));
				if(bn.getActiveStatus()==1) 
				{
					bn.setActive(true);
					bn.setsActive("Yes");
				}
				else {
					bn.setActive(false);
					bn.setsActive("No");
				}
				
				
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("ticketRequestId", ticketRequestId);
	    System.out.println(" in daoTicketSellMstImp, ticketRequestId:" +ticketRequestId);
	      
	      

	      System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}

	

}
