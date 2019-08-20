package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biziitech.attm.bg.dao.DaoCity;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.ClientServiceRepository;
import com.biziitech.attm.bg.repository.ClientTypeRepository;
import com.biziitech.attm.bg.repository.CountryRepository;
import com.biziitech.attm.bg.repository.ServiceRepository;
import com.biziitech.attm.dao.DaoClientService;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelClientService;
import com.biziitech.attm.model.ModelClientType;
import com.biziitech.attm.model.ModelService;



@Controller
public class ClientServiceController {
	
	@Autowired
	private ClientServiceRepository clientServiceReposiotry;
	
	@Autowired
	private DaoClientService daoClientService;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoCity daoCity;
	
	
	@RequestMapping(path="/clientservicecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser();
		  
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
		
		ModelClientService modelClientService =new ModelClientService();	
		model.addAttribute("modelClientService", modelClientService);
		
		List<ModelCountry> countryList = countryRepository.findCountryInOrder();
		model.addAttribute("countryList", countryList);
		
		List<ModelClientType> clientTypeList = clientTypeRepository.findClientTypeInOrder();
		model.addAttribute("clientTypeList",clientTypeList);
		
		List<ModelService> serviceList = serviceRepository.getServiceInOrder();
		model.addAttribute("serviceList", serviceList);
		 
		 return "clientService";
	}
	
	@ResponseBody
	@RequestMapping(path="/clientservicecontroller/checkingclienttype/individual")	
	public List<ModelBgUser> individual(){
		
		
		
		List<ModelBgUser> modelUser = bgUserRepository.getUserNameInOrder();
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	@ResponseBody
	@RequestMapping(path="/clientservicecontroller/checkingclienttype/agent")	
	public List<ModelAgent> agent(){
		
		
		
		List<ModelAgent> modelAgent = agentRepository.getAgentName();
		System.out.println("Size: "+modelAgent.size());
		
		return modelAgent;
	}
	
	@RequestMapping(path = "/clientservicecontroller/saveclientservice/{userId}", method = RequestMethod.POST) 
   	public  String saveClientService(@PathVariable Long userId,
   			ModelClientService modelClientService, Model model) {
   	
   	//System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
   	if(modelClientService.getClientServiceId()==null )
   	{
   		modelClientService.setEntryTimeStamp(entryTime);
   		modelClientService.setEnteredBy(userId);
   		
   		
   		daoClientService.saveClientService(modelClientService);
   		String msg="Successfully Saved";
   		model.addAttribute("message",msg );
    }
   	else
   	{
   				Optional<ModelClientService> existsClientService=clientServiceReposiotry.findById(modelClientService.getClientServiceId());
   				modelClientService.setEnteredBy(existsClientService.get().getEnteredBy());
   				modelClientService.setEntryTimeStamp(existsClientService.get().getEntryTimeStamp());
   				modelClientService.setUpdatedBy(userId);
   				modelClientService.setUpdateTimeStamp(entryTime);
   				
   				daoClientService.saveClientService(modelClientService);
   				String msg="Successfully Saved";
   				model.addAttribute("message",msg );
   	}
   	
   	
   
   	 return "redirect:/clientservicecontroller/init/"+modelClientService.getClientServiceId()+"/"+userId;
   	
   }
	
	@RequestMapping(path = "/clientservicecontroller/init/{id}/{userId}", method = RequestMethod.GET)
    public String editClientService(@PathVariable Long userId,@PathVariable Long id, Model model) {
    
     Optional<ModelClientService> clientServiceById;
     clientServiceById=clientServiceReposiotry.findById(id);
	 if( clientServiceById.get().getActiveStatus()==1)
		 clientServiceById.get().setActive(true); 
     else
    	 clientServiceById.get().setActive(false);
	
     model.addAttribute("modelClientService", clientServiceById);
        if(id!=0) {
        	String msg="Successfully Saved";
        	 model.addAttribute("message",msg );
        }
        else
        {
        	String msg="Some Error Occured";
        	 model.addAttribute("message",msg);
        }
        
        List<ModelCountry> countryList = countryRepository.findCountryInOrder();
		model.addAttribute("countryList", countryList);
		
		List<ModelClientType> clientTypeList = clientTypeRepository.findClientTypeInOrder();
		model.addAttribute("clientTypeList",clientTypeList);
		
		List<ModelService> serviceList = serviceRepository.getServiceInOrder();
		model.addAttribute("serviceList", serviceList);
		
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser();
		  
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
               
        return "clientService";
}
	
	@RequestMapping(path = "/clientservicecontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateClientService(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
    	 Optional<ModelClientService> clientServiceById;
    	 clientServiceById=clientServiceReposiotry.findById(id);
    	 if( clientServiceById.get().getActiveStatus()==1)
    		 clientServiceById.get().setActive(true); 
    		else
    			clientServiceById.get().setActive(false);	
            model.addAttribute("modelClientService", clientServiceById);
            
            List<ModelCountry> countryList = countryRepository.findCountryInOrder();
    		model.addAttribute("countryList", countryList);
    		
    		List<ModelClientType> clientTypeList = clientTypeRepository.findClientTypeInOrder();
    		model.addAttribute("clientTypeList",clientTypeList);
    		
    		List<ModelService> serviceList = serviceRepository.getServiceInOrder();
    		model.addAttribute("serviceList", serviceList);
    		
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
  		  
  		  //String userName=logonUser.getUserName();
            String userName=logonUser.getLoginUser();
  		  
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
    	
        return "clientService";

    }
	
	
	@ResponseBody
	@RequestMapping(path="/clientservicecontroller/getcitybycountryid")	
	public List<ModelCity> getCityByCountryId(@RequestParam("countryId") Long countryId){
		
		
		
		List<ModelCity> modelCity = daoCity.getCityByCountryId(countryId);
		System.out.println("Size: "+modelCity.size());
		
		return modelCity;
	}
	

}
