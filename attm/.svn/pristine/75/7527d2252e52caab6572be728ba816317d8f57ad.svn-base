package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.ClientTypeRepository;
import com.biziitech.attm.custom.model.ModelClientServiceCustom;
import com.biziitech.attm.dao.DaoClientService;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.model.ModelClientType;


@Controller
public class ClientServiceListController {
	
	@Autowired
	private DaoClientService daoClientService;
	
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	
	
	@RequestMapping(path="/clientservicelistcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		List<ModelClientType> clientTypeList = clientTypeRepository.findClientTypeInOrder();
		model.addAttribute("clientTypeList",clientTypeList);
		
		
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
		 
		 return "clientServiceList";
	}
	
	@ResponseBody
	@RequestMapping(path="/clientservicelistcontroller/getClientServiceListOfAgent",method = RequestMethod.GET)
	public List<ModelClientServiceCustom>getClientServiceListOfAgent(Model model, 
			@RequestParam("clientTypeId")Long clientTypeId,
			@RequestParam("clientName")String clientName,
			@RequestParam("remarks")String remarks, 
			@RequestParam("status") int status											
			) {
		
		try {
		
        
		
				
		List<ModelClientServiceCustom> modelClientServiceCustomList = new ArrayList<ModelClientServiceCustom>();
		modelClientServiceCustomList=daoClientService.getClientServiceOfAgent(clientTypeId, clientName, remarks, status);	
		
		return modelClientServiceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path="/clientservicelistcontroller/getClientServiceListOfIndividual",method = RequestMethod.GET)
	public List<ModelClientServiceCustom>getClientServiceListOfIndividual(Model model, 
			@RequestParam("clientTypeId")Long clientTypeId,
			@RequestParam("clientName")String clientName,
			@RequestParam("remarks")String remarks, 
			@RequestParam("status") int status											
			) {
		
		try {
		
        
						
		List<ModelClientServiceCustom> modelClientServiceCustomList = new ArrayList<ModelClientServiceCustom>();
		modelClientServiceCustomList=daoClientService.getClientServiceOfIndividual(clientTypeId, clientName, remarks, status);	
			
		return modelClientServiceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}

}
