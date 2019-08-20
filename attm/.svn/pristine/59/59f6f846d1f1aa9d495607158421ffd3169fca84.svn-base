package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoService;



@Controller
public class ServiceListController {
	
	@Autowired
	private DaoService daoService;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	
	@RequestMapping(path="/servicelistcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
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
		 
		 return "serviceList";
	}
	
	@RequestMapping(path="/servicelistcontroller/search/{userId}")
	public String getServiceList(@PathVariable Long userId,Model model, @RequestParam("serviceName")String serviceName,
			@RequestParam("shortCode")String shortCode,
			@RequestParam("remarks")String remarks, 
			@RequestParam(value = "active", required = false) String active,
			@RequestParam(value = "passport", required = false) String passport											
			) {
		
        int status=1;
        int passportStatus=1;
		
		if(active!=null)
			status=1;
		else
			status=0;
		
		if(passport!=null)
			passportStatus=1;
		else
			passportStatus=0;
		
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
		
		model.addAttribute("serviceList",daoService.getServiceByCraiteria(serviceName, shortCode, remarks, status, passportStatus));	
		return "serviceList";
	}

}
