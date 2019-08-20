package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.TicketOwnerCompanyRepository;
import com.biziitech.attm.bg.repository.TicketRequestRepository;
import com.biziitech.attm.custom.model.ModelTicketRequestCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoTicketRequest;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelTicketOwnerCompany;
import com.biziitech.attm.model.ModelTicketRequest;




@Controller
public class TicketRequestListController {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private TicketOwnerCompanyRepository ticketOwnerCompanyRepository;
	
	@Autowired
	private TicketRequestRepository ticketRequestRepository;
	
	@Autowired
	private DaoTicketRequest daoTicketRequest;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@RequestMapping(path="/ticketrequestlistcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
		model.addAttribute("userList", modelBgUser);
		
		List<ModelAgent> agentList= agentRepository.getAgentName();
		model.addAttribute("agentList",agentList);
		
		List<ModelAgent> agentList2= agentRepository.findAgentNameBySelfFalg();
		model.addAttribute("agentList2",agentList2);
		
		List<ModelTicketOwnerCompany> ticketOwnerCompanyList= ticketOwnerCompanyRepository.findTicketOwnerCompanyName();
		model.addAttribute("ticketOwnerCompanyList",ticketOwnerCompanyList);
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  String userName=logonUser.getUserName();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		  
		  
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
		 
		
		 return "ticketRequestList";
	}
	
	
	@RequestMapping(path="/ticketrequestlistcontroller/search/{userId}")
	public String getTicketRequestList(@PathVariable Long userId,Model model,
			@RequestParam("requesterName")String requesterName,
			@RequestParam("requesterContactPhone")String requesterContactPhone,
			@RequestParam("userName")String userName,
			//@RequestParam("agentName")String agentName,
			@RequestParam("fromAgentName")String fromAgentName,
			@RequestParam("toAgentName")String toAgentName,
			@RequestParam("airLineName")String airLineName,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam(value = "active", required = false) String active
			 
														
			) {
		
        int status=1;
        
      
		
		if(active!=null)
			status=1;
		else
			status=0;
		
		
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  String logonUserName=logonUser.getUserName();
		  
		  System.out.println("logon user name is :" + logonUserName);
		  
		  String name="User : " + logonUserName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		  
		  
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
		
		
		
		List<ModelTicketRequest> modelTicketRequestList=daoTicketRequest.getTicketRequestByCraiteria(requesterName, requesterContactPhone, userName,fromAgentName, toAgentName, airLineName, fromDate, toDate, status);
		model.addAttribute("ticketRequestList", modelTicketRequestList);	
		System.out.println("Size: "+modelTicketRequestList.size());
		return "ticketRequestList";
	}



@ResponseBody
@RequestMapping(path="/ticketrequestlistcontroller/getticketrequestdetails", method = RequestMethod.GET)
public List<ModelTicketRequestCustom> getTicketRequestDetails(
		@RequestParam("requesterName")String requesterName,
		@RequestParam("requesterContactPhone")String requesterContactPhone,
		@RequestParam("userName")Long userName,
		//@RequestParam("agentName")String agentName,
		@RequestParam("fromAgentName")Long fromAgentName,
		@RequestParam("toAgentName")Long toAgentName,
		//@RequestParam("airLineName")String airLineName,
		@RequestParam("ownerCompanyName")Long ownerCompanyName,
		@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
		@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
		@RequestParam("activeStatus") int activeStatus
		 ){
	
	System.out.println("in ticketRequest Controller's getTicketRequestDetails method");
	
	try {
	
	List<ModelTicketRequestCustom> modelTicketRequestCustomList = daoTicketRequest.getTicketRequestDetails(requesterName, requesterContactPhone, userName,fromAgentName, toAgentName, ownerCompanyName, fromDate, toDate, activeStatus);
	System.out.println("Size: "+modelTicketRequestCustomList.size());
	
	return modelTicketRequestCustomList;
	
	}		
	catch(Exception e) {			
	 e.printStackTrace();
	 System.out.println("error");
		return null;
	}
}

@ResponseBody
@RequestMapping(path="/ticketrequestlistcontroller/getticketrequestid", method = RequestMethod.GET)
public List<ModelTicketRequestCustom> getTicketRequestId(
		@RequestParam("ticketRequestId")Long ticketRequestId
		
		 ){
	
	System.out.println("in ticketRequest Controller's getTicketRequestId method");
	
	try {
	
		Optional<ModelTicketRequest> existsRequest = ticketRequestRepository.findById(ticketRequestId);
		
		int autoPayment=existsRequest.get().getAutoPayment();
		
		if(autoPayment==1) 
		{
			List<ModelTicketRequestCustom>modelTicketRequestCustomList = daoTicketRequest.getTicketRequestIdInTicketPurchase(ticketRequestId);
			
			return modelTicketRequestCustomList;
		}
		else {
			List<ModelTicketRequestCustom>modelTicketRequestCustomList = daoTicketRequest.getTicketRequestIdInTicketSell(ticketRequestId);
			
			return modelTicketRequestCustomList;
		}	
	
	}		
	catch(Exception e) {			
	 e.printStackTrace();
	 System.out.println("error");
		return null;
	}
}


}