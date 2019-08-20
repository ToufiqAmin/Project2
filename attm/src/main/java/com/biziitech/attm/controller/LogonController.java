package com.biziitech.attm.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.dao.DaoLogon;






@Controller
public class LogonController {
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	
	@RequestMapping(path="/logoncontroller/init")
	public String init(Model model) {
		ModelBgUser modelBgUser = new  ModelBgUser();
		
		model.addAttribute("user",modelBgUser);
		
		return "logon";
	}
	
	@RequestMapping(path="/logoncontroller/validateuserpassword")
	//@ResponseBody
	public String validateUserPassword(Model model,HttpServletRequest request,
			   
			  @RequestParam("username") String pUserName,
	          @RequestParam("password") String pPassword) {
		
		        // declare status variable
		        // initialize variable with dao method. Dao method returns 1 if user name and passwod match. Otherwise returns 0
				// return status variable
		
		 
		      
		System.out.println("userName :"+pUserName);
		System.out.println("password :"+pPassword);
		
		List<ModelUser> user=daoLogon.userValidate(pUserName, pPassword);
			
			
		System.out.println("data size"  + user.size());
			 
		
		int checkSize=user.size();
		
		
		
		if(checkSize==1) {
			System.out.println("wellcome to mas");
			
			Long userId=user.get(0).getUserId();
			
			System.out.println("access user id " +userId);
			
			
			ModelUser logonUser=daoLogon.getLogonUserName(userId);
			  
			  //String userName=logonUser.getUserName();
			String userName=logonUser.getLoginUser();
			  
			  System.out.println("logon user name is :" + userName);
			  
			  String name="User : " + userName;
			  model.addAttribute("name",name );	
			  
			  model.addAttribute("userId",userId);
			
			
			  ModelBgUser modelBgUser=new ModelBgUser();
			
			  modelBgUser.setUserId(userId);
			
			model.addAttribute("modelUser", modelBgUser);	
			
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
			
			
			return "home_page_left_panel";
		}
		
		if(checkSize==0) {
			
			String msg="User Name Or Password Is Incorrect .!";
       	    model.addAttribute("message",msg );
			System.out.println("user name or password is incorrect");
			
			String uname=pUserName;
			String password=pPassword;
			
			model.addAttribute("uname", uname);
			model.addAttribute("password",password);
			
		}
		
		
			
		return"logon";
		
		
	}

}
