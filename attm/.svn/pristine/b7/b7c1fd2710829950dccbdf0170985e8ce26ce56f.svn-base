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

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.ServiceRepository;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoService;
import com.biziitech.attm.model.ModelService;





@Controller
public class ServiceController {
	
	@Autowired
	private DaoService daoService;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	
	@RequestMapping(path="/servicecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser(); 
		
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		
		
		ModelService modelService =new ModelService();
		
		 model.addAttribute("modelService", modelService);
		 
		 String msg=" ";
	     model.addAttribute("message", msg);
	     
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
		 
		 return "service";
	}
	
	@RequestMapping(path = "/servicecontroller/saveservice/{userId}", method = RequestMethod.POST) 
   	public  String saveService(@PathVariable Long userId,ModelService modelService, Model model) {
   	
   	//System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
   	if(modelService.getServiceId()==null )
   	{
   		modelService.setEntryTimeStamp(entryTime);
   		modelService.setEnteredBy(userId);
   		
   		
   		daoService.saveService(modelService);
   		String msg="Successfully Saved";
   		model.addAttribute("message",msg );
    }
   	else
   	{
   				Optional<ModelService> existService=serviceRepository.findById(modelService.getServiceId());
   				modelService.setEnteredBy(existService.get().getEnteredBy());
   				modelService.setEntryTimeStamp(existService.get().getEntryTimeStamp());
   				modelService.setUpdatedBy(userId);
   				modelService.setUpdateTimeStamp(entryTime);
   				
   				daoService.saveService(modelService);
   				String msg="Successfully Saved";
   				model.addAttribute("message",msg );
   	}
   	
  
   
   	return "redirect:/servicecontroller/editservice/"+modelService.getServiceId()+"/"+userId;
   	
   }
	
	
	@RequestMapping(path = "/servicecontroller/editservice/{id}/{userId}", method = RequestMethod.GET)
    public String editService(@PathVariable Long userId,@PathVariable Long id, Model model) {
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser(); 
		
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
    
     Optional<ModelService> serviceById;
     serviceById=serviceRepository.findById(id);
	 if( serviceById.get().getActiveStatus()==1)
		 serviceById.get().setActive(true); 
     else
    	 serviceById.get().setActive(false);
	 if( serviceById.get().getPassportRequired()==1)
		 serviceById.get().setPassport(true);
		else
			serviceById.get().setPassport(false);
     model.addAttribute("modelService", serviceById);
        if(id!=0) {
        	String msg="Successfully Saved";
        	 model.addAttribute("message",msg );
        }
        else
        {
        	String msg="Some Error Occured";
        	 model.addAttribute("message",msg);
        }
        
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
               
        return "service";
}
	
	   @RequestMapping(path = "/servicecontroller/update/{id}/{userId}", method = RequestMethod.GET)
	    public String updateService(@PathVariable Long userId,@PathVariable Long id, Model model) {
	    	
		   ModelUser logonUser=daoLogon.getLogonUserName(userId);
			  
			  //String userName=logonUser.getUserName();
		   String userName=logonUser.getLoginUser(); 
		   
			  System.out.println("logon user name is :" + userName);
			  
			  String name="User : " + userName;
			  model.addAttribute("name",name );	
			  
			  model.addAttribute("userId",userId);
		   
		   Optional<ModelService> serviceById;
		     serviceById=serviceRepository.findById(id);
	    	 if( serviceById.get().getActiveStatus()==1)
	    		 serviceById.get().setActive(true); 
	    		else
	    		serviceById.get().setActive(false);
	    	 if( serviceById.get().getPassportRequired()==1)
	    		 serviceById.get().setPassport(true);
	    		else
	    			serviceById.get().setPassport(false);
	    	 model.addAttribute("modelService", serviceById);
	            if(id!=0) {
	            	String msg=" ";
	            	 model.addAttribute("message",msg );
	            }
	            else
	            {
	            	String msg="Some Error Occured";
	            	 model.addAttribute("message",msg);
	            }
	            
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
	    	
	        return "service";

	    } 	
	
	

}
