package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biziitech.attm.bg.dao.DaoUser;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentLedgerRepository;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.UserRepository;
import com.biziitech.attm.custom.model.ModelTransactionCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.daoImp.DaoPassengerLedgerImp;
import com.biziitech.attm.daoImp.DaoTranTypeImp;
import com.biziitech.attm.model.ModelPaymentChd;
import com.biziitech.attm.model.ModelPaymentMst;
import com.biziitech.attm.model.ModelTranType;

@Controller
public class PassengerLedgerController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DaoTranTypeImp daoTranTypeImp;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	private Long systemUserId;
	
	@Autowired
	private DaoPassengerLedgerImp daoPassengerLedgerImp;
	
	@Autowired
	private AgentLedgerRepository agentLedgerRepository;
	
	
	@RequestMapping(path="/passengerledgercontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
				
		String msg=" ";
		model.addAttribute("message",msg );
		
		
		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
		model.addAttribute("passengerList", modelBgUser);
		
		List<ModelTranType> modelTranType = daoTranTypeImp.getAllTranType();
		model.addAttribute("tranTypeList", modelTranType);
		
		this.systemUserId=userId;
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  String userName=logonUser.getUserName();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		  this.systemUserId=userId;
		  
		  
		//  for setup
			
					List<ModelUserObject> listModelUserObjectSetup= new ArrayList<ModelUserObject>();
					
					listModelUserObjectSetup=daoUserObject.getUserObjectByObjectGroup(userId,"S");
					
					System.out.println("listModelUserObjectSetup : " + listModelUserObjectSetup);
					
					System.out.println("size " + listModelUserObjectSetup.size() );
					
					model.addAttribute("listModelUserObjectSetup", listModelUserObjectSetup);
					
					
					//for Transactions
					
			        List<ModelUserObject> listModelUserObjectTransaction= new ArrayList<ModelUserObject>();
					
			        listModelUserObjectTransaction=daoUserObject.getUserObjectByObjectGroup(userId,"T");
					
					System.out.println("listModelUserObjectTransaction : " + listModelUserObjectTransaction);
					
					System.out.println("size " + listModelUserObjectTransaction.size() );
					
					model.addAttribute("listModelUserObjectTransaction", listModelUserObjectTransaction);
					
			       //for Tools
					
			        List<ModelUserObject> listModelUserObjectTool= new ArrayList<ModelUserObject>();
					
			        listModelUserObjectTool=daoUserObject.getUserObjectByObjectGroup(userId,"U");
					
					System.out.println("listModelUserObjectTool : " + listModelUserObjectTool);
					
					System.out.println("listModelUserObjectTool size " + listModelUserObjectTool.size() );
					
					model.addAttribute("listModelUserObjectTool", listModelUserObjectTool);
					
					
					
			        //for reports
					
			        List<ModelUserObject> listModelUserObjectReport= new ArrayList<ModelUserObject>();
					
			        listModelUserObjectReport=daoUserObject.getUserObjectByObjectGroup(userId,"R");
					
					System.out.println("listModelUserObjectReport : " + listModelUserObjectReport);
					
					System.out.println("listModelUserObjectReport size " + listModelUserObjectReport.size());
					
					model.addAttribute("listModelUserObjectReport", listModelUserObjectReport);
					
					
					
					for(ModelUserObject a:listModelUserObjectSetup) {
						
						System.out.println("object name " + a.getModelObject().getObjectName());
					}
		
		
		
		return "passengerLedger";	
	}
	
	
	//created by sohel rana on 26/06/2019
	
	//transaction search created by sohel rana on 24/06/2019
	
	@ResponseBody
    @RequestMapping(value = "/passengerledgercontroller/transaction/search", method=RequestMethod.GET)
    public List<ModelTransactionCustom> transactionSearch(@RequestParam("userId") Long userId,@RequestParam("typeId") Long tranTypeId,@DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam("startDate") Date startDate,@DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam("endDate") Date endDate) {
	   
		 System.out.println("user Id :"+ userId );
		 System.out.println("from date :"+ startDate );
		 System.out.println("to date :"+ endDate );
		 
		 List<ModelTransactionCustom> searchData=daoPassengerLedgerImp.searchTranDetails(userId, tranTypeId, startDate, endDate);
		 
		 System.out.println("list data size" + searchData.size());
		 
		 List<ModelTransactionCustom> listModelTransaction= new ArrayList<ModelTransactionCustom>();
		 
		 for(ModelTransactionCustom m:searchData) {
			           
			 Long checkId=m.getChildId();	
			 Long masterId=m.getTransactionId();
			
			 if(checkId==null || checkId==0) {
				 
				 System.out.println("this payment against master");
				 
				 List<ModelPaymentMst> paymentList=agentLedgerRepository.findAmountFromMst(masterId);
				 
				 Double paidAmount=paymentList.get(0).getPaymentAmount();
				 
				 System.out.println("you paid " + paidAmount);
				 
				 m.setPaidAmount(paidAmount);
				 
				 
			 }
			 
			 else {
				 
				 System.out.println("this payment against child");
				 
				 Long queryId=m.getTransactionId();
				 List<ModelPaymentChd> paymentList1=agentLedgerRepository.findAmountFromChd(queryId);
				 Double amount=paymentList1.get(0).getPaymentAmount();				 
				 System.out.println("you paid child " + queryId);
				 Double paidAmount=paymentList1.get(0).getPaymentAmount();
				 m.setPaidAmount(paidAmount);
			 }
			 
			
		 }
	
	   
        return searchData;
	}


}
