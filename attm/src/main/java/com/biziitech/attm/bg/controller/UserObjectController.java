package com.biziitech.attm.bg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.biziitech.attm.bg.daoimp.DaoUserImp;
import com.biziitech.attm.bg.model.ModelObject;
import com.biziitech.attm.bg.model.ModelObjectGroup;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.ObjectRepository;
import com.biziitech.attm.daoImp.DaoLogonImp;




@Controller
public class UserObjectController {
	
	
	@Autowired
	private DaoUserImp daoUserImp;
	
	@Autowired
	private ObjectRepository objectRepository;
	
	//@Autowired
	//private DaoProductionPlan daoProductionPlan;
	
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	
	
	@Autowired
	private DaoLogonImp daoLogonImp;


private Long systemUserId;
	
	
	@RequestMapping(path="/userobjectcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		
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
				
		
		//List<ModelUser> userList= daoUserImp.getAllUSerName();
				
		List<ModelUser> userList=	daoUserImp.getAllUSerNameInOrder();
		model.addAttribute("userList",userList);
		
		
		List<ModelObjectGroup> objectGroup = daoUserObject.getObjectGroup();
		model.addAttribute("objectGroup",objectGroup);
		
		
		List<ModelObject> objectList = objectRepository.findObject();
		model.addAttribute("objectList",objectList);
		
		return "user_object";
	}
	
	
	@RequestMapping(path="/userobjectcontroller/findobject")
	@ResponseBody
	public List<ModelUserObject> findObject(@RequestParam("objectGroupId")Long objectGroupId){
		
		
		System.out.println("objectGroupId : " + objectGroupId);
		System.out.println("getUserObjectByObjectGroupId : " + daoUserObject.getUserObjectByObjectGroupId(objectGroupId));
		
		return daoUserObject.getUserObjectByObjectGroupId(objectGroupId);
	}
	
	
	
	/*
	   * 
	   * 
	   * creator : sas
	   * 
	   * */
		@ResponseBody
		@RequestMapping(path="/userobjectcontroller/getuserobject")
		public List<ModelUserObject> getUserObject(Model model,ModelUserObject modelUserObject,
			      @RequestParam("userId") Long pUserId){
			
			System.out.println("\n User Object Search Portion \n");
			
			System.out.println("Ajax data sent " + "\n UserId :" +pUserId);
			
			List<ModelUserObject> modelUserObjectList = daoUserObject.getUserObject(pUserId);
			System.out.println("modelUserObjectList size : "+ modelUserObjectList.size() );
			System.out.println("modelUserObjectList : "+ modelUserObjectList );
			
		 return modelUserObjectList;
		
		}
	
	
	
	
	@RequestMapping(path="/userobjectcontroller/saveuserobject", method = RequestMethod.POST)
	@ResponseBody
		public List<ModelUserObject> saveUserObject(Model model,
	            ModelUser modelUser,ModelObject modelObject,
				@RequestParam("userId") Long userId,
				@RequestParam("objectId") Long objectId,
				@RequestParam("remarks") String remarks,
				@RequestParam("activeStatus") int activeStatus,
				@RequestParam("allObjectactive") int allObjectActive
				)
		
		{
		
		
		
		
		System.out.println(" userId " +userId);
		System.out.println("objectId " +objectId);
		System.out.println("remarks " +remarks);
		System.out.println("activeStatus " +activeStatus);
		System.out.println("allObjectActive " +allObjectActive);
		
		
		if(allObjectActive==1)
		{
			
			System.out.println("Save allObjectActive ");
			
			
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			//List<ModelUserObject> modelUserObjectList= new ArrayList<ModelUserObject>(); 
			List<ModelObject> objectList = objectRepository.findObject();
			
			System.out.println(" objectList : "+ objectList.size());
			System.out.println(" objectList : "+ objectList);
			
			for(ModelObject a:objectList) {
				
				//System.out.println("object name : " + a.getObjectName());
				
				ModelUserObject modelUserObject=new ModelUserObject();
				
				modelUser.setUserId(userId);
				modelUserObject.setModelUser(modelUser);
				
                modelUserObject.setRemarks(remarks);
				
				modelUserObject.setActiveStatus(activeStatus);
				
				modelUserObject.setEnteredBy(1L);
				
				modelUserObject.setEntryTimestamp(entryTime);
				
				
				modelObject.setObjectId(a.getObjectId());
				modelUserObject.setModelObject(modelObject);
				
				daoUserObject.saveUserObject(modelUserObject);
				
				
			}
			
		//for(int i=0;i<objectList.size();i++)
		//}
			
			//ModelUserObject modelUserObject=new ModelUserObject();
			
			System.out.println(" user ID : "+ userId);
		
			List<ModelUserObject> modelUserObjectAllList= new ArrayList<ModelUserObject>();
			modelUserObjectAllList = daoUserObject.getUserObjectById(userId);
			
			
			System.out.println(" modelUserObjectAllList Size : "+ modelUserObjectAllList.size());
			System.out.println(" controller : Model User Object  find by Id : "+ modelUserObjectAllList);
			
             for(ModelUserObject a:modelUserObjectAllList) {
				
				System.out.println("object name : " + a.getUserObjectId());
              }
			
			return modelUserObjectAllList;
			
		//	return null;
		}
		else
		{
			
			ModelUserObject modelUserObject=new ModelUserObject();
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			
			modelUser.setUserId(userId);
			modelUserObject.setModelUser(modelUser);
			
			modelObject.setObjectId(objectId);
			modelUserObject.setModelObject(modelObject);
			
			modelUserObject.setRemarks(remarks);
			
			modelUserObject.setActiveStatus(activeStatus);
			
			modelUserObject.setEnteredBy(1L);
			
			modelUserObject.setEntryTimestamp(entryTime);
			
			
			daoUserObject.saveUserObject(modelUserObject);
			
			
			
		
			List<ModelUserObject> modelUserObjectList= new ArrayList<ModelUserObject>();
			System.out.println(" controller : after save user object Id : " + modelUserObject.getUserObjectId());
			modelUserObjectList = daoUserObject.getUserObjectById(modelUserObject.getUserObjectId());
			
			System.out.println(" controller : Model User Object  find by Id : " + modelUserObjectList);
			
			
			
			return modelUserObjectList;
			
			
		}
	
		
		
		
		}
	
	
	
	
	
	@RequestMapping(path="/userobjectcontroller/saveuserobjectforallobject", method = RequestMethod.POST)
	@ResponseBody
		public List<ModelUserObject> saveUserObjectForAllObject(Model model,ModelUserObject modelUserObject,
	            ModelUser modelUser,ModelObject modelObject,
				@RequestParam("userId") Long userId,
			    @RequestParam("remarks") String remarks,
				@RequestParam("activeStatus") int activeStatus)
		
		{
		System.out.println(" userId " +userId);
		System.out.println("remarks " +remarks);
		System.out.println("activeStatus " +activeStatus);
		
		
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		//List<ModelUserObject> modelUserObjectList= new ArrayList<ModelUserObject>(); 
		List<ModelObject> objectList = objectRepository.findObject();
		
	//for(int i=0;i<objectList.size();i++)
	//{
		for (ModelObject object : objectList) {
			
			
			modelUser.setUserId(userId);
			modelUserObject.setModelUser(modelUser);
			
			//modelObject.setObjectId(objectList.get(0).getObjectId());
			
			modelObject.setObjectId(object.getObjectId());
			modelUserObject.setModelObject(modelObject);
			
			modelUserObject.setRemarks(remarks);
			
			modelUserObject.setActiveStatus(activeStatus);
			
			modelUserObject.setEnteredBy(1L);
			
			modelUserObject.setEntryTimestamp(entryTime);
			
			daoUserObject.saveUserObject(modelUserObject);
			
		
		}
		
	//}
	
		List<ModelUserObject> modelUserObjectAllList= new ArrayList<ModelUserObject>();
		modelUserObjectAllList = daoUserObject.getUserObjectById(modelUserObject.getUserObjectId());
		
		System.out.println(" controller : Model User Object  find by Id : " + modelUserObjectAllList);
		
		
		
		return modelUserObjectAllList;
		
		
		}

	
	
	
	
	
	
	
	

}
