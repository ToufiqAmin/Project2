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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biziitech.attm.bg.daoimp.DaoModuleImp;
import com.biziitech.attm.bg.daoimp.DaoObjectGroupImp;
import com.biziitech.attm.bg.daoimp.DaoObjectImp;
import com.biziitech.attm.bg.daoimp.DaoObjectProcessImp;
import com.biziitech.attm.bg.daoimp.DaoObjectTypeImp;
import com.biziitech.attm.bg.daoimp.DaoSystemImp;
import com.biziitech.attm.bg.model.ModelModule;
import com.biziitech.attm.bg.model.ModelObject;
import com.biziitech.attm.bg.model.ModelSystem;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.daoImp.DaoLogonImp;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.SystemRepository;

@Controller
public class ObjectController {
	
	
	@Autowired
	private DaoSystemImp daoSystemImp;
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Autowired
	private DaoModuleImp daoModuleImp;
	
	
	@Autowired
	private DaoObjectImp daoObjectImp;
	
	@Autowired
	private DaoObjectProcessImp daoObjectProcessImp;
	
	@Autowired
	private DaoObjectGroupImp daoObjectGroupImp;
	
	@Autowired
	private DaoObjectTypeImp daoObjectTypeImp;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogonImp daoLogonImp;


private Long systemUserId;
	
	
	 @RequestMapping(path ="/object/init/{userId}", method = RequestMethod.GET )
	    public String createObject(@PathVariable Long userId,Model model) {
		 
		 
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
				
		 
		    ModelSystem newsystem=new ModelSystem();
			model.addAttribute("newsystem", newsystem);
			
			
			ModelModule newmodule=new ModelModule();	
			newmodule.setActive(true);
		    model.addAttribute("newmodule", newmodule);
		    
		    ModelObject newobject=new ModelObject();
			newobject.setActive(true);
			model.addAttribute("newobject", newobject);
		    
		 
		  
	     return "object";

	}
	 
	 @RequestMapping(path = "/system/save", method = RequestMethod.POST) 
 	public  String saveSystem(ModelSystem sys) {
 	
 	if(sys.getSystemId()==null )
 			{
 			java.util.Date date = new java.util.Date();
 			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
 				sys.setEntryTimestamp(entryTime);
 				sys.setEnteredBy(1L);
 				daoSystemImp.saveSystem(sys);
 			}
 	
 	else
	{
		Optional<ModelSystem> existssystem=systemRepository.findById(sys.getSystemId());
		sys.setEnteredBy(existssystem.get().getEnteredBy());
		sys.setEntryTimestamp(existssystem.get().getEntryTimestamp());
		
		daoSystemImp.saveSystem(sys);
	}

 			
 	 return "redirect:/object/init/"+systemUserId;
 	
 }
	 
	 @RequestMapping(path = "/objectcontroller/editsystem/{id}", method = RequestMethod.GET)
	    public String editsystem(@PathVariable Long id, Model model) {
	       
		 Optional<ModelSystem> systemById;
	     systemById=systemRepository.findById(id);
		 if( systemById.get().getActiveStatus()==1)
			 systemById.get().setActive(true); 
			else
			systemById.get().setActive(false);	
	        model.addAttribute("newsystem", systemById);
	        if(id!=0) {
	        	String msg="Successfully Saved";
	        	 model.addAttribute("message",msg );
	        }
	        else
	        {
	        	String msg="Some Error Occured";
	        	 model.addAttribute("message",msg);
	        }
	               
	        return "object";
	}
	 
	    @RequestMapping(path="/system/search")
		public String geSystemList(Model model,@RequestParam("systemName")String systemName,
				@RequestParam("remarks")String remarks,
				@RequestParam(value = "active", required = false) String active){
	    	
	    	
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
		
			
	        int status=1;
			
			if(active!=null)
				status=1;
			else
				status=0;
			
			model.addAttribute("systemList",daoSystemImp.getSystemListByCraiteria(systemName, remarks, status));
			
			ModelSystem newsystem=new ModelSystem();
			model.addAttribute("newsystem", newsystem);
			
			
			ModelModule newmodule=new ModelModule();	
			newmodule.setActive(true);
		    model.addAttribute("newmodule", newmodule);
		    
		    ModelObject newobject=new ModelObject();
			newobject.setActive(true);
			model.addAttribute("newobject", newobject);

			
			return "object";
		}
	    
	    @ResponseBody
		@RequestMapping(path="/object/init/getSystemId/{id}", method=RequestMethod.GET)
		public Optional<ModelSystem> loadEntity(@PathVariable("id") Long id) {
	    	
		        return daoSystemImp.getSystemById(id);
			}
	    
	    @RequestMapping(path = "/module/save", method = RequestMethod.POST) 
	 	public  String moduleSystem(ModelModule module) {
	 	
	 	if(module.getModuleId()==null )
	 			{
	 			java.util.Date date = new java.util.Date();
	 			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
	 			module.setEntryTimestamp(entryTime);
	 			module.setEnteredBy(1L);
	 			daoModuleImp.saveModule(module);
	 			}
	 			
	 	 return "redirect:/object/init/"+systemUserId;
	 	
	 }
	    
	    @RequestMapping(path="/system/module/search/{id}", method=RequestMethod.GET)
	    public String getModuleBySystem(@PathVariable("id") Long systemid ) {
	    	
	    	
	    	
					
	    	daoModuleImp.getModuleFromSystem(systemid);
	    	
	    	
			
			return "redirect:/object/modulesearch";
	    }
	    
	    
	    @RequestMapping(path="/module/object/search/{id}", method=RequestMethod.GET)
	    public String getObjectByModule(@PathVariable("id") Long moduleid ) {
					
	    	daoObjectImp.getObjectFromModule(moduleid );
	    				
			return "redirect:/module/objectsearch/"+moduleid;
	    }
	    
	    @RequestMapping(path="/object/process/search/{id}", method=RequestMethod.GET)
	    public String getProcessByObject(@PathVariable("id") Long objectid ) {
					
	    	daoObjectProcessImp.getObjectProcessFromObject(objectid);
	    				
			return "redirect:/object/processsearch";
	    }
	    
	    
		@RequestMapping(path="/object/modulesearch", method=RequestMethod.GET)
	    public String getModuleFromSystem(Model model) {
			
			model.addAttribute("systemList",daoSystemImp.getSystemList());
	    	model.addAttribute("moduleList",daoModuleImp.getModuleList());
	    	
	    	
	    	ModelSystem newsystem=new ModelSystem();
			model.addAttribute("newsystem", newsystem);
			
			
			ModelModule newmodule=new ModelModule();	
			newmodule.setActive(true);
		    model.addAttribute("newmodule", newmodule);
		    
		    ModelObject newobject=new ModelObject();	
		    newobject.setActive(true);
		    model.addAttribute("newobject", newobject);
		    
		    model.addAttribute("objectGroupList",daoObjectGroupImp.getObjectGroupList());
		    model.addAttribute("objectTypeList",daoObjectTypeImp.getObjectTypeList());
		    
		    Long userId=systemUserId;
		    System.out.println("user ID :" +userId);
			 ModelUser modelUser=new ModelUser();
			 modelUser.setUserId(userId);
			 //this.systemUserId=userId;
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
	    	
	    	return "object";
	    	
	    }
		
		
		@RequestMapping(path="/module/objectsearch/{moduleId}", method=RequestMethod.GET)
	    public String getObjectFromModule(@PathVariable("moduleId") Long moduleId,Model model) {
			
			//model.addAttribute("systemList",daoSystemImp.getSystemList());
			model.addAttribute("objectList",daoObjectImp.getObjectList());
			List<ModelObject> modelObjectList=daoObjectImp.getObjectFromModule(moduleId);
			System.out.println("Size: "+modelObjectList.size());
			model.addAttribute("objectList",modelObjectList);
	    	model.addAttribute("moduleList",daoModuleImp.getModuleList());
	    	
	    	
	    	ModelSystem newsystem=new ModelSystem();
			model.addAttribute("newsystem", newsystem);
			
			
			ModelModule newmodule=new ModelModule();	
			newmodule.setActive(true);
		    model.addAttribute("newmodule", newmodule);
		    
		    ModelObject newobject=new ModelObject();	
		    newobject.setActive(true);
		    model.addAttribute("newobject", newobject);
		    
		    model.addAttribute("objectGroupList",daoObjectGroupImp.getObjectGroupList());
		    model.addAttribute("objectTypeList",daoObjectTypeImp.getObjectTypeList());
		    //model.addAttribute("objectList", daoObjectImp.getAllObjectList());
		    
		    Long userId=systemUserId;
		    System.out.println("user ID :" +userId);
			 ModelUser modelUser=new ModelUser();
			 modelUser.setUserId(userId);
			 //this.systemUserId=userId;
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
	    	
	    	return "object";
	    	
	    }
		
		
		@RequestMapping(path="/object/processsearch", method=RequestMethod.GET)
	    public String getProcessFromObject(Model model) {
			
			model.addAttribute("objectProcessList", daoObjectProcessImp.getObjectProcessList());
			model.addAttribute("systemList",daoSystemImp.getSystemList());
			model.addAttribute("objectList",daoObjectImp.getObjectList());
	    	model.addAttribute("moduleList",daoModuleImp.getModuleList());
	    	
	    	
	    	ModelSystem newsystem=new ModelSystem();
			model.addAttribute("newsystem", newsystem);
			
			
			ModelModule newmodule=new ModelModule();	
			newmodule.setActive(true);
		    model.addAttribute("newmodule", newmodule);
		    
		    ModelObject newobject=new ModelObject();	
		    newobject.setActive(true);
		    model.addAttribute("newobject", newobject);
		    
		    model.addAttribute("objectGroupList",daoObjectGroupImp.getObjectGroupList());
		    model.addAttribute("objectTypeList",daoObjectTypeImp.getObjectTypeList());
		    model.addAttribute("objectList", daoObjectImp.getAllObjectList());
	    	
	    	return "object";
	    	
	    }
		
		@RequestMapping(path = "/object/save", method = RequestMethod.POST) 
	 	public  String saveObject(ModelObject object) {
	 	
	 	if(object.getObjectId()==null )
	 			{
	 			java.util.Date date = new java.util.Date();
	 			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
	 				object.setEntryTimestamp(entryTime);
	 				object.setEnteredBy(1L);
	 				daoObjectImp.saveObject(object);
	 			}

	 			
	 	 return "redirect:/object/init/"+systemUserId;
	 	
	 }	
		
		
		    @ResponseBody
			@RequestMapping(path="/object/init/getModuleId/{id}", method=RequestMethod.GET)
			public Optional<ModelModule> loadEntitys(@PathVariable("id") Long id) {
		    	
			        return daoModuleImp.getModuleById(id);
				}	
	    
}
