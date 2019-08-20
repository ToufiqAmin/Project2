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

import com.biziitech.attm.bg.dao.DaoCity;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.dao.DaoUser_attm;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.bg.repository.AirLineRepository;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.CountryRepository;
import com.biziitech.attm.bg.repository.TicketOwnerCompanyRepository;
import com.biziitech.attm.bg.repository.TicketRequestRepository;
import com.biziitech.attm.custom.model.ModelBgUserCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoTicketRequest;
import com.biziitech.attm.dao.DaoTicketRequestCancel;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelAirLine;
import com.biziitech.attm.model.ModelTicketOwnerCompany;
import com.biziitech.attm.model.ModelTicketRequest;
import com.biziitech.attm.model.ModelTicketRequestCancel;





@Controller
public class TicketRequestController {
	
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private TicketOwnerCompanyRepository ticketOwnerCompanyRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private DaoTicketRequest daoTicketRequest;
	
	@Autowired
	private TicketRequestRepository ticketRequestRepository;
	
	@Autowired
	private DaoTicketRequestCancel daoTicketRequestCancel;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private DaoUser_attm daoUser;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoCity daoCity;
	
	private Long systemUserId;
	
	@RequestMapping(path="/ticketrequestcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		
		ModelTicketRequest modelTicketRequest= new ModelTicketRequest();
		model.addAttribute("modelTicketRequest_ATTM", modelTicketRequest);
		
		List<ModelAirLine> airLineList= airLineRepository.getAirLineName();
		model.addAttribute("airLineList",airLineList);
		
		List<ModelTicketOwnerCompany> ticketOwnerCompanyList= ticketOwnerCompanyRepository.findTicketOwnerCompanyName();
		model.addAttribute("ticketOwnerCompanyList",ticketOwnerCompanyList);
		
		List<ModelAgent> agentList= agentRepository.getAgentName();
		model.addAttribute("agentList",agentList);
		
		List<ModelAgent> agentList2= agentRepository.findAgentNameBySelfFalg();
		model.addAttribute("agentList2",agentList2);
		
		List<ModelCountry> countryList = countryRepository.findCountryInOrder();
		model.addAttribute("countryList", countryList);
		
		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
		model.addAttribute("userList", modelBgUser);
		 
		String msg=" ";
		model.addAttribute("message",msg );
		
		
		this.systemUserId=userId;
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  String userName=logonUser.getUserName();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		  
		  List<ModelCity> modelCity = daoCity.getCityInOrder();
 		  model.addAttribute("cityList", modelCity);
		  
		  
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
		 
		 return "ticketRequest";
	}
	
	@ResponseBody
	@RequestMapping(path="/attm_ticketrequestcontroller/checkinguserid")
	public List<ModelBgUserCustom> checkingUserId(@RequestParam("passportNo")String passportNo){
		
		System.out.println("passportNo: " + passportNo);
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserName(passportNo);
		List<ModelBgUserCustom> modelUser = daoUser.getUserByPassportNo(passportNo);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	
	@RequestMapping(path="/attm_ticketrequestcontroller/getuserid")
	@ResponseBody
	public List<ModelBgUserCustom> getUserId(@RequestParam("userId")Long userId){
		
		System.out.println("userId: " + userId);
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelBgUserCustom> modelUser = daoUser.getUserNameById(userId);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
/*	
	@RequestMapping(path = "/ticketrequestcontroller/saveticketrequest/{userId}", method = RequestMethod.POST) 
   	public  String saveTicketRequest(@PathVariable Long userId,ModelTicketRequest modelTicketRequest,
   			//@RequestParam("modelUser_ATTM.dob")@DateTimeFormat(pattern="yyyy-MM-dd")Date dob,
   			Model model) {
   	
   	//System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
	
	
	Date date2 = modelTicketRequest.getPossibleFlightDate();
	
	System.out.println("Date 1: "+entryTime+" Date 2 : "+date2);
	System.out.println("Ticket Request ID: "+modelTicketRequest.getTicketRequestId());
	
	if(date2.compareTo(date) < 0) 
	{
		System.out.println("Date 2 is before entryTime");
		
		String msg="Possible Flight Date cannot be in past Dates";
		model.addAttribute("message",msg);
		
		 model.addAttribute("modelTicketRequest_ATTM", modelTicketRequest);
		
		
		List<ModelCountry> countryList = countryRepository.findCountryInOrder();
		model.addAttribute("countryList", countryList);
		
		List<ModelAirLine> airLineList= airLineRepository.getAirLineName();
		model.addAttribute("airLineList",airLineList);
		
		List<ModelAgent> agentList= agentRepository.getAgentName();
		model.addAttribute("agentList",agentList);
		
		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
		model.addAttribute("userList", modelBgUser);
		
		List<ModelCity> modelCity = daoCity.getCityInOrder();
		model.addAttribute("cityList", modelCity);
		  	
		  	
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
		
		
		return "ticketRequest";
		
	}
	else if (date2.compareTo(date) > 0)
	{
		
   	if(modelTicketRequest.getTicketRequestId()==null )
   	{
   		
   		if(modelTicketRequest.getModelUser_ATTM().getUserId()==null) 
   		{	
   			ModelBgUser modelUser = new ModelBgUser();
   			modelUser.setUserId(null);
   			modelTicketRequest.setModelUser_ATTM(modelUser);
   			
   		}
   		else if(modelTicketRequest.getModelAgent().getAgentId()==null) 
   		{
   			ModelAgent modelAgent = new ModelAgent();
   			modelAgent.setAgentId(null);
   			modelTicketRequest.setModelAgent(modelAgent);
   		}

   		modelTicketRequest.setpOFlagStatus(0);
   		daoTicketRequest.saveTicketRequest(modelTicketRequest);
   		String msg="Successfully Saved";
   		model.addAttribute("message",msg );
    }
   	else
   	{
   				Optional<ModelTicketRequest> existsTicketRequest=ticketRequestRepository.findById(modelTicketRequest.getTicketRequestId());
   
   				if(modelTicketRequest.getModelUser_ATTM().getUserId()==null) 
   		   		{	
   		   			ModelBgUser modelUser = new ModelBgUser();
   		   			modelUser.setUserId(null);
   		   			modelTicketRequest.setModelUser_ATTM(modelUser);
   		   			
   		   		}
   		   		else if(modelTicketRequest.getModelAgent().getAgentId()==null) 
   		   		{
   		   			ModelAgent modelAgent = new ModelAgent();
   		   			modelAgent.setAgentId(null);
   		   			modelTicketRequest.setModelAgent(modelAgent);
   		   		}
   				modelTicketRequest.setpOFlagStatus(0);
   				daoTicketRequest.saveTicketRequest(modelTicketRequest);
   				String msg="Successfully Saved";
   				model.addAttribute("message",msg );
   	}
   	
	}
     	
   	ModelTicketRequest modelTicketRequest2= new ModelTicketRequest();
	model.addAttribute("modelTicketRequest_ATTM", modelTicketRequest2);
	
	List<ModelCountry> countryList = countryRepository.findCountryInOrder();
	model.addAttribute("countryList", countryList);
   
	return "redirect:/ticketrequestcontroller/add/"+modelTicketRequest.getTicketRequestId()+"/"+userId;
   	
   }

	*/
	
	@ResponseBody
	@RequestMapping(path = "/ticketrequestcontroller/saveticketrequest", method = RequestMethod.POST) 
	public List<ModelTicketRequest> saveTicketRequest(ModelBgUser modelBgUser,ModelAirLine modelAirLine,
			ModelTicketRequest modelTicketRequest,
			@RequestParam("ticketRequestId") Long ticketRequestId,
			@RequestParam("requesterId") Long requesterId,
			@RequestParam("requesterName") String requesterName,
			@RequestParam("requesterContactPhone") String requesterContactPhone,
			@RequestParam("userId") Long userId,
			@RequestParam("userName") String userName,
			@RequestParam("contactNo") String contactNo,
			@RequestParam("fromAgentId") Long fromAgentId,
			@RequestParam("toAgentId") Long toAgentId,
			@RequestParam("requestDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date requestDate,
			//@RequestParam("airLineId") Long airLineId,
			@RequestParam("ticketOwnerCompanyId") Long ticketOwnerCompanyId,
			@RequestParam("possibleFlightDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date possibleFlightDate,
			@RequestParam("noOfTickets") Integer noOfTickets,
			@RequestParam("advPayment") Double advPayment,
			@RequestParam("autoPayment") int autoPayment,
			@RequestParam("fromCountryId") Long fromCountryId,
			@RequestParam("fromCityId") Long fromCityId,
			@RequestParam("toCountryId") Long toCountryId,
			@RequestParam("toCityId") Long toCityId,
			@RequestParam("approxAMT") Double approxAMT,			
			@RequestParam("agreementAMT") Double agreementAMT,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks

						
			) {
	
		System.out.println("IN ticketrequestcontroller saveTicketRequest Method");
		System.out.println("userId: "+userId+" requesterId: "+requesterId+" ticketOwnerCompanyId: "+ticketOwnerCompanyId);
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {
			
			if(ticketRequestId==null) 
			{
			
				System.out.println("save 1");
				
				if(requesterId==0||requesterId==null)
				{
					if(userId==0||userId==null) {
					modelTicketRequest.setRequesterId(null);
					modelTicketRequest.setRequesterName(null);
					modelTicketRequest.setRequesterContactPhone(null);
					modelTicketRequest.setModelUser_ATTM(null);
					}
					else {
						modelTicketRequest.setRequesterId(userId);
						modelTicketRequest.setRequesterName(userName);
						modelTicketRequest.setRequesterContactPhone(contactNo);
						modelBgUser.setUserId(userId);
						modelTicketRequest.setModelUser_ATTM(modelBgUser);
					}
				}
				else {
					if(userId==0||userId==null) 
					{
						modelTicketRequest.setRequesterId(requesterId);
						modelTicketRequest.setRequesterName(requesterName);
						modelTicketRequest.setRequesterContactPhone(requesterContactPhone);
						modelTicketRequest.setModelUser_ATTM(null);
					}
					else 
					{
						modelTicketRequest.setRequesterId(requesterId);
						modelTicketRequest.setRequesterName(requesterName);
						modelTicketRequest.setRequesterContactPhone(requesterContactPhone);
						modelBgUser.setUserId(userId);
						modelTicketRequest.setModelUser_ATTM(modelBgUser);
					}
				}				
				
				System.out.println("save 2");
				
				ModelAgent modelAgent = new ModelAgent();
				ModelAgent modelAgent2 = new ModelAgent();
				if(fromAgentId==0||fromAgentId==null) 
				{
					modelTicketRequest.setFromAgent(null);
					
				}
				else 
				{
					modelAgent.setAgentId(fromAgentId);
					modelTicketRequest.setFromAgent(modelAgent);
					
					
				}
				if(toAgentId==0||toAgentId==null) 
				{
					modelTicketRequest.setToAgent(null);
				}
				else {
					modelAgent2.setAgentId(toAgentId);
					modelTicketRequest.setToAgent(modelAgent2);
				}
				System.out.println("save 3");
								
				ModelTicketOwnerCompany modelTicketOwnerCompany = new ModelTicketOwnerCompany();
				if(ticketOwnerCompanyId==0||ticketOwnerCompanyId==null) 
				{
					modelTicketRequest.setModelTicketOwnerCompany(null);
				}
				else {
					modelTicketOwnerCompany.setTicketOwnerCompanyId(ticketOwnerCompanyId);				
					modelTicketRequest.setModelTicketOwnerCompany(modelTicketOwnerCompany);
				}
				
				System.out.println("save 4");
				
				modelTicketRequest.setRequestDate(requestDate);
				modelTicketRequest.setPossibleFlightDate(possibleFlightDate);
				modelTicketRequest.setAdvPayment(advPayment);
				modelTicketRequest.setAutoPayment(autoPayment);
				modelTicketRequest.setNoOfTickets(noOfTickets);
				modelTicketRequest.setApproxAMT(approxAMT);
				modelTicketRequest.setAgreementAMT(agreementAMT);
				modelTicketRequest.setActiveStatus(activeStatus);
				modelTicketRequest.setRemarks(remarks);
				
				System.out.println("save 5");
				
				ModelCountry modelCountry = new ModelCountry();
				modelCountry.setCountryId(fromCountryId);
				modelTicketRequest.setCountryFrom(modelCountry);
				
				ModelCity modelCity = new ModelCity();
				modelCity.setCityId(fromCityId);
				modelTicketRequest.setFromCity(modelCity);
				
				ModelCountry modelCountry2 = new ModelCountry();
				modelCountry2.setCountryId(toCountryId);
				modelTicketRequest.setCountryTo(modelCountry2);
				
				ModelCity modelCity2 = new ModelCity();
				modelCity2.setCityId(toCityId);
				modelTicketRequest.setToCity(modelCity2);
				
				System.out.println("save 6");

				daoTicketRequest.saveTicketRequest(modelTicketRequest);
				
			
		}
		else {
		System.out.println("ticketRequestId : " + ticketRequestId);
		
		Optional<ModelTicketRequest> existsTicketPurchaseMst=ticketRequestRepository.findById(ticketRequestId);
				
		//ModelTicketRequest modelTicketRequest = new ModelTicketRequest();
		modelTicketRequest.setRequesterId(requesterId);
		modelTicketRequest.setRequesterName(requesterName);
		modelTicketRequest.setRequesterContactPhone(requesterContactPhone);
		
		
		if(userId==0) 
		{
			modelTicketRequest.setModelUser_ATTM(null);
		}
		else 
		{
			modelBgUser.setUserId(userId);
			modelTicketRequest.setModelUser_ATTM(modelBgUser);
		}
		

		ModelTicketOwnerCompany modelTicketOwnerCompany = new ModelTicketOwnerCompany();
		modelTicketOwnerCompany.setTicketOwnerCompanyId(ticketOwnerCompanyId);
		modelTicketRequest.setModelTicketOwnerCompany(modelTicketOwnerCompany);
		modelTicketRequest.setAdvPayment(advPayment);
		modelTicketRequest.setAutoPayment(autoPayment);
		ModelAgent modelAgent = new ModelAgent();
		ModelAgent modelAgent2 = new ModelAgent();
		if(fromAgentId==0) 
		{
			modelTicketRequest.setFromAgent(null);
			
		}
		else 
		{
			modelAgent.setAgentId(fromAgentId);
			modelTicketRequest.setFromAgent(modelAgent);
			
			
		}
		if(toAgentId==0||toAgentId==null) 
		{
			modelTicketRequest.setToAgent(null);
		}
		else {
			modelAgent2.setAgentId(toAgentId);
			modelTicketRequest.setToAgent(modelAgent2);
		}

		
		modelTicketRequest.setRequestDate(requestDate);
		modelTicketRequest.setPossibleFlightDate(possibleFlightDate);
		
		
		modelTicketRequest.setPossibleFlightDate(possibleFlightDate);
		modelTicketRequest.setNoOfTickets(noOfTickets);;
		modelTicketRequest.setNoOfTickets(noOfTickets);
		modelTicketRequest.setApproxAMT(approxAMT);
		modelTicketRequest.setAgreementAMT(agreementAMT);
		modelTicketRequest.setActiveStatus(activeStatus);
		modelTicketRequest.setRemarks(remarks);
		
		ModelCountry modelCountry = new ModelCountry();
		modelCountry.setCountryId(fromCountryId);
		modelTicketRequest.setCountryFrom(modelCountry);
		
		ModelCity modelCity = new ModelCity();
		modelCity.setCityId(fromCityId);
		modelTicketRequest.setFromCity(modelCity);
		
		ModelCountry modelCountry2 = new ModelCountry();
		modelCountry2.setCountryId(toCountryId);
		modelTicketRequest.setCountryTo(modelCountry2);
		
		ModelCity modelCity2 = new ModelCity();
		modelCity2.setCityId(toCityId);
		modelTicketRequest.setToCity(modelCity2);
		modelTicketRequest.setTicketRequestId(ticketRequestId);
	
		daoTicketRequest.saveTicketRequest(modelTicketRequest);
		}
			
		List<ModelTicketRequest> modelTicketRequestList = daoTicketRequest.getTicketRequestById(modelTicketRequest.getTicketRequestId());
		System.out.println("Size : " + modelTicketRequestList.size());
		
		
	 return modelTicketRequestList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	
}
	
	
	
	@RequestMapping(path = "/ticketrequestcontroller/add/{id}/{userId}", method = RequestMethod.GET)
    public String editTicketRequest(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
    	 Optional<ModelTicketRequest> ticketRequestById;
    	 ticketRequestById=ticketRequestRepository.findById(id);
    	 if( ticketRequestById.get().getActiveStatus()==1)
    		 ticketRequestById.get().setActive(true); 
    		else
    			ticketRequestById.get().setActive(false);
    	 
    	 if(ticketRequestById.get().getpOFlagStatus()==1) 
			{
    		 ticketRequestById.get().setsPOFlag("Yes");
    		 ticketRequestById.get().setpOFlag(true);
			}
			else 
			{
				ticketRequestById.get().setsPOFlag("No");
				ticketRequestById.get().setpOFlag(false);
			}
    	 
    	 if(ticketRequestById.get().getAutoPayment()==1) 
    	 {
    		 ticketRequestById.get().setsAutoPayment("Yes");
    		 ticketRequestById.get().setAutoPay(true);
    		 
    	 }
    	 else {
    		 
    		 ticketRequestById.get().setsAutoPayment("No");
    		 ticketRequestById.get().setAutoPay(false);
    	 }
    	 
            model.addAttribute("modelTicketRequest_ATTM", ticketRequestById);
            
            List<ModelCountry> countryList = countryRepository.findCountryInOrder();
    		model.addAttribute("countryList", countryList);
    		
    		List<ModelAirLine> airLineList= airLineRepository.getAirLineName();
    		model.addAttribute("airLineList",airLineList);
    		
    		List<ModelAgent> agentList= agentRepository.getAgentName();
    		model.addAttribute("agentList",agentList);
    		
    		List<ModelTicketOwnerCompany> ticketOwnerCompanyList= ticketOwnerCompanyRepository.findTicketOwnerCompanyName();
    		model.addAttribute("ticketOwnerCompanyList",ticketOwnerCompanyList);
    		
    		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
    		model.addAttribute("userList", modelBgUser);
    		
    		List<ModelCity> modelCity = daoCity.getCityInOrder();
   		  	model.addAttribute("cityList", modelCity);
    		
    		if(id!=0) {
            	String msg="Successfully Saved";
            	 model.addAttribute("message",msg );
            }
            else
            {
            	String msg="Some Error Occured";
            	 model.addAttribute("message",msg);
            }
    		
    		
    		
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
    	
        return "ticketRequest";

    }
	
	@RequestMapping(path = "/ticketrequestcontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateTicketRequest(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
    	 Optional<ModelTicketRequest> ticketRequestById;
    	 ticketRequestById=ticketRequestRepository.findById(id);
    	 if( ticketRequestById.get().getActiveStatus()==1)
    		 ticketRequestById.get().setActive(true); 
    		else
    			ticketRequestById.get().setActive(false);
    	 if(ticketRequestById.get().getpOFlagStatus()==1) 
			{
 		 ticketRequestById.get().setsPOFlag("Yes");
 		 ticketRequestById.get().setpOFlag(true);
			}
			else 
			{
				ticketRequestById.get().setsPOFlag("No");
				ticketRequestById.get().setpOFlag(false);
			}
    	 
    	 if(ticketRequestById.get().getAutoPayment()==1) 
    	 {
    		 ticketRequestById.get().setsAutoPayment("Yes");
    		 ticketRequestById.get().setAutoPay(true);
    		 
    	 }
    	 else {
    		 
    		 ticketRequestById.get().setsAutoPayment("No");
    		 ticketRequestById.get().setAutoPay(false);
    	 }
    	 
            model.addAttribute("modelTicketRequest_ATTM", ticketRequestById);
            
            List<ModelCountry> countryList = countryRepository.findCountryInOrder();
    		model.addAttribute("countryList", countryList);
    		
    		List<ModelAirLine> airLineList= airLineRepository.getAirLineName();
    		model.addAttribute("airLineList",airLineList);
    		
    		List<ModelTicketOwnerCompany> ticketOwnerCompanyList= ticketOwnerCompanyRepository.findTicketOwnerCompanyName();
    		model.addAttribute("ticketOwnerCompanyList",ticketOwnerCompanyList);
    		
    		List<ModelAgent> agentList= agentRepository.getAgentName();
    		model.addAttribute("agentList",agentList);
    		
    		List<ModelAgent> agentList2= agentRepository.findAgentNameBySelfFalg();
    		model.addAttribute("agentList2",agentList2);
    		
    		List<ModelBgUser> modelBgUser = bgUserRepository.getUserNameInOrder();
    		model.addAttribute("userList", modelBgUser);
    		
    		List<ModelCity> modelCity = daoCity.getCityInOrder();
   		  	model.addAttribute("cityList", modelCity);
    		
            if(id!=0) {
            	String msg=" ";
            	 model.addAttribute("message",msg );
            }
            else
            {
            	String msg="Some Error Occured";
            	 model.addAttribute("message",msg);
            }
            
            
            
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
    	
        return "ticketRequest";

    }
	
	@ResponseBody
	@RequestMapping(path = "/ticketrequestcontroller/saveticketrequestcancel", method = RequestMethod.POST) 
	public List<ModelTicketRequestCancel> saveTicketRequestCancel(ModelTicketRequest modelTicketRequest,
			ModelTicketRequestCancel modelTicketRequestCancel,ModelCity modelCity,
			@RequestParam("ticketRequestCancelId") Long ticketRequestCancelId,
			@RequestParam("ticketRequestId") Long ticketRequestId,
			@RequestParam("cancelDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date cancelDate,
			@RequestParam("cancelQty") Double cancelQty,
			@RequestParam("cancelReason") String cancelReason,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks
			) {
	
		System.out.println("IN ticketrequestcontroller saveticketrequestcancel Method");
		
		try {
			
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			if(ticketRequestCancelId==null) 
			{
			
			modelTicketRequestCancel.setCancelDate(cancelDate);
			modelTicketRequestCancel.setCancelQty(cancelQty);
			modelTicketRequestCancel.setCancelReason(cancelReason);
			Optional<ModelTicketRequest> existsTicketRequest=ticketRequestRepository.findById(ticketRequestId);
			modelTicketRequest.setTicketRequestId(existsTicketRequest.get().getTicketRequestId());
			modelTicketRequest.setRequestDate(existsTicketRequest.get().getRequestDate());
			modelTicketRequest.setModelUser_ATTM(existsTicketRequest.get().getModelUser_ATTM());
			modelTicketRequest.setActiveStatus(0);
			modelTicketRequest.setpOFlagStatus(existsTicketRequest.get().getpOFlagStatus());
			modelTicketRequest.setAgreementAMT(existsTicketRequest.get().getAgreementAMT());
			modelTicketRequest.setApproxAMT(existsTicketRequest.get().getApproxAMT());
			//modelCity.setCityId(existsTicketRequest.get().getFromCity().getCityId());
			//modelTicketRequest.setCityFrom(existsTicketRequest.get().getFromCity());
			modelTicketRequest.setFromCity(existsTicketRequest.get().getFromCity());
			//modelTicketRequest.setCityTo(existsTicketRequest.get().getCityTo());
			modelTicketRequest.setToCity(existsTicketRequest.get().getToCity());
			modelTicketRequest.setCountryFrom(existsTicketRequest.get().getCountryFrom());
			modelTicketRequest.setCountryTo(existsTicketRequest.get().getCountryTo());
			modelTicketRequest.setModelAgent(existsTicketRequest.get().getModelAgent());
			modelTicketRequest.setModelAirLine(existsTicketRequest.get().getModelAirLine());
			modelTicketRequest.setNoOfTickets(existsTicketRequest.get().getNoOfTickets());
			modelTicketRequest.setpORemarks(existsTicketRequest.get().getpORemarks());
			modelTicketRequest.setPossibleFlightDate(existsTicketRequest.get().getPossibleFlightDate());
			modelTicketRequest.setRemarks(existsTicketRequest.get().getRemarks());
			modelTicketRequest.setRequesterContactPhone(existsTicketRequest.get().getRequesterContactPhone());
			modelTicketRequest.setRequesterId(existsTicketRequest.get().getRequesterId());
			modelTicketRequest.setRequesterName(existsTicketRequest.get().getRequesterName());
			modelTicketRequestCancel.setModelTicketRequest(modelTicketRequest);
			if(activeStatus==1) 
			{
				modelTicketRequestCancel.setActive(true);
				modelTicketRequestCancel.setsActive("Yes");
			}
			else 
			{
				modelTicketRequestCancel.setActive(false);
				modelTicketRequestCancel.setsActive("No");
			}
			modelTicketRequestCancel.setRemarks(remarks);
			modelTicketRequestCancel.setEnteredBy(systemUserId);
			modelTicketRequestCancel.setEntryTimeStamp(entryTime);
		
		
			daoTicketRequestCancel.saveTicketRequestCancel(modelTicketRequestCancel);
			daoTicketRequest.saveTicketRequest(modelTicketRequest);
		}
		else {
		System.out.println("ticketRequestCancel Id : " + ticketRequestCancelId);
				
		modelTicketRequestCancel.setCancelDate(cancelDate);
		modelTicketRequestCancel.setCancelQty(cancelQty);
		modelTicketRequestCancel.setCancelReason(cancelReason);
		modelTicketRequest.setTicketRequestId(ticketRequestId);
		modelTicketRequest.setActiveStatus(0);
		modelTicketRequestCancel.setModelTicketRequest(modelTicketRequest);
		if(activeStatus==1) 
		{
			modelTicketRequestCancel.setActive(true);
			modelTicketRequestCancel.setsActive("Yes");
		}
		else 
		{
			modelTicketRequestCancel.setActive(false);
			modelTicketRequestCancel.setsActive("No");
		}
		modelTicketRequestCancel.setRemarks(remarks);
		modelTicketRequestCancel.setUpdatedBy(systemUserId);
		modelTicketRequestCancel.setUpdateTimeStamp(entryTime);
		modelTicketRequestCancel.setTicketRequestCancelId(ticketRequestCancelId);
	
		daoTicketRequestCancel.saveTicketRequestCancel(modelTicketRequestCancel);
		daoTicketRequest.saveTicketRequest(modelTicketRequest);
		}
			
		List<ModelTicketRequestCancel> modelTicketRequestCancelList = daoTicketRequestCancel.getCancelById(modelTicketRequestCancel.getTicketRequestCancelId());
		System.out.println("Size : " + modelTicketRequestCancelList.size());
   	 		

	 return modelTicketRequestCancelList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	
}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketrequestcontroller/getcitybycountryid")	
	public List<ModelCity> getCityByCountryId(@RequestParam("countryId") Long countryId){
		
		
		
		List<ModelCity> modelCity = daoCity.getCityByCountryId(countryId);
		System.out.println("Size: "+modelCity.size());
		
		return modelCity;
	}
	

}
