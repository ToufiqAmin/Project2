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
import com.biziitech.attm.bg.repository.TicketOwnerCompanyRepository;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoTicketOwnerCompany;
import com.biziitech.attm.model.ModelTicketOwnerCompany;

@Controller
public class TicketOwnerCompanyController {
	
	@Autowired
	private TicketOwnerCompanyRepository ticketOwnerCompanyRepository;
	
	@Autowired
	private DaoTicketOwnerCompany daoTicketOwnerCompany;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@RequestMapping(path="/ticketownercompanycontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelTicketOwnerCompany modelTicketOwnerCompany =new ModelTicketOwnerCompany();
		modelTicketOwnerCompany.setActive(true);
		model.addAttribute("modelTicketOwnerCompany", modelTicketOwnerCompany);
		
		 
		String msg=" ";
		model.addAttribute("message",msg );
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		 // String userName=logonUser.getUserName();
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
		 
		 return "ticketOwnerCompany";
	}
	
	@RequestMapping(path = "/ticketownercompanycontroller/saveticketownercompany/{userId}", method = RequestMethod.POST) 
   	public  String saveTicketOwnerCompany(@PathVariable Long userId,ModelTicketOwnerCompany modelTicketOwnerCompany, Model model) {
   	
   	//System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
   	if(modelTicketOwnerCompany.getTicketOwnerCompanyId()==null )
   	{
   		modelTicketOwnerCompany.setEnteredBy(userId);
   		modelTicketOwnerCompany.setEntryTimeStamp(entryTime);
   		
   		daoTicketOwnerCompany.saveTicketOwnerCompany(modelTicketOwnerCompany);
    }
   	else
   	{
   		Optional<ModelTicketOwnerCompany> existsTicketOwnerCompany=ticketOwnerCompanyRepository.findById(modelTicketOwnerCompany.getTicketOwnerCompanyId());
   
   		modelTicketOwnerCompany.setEnteredBy(existsTicketOwnerCompany.get().getEnteredBy());
   		modelTicketOwnerCompany.setEntryTimeStamp(existsTicketOwnerCompany.get().getEntryTimeStamp());	
   		modelTicketOwnerCompany.setUpdatedBy(userId);
   		modelTicketOwnerCompany.setUpdateTimeStamp(entryTime);
   		daoTicketOwnerCompany.saveTicketOwnerCompany(modelTicketOwnerCompany);

   	}
   	

   
	 return "redirect:/ticketownercompanycontroller/editticketownercompany/"+modelTicketOwnerCompany.getTicketOwnerCompanyId()+"/"+userId;
   	
   }
	
	@RequestMapping(path = "/ticketownercompanycontroller/editticketownercompany/{id}/{userId}", method = RequestMethod.GET)
    public String editTicketOwnerCompany(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
     System.out.println("TicketOwnerCompany ID :" +id);
    	   
     Optional<ModelTicketOwnerCompany> ticketOwnerCompanyById;
     ticketOwnerCompanyById=ticketOwnerCompanyRepository.findById(id);
	 if( ticketOwnerCompanyById.get().getActiveStatus()==1)
		 ticketOwnerCompanyById.get().setActive(true); 
     else
    	 ticketOwnerCompanyById.get().setActive(false);	
     model.addAttribute("modelTicketOwnerCompany", ticketOwnerCompanyById);
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
               
        return "ticketOwnerCompany";
}
	
	@RequestMapping(path = "/ticketownercompanycontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateTicketOwnerCompany(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
		 Optional<ModelTicketOwnerCompany> ticketOwnerCompanyById;
	     ticketOwnerCompanyById=ticketOwnerCompanyRepository.findById(id);
    	 if( ticketOwnerCompanyById.get().getActiveStatus()==1)
    		 ticketOwnerCompanyById.get().setActive(true); 
    		else
    			ticketOwnerCompanyById.get().setActive(false);	
            model.addAttribute("modelTicketOwnerCompany", ticketOwnerCompanyById);
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
    	
        return "ticketOwnerCompany";

    }

}
