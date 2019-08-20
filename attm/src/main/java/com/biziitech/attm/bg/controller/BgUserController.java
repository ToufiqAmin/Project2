package com.biziitech.attm.bg.controller;

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

import com.biziitech.attm.bg.dao.DaoCity;
import com.biziitech.attm.bg.dao.DaoUser;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.dao.DaoUserType_attm;
import com.biziitech.attm.bg.dao.DaoUser_attm;
import com.biziitech.attm.bg.daoimp.DaoPhoneImp;
import com.biziitech.attm.bg.model.ModelUserType;
import com.biziitech.attm.bg.model.ModelBgUser;
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.model.ModelPhone;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.BgUserRepository;
import com.biziitech.attm.bg.repository.CountryRepository;
import com.biziitech.attm.bg.repository.UserRepository;
import com.biziitech.attm.dao.DaoLogon;






@Controller
public class BgUserController {
	
	@Autowired
	private DaoUser_attm daoUser_attm;
	
	@Autowired
	private DaoUser daoUser;
	
	@Autowired
	private DaoUserType_attm daoUserType_attm;
	
	@Autowired
	private BgUserRepository bgUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private DaoPhoneImp daoPhoneImp;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoCity daoCity;
	
	Long userId;

	
	@RequestMapping(path="/bgusercontroller/init/{userId}")
	public String init(@PathVariable Long userId, Model model) {
		
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		
		this.userId=userId;
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		
		
		
		
		List<ModelUserType> userTypeList= daoUserType_attm.getUserTypeListInOrder();
		model.addAttribute("userTypeList",userTypeList);
		
		ModelUser modelUser = new ModelUser();
		model.addAttribute("modelUser",modelUser);
		
		List<ModelCountry> countryList= countryRepository.findCountryInOrder();
		model.addAttribute("countryList",countryList);
		
		List<ModelCity> modelCity = daoCity.getCityInOrder();
		model.addAttribute("cityList", modelCity);
		
//		List<ModelPhone> numberList= new ArrayList<>();
//        
//        model.addAttribute("phoneList",numberList);
//        
//        ModelPhone modelPhone= new ModelPhone();	
//        model.addAttribute("modelPhone",modelPhone);
		
		
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
		
		String msg=" ";
		model.addAttribute("message",msg );
		 
		 return "bgUser";
	}
	
	@RequestMapping(path = "/bgusercontroller/saveuser/{id}", method = RequestMethod.POST) 
	public  String saveUser(ModelUser modelUser,@PathVariable Long id, Model model) {
	System.out.println("Security Code: "+modelUser.getSecurityCode());
		
	java.util.Date date = new java.util.Date();
	java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
	
	if(modelUser.getUserId()==null)
	{
			
		modelUser.setEntryTimestamp(entryTime);
		modelUser.setEnteredBy(id);
		//modelBgUser.setOrgBranchId(1L);
		//modelBgUser.setOrgId(1L);
		
		daoUser.save(modelUser);
		String msg="Successfully Saved";
   	 	model.addAttribute("message",msg );
	}
	else
	{
				Optional<ModelUser> existsuom=userRepository.findById(modelUser.getUserId());
				modelUser.setEnteredBy(existsuom.get().getEnteredBy());
				modelUser.setEntryTimestamp(existsuom.get().getEntryTimestamp());
				modelUser.setUpdatedBy(id);
				modelUser.setUpdateTimestap(entryTime);
				
				//modelBgUser.setOrgBranchId(1L);
				//modelBgUser.setOrgId(1L);
			
				daoUser.save(modelUser);
				String msg="Successfully Saved";
	        	model.addAttribute("message",msg );
	}
	
//	List<ModelUserType> userTypeList= daoUserType_attm.getUserTypeListInOrder();
//	model.addAttribute("userTypeList",userTypeList);
//	
//	ModelBgUser modelUser = new ModelBgUser();
//	model.addAttribute("modelUser_ATTM",modelUser);


	return "redirect:/bgusercontroller/edituser/"+modelUser.getUserId()+"/"+id;
	
}
	
	
	@ResponseBody
	@RequestMapping(path = "/bgusercontroller/saveuser", method = RequestMethod.POST)
	public List<ModelUser> saveUser(ModelUser modelUser,
			@RequestParam("titleName")String titleName,@RequestParam("sCode")String sCode,
			@RequestParam("userId")Long userId,@RequestParam("userName")String userName,
			@RequestParam("userType")Long userType,@RequestParam("loginUser")String loginUser,
			@RequestParam("genderStatus")Integer genderStatus,@RequestParam("spouseName")String spouseName,
			@RequestParam("fatherName")String fatherName,@RequestParam("motherName")String motherName,
			@RequestParam("profession")String profession,@RequestParam("passportNo")String passportNo,
			@RequestParam("dob")@DateTimeFormat(pattern="yyyy-MM-dd")Date dob,
			@RequestParam("bloodGroup")String bloodGroup,@RequestParam("email")String email,
			@RequestParam("nationalId")String nationalId,@RequestParam("userCity")String userCity,
			@RequestParam("district")String district,@RequestParam("division")String division,
			@RequestParam("upazilla")String upazilla,@RequestParam("policeStation")String policeStation,
			@RequestParam("postCode")Integer postCode,@RequestParam("countryId")Long countryId,
			@RequestParam("contactNo")String contactNo,@RequestParam("userAddress")String userAddress,
			@RequestParam("remarks")String remarks,@RequestParam("activeStatus")int activeStatus
			)
	{
		
		Long id = this.userId;
		
	
			System.out.println("gender: "+genderStatus);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			
			
			
			if(userId==null)
			{
				modelUser.setTitleName(titleName);
				modelUser.setSecurityCode(sCode);
				modelUser.setUserName(userName);
				//modelUser.setUserType(userType);
				ModelUserType modelUserType = new ModelUserType();
				modelUserType.setUserTypeId(userType);
				modelUser.setModelUserType(modelUserType);
				modelUser.setLoginUser(loginUser);
				modelUser.setGenderStatus(genderStatus);
				modelUser.setSpouseName(spouseName);
				modelUser.setFatherName(fatherName);
				modelUser.setMotherName(motherName);
				modelUser.setProfession(profession);
				modelUser.setPassportNo(passportNo);
				modelUser.setDob(dob);
				modelUser.setBloodGroup(bloodGroup);
				modelUser.setEmail(email);
				modelUser.setNationalId(nationalId);
				modelUser.setUserCity(userCity);
				modelUser.setUserDistrict(district);
				modelUser.setUserDivision(division);
				modelUser.setUserUpazilla(upazilla);
				modelUser.setUserPoliceStation(policeStation);
				modelUser.setUserPostcode(postCode);
				ModelCountry modelCountry= new ModelCountry();
				modelCountry.setCountryId(countryId);
				modelUser.setModelCountry(modelCountry);
				modelUser.setContactNo(contactNo);
				modelUser.setUserAddress(userAddress);
				modelUser.setUserRemarks(remarks);
				modelUser.setActiveStatus(activeStatus);
				modelUser.setEntryTimestamp(entryTime);
				modelUser.setEnteredBy(id);
				
				daoUser.save(modelUser);
			
			}
			else 
			{
				Optional<ModelUser> userList = userRepository.findById(userId);
				
				modelUser.setTitleName(titleName);
				modelUser.setSecurityCode(sCode);
				modelUser.setUserName(userName);
				//modelUser.setUserType(userType);
				ModelUserType modelUserType = new ModelUserType();
				modelUserType.setUserTypeId(userType);
				modelUser.setModelUserType(modelUserType);
				modelUser.setLoginUser(loginUser);
				modelUser.setUserId(userId);
				modelUser.setGenderStatus(genderStatus);
				modelUser.setSpouseName(spouseName);
				modelUser.setFatherName(fatherName);
				modelUser.setMotherName(motherName);
				modelUser.setProfession(profession);
				modelUser.setPassportNo(passportNo);
				modelUser.setDob(dob);
				modelUser.setBloodGroup(bloodGroup);
				modelUser.setEmail(email);
				modelUser.setNationalId(nationalId);
				modelUser.setUserCity(userCity);
				modelUser.setUserDistrict(district);
				modelUser.setUserDivision(division);
				modelUser.setUserUpazilla(upazilla);
				modelUser.setUserPoliceStation(policeStation);
				modelUser.setUserPostcode(postCode);
				ModelCountry modelCountry= new ModelCountry();
				modelCountry.setCountryId(countryId);
				modelUser.setModelCountry(modelCountry);
				modelUser.setContactNo(contactNo);
				modelUser.setUserAddress(userAddress);
				modelUser.setUserRemarks(remarks);
				modelUser.setActiveStatus(activeStatus);
				modelUser.setEntryTimestamp(userList.get().getEntryTimestamp());
				modelUser.setEnteredBy(userList.get().getEnteredBy());
				modelUser.setUpdatedBy(id);
				modelUser.setUpdateTimestap(entryTime);
				daoUser.save(modelUser);
				
			}
			
			List<ModelUser> modelUserList = userRepository.findUserById(modelUser.getUserId());
			System.out.println("Size: "+modelUserList.size());
			
			return modelUserList;		
		
	}
	
	
	@RequestMapping(path = "/bgusercontroller/edituser/{id}/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, @PathVariable Long userId, Model model) {
	 Optional<ModelUser> userById;
	 userById= userRepository.findById(id);
	if( userById.get().getActiveStatus()==1)
		userById.get().setActive(true);
		else
			userById.get().setActive(false);
        model.addAttribute("modelUser", userById);
        if(id!=0) {
        	String msg="Successfully Saved";
        	 model.addAttribute("message",msg );
        }
        else
        {
        	String msg="Some Error Occured";
        	 model.addAttribute("message",msg);
        }
        
        ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  //String userName=logonUser.getUserName();
		String userName=logonUser.getLoginUser();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
        
        List<ModelUserType> userTypeList= daoUserType_attm.getUserTypeListInOrder();
    	model.addAttribute("userTypeList",userTypeList);
    	
    	List<ModelCountry> countryList= countryRepository.findCountryInOrder();
		model.addAttribute("countryList",countryList);
		
		List<ModelCity> modelCity = daoCity.getCityInOrder();
		model.addAttribute("cityList", modelCity);
		
//		ModelBgUser modelBgUser = new ModelBgUser();
//        modelBgUser.setUserId(id);
//        
//        ModelPhone modelPhone = new ModelPhone();
//        modelPhone.setModelBgUser(modelBgUser);
//        
//       ModelCountry modelCountry=userById.get().getModelCountry();
//       modelPhone.setModelCountry(modelCountry);
//       model.addAttribute("modelPhone",modelPhone);
//       
//       List<ModelPhone> numberList= daoPhoneImp.getUserPhoneDataByUserId(id);
//        
//       model.addAttribute("phoneList",numberList);
		
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
                
        return "bgUser";
}
	
	@RequestMapping(path = "/bgusercontroller/update/{id}/{userId}", method = RequestMethod.GET)
    public String updateUser(@PathVariable Long id, @PathVariable Long userId, Model model) {
    	
    	 Optional<ModelUser> userById;
    	 userById=userRepository.findById(id);
    	 
    	 List<ModelUserType> userTypeList= daoUserType_attm.getUserTypeListInOrder();
 		 model.addAttribute("userTypeList",userTypeList);
 		 
 		List<ModelCountry> countryList= countryRepository.findCountryInOrder();
		model.addAttribute("countryList",countryList);
    	 
    	 if( userById.get().getActiveStatus()==1)
    		 userById.get().setActive(true); 
    		else
    			userById.get().setActive(false);	
            model.addAttribute("modelUser", userById);
            if(id!=0) {
            	String msg=" ";
            	 model.addAttribute("message",msg );
            }
            else
            {
            	String msg="Some Error Occured";
            	 model.addAttribute("message",msg);
            }
            
            ModelUser logonUser=daoLogon.getLogonUserName(userId);
  		  
  		  //String userName=logonUser.getUserName();
    		String userName=logonUser.getLoginUser();  
  		  
  		  System.out.println("logon user name is :" + userName);
  		  
  		  String name="User : " + userName;
  		  model.addAttribute("name",name );	
  		  
  		  model.addAttribute("userId",userId);
  		  
  		List<ModelCity> modelCity = daoCity.getCityInOrder();
		model.addAttribute("cityList", modelCity);
            
//            ModelBgUser modelBgUser = new ModelBgUser();
//            modelBgUser.setUserId(id);
//            
//            ModelPhone modelPhone = new ModelPhone();
//            modelPhone.setModelBgUser(modelBgUser);
//            
//           ModelCountry modelCountry=userById.get().getModelCountry();
//           modelPhone.setModelCountry(modelCountry);
//           model.addAttribute("modelPhone",modelPhone);
//           
//           List<ModelPhone> numberList= daoPhoneImp.getUserPhoneDataByUserId(id);
//            
//           model.addAttribute("phoneList",numberList);
  		  
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
    	
        return "bgUser";

    }
	
//	@RequestMapping(path = "/bgusercontroller/savephone", method = RequestMethod.POST)
//    public String savePhone(ModelPhone phone, Model model) {
//	 java.util.Date date = new java.util.Date();
//		java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
//		if(phone.getPhoneId()==null )
//		{
//			phone.setEntryTimestap(sqlDate);
//			phone.setEntered_By(1);
//			 this.daoPhoneImp.savePhone(phone);
//		}
//		else {
//			
//			Optional<ModelPhone> exitsPhone= this.daoPhoneImp.getPhoneById(phone.getPhoneId());
//			
//			phone.setUpdatedBy(1);
//			phone.setUpdateTimestap(sqlDate);
//	    	phone.setEntered_By(exitsPhone.get().getEntered_By());
//	    	phone.setEntryTimestap(exitsPhone.get().getEntryTimestap());
//	    	this.daoPhoneImp.savePhone(phone);
//		}
//		
//		
//		
//        return "redirect:/bgusercontroller/edituser/"+phone.getModelBgUser().getUserId();
//}
//
// @ResponseBody
//    @RequestMapping(value = "/bgusercontroller/phone/{id}", method = RequestMethod.GET)
//    public Optional<ModelPhone> loadEntity(@PathVariable("id") Long id) {
////	 System.out.println(phone.getPhoneById(id));
//        return daoPhoneImp.getPhoneById(id);
//}
	
	@ResponseBody
	@RequestMapping(path="/bgusercontroller/getcitybycountryid")	
	public List<ModelCity> getCityByCountryId(@RequestParam("countryId") Long countryId){
		
		
		
		List<ModelCity> modelCity = daoCity.getCityByCountryId(countryId);
		System.out.println("Size: "+modelCity.size());
		
		return modelCity;
	}
	
	
}
