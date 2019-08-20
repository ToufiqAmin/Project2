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
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.dao.DaoAgent;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.model.ModelAgent;





@Controller
public class AgentController {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private DaoAgent daoAgent;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@RequestMapping(path="/agentcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelAgent modelAgent_ATTM =new ModelAgent();
		
		 model.addAttribute("modelAgent_ATTM", modelAgent_ATTM);
		
		String msg=" ";
		model.addAttribute("message",msg );
		
		
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
		 
		 return "agent";
	}
	
	@RequestMapping(path = "/agentcontroller/saveagent/{userId}", method = RequestMethod.POST) 
   	public  String saveAgent(@PathVariable Long userId,ModelAgent modelAgent_ATTM, Model model) {
   	
   	//System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
   	if(modelAgent_ATTM.getAgentId()==null )
   	{
   			
   		
   		daoAgent.saveATTM_Agent(modelAgent_ATTM);
   		String msg="Successfully Saved";
   		model.addAttribute("message",msg );
    }
   	else
   	{
   				Optional<ModelAgent> existsAgent=agentRepository.findById(modelAgent_ATTM.getAgentId());
   
   				
   				daoAgent.saveATTM_Agent(modelAgent_ATTM);
   				String msg="Successfully Saved";
   				model.addAttribute("message",msg );
   	}
   	
//   	ModelAgent modelAgent_ATTM2 =new ModelAgent();
//	
//	 model.addAttribute("modelAgent_ATTM", modelAgent_ATTM2);
   
   	return "redirect:/agentcontroller/editagent/"+modelAgent_ATTM.getAgentId()+"/"+userId;
   	
   }
	
	@RequestMapping(path = "/agentcontroller/editagent/{id}/{userId}", method = RequestMethod.GET)
    public String editAgent(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
    	 System.out.println("Agent ID :" +id);
    	 
    	 
    	 
   
     Optional<ModelAgent> agentById;
     agentById=agentRepository.findById(id);
	 if( agentById.get().getActiveStatus()==1)
		 agentById.get().setActive(true); 
     else
    	 agentById.get().setActive(false);
	 if(agentById.get().getSelfFlagStatus()==1)
	 {
		 agentById.get().setSelfFlag(true);
	 }
	 else 
	 {
		 agentById.get().setSelfFlag(false);
	 }
     model.addAttribute("modelAgent_ATTM", agentById);
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
               
        return "agent";
}

	
	@RequestMapping(path = "/agentcontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateAgent(@PathVariable Long userId,@PathVariable Long id, Model model) {
    	
    	 Optional<ModelAgent> agentById;
    	 agentById=agentRepository.findById(id);
    	 if( agentById.get().getActiveStatus()==1)
    		 agentById.get().setActive(true); 
    		else
    			agentById.get().setActive(false);	
            model.addAttribute("modelAgent_ATTM", agentById);
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
    	
        return "agent";

    }

}
