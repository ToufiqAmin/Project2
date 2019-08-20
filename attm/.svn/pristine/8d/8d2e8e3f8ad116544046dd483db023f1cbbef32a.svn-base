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
import com.biziitech.attm.bg.repository.ClientServiceRepository;
import com.biziitech.attm.bg.repository.PassportReceiveRepository;
import com.biziitech.attm.bg.repository.ServiceRepository;
import com.biziitech.attm.custom.model.ModelPassportReceiveCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoPassportReceive;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelClientService;
import com.biziitech.attm.model.ModelPassportReceive;
import com.biziitech.attm.model.ModelService;




@Controller
public class PassportReceiveController {

	@Autowired
	private DaoPassportReceive daoPassportReceive;
	
	@Autowired
	private PassportReceiveRepository passportReceiveRepository;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ClientServiceRepository clientServiceReposiotry;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@RequestMapping(path="/passportreceivecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		String msg=" ";
		model.addAttribute("message",msg );
		
		ModelPassportReceive modelPassportReceive = new ModelPassportReceive();
		model.addAttribute("modelPassportReceive_ATTM",modelPassportReceive);
		
		List<ModelService> serviceList = serviceRepository.getServiceInOrder();
		model.addAttribute("serviceList", serviceList);
		
		
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
		 
		 return "passportReceive";
	}
	
	@RequestMapping(path="/attm_passportreceivecontroller/checkinguserid")
	@ResponseBody
	public List<ModelBgUser> checkingUserId(@RequestParam("passportNo")String passportNo){
		
		System.out.println("passportNo: " + passportNo);
		
		List<ModelBgUser> modelUser = bgUserRepository.getUserName(passportNo);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	@ResponseBody
	@RequestMapping(path = "/passportreceivecontroller/savepassportreceive", method = RequestMethod.POST) 
	public List<ModelPassportReceiveCustom> savePassportReceive(ModelPassportReceive modelPassportReceive,
			ModelBgUser modelUser,ModelClientService modelClientService,
			@RequestParam("passportReceiveId") Long passportReceiveId,
			@RequestParam("receiveDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date receiveDate,
			@RequestParam("clientServiceId") Long clientServiceId,
			@RequestParam("userId") Long userId,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks
			) {
	
		System.out.println("IN passportreceivecontroller savePassportReceive Method");
		
		try {
			
			if(passportReceiveId==null) 
			{
			
			modelPassportReceive.setReceiveDate(receiveDate);
			modelClientService.setClientServiceId(clientServiceId);
			modelPassportReceive.setModelClientService(modelClientService);
			modelUser.setUserId(userId);
			modelPassportReceive.setModelUser(modelUser);
			modelPassportReceive.setActiveStatus(activeStatus);
			if(activeStatus==1) 
			{
				modelPassportReceive.setActive(true);
				modelPassportReceive.setsActive("Yes");
			}
			else 
			{
				modelPassportReceive.setActive(false);
				modelPassportReceive.setsActive("No");
			}
			modelPassportReceive.setRemarks(remarks);
		
		
		daoPassportReceive.savePassportReceive_ATTM(modelPassportReceive);
		}
		else {
		System.out.println("passportReceive Id : " + passportReceiveId);
				
		modelPassportReceive.setReceiveDate(receiveDate);
		modelClientService.setClientServiceId(clientServiceId);
		modelPassportReceive.setModelClientService(modelClientService);
		modelUser.setUserId(userId);
		modelPassportReceive.setModelUser(modelUser);
		modelPassportReceive.setActiveStatus(activeStatus);
		if(activeStatus==1) 
		{
			modelPassportReceive.setActive(true);
			modelPassportReceive.setsActive("Yes");
		}
		else 
		{
			modelPassportReceive.setActive(false);
			modelPassportReceive.setsActive("No");
		}
		modelPassportReceive.setRemarks(remarks);
		
		modelPassportReceive.setPassportReceiveId(passportReceiveId);
	
	
	    daoPassportReceive.savePassportReceive_ATTM(modelPassportReceive);
		}
			
		List<ModelPassportReceiveCustom> modelPassportReceiveList = daoPassportReceive.getPassportReceiveById(modelPassportReceive.getPassportReceiveId());
		System.out.println("Size : " + modelPassportReceiveList.size());
   	 	

	

	

	 return modelPassportReceiveList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	
}
	
	@RequestMapping(path = "/passportreceivecontroller/update/{id}", method = RequestMethod.GET)
    public String updatePassportReceive(@PathVariable Long id, Model model) {
    	
    	 Optional<ModelPassportReceive> passportReceiveById;
    	 passportReceiveById=passportReceiveRepository.findById(id);
    	 
    	
    	 
    	 if( passportReceiveById.get().getActiveStatus()==1)
    		 passportReceiveById.get().setActive(true); 
    		else
    			passportReceiveById.get().setActive(false);	

            model.addAttribute("modelPassportReceive_ATTM", passportReceiveById);
            if(id!=0) {
            	String msg=" ";
            	 model.addAttribute("message",msg );
            }
            else
            {
            	String msg="Some Error Occured";
            	 model.addAttribute("message",msg);
            }
    	
        return "passportReceive";

    }
	
	@ResponseBody
	@RequestMapping(path="/passportreceivecontroller/getpassportreceiveundone",method = RequestMethod.GET)
	public List<ModelPassportReceiveCustom>getPassportReceiveUnDone(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("passengerName")String passengerName,
			@RequestParam("userId")Long userId, 
			@RequestParam("serviceId")Long serviceId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate
			) {
		
		try {
		
        
		
		System.out.println("in getPassportReceiveUnDone");		
		List<ModelPassportReceiveCustom> ModelPassportReceiveCustomList = new ArrayList<ModelPassportReceiveCustom>();
		ModelPassportReceiveCustomList=daoPassportReceive.getPassportReceiveUnDone(passportNo, passengerName, userId, serviceId, fromDate, toDate);	
		System.out.println("Size: "+ModelPassportReceiveCustomList.size());	
		return ModelPassportReceiveCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(path="/passportreceivecontroller/getpassportreceivedone",method = RequestMethod.GET)
	public List<ModelPassportReceiveCustom>getPassportReceiveDone(Model model, 
			@RequestParam("passportNo")String passportNo,
			@RequestParam("passengerName")String passengerName,
			@RequestParam("userId")Long userId, 
			@RequestParam("serviceId")Long serviceId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate
			) {
		
		try {
		
        
		
				
		List<ModelPassportReceiveCustom> ModelPassportReceiveList = new ArrayList<ModelPassportReceiveCustom>();
		ModelPassportReceiveList=daoPassportReceive.getPassportReceiveDone(passportNo, passengerName, userId, serviceId, fromDate, toDate);	
		
		return ModelPassportReceiveList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	
}
