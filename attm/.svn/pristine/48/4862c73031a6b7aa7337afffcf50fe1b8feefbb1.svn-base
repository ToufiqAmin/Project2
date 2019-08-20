package com.biziitech.attm.bg.controller;

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
import com.biziitech.attm.bg.dao.DaoUserType_attm;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.model.ModelUserType;
import com.biziitech.attm.bg.repository.BgUserTypeRepository;
import com.biziitech.attm.dao.DaoLogon;





@Controller
public class BgUserTypeController {
	
	@Autowired
	private DaoUserType_attm daoUserType_attm;
	
	@Autowired
	private BgUserTypeRepository attm_BgUserTypeRepository;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@RequestMapping(path="/bgusertypecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		
		 ModelUserType modelUserType=new ModelUserType();
		 model.addAttribute("modelUserType_ATTM", modelUserType);
		 
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
		 
		 return "bgUserType";
	}
	
	@RequestMapping(path = "/bgusertypecontroller/saveusertype/{id}", method = RequestMethod.POST) 
   	public  String saveUserType(ModelUserType modelUserType,@PathVariable Long id, Model model) {
   	
   	System.out.println("in save user type method");
   	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
   	if(modelUserType.getUserTypeId()==null )
   	{
   			
   		modelUserType.setEntryTimestamp(entryTime);
   		modelUserType.setEnteredBy(id);
   		daoUserType_attm.saveUserType_ATTM(modelUserType);
   		String msg="Successfully Saved";
   		model.addAttribute("message",msg );
    }
   	else
   	{
   				Optional<ModelUserType> existsUserType=attm_BgUserTypeRepository.findById(modelUserType.getUserTypeId());
   				modelUserType.setEnteredBy(existsUserType.get().getEnteredBy());
   				modelUserType.setEntryTimestamp(existsUserType.get().getEntryTimestamp());
   				modelUserType.setUpdatedBy(id);
   				modelUserType.setEntryTimestamp(entryTime);
   				
   				daoUserType_attm.saveUserType_ATTM(modelUserType);
   				String msg="Successfully Saved";
   				model.addAttribute("message",msg );
   	}
   	
   
   	 return "redirect:/bgusertypecontroller/add/"+modelUserType.getUserTypeId()+"/"+id;
   	
   }
	
	
	@RequestMapping(path = "/bgusertypecontroller/add/{id}/{userId}", method = RequestMethod.GET)
    public String editBgUserType(@PathVariable Long id,@PathVariable Long userId, Model model) {
    	
    	 Optional<ModelUserType> bgUserTypeById;
    	 bgUserTypeById=attm_BgUserTypeRepository.findById(id);
    	 if( bgUserTypeById.get().getActiveStatus()==1)
    		 bgUserTypeById.get().setActive(true); 
    		else
    			bgUserTypeById.get().setActive(false);	
            model.addAttribute("modelUserType_ATTM", bgUserTypeById);
            
            ModelUser logonUser=daoLogon.getLogonUserName(userId);
  		  
  		  //String userName=logonUser.getUserName();
            String userName=logonUser.getLoginUser();
  		  
  		  System.out.println("logon user name is :" + userName);
  		  
  		  String name="User : " + userName;
  		  model.addAttribute("name",name );	
  		  
  		  model.addAttribute("userId",userId);
            
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
    	
        return "bgUserType";

    }
	
	@RequestMapping(path = "/bgusertypecontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateBgUserType(@PathVariable Long id,@PathVariable Long userId, Model model) {
    	
    	 Optional<ModelUserType> bgUserTypeById;
    	 bgUserTypeById=attm_BgUserTypeRepository.findById(id);
    	 if( bgUserTypeById.get().getActiveStatus()==1)
    		 bgUserTypeById.get().setActive(true); 
    		else
    			bgUserTypeById.get().setActive(false);	
            model.addAttribute("modelUserType_ATTM", bgUserTypeById);
            
            ModelUser logonUser=daoLogon.getLogonUserName(userId);
    		  
    		  //String userName=logonUser.getUserName();
            String userName=logonUser.getLoginUser();
    		  
    		  System.out.println("logon user name is :" + userName);
    		  
    		  String name="User : " + userName;
    		  model.addAttribute("name",name );	
    		  
    		  model.addAttribute("userId",userId);
    		  
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
    	
        return "bgUserType";

    }

}
