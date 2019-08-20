package com.biziitech.attm.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


import com.biziitech.attm.bg.repository.TicketPurchaseChdRepository;
import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.dao.DaoTicketPurchaseChd;
import com.biziitech.attm.model.ModelTicketPurchaseChd;

@Service
public class DaoTicketPurchaseChdImp implements DaoTicketPurchaseChd{
	
	@Autowired
	private TicketPurchaseChdRepository ticketPurchaseChdRepository;
	
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
	public void saveTicketPurchaseChd(ModelTicketPurchaseChd modelTicketPurchaseChd) {
		// TODO Auto-generated method stub
		ticketPurchaseChdRepository.save(modelTicketPurchaseChd);
	}

	@Override
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdByMstId(Long ticketPurchaseMstId) {
		// TODO Auto-generated method stub
		System.out.println("in DaoTicketPurChaseChdImp, getTicketPurchaseChdByMstId method");
		System.out.println("ticketPurchaseMstId: "+ticketPurchaseMstId);
		List<ModelTicketPurchaseChd> resultList= ticketPurchaseChdRepository.getTicketPurchaseChdByMstId(ticketPurchaseMstId);
		
		List<ModelTicketPurchaseChd> ticketPurchaseMstList=new ArrayList<>();
		
		for(ModelTicketPurchaseChd ticketPurchaseMst: resultList) {
				if(ticketPurchaseMst.getActiveStatus()==1)
				 { 
					ticketPurchaseMst.setsActive("Yes");
					ticketPurchaseMst.setActive(true);
				 }
				 
				 else
				 {
					 ticketPurchaseMst.setsActive("NO");
					 ticketPurchaseMst.setActive(false);
				     
				 }
				
				
				ticketPurchaseMstList.add(ticketPurchaseMst);
				}
		
				
		return ticketPurchaseMstList;
	}

	@Override
	public List<ModelTicketPurchaseChd> getTicketPurchaseChdById(Long ticketPurchaseChdId) {
		// TODO Auto-generated method stub
		List<ModelTicketPurchaseChd> resultList= ticketPurchaseChdRepository.getTicketPurchaseChdById(ticketPurchaseChdId);
		
		List<ModelTicketPurchaseChd> ticketPurchaseChdList=new ArrayList<>();
		
		for(ModelTicketPurchaseChd ticketPurchaseChd: resultList) {
				if(ticketPurchaseChd.getActiveStatus()==1)
				 { 
					ticketPurchaseChd.setsActive("Yes");
					ticketPurchaseChd.setActive(true);
				 }
				 
				 else
				 {
					 ticketPurchaseChd.setsActive("NO");
					 ticketPurchaseChd.setActive(false);
				     
				 }
				
				
				ticketPurchaseChdList.add(ticketPurchaseChd);
				}
	
	
	
			return ticketPurchaseChdList;
	}

	@Override
	public List<ModelTicketPurchaseCustom> getTicketPurchaseChdByMst(Long ticketPurchaseMstId) {
		// TODO Auto-generated method stub
		
		String qry="SELECT a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,\r\n" + 
				"j.TICKET_NO,j.USER_ID,b.user_name,\r\n" + 
				"j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,\r\n" + 
				"j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,\r\n" + 
				"j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,\r\n" + 
				"j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,\r\n" + 
				"j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,\r\n" + 
				"j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD\r\n" + 
				"FROM attm_ticket_purchase_chd j\r\n" + 
				"INNER JOIN attm_ticket_purchase_mst g ON j.TICKET_PURCHASE_MST_ID=g.TICKET_PURCHASE_MST_ID\r\n" + 
				"INNER JOIN attm_ticket_request a ON g.TICKET_REQUEST_ID=a.TICKET_REQUEST_ID\r\n" + 
				"LEFT OUTER JOIN bg_user b ON j.USER_ID=b.user_id\r\n" + 
				"LEFT OUTER JOIN attm_agent c ON a.FROM_AGENT_ID=c.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_agent h ON a.TO_AGENT_ID=h.AGENT_ID\r\n" + 
				"LEFT OUTER JOIN attm_ticket_owner_company d ON a.TICKET_OWNER_COMPANY_ID=d.TICKET_OWNER_COMPANY_ID\r\n" + 
				"INNER JOIN bg_country e ON j.FROM_COUNTRY=e.country_id\r\n" + 
				"INNER JOIN bg_country f ON j.TO_COUNTRY=f.country_id\r\n" + 
				"INNER JOIN bg_city l ON j.FROM_CITY=l.CITY_ID\r\n" + 
				"INNER JOIN bg_city m ON j.TO_CITY=m.CITY_ID\r\n" + 
				"WHERE j.ACTIVE_STATUS=1 \r\n" + 
				"and g.TICKET_PURCHASE_MST_ID=coalesce(:ticketPurchaseMstId,g.TICKET_PURCHASE_MST_ID)\r\n" + 
				"GROUP BY a.TICKET_REQUEST_ID,a.REQUEST_DATE,\r\n" + 
				"a.FROM_AGENT_ID,c.AGENT_NAME,c.SELF_FLAG,\r\n" + 
				"a.TO_AGENT_ID,h.AGENT_NAME,h.SELF_FLAG,\r\n" + 
				"a.TICKET_OWNER_COMPANY_ID,d.OWNER_COMPANY_NAME,\r\n" + 
				"g.TICKET_PURCHASE_MST_ID, j.TICKET_PURCHASE_CHD_ID,\r\n" + 
				"j.TICKET_NO,j.USER_ID,b.user_name,\r\n" + 
				"j.PNR,j.TICKET_AMT_USD,j.TICKET_AMT_BDT,\r\n" + 
				"j.FROM_COUNTRY,e.country_name,j.FROM_CITY,l.CITY_NAME,\r\n" + 
				"j.TO_COUNTRY,f.country_name,j.TO_CITY,m.CITY_NAME,\r\n" + 
				"j.REMARKS,j.ACTIVE_STATUS,j.AGENT_PASSENGER_NAME,\r\n" + 
				"j.SELLING_PRICE_USD,j.SELLING_PRICE_BDT,\r\n" + 
				"j.PURCHASE_PRICE_BDT,j.PURCHASE_PRICE_USD";
		
		RowMapper<ModelTicketPurchaseCustom> serviceMapper=new RowMapper<ModelTicketPurchaseCustom>() {

			@Override
			public ModelTicketPurchaseCustom mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				ModelTicketPurchaseCustom bn= new ModelTicketPurchaseCustom();
				
				
				bn.setTicketPurchaseMstId(rs.getLong("TICKET_PURCHASE_MST_ID"));
				bn.setTicketPurchaseChdId(rs.getLong("TICKET_PURCHASE_CHD_ID"));
				bn.setTicketNo(rs.getString("TICKET_NO"));
				bn.setpNR(rs.getString("PNR"));
				bn.setTicketAMTUSD(rs.getDouble("TICKET_AMT_USD"));
				bn.setTicketAMTBDT(rs.getDouble("TICKET_AMT_BDT"));
				bn.setSellingPriceUSD(rs.getDouble("SELLING_PRICE_USD"));
				bn.setSellingPriceBDT(rs.getDouble("SELLING_PRICE_BDT"));
				bn.setPurchasePriceUSD(rs.getDouble("PURCHASE_PRICE_USD"));
				bn.setPurchasePriceBDT(rs.getDouble("PURCHASE_PRICE_BDT"));
				bn.setAgentPassengerName(rs.getString("AGENT_PASSENGER_NAME"));
				bn.setRemarks(rs.getString("j.REMARKS"));
				bn.setActiveStatus(rs.getInt("j.ACTIVE_STATUS"));
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
				bn.setFromAgentName(rs.getString("h.AGENT_NAME"));
				bn.setFromAgentSelfStatus(rs.getInt("h.SELF_FLAG"));
				bn.setToAgentId(rs.getLong("FROM_AGENT_ID"));
				bn.setToAgentName(rs.getString("c.AGENT_NAME"));
				bn.setToAgentSelfStatus(rs.getInt("c.SELF_FLAG"));
				bn.setUserId(rs.getLong("USER_ID"));
				bn.setUserName(rs.getString("user_name"));
				
				if(bn.getUserId()!=null|| bn.getUserId()!=0) 
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
				
				
				
//				bn.setAirLineId(rs.getLong("AIRLINE_ID"));
//				bn.setAirLineName(rs.getString("AIRLINE_NAME"));
				bn.setFromCountryId(rs.getLong("j.FROM_COUNTRY"));
				bn.setFromCountryName(rs.getString("e.country_name"));
				bn.setFromCityId(rs.getLong("j.FROM_CITY"));
				bn.setFromCityName(rs.getString("l.CITY_NAME"));
				bn.setToCountryId(rs.getLong("j.TO_COUNTRY"));
				bn.setTocountryName(rs.getString("f.country_name"));
				bn.setToCityId(rs.getLong("j.TO_CITY"));
				bn.setToCityName(rs.getString("m.CITY_NAME"));
				bn.setTicketOwnerCompanyId(rs.getLong("TICKET_OWNER_COMPANY_ID"));
				bn.setOwnerCompanyName(rs.getString("OWNER_COMPANY_NAME"));
				
				
				//bn.setPurchasedQTY(purChasedQTY);
				
				return bn;
			}

		};
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      parameters.addValue("ticketPurchaseMstId", ticketPurchaseMstId);
	      System.out.println(" in daoTicketPurchaseMstImp, ticketPurchaseMstId:" +ticketPurchaseMstId);

	      

		System.out.println("service mapper ");
		return namedParameterJdbcTemplate.query(qry,parameters,serviceMapper);
	}		

}
