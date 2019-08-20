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
import com.biziitech.attm.bg.daoimp.DaoObjectGroupImp;
import com.biziitech.attm.bg.daoimp.DaoSystemImp;
import com.biziitech.attm.bg.model.ModelObjectGroup;
import com.biziitech.attm.bg.model.ModelSystem;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.ObjectGroupRepository;
import com.biziitech.attm.daoImp.DaoLogonImp;




@Controller
public class ObjectGroupController {
	
	  @Autowired
	  private DaoSystemImp daoSystemImp;
	  
	  @Autowired
	  private DaoObjectGroupImp daoObjectGroupImp;
	  
	  @Autowired 
	   private ObjectGroupRepository objectGroupRepository;
	  
	  @Autowired
		private DaoUserObject daoUserObject;
		
		@Autowired
		private DaoLogonImp daoLogonImp;


	private Long systemUserId;

	 @RequestMapping(path = "/objectgroup/init/{userId}", method = RequestMethod.GET)
	    public String createUom(@PathVariable Long userId,Model model) {
		 
		 System.out.println("user ID :" +userId);
		 ModelUser modelUser=new ModelUser();
		 modelUser.setUserId(userId);
		 this.systemUserId=userId;
		 ModelUser logonUser=daoLogonImp.getLogonUserName(userId);
		 String userName=logonUser.getUserName();
		 System.out.println("logon user name is :" + userName);
		 String name=userName;
		 model.addAttribute("name",name );	  
			
		 // left panel list
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
			    
				//left panel list
		 
		 ModelObjectGroup objectGroup=new ModelObjectGroup();
		 model.addAttribute("newobjectgroup", objectGroup);
		 
		 List<ModelSystem> systemList= daoSystemImp.getSystemList();
		 model.addAttribute("systemList", systemList);
		 
		 String msg=" ";
	     model.addAttribute("message", msg);
	        
	     return "objectGroup";

	}
	 
	   @RequestMapping(path = "/objectgroup/save", method = RequestMethod.POST) 
	   public  String saveObjectGroupData(ModelObjectGroup objectGroup) {
	       if(objectGroup.getObjectGroupId()==null)
			{
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			    objectGroup.setEntryTimestamp(entryTime);
				objectGroup.setEnteredBy(1L);
				daoObjectGroupImp.saveObjectGroup(objectGroup);
			}
			else
			{
				Optional<ModelObjectGroup> existsobjectgroup=objectGroupRepository.findById(objectGroup.getObjectGroupId());
				objectGroup.setEnteredBy(existsobjectgroup.get().getEnteredBy());
				objectGroup.setEntryTimestamp(existsobjectgroup.get().getEntryTimestamp());
				
				daoObjectGroupImp.saveObjectGroup(objectGroup);
			}
	
	   return "redirect:/objectgroupcontroller/editobjectgroupdata/"+objectGroup.getObjectGroupId();
	
   }
	   
	   
	   @RequestMapping(path = "/objectgroupcontroller/editobjectgroupdata/{id}", method = RequestMethod.GET)
	    public String editobjectgroupdata(@PathVariable Long id, Model model) {
	    
		   
		   System.out.println("system user :" + systemUserId);
		    Long userId=systemUserId;
		    
		     ModelUser logonUser=daoLogonImp.getLogonUserName(userId);
			 String userName=logonUser.getUserName();
			 System.out.println("logon user name is :" + userName);
			 String name=userName;
			 model.addAttribute("name",name );
			 model.addAttribute("userId",userId);
		    // left panel list
		   
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
				//left panel list
		   
		   
		   
		   List<ModelSystem> systemList= daoSystemImp.getSystemList();
		   model.addAttribute("systemList", systemList);
			 

	    	 
	     Optional<ModelObjectGroup> objectGroupById;
	     objectGroupById=objectGroupRepository.findById(id);
		 if( objectGroupById.get().getActiveStatus()==1)
			 objectGroupById.get().setActive(true); 
			else
				objectGroupById.get().setActive(false);	
	            model.addAttribute("newobjectgroup", objectGroupById);
	        if(id!=0) {
	        	String msg="Successfully Saved";
	        	 model.addAttribute("message",msg );
	        }
	        else
	        {
	        	String msg="Some Error Occured";
	        	 model.addAttribute("message",msg);
	        }
	               
	        return "objectGroup";
	}
	   
	   @RequestMapping(path = "/objectgroup/update/{id}", method = RequestMethod.GET)
	    public String updateobjectgroupdata(@PathVariable Long id, Model model) {
		   
		   
		   System.out.println("system user :" + systemUserId);
		    Long userId=systemUserId;
		    
		     ModelUser logonUser=daoLogonImp.getLogonUserName(userId);
			 String userName=logonUser.getUserName();
			 System.out.println("logon user name is :" + userName);
			 String name=userName;
			 model.addAttribute("name",name );
			 model.addAttribute("userId",userId);
		    // left panel list
		   
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
				//left panel list
	    
		   List<ModelSystem> systemList= daoSystemImp.getSystemList();
		   model.addAttribute("systemList", systemList);
	    	 
	     Optional<ModelObjectGroup> objectGroupById;
	     objectGroupById=objectGroupRepository.findById(id);
		 if( objectGroupById.get().getActiveStatus()==1)
			 objectGroupById.get().setActive(true); 
			else
				objectGroupById.get().setActive(false);	
	            model.addAttribute("newobjectgroup", objectGroupById);
	        if(id!=0) {
	        	
	       
	        }
	        else
	        {
	        	String msg="Some Error Occured";
	        	 model.addAttribute("message",msg);
	        }
	               
	        return "objectGroup";
	}
	
}
