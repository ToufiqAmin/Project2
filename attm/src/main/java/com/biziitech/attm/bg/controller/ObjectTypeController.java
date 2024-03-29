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
import com.biziitech.attm.bg.daoimp.DaoObjectTypeImp;
import com.biziitech.attm.bg.model.ModelObjectType;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.ObjectTypeRepository;
import com.biziitech.attm.daoImp.DaoLogonImp;



@Controller
public class ObjectTypeController {
	
	
	@Autowired
	private DaoObjectTypeImp daoObjectTypeImp;

	@Autowired 
	private ObjectTypeRepository objectTypeRepository;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogonImp daoLogonImp;


    private Long systemUserId;
	
	 @RequestMapping(path = "/objecttype/init/{userId}", method = RequestMethod.GET)
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
		 
		 
		 ModelObjectType objectType=new ModelObjectType();
		 model.addAttribute("objectType", objectType);
		 
		 String msg=" ";
	     model.addAttribute("message", msg);
	        
	     return "object_Type";

	}
	 
 @RequestMapping(path = "/objectType/save", method = RequestMethod.POST) 
 	public  String saveObjectType(ModelObjectType objectType) {
 	
 	if(objectType.getObjectTypeId()==null )
 			{
 			java.util.Date date = new java.util.Date();
 			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
 			    objectType.setEntryTimestamp(entryTime);
 			    objectType.setEnteredBy(1L);
 				daoObjectTypeImp.saveObjectType(objectType);
 			}
 			else
 			{
 				Optional<ModelObjectType> existsobjecttype=objectTypeRepository.findById(objectType.getObjectTypeId());
 				objectType.setEnteredBy(existsobjecttype.get().getEnteredBy());
 				objectType.setEntryTimestamp(existsobjecttype.get().getEntryTimestamp());
 				
 				daoObjectTypeImp.saveObjectType(objectType);
 			}
 
 	 return "redirect:/objecttypecontroller/editobjecttype/"+objectType.getObjectTypeId();
 	
 }
 
 
 @RequestMapping(path = "/objecttypecontroller/editobjecttype/{id}", method = RequestMethod.GET)
 public String editobjecttype(@PathVariable Long id, Model model) {
 
  Optional<ModelObjectType> objectTypeById;
  objectTypeById=objectTypeRepository.findById(id);
	 if( objectTypeById.get().getActiveStatus()==1)
		 objectTypeById.get().setActive(true); 
		else
			objectTypeById.get().setActive(false);	
            model.addAttribute("objectType", objectTypeById);
     if(id!=0) {
     	String msg="Successfully Saved";
     	 model.addAttribute("message",msg );
     }
     else
     {
     	String msg="Some Error Occured";
     	 model.addAttribute("message",msg);
     }
     
     System.out.println("user ID :" +systemUserId);
     Long userId=systemUserId;
	 ModelUser modelUser=new ModelUser();
	 modelUser.setUserId(userId);
	 //this.systemUserId=userId;
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
            
     return "object_type";
}
 
 
 @RequestMapping(path = "/objecttype/update/{id}", method = RequestMethod.GET)
 public String updateobjecttype(@PathVariable Long id, Model model) {
	 
	 
	 
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
	 
 	
 	 Optional<ModelObjectType> objectTypeById;
 	  objectTypeById=objectTypeRepository.findById(id);
 	 if( objectTypeById.get().getActiveStatus()==1)
 		 objectTypeById.get().setActive(true); 
 		else
 		 objectTypeById.get().setActive(false);	
         model.addAttribute("objectType", objectTypeById);
         if(id!=0) {
         	String msg=" ";
         	 model.addAttribute("message",msg );
         }
         else
         {
         	String msg="Some Error Occured";
         	 model.addAttribute("message",msg);
         }
 	
     return "object_type";

 } 	

}
