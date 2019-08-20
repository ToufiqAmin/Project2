package com.biziitech.attm.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.biziitech.attm.custom.model.ModelTransactionCustom;
import com.biziitech.attm.dao.DaoPassengerLedger;


@Service
public class DaoPassengerLedgerImp implements DaoPassengerLedger {

		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public List<ModelTransactionCustom> searchTranDetails(Long userId, Long tranTypeId, Date fromDate, Date toDate) {
		
		
		String qry="SELECT a.parent_tran_id,a.TRAN_ID,c.TRAN_TYPE_ID,a.TRAN_DATE,a.TRAN_AMT,a.TRAN_OWNER_ID,b.user_NAME,b.USER_ID,c.tran_type_id,c.TYPE_NAME FROM attm_tran a INNER JOIN bg_user b ON a.TRAN_OWNER_ID=b.user_ID INNER JOIN attm_tran_type c ON a.TRAN_TYPE_ID=c.TRAN_TYPE_ID "
				   + " and a.tran_date BETWEEN coalesce(:done_start_date,a.tran_date) AND coalesce(:done_end_date,a.tran_date) and a.TRAN_OWNER_TYPE_ID=2 and b.user_id=coalesce(:userId,b.user_id) and c.tran_type_id=coalesce(:tranTypeId,c.tran_type_id)";
			
		
		
		RowMapper<ModelTransactionCustom> serviceMapper=new RowMapper<ModelTransactionCustom>() {
			@Override
			public ModelTransactionCustom mapRow(ResultSet rs, int rownum) throws SQLException{
				ModelTransactionCustom bn=new ModelTransactionCustom();
		        
				Long checkId=rs.getLong("parent_tran_id");				
				bn.setPassengerId(rs.getLong("user_id"));
				bn.setTransactionId(rs.getLong("tran_id"));
				bn.setTransactionAmount(rs.getDouble("tran_amt"));
				bn.setPassengerName(rs.getString("user_name"));
				bn.setTransactionDate(rs.getDate("tran_date"));
				bn.setTransactionTypeName(rs.getString("type_name"));
				//bn.setPaidAmount(rs.getDouble("amt"));
				
				System.out.println("parent tran id is " +checkId);
				
				if(checkId==null || checkId==0) {
					//bn.setMasterId(rs.getLong("tran_id"));
				}
			    
				else {
					bn.setChildId(rs.getLong("parent_tran_id"));
				}
				
				bn.setTransactionTypeId(rs.getLong("tran_type_id"));
				
			
					
			return bn;
			
			}	
		};
		

		MapSqlParameterSource parameters = new MapSqlParameterSource();
	      
	      parameters.addValue("userId", userId);
	      parameters.addValue("done_start_date", fromDate);
	      parameters.addValue("done_end_date", toDate);
	      parameters.addValue("tranTypeId", tranTypeId);
	 
	   
		return  namedJdbcTemplate.query(qry,parameters,serviceMapper);
		
	}

}
