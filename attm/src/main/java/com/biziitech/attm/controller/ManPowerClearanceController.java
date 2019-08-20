package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.ManPowerClearanceRepository;
import com.biziitech.attm.custom.model.ModelManPowerClearanceCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoManPowerClearance;
import com.biziitech.attm.model.ModelClientService;
import com.biziitech.attm.model.ModelManPowerClearance;





@Controller
public class ManPowerClearanceController {
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private ManPowerClearanceRepository manPowerClearanceRepository;
	
	@Autowired
	private DaoManPowerClearance daoManPowerClearance;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	private Long systemUserId;
	
	
	@RequestMapping(path="/manpowerclearancecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		ModelManPowerClearance manPowerClearance =new ModelManPowerClearance();
		
		 model.addAttribute("manPowerClearance", manPowerClearance);
		 
		String msg=" ";
		model.addAttribute("message",msg );
		
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		this.systemUserId=userId;
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
		  
		 
		 return "manPowerClearance";
	}
	
	
	@RequestMapping(path="/attm_manpowerclearancecontroller/checkinguserid")
	@ResponseBody
	public List<ModelBgUser> checkingUserId(@RequestParam("passportNo")String passportNo){
		
		System.out.println("passportNo: " + passportNo);
		
		List<ModelBgUser> modelUser = bgUserRepository.getUserName(passportNo);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	@ResponseBody
	@RequestMapping(path = "/manpowerclearancecontroller/savemanpowerclearance", method = RequestMethod.POST) 
   	public List<ModelManPowerClearanceCustom> saveManPowerClearance(ModelManPowerClearance modelManPowerClearance_ATTM,
   			ModelBgUser modelUser,ModelClientService modelClientService,
   			@RequestParam("manpowerClearanceId") Long manpowerClearanceId,
   			@RequestParam("expenseAmount") Double expenseAmount,
   			@RequestParam("clearanceAgentName") String clearanceAgentName,
   			@RequestParam("sentDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date sentDate,
   			@RequestParam("receiveDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date receiveDate,
   			@RequestParam("deliveryDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date deliveryDate,
			@RequestParam("clientServiceId") Long clientServiceId,
			@RequestParam("userId") Long userId,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks
			) {
   	
		System.out.println("IN manpowerclearancecontroller saveManPowerClearance Method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {
			if(manpowerClearanceId==null) 
			{
			
			modelManPowerClearance_ATTM.setExpenseAmount(expenseAmount);
			modelManPowerClearance_ATTM.setClearanceAgentName(clearanceAgentName);
			modelManPowerClearance_ATTM.setSentDate(sentDate);
			modelManPowerClearance_ATTM.setReceiveDate(receiveDate);
			modelManPowerClearance_ATTM.setDeliveryDate(deliveryDate);
			modelClientService.setClientServiceId(clientServiceId);
			modelManPowerClearance_ATTM.setModelClientService(modelClientService);
			modelUser.setUserId(userId);
			modelManPowerClearance_ATTM.setModelUser(modelUser);
			modelManPowerClearance_ATTM.setActiveStatus(activeStatus);
			if(activeStatus==1) 
			{
				modelManPowerClearance_ATTM.setActive(true);
				modelManPowerClearance_ATTM.setsActive("Yes");
			}
			else 
			{
				modelManPowerClearance_ATTM.setActive(false);
				modelManPowerClearance_ATTM.setsActive("No");
			}
			modelManPowerClearance_ATTM.setEnteredBy(systemUserId);
			modelManPowerClearance_ATTM.setEntryTimeStamp(entryTime);
			modelManPowerClearance_ATTM.setRemarks(remarks);
		
		
			daoManPowerClearance.saveATTM_ManPowerClearance(modelManPowerClearance_ATTM);
			
			}
			else {
			System.out.println("manpowerClearance Id : " + manpowerClearanceId);
					
			modelManPowerClearance_ATTM.setExpenseAmount(expenseAmount);
			modelManPowerClearance_ATTM.setClearanceAgentName(clearanceAgentName);
			modelManPowerClearance_ATTM.setSentDate(sentDate);
			modelManPowerClearance_ATTM.setReceiveDate(receiveDate);
			modelManPowerClearance_ATTM.setDeliveryDate(deliveryDate);
			modelClientService.setClientServiceId(clientServiceId);
			modelManPowerClearance_ATTM.setModelClientService(modelClientService);
			modelUser.setUserId(userId);
			modelManPowerClearance_ATTM.setModelUser(modelUser);
			modelManPowerClearance_ATTM.setActiveStatus(activeStatus);
			if(activeStatus==1) 
			{
				modelManPowerClearance_ATTM.setActive(true);
				modelManPowerClearance_ATTM.setsActive("Yes");
			}
			else 
			{
				modelManPowerClearance_ATTM.setActive(false);
				modelManPowerClearance_ATTM.setsActive("No");
			}
			modelManPowerClearance_ATTM.setRemarks(remarks);
			
			modelManPowerClearance_ATTM.setUpdatedBy(systemUserId);
			modelManPowerClearance_ATTM.setUpdateTimeStamp(entryTime);
		
			modelManPowerClearance_ATTM.setManPowerClearanceId(manpowerClearanceId);
			
			
			
			daoManPowerClearance.saveATTM_ManPowerClearance(modelManPowerClearance_ATTM);
			}
		
				
		List<ModelManPowerClearanceCustom> modelManPowerClearanceList = daoManPowerClearance.getMCById(modelManPowerClearance_ATTM.getManPowerClearanceId());
		System.out.println("Size : " + modelManPowerClearanceList.size());
   	
   
   	 return modelManPowerClearanceList;
   	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
   	
   }
	
	@RequestMapping(path = "/manpowerclearancecontroller/update/{id}", method = RequestMethod.GET)
    public String updateManPowerClearance(@PathVariable Long id, Model model) {
    	
    	 Optional<ModelManPowerClearance> manPowerClearanceById;
    	 manPowerClearanceById=manPowerClearanceRepository.findById(id);
    	 if( manPowerClearanceById.get().getActiveStatus()==1)
    		 manPowerClearanceById.get().setActive(true); 
    		else
    			manPowerClearanceById.get().setActive(false);	
            model.addAttribute("manPowerClearance", manPowerClearanceById);
            if(id!=0) {
            	String msg=" ";
            	 model.addAttribute("message",msg );
            }
            else
            {
            	String msg="Some Error Occured";
            	 model.addAttribute("message",msg);
            }
    	
        return "manPowerClearance";

    }
	
	@ResponseBody
	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearanceundonewithpr",method = RequestMethod.GET)
	public List<ModelManPowerClearanceCustom>getManPowerClearanceUnDoneWithPR(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("userName")String userName,
			@RequestParam("userId")Long userId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("clearanceAgentName")String clearanceAgentName
			) {
		
		try {
			
			System.out.println("in manpowerclearancecontroller getmanpowerclearanceundonewithpr");
				
		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCUnDoneWithPR(passportNo, userName, userId, fromDate, toDate, clearanceAgentName);	
		System.out.println("Size: "+ModelManPowerClearanceCustomList.size());
		return ModelManPowerClearanceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
//	@ResponseBody
//	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearanceundonewithoutpr",method = RequestMethod.GET)
//	public List<ModelManPowerClearanceCustom>getManPowerClearanceUnDoneWithoutPR(Model model, 
//			@RequestParam("passportNo")String passportNo,
//			@RequestParam("userName")String userName,
//			@RequestParam("userId")Long userId,
//			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
//			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate
//			) {
//		
//		try {
//		System.out.println("in getManPowerClearanceUnDoneWithoutPR method");		
//		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
//		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCUnDoneWithoutPR(passportNo, userName, userId, fromDate, toDate);	
//		System.out.println("Size: "+ModelManPowerClearanceCustomList.size());
//		return ModelManPowerClearanceCustomList;
//		
//		}		
//		catch(Exception e) {			
//		 e.printStackTrace();
//		 System.out.println("error");
//			return null;
//		}
//	}
	
	@ResponseBody
	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearancedonewithpr",method = RequestMethod.GET)
	public List<ModelManPowerClearanceCustom>getManPowerClearanceDoneWithPR(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("userName")String userName,
			@RequestParam("userId")Long userId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("clearanceAgentName")String clearanceAgentName
			) {
		
		try {
			
		System.out.println("in manpowerclearancecontroller getmanpowerclearancedonewithpr");	
				
		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCDoneWithPR(passportNo, userName, userId, fromDate, toDate, clearanceAgentName);	
		
		return ModelManPowerClearanceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	
//	@ResponseBody
//	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearancedonewithoutpr",method = RequestMethod.GET)
//	public List<ModelManPowerClearanceCustom>getManPowerClearanceDoneWithoutPR(Model model, 
//			@RequestParam("passportNo")String passportNo,
//			@RequestParam("userName")String userName,
//			@RequestParam("userId")Long userId,
//			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
//			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate
//			) {
//		
//		try {
//				
//		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
//		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCDoneWithoutPR(passportNo, userName, userId, fromDate, toDate);	
//		
//		return ModelManPowerClearanceCustomList;
//		
//		}		
//		catch(Exception e) {			
//		 e.printStackTrace();
//		 System.out.println("error");
//			return null;
//		}
//	}

	
	@ResponseBody
	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearancedonewithreceivedate",method = RequestMethod.GET)
	public List<ModelManPowerClearanceCustom>getManPowerClearanceDoneWithReceiveDate(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("userName")String userName,
			@RequestParam("userId")Long userId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("clearanceAgentName")String clearanceAgentName
			) {
		
		try {
			
			
			System.out.println("in manpowerclearancecontroller getmanpowerclearancedonewithreceivedate");	
				
		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCDoneWithReceiveDate(passportNo, userName, userId, fromDate, toDate, clearanceAgentName);	
		System.out.println("Size: "+ModelManPowerClearanceCustomList.size());
		return ModelManPowerClearanceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path="/manpowerclearancecontroller/getmanpowerclearancedonewithoutreceivedate",method = RequestMethod.GET)
	public List<ModelManPowerClearanceCustom>getManPowerClearanceDoneWithoutReceiveDate(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("userName")String userName,
			@RequestParam("userId")Long userId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("clearanceAgentName")String clearanceAgentName
			) {
		
		try {
		
			System.out.println("in manpowerclearancecontroller getmanpowerclearancedonewithoutreceivedate");	
			
		List<ModelManPowerClearanceCustom> ModelManPowerClearanceCustomList = new ArrayList<ModelManPowerClearanceCustom>();
		ModelManPowerClearanceCustomList=daoManPowerClearance.getMCDoneWithoutReceiveDate(passportNo, userName, userId, fromDate, toDate, clearanceAgentName);
		System.out.println("Size: "+ModelManPowerClearanceCustomList.size());
		return ModelManPowerClearanceCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	
	
}
