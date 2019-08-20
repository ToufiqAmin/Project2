package com.biziitech.attm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.biziitech.attm.bg.dao.DaoCurrency;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.dao.DaoUser_attm;
import com.biziitech.attm.bg.model.ModelCity;
import com.biziitech.attm.bg.model.ModelCountry;
import com.biziitech.attm.bg.model.ModelCurrency;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.bg.repository.AirLineRepository;
import com.biziitech.attm.bg.repository.CountryRepository;
import com.biziitech.attm.bg.repository.PaymentChdRepository;
import com.biziitech.attm.bg.repository.PaymentMstRepository;
import com.biziitech.attm.bg.repository.TicketOwnerCompanyRepository;
import com.biziitech.attm.bg.repository.TicketPurchaseChdRepository;
import com.biziitech.attm.bg.repository.TicketPurchaseMstRepository;
import com.biziitech.attm.bg.repository.TicketSellChdRepository;
import com.biziitech.attm.bg.repository.TranRepository;
import com.biziitech.attm.bg.repository.UserRepository;
import com.biziitech.attm.custom.model.ModelBgUserCustom;
import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoPaymentChd;
import com.biziitech.attm.dao.DaoPaymentMst;
import com.biziitech.attm.dao.DaoTicketPurchaseCancel;
import com.biziitech.attm.dao.DaoTicketPurchaseChd;
import com.biziitech.attm.dao.DaoTicketPurchaseMst;
import com.biziitech.attm.dao.DaoTicketSellChd;
import com.biziitech.attm.dao.DaoTicketSellMst;
import com.biziitech.attm.dao.DaoTran;
import com.biziitech.attm.dao.DaoTranType;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelAirLine;
import com.biziitech.attm.model.ModelPaymentChd;
import com.biziitech.attm.model.ModelPaymentMst;
import com.biziitech.attm.model.ModelTicketOwnerCompany;
import com.biziitech.attm.model.ModelTicketPurchaseCancel;
import com.biziitech.attm.model.ModelTicketPurchaseChd;
import com.biziitech.attm.model.ModelTicketPurchaseMst;
import com.biziitech.attm.model.ModelTicketRequest;
import com.biziitech.attm.model.ModelTicketSellChd;
import com.biziitech.attm.model.ModelTicketSellMst;
import com.biziitech.attm.model.ModelTran;
import com.biziitech.attm.model.ModelTranType;


@Controller
public class TicketPurchaseController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DaoTicketPurchaseMst daoTicketPurchaseMst;
	
	@Autowired
	private TicketPurchaseMstRepository ticketPurchaseMstRepository;
	
	@Autowired
	private TicketPurchaseChdRepository ticketPurchaseChdRepository;
	
	@Autowired
	private DaoTicketPurchaseChd daoTicketPurchaseChd;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private DaoUser_attm daoUser;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private AirLineRepository airLineRepository;
	
	@Autowired
	private TranRepository tranRepository;
	
	@Autowired
	private TicketOwnerCompanyRepository ticketOwnerCompanyRepository;
	
	@Autowired
	private PaymentMstRepository paymentMstRepository;
	
	@Autowired
	private PaymentChdRepository paymentChdRepository;
	
	@Autowired
	private DaoCity daoCity;
	
	@Autowired
	private DaoTran daoTran;
	
	@Autowired
	private DaoTranType daoTranType;
	
	@Autowired
	private DaoCurrency daoCurrency;
	
	@Autowired
	private DaoTicketSellMst daoTicketSellMst;
	
	@Autowired
	private DaoTicketSellChd daoTicketSellChd;
	
	@Autowired
	private DaoPaymentMst daoPaymentMst;
	
	@Autowired
	private DaoPaymentChd daoPaymentChd;
	
	@Autowired
	private DaoTicketPurchaseCancel daoTicketPurchaseCancel;
	
	@Autowired
	private TicketSellChdRepository ticketSellChdRepository;
	
	private Long systemUserId;
	
	@RequestMapping(path="/ticketpurchasecontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		List<ModelAirLine> airLineList= airLineRepository.getAirLineName();
		model.addAttribute("airLineList",airLineList);
		
		List<ModelAgent> agentList= agentRepository.getAgentName();
		model.addAttribute("agentList",agentList);
		
		List<ModelAgent> agentList2= agentRepository.findAgentNameBySelfFalg();
		model.addAttribute("agentList2",agentList2);
		
		List<ModelCountry> countryList = countryRepository.findCountryInOrder();
		model.addAttribute("countryList", countryList);
		
		List<ModelUser> modelUser = userRepository.getUserNameInOrder();
		model.addAttribute("userList", modelUser);
		
		List<ModelTicketOwnerCompany> ticketOwnerCompanyList= ticketOwnerCompanyRepository.findTicketOwnerCompanyName();
		model.addAttribute("ticketOwnerCompanyList",ticketOwnerCompanyList);
		
		  List<ModelCity> modelCity = daoCity.getCityInOrder();
		  model.addAttribute("cityList", modelCity);
		 
		String msg=" ";
		model.addAttribute("message",msg );
		
		
		this.systemUserId=userId;
		ModelUser logonUser=daoLogon.getLogonUserName(userId);
		  
		  String userName=logonUser.getUserName();
		  
		  System.out.println("logon user name is :" + userName);
		  
		  String name="User : " + userName;
		  model.addAttribute("name",name );	
		  
		  model.addAttribute("userId",userId);
		  this.systemUserId=userId;
		  
		  
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
		 
		 return "ticketPurchase";
	}

	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/checkinguserid")
	public List<ModelBgUserCustom> checkingUserId(@RequestParam("passportNo")String passportNo){
		
		System.out.println("passportNo: " + passportNo);
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserName(passportNo);
		List<ModelBgUserCustom> modelUser = daoUser.getUserByPassportNo(passportNo);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getuserid")
	public List<ModelBgUserCustom> getUserId(@RequestParam("userId")Long userId){
		
		System.out.println("userId: " + userId);
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelBgUserCustom> modelUser = daoUser.getUserNameById(userId);
		System.out.println("Size: "+modelUser.size());
		
		return modelUser;
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(path = "/ticketpurchasecontroller/saveticketpurchasemst", method = RequestMethod.POST) 
	public List<ModelTicketPurchaseCustom> saveTicketPurchaseMst(ModelTicketPurchaseMst modelTicketPurchaseMst,
			ModelAirLine modelAirLine,ModelTicketRequest modelTicketRequest,
			@RequestParam("ticketPurchaseMstId") Long ticketPurchaseMstId,
			@RequestParam("purchaseDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date purchaseDate,
			@RequestParam("flightDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date flightDate,
			@RequestParam("ticketRequestId") Long ticketRequestId,
			//@RequestParam("airLineId") Long airLineId,
			@RequestParam("ticketOwnerCompanyId") Long ticketOwnerCompanyId,
			@RequestParam("fromAgentId") Long fromAgentId,
			@RequestParam("toAgentId") Long toAgentId,
			@RequestParam("noOfTickets") Integer noOfTickets,
			@RequestParam("totalAMT") Double totalAMT,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks,
			@RequestParam("agentPassengerName") String agentPassengerName,
			@RequestParam("userId") Long userId,
			@RequestParam("fromCountryId") Long fromCountryId,
			@RequestParam("fromCityId") Long fromCityId,
			@RequestParam("toCountryId") Long toCountryId,
			@RequestParam("toCityId") Long toCityId,
			@RequestParam("ticketAMTUSD") Double ticketAMTUSD,
			@RequestParam("ticketAMTBDT") Double ticketAMTBDT,
			@RequestParam("sellingPriceUSD") Double sellingPriceUSD,
			@RequestParam("sellingPriceBDT") Double sellingPriceBDT,
			@RequestParam("autoPayment") Integer autoPayment
			
			) {
	
		System.out.println("IN ticketpurchasecontroller saveTicketPurchaseMst Method");
		System.out.println("activeStatus: "+activeStatus+" autoPayment: "+autoPayment);
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {			
						
			if(ticketPurchaseMstId==null) 
			{
			
				System.out.println("in try con. 1");
				//System.out.println("purchaseDate: "+purchaseDate+" flightDate: "+flightDate+" ticketRequestId: "+ticketRequestId);
				modelTicketPurchaseMst.setPurchaseDate(purchaseDate);
				modelTicketPurchaseMst.setFlightDate(flightDate);
				if(ticketRequestId==0||ticketRequestId==null) 
				{
					modelTicketPurchaseMst.setModelTicketRequest(null);
				}
				else {
					modelTicketRequest.setTicketRequestId(ticketRequestId);
					modelTicketPurchaseMst.setModelTicketRequest(modelTicketRequest);
				}
				System.out.println("in try con. 2");
				
				modelTicketPurchaseMst.setNoOfTickets(noOfTickets);
				modelTicketPurchaseMst.setTotalAmount(totalAMT);
				modelTicketPurchaseMst.setActiveStatus(activeStatus);
				modelTicketPurchaseMst.setRemarks(remarks);
				modelTicketPurchaseMst.setEnteredBy(systemUserId);
				modelTicketPurchaseMst.setEntryTimeStamp(entryTime);
				
				if(ticketOwnerCompanyId==0|| ticketOwnerCompanyId==null) 
				{
					modelTicketPurchaseMst.setModelTicketOwnerCompany(null);
					modelTicketPurchaseMst.setModelTicketOwnerCompany2(null);
				}
				else {
					ModelTicketOwnerCompany modelTicketOwnerCompany = new ModelTicketOwnerCompany();
					modelTicketOwnerCompany.setTicketOwnerCompanyId(ticketOwnerCompanyId);
					modelTicketPurchaseMst.setModelTicketOwnerCompany(modelTicketOwnerCompany);
					modelTicketPurchaseMst.setModelTicketOwnerCompany2(modelTicketOwnerCompany);
				}
				
				
				if(fromAgentId==null || fromAgentId==0) 
				{
					modelTicketPurchaseMst.setModelAgent(null);
				}
				else {
					ModelAgent modelAgent = new ModelAgent();
					modelAgent.setAgentId(fromAgentId);
					modelTicketPurchaseMst.setModelAgent(modelAgent);
				}
				
				System.out.println("fromAgentId: "+fromAgentId);
				
				if(toAgentId==null || toAgentId ==0) 
				{
					toAgentId = 0L;
				}
				
				if(userId==null||userId==0) 
				{
					userId= 0L;
				}
				
				System.out.println("toAgentId: "+toAgentId);

				System.out.println("in try con. 3");
				
			daoTicketPurchaseMst.saveTicketPurchaseMst(modelTicketPurchaseMst);
			
			
			
			/*
			 * Date: 13/5/2019
			 * KTA
			 * updated: 14/5/2019
			 */
			System.out.println("ticketPurchaseMst: "+modelTicketPurchaseMst.getTicketPurchaseMstId());
			List<ModelTicketPurchaseMst> modelTicketPurchaseMstList = daoTicketPurchaseMst.getTicketPurchaseMstByMstId(modelTicketPurchaseMst.getTicketPurchaseMstId());
			System.out.println("Size: "+modelTicketPurchaseMstList.size());
						
			Long baseTranId = modelTicketPurchaseMstList.get(0).getTicketPurchaseMstId();
			System.out.println("baseTranId: "+baseTranId);
			//Long airLingId = modelTicketPurchaseMstList.get(0).getModelAirLine().getAirLineId();
			//System.out.println("airLingId: "+airLingId);
			Double tranAMT = modelTicketPurchaseMstList.get(0).getTotalAmount();
			System.out.println("tranAMT: "+tranAMT);
			
			int active = modelTicketPurchaseMstList.get(0).getActiveStatus();
			System.out.println("active: "+active);
			//System.out.println("baseTranId: "+baseTranId+" airLingId: "+airLingId+" tranAMT: "+tranAMT+
					//" fromAgentId: "+fromAgentId+" toAgentId: "+toAgentId+" active: "+active);
			
			if(autoPayment==0|| autoPayment==null) 
			{
				//Long baseTranId2 = null;
				System.out.println("In saveTicketPurchaseMst Method's if");
				this.savePurchaseChd(noOfTickets, baseTranId, agentPassengerName, userId, 
						fromCountryId, fromCityId, toCountryId, toCityId, ticketAMTUSD, 
						ticketAMTBDT, sellingPriceUSD, sellingPriceBDT,activeStatus);
				//Long ticketSellMstId =this.saveTicketSellMst(baseTranId, noOfTickets, toAgentId, activeStatus);
				
				Long tranId=this.saveTran(baseTranId,userId,fromAgentId,toAgentId,ticketOwnerCompanyId,tranAMT,active);
				this.saveTranPurchaseChd(tranId,baseTranId,userId,fromAgentId,toAgentId,activeStatus);
				
				String baseTranTable ="ATTM_TICKET_PURCHASE_CHD";
								
				Long paymentMstId=this.savePurchasePaymentMst(tranId);
				this.savePurchasePaymentChd(paymentMstId,baseTranTable,activeStatus);
				
			}
			else 
			{
				System.out.println("In saveTicketPurchaseMst Method's else");
				this.savePurchaseChd(noOfTickets, baseTranId, agentPassengerName, userId, 
						fromCountryId, fromCityId, toCountryId, toCityId, ticketAMTUSD, 
						ticketAMTBDT, sellingPriceUSD, sellingPriceBDT,activeStatus);
				
				Long tranId=this.saveTran(baseTranId,userId,fromAgentId,toAgentId,ticketOwnerCompanyId,tranAMT,active);
				this.saveTranPurchaseChd(tranId,baseTranId,userId,fromAgentId,toAgentId,activeStatus);
								
				String baseTranTable ="ATTM_TICKET_PURCHASE_CHD";				
				Long paymentMstId=this.savePurchasePaymentMst(tranId);
				this.savePurchasePaymentChd(paymentMstId,baseTranTable,activeStatus);
				
				
				Long ticketSellMstId = this.saveTicketSellMst(baseTranId, noOfTickets, toAgentId, activeStatus,ticketRequestId);
				
				//Long tranSellMstId=this.saveTranTicketSellMst(ticketSellMstId, userId, toAgentId, airLineId, tranAMT, activeStatus);
				this.saveTicketSellChd(userId,sellingPriceUSD,sellingPriceBDT,activeStatus,ticketSellMstId, 
						baseTranId,agentPassengerName);
				//ticketPurchaseMstId
				
				Long tranSellMstId=this.saveTranSellMst(tranId, ticketSellMstId, userId, fromAgentId, toAgentId, tranAMT, activeStatus);
				this.saveTranSellChd(ticketSellMstId, tranSellMstId, userId, fromAgentId, toAgentId, activeStatus);
				
				Long sellPaymentMstId = this.saveSellPaymentMst(tranSellMstId);
				String baseTranTable2 ="ATTM_TICKET_SELL_CHD";
				this.saveSellPaymentChd(sellPaymentMstId, activeStatus);
				
				Optional<ModelTran> existsTran = tranRepository.findById(tranId);
				
				
			
			}

			
			
			
		}
		else {
		System.out.println("ticketPurchaseMstId Id : " + ticketPurchaseMstId);
		
		Optional<ModelTicketPurchaseMst> existsTicketPurchaseMst=ticketPurchaseMstRepository.findById(ticketPurchaseMstId);
				
		modelTicketPurchaseMst.setPurchaseDate(purchaseDate);
		modelTicketPurchaseMst.setFlightDate(flightDate);
		modelTicketRequest.setTicketRequestId(ticketRequestId);
		modelTicketPurchaseMst.setModelTicketRequest(modelTicketRequest);
		modelTicketPurchaseMst.setNoOfTickets(noOfTickets);
		modelTicketPurchaseMst.setTotalAmount(totalAMT);
		modelTicketPurchaseMst.setActiveStatus(activeStatus);
		modelTicketPurchaseMst.setRemarks(remarks);
		modelTicketPurchaseMst.setUpdatedBy(systemUserId);
		modelTicketPurchaseMst.setUpdateTimeStamp(entryTime);
		modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
		modelTicketPurchaseMst.setEnteredBy(existsTicketPurchaseMst.get().getEnteredBy());
		modelTicketPurchaseMst.setEntryTimeStamp(existsTicketPurchaseMst.get().getEntryTimeStamp());
		ModelTicketOwnerCompany modelTicketOwnerCompany = new ModelTicketOwnerCompany();
		modelTicketOwnerCompany.setTicketOwnerCompanyId(ticketOwnerCompanyId);
		modelTicketPurchaseMst.setModelTicketOwnerCompany(modelTicketOwnerCompany);
	
		daoTicketPurchaseMst.saveTicketPurchaseMst(modelTicketPurchaseMst);
		}
			
		List<ModelTicketPurchaseCustom> modelTicketPurchaseMstCustomList = daoTicketPurchaseMst.getTicketPurchaseMstById(modelTicketPurchaseMst.getTicketPurchaseMstId());
		System.out.println("Size : " + modelTicketPurchaseMstCustomList.size());
		
		
	 return modelTicketPurchaseMstCustomList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	
}
	
	@Transactional
	public Long saveTran(Long baseTranId,
			Long userId,Long fromAgentId, Long toAgentId,Long ticketOwnerCompanyId, Double tranAMT,
			int activeStatus)
	{
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("In saveTran method");
		
		System.out.println("fromAgentId: "+fromAgentId+" toAgentId: "+toAgentId+" userId: "+userId);
		
		ModelTran modelTran = new ModelTran();
		modelTran.setBaseTranId(baseTranId);
		String x = "ATTM_TICKET_PURCHASE_MST";
		modelTran.setBaseTranTable(x);
		ModelUser modelUser = new ModelUser();
		
		if(userId==0|| userId==null) 
		{
			modelTran.setModelUser(null);
		}
		else 
		{
			modelUser.setUserId(userId);
			modelTran.setModelUser(modelUser);
			//Optional<ModelUser> modelUserOpt = userRepository.findById(userId);
			//String userName = modelUserOpt.get().getUserName();
			modelTran.setTranOwnerId(userId);
			modelTran.setTranOwnerTypeId(2);
		}
		
		if(ticketOwnerCompanyId==0||ticketOwnerCompanyId==null) 
		{
			
		}
		else 
		{
			modelTran.setTranOwnerId(ticketOwnerCompanyId);
			modelTran.setTranOwnerTypeId(3);
		}
		
		
		ModelAgent modelAgent = new ModelAgent();
		if(toAgentId==0 || toAgentId==null) 
		{
			modelTran.setModelAgent(null);
		}
		else 
		{
			modelAgent.setAgentId(toAgentId);
			modelTran.setModelAgent(modelAgent);
			modelTran.setOtherOwnerId(toAgentId);
			modelTran.setOtherOwnerTypeId(1);
		}
		
		
		if(fromAgentId==0 || fromAgentId==null) 
		{
			
			modelTran.setModelAgent(null);
		}
		else {
			modelTran.setTranOwnerId(fromAgentId);
			modelTran.setTranOwnerTypeId(1);
		}


		modelTran.setTranAMT(tranAMT);
		modelTran.setActiveStatus(activeStatus);
		modelTran.setTranDate(entryTime);
		
		ModelTranType modelTrantype = new ModelTranType();
		
		String typeName="Ticket Purchase";
		
		List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
		
		Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
		
		modelTrantype.setTranTypeId(tranTypeId);
		modelTran.setModelTranType(modelTrantype);
		
		String currencyName= "Taka";
		
		List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
		Long currencyId = modelCurrencyList.get(0).getCurrencyId();
		
		ModelCurrency modelCurrency = new ModelCurrency(); 
		modelCurrency.setCurrencyId(currencyId);
		modelTran.setModelCurrency(modelCurrency);
		modelTran.setParentTranId(null);
		
		daoTran.saveTran(modelTran);
		
		
		
		return modelTran.getTranId();
		
	}
	/*
	@Transactional
	public Long saveTranTicketSellMst(Long ticketSellMstId,
			Long userId, Long toAgentId, Long airLineId, Double tranAMT,
			int activeStatus)
	{
		System.out.println("In saveTranTicketSellMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		
		
		ModelTran modelTran = new ModelTran();
		modelTran.setBaseTranId(ticketSellMstId);
		String x = "ATTM_TICKET_PURCHASE_MST";
		modelTran.setBaseTranTable(x);
		ModelUser modelUser = new ModelUser();
		if(userId==0|| userId==null) 
		{
			modelTran.setModelUser(null);
		}
		else 
		{
			modelUser.setUserId(userId);
			modelTran.setModelUser(modelUser);
		}
		
		
		ModelAgent modelAgent = new ModelAgent();
		if(toAgentId==0 || toAgentId==null) 
		{
			modelTran.setModelAgent(null);
		}
		else 
		{
			modelAgent.setAgentId(toAgentId);
			modelTran.setModelAgent(modelAgent);
		}
		ModelAirLine modelAirLine = new ModelAirLine();
		modelAirLine.setAirLineId(airLineId);
		modelTran.setModelAirLine(modelAirLine);
		modelTran.setTranAMT(tranAMT);
		modelTran.setActiveStatus(activeStatus);
		modelTran.setTranDate(entryTime);
		
		ModelTranType modelTrantype = new ModelTranType();
		
		String typeName="Ticket Sell";
		
		List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
		
		Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
		
		modelTrantype.setTranTypeId(tranTypeId);
		modelTran.setModelTranType(modelTrantype);
		
		String currencyName= "Taka";
		
		List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
		Long currencyId = modelCurrencyList.get(0).getCurrencyId();
		
		ModelCurrency modelCurrency = new ModelCurrency(); 
		modelCurrency.setCurrencyId(currencyId);
		modelTran.setModelCurrency(modelCurrency);
		modelTran.setParentTranId(ticketSellMstId);
		
		daoTran.saveTran(modelTran);
		
		
		
		return modelTran.getTranId();
		
	}
	*/
	@Transactional
	public Long saveTranSellMst(Long tranId,Long baseTranId,
			Long userId,Long fromAgentId, Long toAgentId, Double tranAMT,
			int activeStatus)
	{
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("In saveTranSellMst method");
		
		Optional<ModelTran> modelTranOpt = tranRepository.findById(tranId);
		
		ModelTran modelTran = new ModelTran();
		modelTran.setBaseTranId(baseTranId);
		String x = "ATTM_TICKET_SELL_MST";
		modelTran.setBaseTranTable(x);
		ModelUser modelUser = new ModelUser();
		if(userId==0||userId==null) 
		{
			modelTran.setModelUser(null);
		}
		else {
			modelUser.setUserId(userId);
			modelTran.setModelUser(modelUser);
		}
		
		modelTran.setTranOwnerId(modelTranOpt.get().getOtherOwnerId());
		modelTran.setTranOwnerTypeId(modelTranOpt.get().getOtherOwnerTypeId());
		
		modelTran.setOtherOwnerId(modelTranOpt.get().getTranOwnerId());
		modelTran.setOtherOwnerTypeId(modelTranOpt.get().getTranOwnerTypeId());
		
		modelTran.setPairTranId(tranId);
		modelTran.setTranAMT(tranAMT);
		modelTran.setActiveStatus(activeStatus);
		modelTran.setTranDate(entryTime);
		
		ModelTranType modelTrantype = new ModelTranType();
		
		String typeName="Ticket Sell";
		
		List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
		
		Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
		
		modelTrantype.setTranTypeId(tranTypeId);
		modelTran.setModelTranType(modelTrantype);
		
		String currencyName= "Taka";
		
		List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
		Long currencyId = modelCurrencyList.get(0).getCurrencyId();
		
		ModelCurrency modelCurrency = new ModelCurrency(); 
		modelCurrency.setCurrencyId(currencyId);
		modelTran.setModelCurrency(modelCurrency);
		modelTran.setParentTranId(null);
		
		daoTran.saveTran(modelTran);
		
		//Optional<ModelTran> modelTranOpt = tranRepository.findById(tranId);
		ModelTran modelTran2 = new ModelTran();
		modelTran2.setBaseTranId(modelTranOpt.get().getBaseTranId());
		modelTran2.setBaseTranTable(modelTranOpt.get().getBaseTranTable());
		modelTran2.setModelAgent(modelTranOpt.get().getModelAgent());
		modelTran2.setModelCurrency(modelTranOpt.get().getModelCurrency());
		modelTran2.setModelTranType(modelTranOpt.get().getModelTranType());
		modelTran2.setModelUser(modelTranOpt.get().getModelUser());
		modelTran2.setOtherOwnerId(modelTranOpt.get().getOtherOwnerId());
		modelTran2.setOtherOwnerTypeId(modelTranOpt.get().getOtherOwnerTypeId());
		modelTran2.setTranOwnerId(modelTranOpt.get().getTranOwnerId());
		modelTran2.setTranOwnerTypeId(modelTranOpt.get().getTranOwnerTypeId());
		modelTran2.setPairTranId(modelTran.getTranId());
		modelTran2.setParentTranId(modelTranOpt.get().getParentTranId());
		modelTran2.setTranId(modelTranOpt.get().getTranId());
		modelTran2.setActiveStatus(activeStatus);
		modelTran2.setTranDate(modelTranOpt.get().getTranDate());
		modelTran2.setTranAMT(modelTranOpt.get().getTranAMT());
		
		daoTran.saveTran(modelTran2);
		
		return modelTran.getTranId();
		
	}

	
	
	@Transactional
	public void saveTranPurchaseChd(Long tranId,Long baseTranId,Long userId,Long fromAgentId, Long toAgentId,
			int activeStatus) 
	{
		List<ModelTicketPurchaseChd> modelTicketPurchaseChdList = daoTicketPurchaseChd.getTicketPurchaseChdByMstId(baseTranId);
		int n= modelTicketPurchaseChdList.size();
		System.out.println("Size: "+n);
		for(int i=0;i<n;i++) 
		{
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			System.out.println("In TranPurchaseChd method");
			
			ModelTran modelTran = new ModelTran();
			modelTran.setBaseTranId(modelTicketPurchaseChdList.get(i).getTicketPurchaseChdId());
			String x = "ATTM_TICKET_PURCHASE_CHD";
			modelTran.setBaseTranTable(x);
			ModelUser modelUser = new ModelUser();
			if(userId==0|| userId==null) 
			{
				modelTran.setModelUser(null);
			}
			else 
			{
				modelUser.setUserId(userId);
				modelTran.setModelUser(modelUser);
				modelTran.setTranOwnerId(userId);
				modelTran.setTranOwnerTypeId(2);
			}
			
			
			ModelAgent modelAgent = new ModelAgent();
			if(toAgentId==0 || toAgentId==null) 
			{
				modelTran.setModelAgent(null);
			}
			else 
			{
				modelAgent.setAgentId(toAgentId);
				modelTran.setModelAgent(modelAgent);
				modelTran.setTranOwnerId(toAgentId);
				modelTran.setTranOwnerTypeId(1);
			}

			if(fromAgentId==0 || fromAgentId ==null) 
			{
				modelTran.setOtherOwnerId(null);
				modelTran.setOtherOwnerTypeId(null);
			}
			else {
				modelTran.setOtherOwnerId(fromAgentId);
				modelTran.setOtherOwnerTypeId(1);
			}
			
			modelTran.setTranAMT(modelTicketPurchaseChdList.get(i).getTicketAMTBDT());
			modelTran.setActiveStatus(activeStatus);
			modelTran.setTranDate(entryTime);
			
			ModelTranType modelTrantype = new ModelTranType();
			
			String typeName="Ticket Purchase";
			
			List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
			
			Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
			
			modelTrantype.setTranTypeId(tranTypeId);
			modelTran.setModelTranType(modelTrantype);
			
			String currencyName= "Taka";
			
			List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
			Long currencyId = modelCurrencyList.get(0).getCurrencyId();
			
			ModelCurrency modelCurrency = new ModelCurrency(); 
			modelCurrency.setCurrencyId(currencyId);
			modelTran.setModelCurrency(modelCurrency);
			modelTran.setParentTranId(tranId);
			
			daoTran.saveTran(modelTran);
		}
	}
	
	@Transactional
	public void saveTranSellChd(Long ticketSellMstId, Long tranSellMstId,Long userId,Long fromAgentId, Long toAgentId,
			int activeStatus) 
	{
		System.out.println("in saveTranSellChd method  ticketSellMstId: "+ticketSellMstId);
		//List<ModelTicketSellCustom> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByMstId(ticketSellMstId);
		List<ModelTicketSellChd> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByMstId(ticketSellMstId);
		Optional<ModelTran> modelTranOpt = tranRepository.findById(tranSellMstId);
		int n= modelTicketSellChdList.size();
		System.out.println("Size: "+n);
		for(int i=0;i<n;i++) 
		{
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			System.out.println("In saveTranSellChd method");
			
			ModelTran modelTran = new ModelTran();
			modelTran.setBaseTranId(modelTicketSellChdList.get(i).getTicketSellChdId());
			String x = "ATTM_TICKET_SELL_CHD";
			modelTran.setBaseTranTable(x);
			ModelUser modelUser = new ModelUser();
			if(userId==0|| userId==null) 
			{
				modelTran.setModelUser(null);
			}
			else 
			{
				modelUser.setUserId(userId);
				modelTran.setModelUser(modelUser);
				
			}
			
			modelTran.setTranOwnerId(modelTranOpt.get().getTranOwnerId());
			modelTran.setTranOwnerTypeId(modelTranOpt.get().getTranOwnerTypeId());
			
			ModelAgent modelAgent = new ModelAgent();
			if(toAgentId==0 || toAgentId==null) 
			{
				modelTran.setModelAgent(null);
			}
			else 
			{
				modelAgent.setAgentId(toAgentId);
				modelTran.setModelAgent(modelAgent);
				
			}
			
			modelTran.setOtherOwnerId(modelTranOpt.get().getOtherOwnerId());
			modelTran.setOtherOwnerTypeId(modelTranOpt.get().getOtherOwnerTypeId());

			
			
			modelTran.setTranAMT(modelTicketSellChdList.get(i).getTicketAMTBDT());
			modelTran.setActiveStatus(activeStatus);
			modelTran.setTranDate(entryTime);
			
			ModelTranType modelTrantype = new ModelTranType();
			
			String typeName="Ticket Sell";
			
			List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
			
			Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
			
			modelTrantype.setTranTypeId(tranTypeId);
			modelTran.setModelTranType(modelTrantype);
			
			String currencyName= "Taka";
			
			List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
			Long currencyId = modelCurrencyList.get(0).getCurrencyId();
			
			ModelCurrency modelCurrency = new ModelCurrency(); 
			modelCurrency.setCurrencyId(currencyId);
			modelTran.setModelCurrency(modelCurrency);
			//Optional<ModelTran> modelTranOpt = tranRepository.findById(tranSellMstId);
			modelTran.setParentTranId(tranSellMstId);
			
			daoTran.saveTran(modelTran);
		}
	}
	
	
	
	
	@Transactional
	public void savePurchaseChd(Integer noOfTickets,Long ticketPurchaseMstId,String agentPassengerName,Long userId,
			Long fromCountryId, Long fromCityId, Long toCountryId, Long toCityId, 
			Double ticketAMTUSD, Double ticketAMTBDT,Double sellingPriceUSD, Double sellingPriceBDT, 
			int activeStatus) 
	{
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("In savePurchaseChd method");

		
		for(int i=0;i<noOfTickets;i++) 
		{
			System.out.println("ticketPurchaseMstId: "+ticketPurchaseMstId);
			ModelTicketPurchaseChd modelTicketPurchaseChd = new ModelTicketPurchaseChd();
			
			ModelTicketPurchaseMst modelTicketPurchaseMst = new ModelTicketPurchaseMst();
			
			modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
			modelTicketPurchaseChd.setModelTicketPurchaseMst(modelTicketPurchaseMst);
			
			System.out.println("userId: "+userId);
			modelTicketPurchaseChd.setAgentPassengerName(agentPassengerName);
			
			ModelUser modelUser = new ModelUser();
			if(userId==0|| userId==null) 
			{
				modelTicketPurchaseChd.setModelUser(null);
			}
			else 
			{
				modelUser.setUserId(userId);
				modelTicketPurchaseChd.setModelUser(modelUser);
			}
			
			System.out.println("noOfTickets: "+noOfTickets);
			
			ModelCity modelCity = new ModelCity();
			if(fromCityId==0|| fromCityId==null) {
				modelTicketPurchaseChd.setCityFrom(null);
			}
			else {
				modelCity.setCityId(fromCityId);
				modelTicketPurchaseChd.setCityFrom(modelCity);
			}
			
			
			ModelCountry modelCountry = new ModelCountry();
			if(fromCountryId==0|| fromCountryId==null) {
				modelTicketPurchaseChd.setCountryFrom(null);
			}
			else {
				modelCountry.setCountryId(fromCountryId);
				modelTicketPurchaseChd.setCountryFrom(modelCountry);
			}
			
			
			ModelCountry modelCountry2 = new ModelCountry();
			if(toCountryId==0|| toCountryId==null) {
				modelTicketPurchaseChd.setCountryTo(null);
			}
			else {
				modelCountry2.setCountryId(toCountryId);
				modelTicketPurchaseChd.setCountryTo(modelCountry2);
			}
			
			
			ModelCity modelCity2 = new ModelCity();
			if(toCityId==0||toCityId==null) {
				modelTicketPurchaseChd.setCityTo(null);
			}
			else {
				modelCity2.setCityId(toCityId);
				modelTicketPurchaseChd.setCityTo(modelCity2);
			}
			
			
			System.out.println("toCityId: "+toCityId);
			
			modelTicketPurchaseChd.setTicketAMTUSD(ticketAMTUSD);
			modelTicketPurchaseChd.setTicketAMTBDT(ticketAMTBDT);
			modelTicketPurchaseChd.setSellingPriceUSD(sellingPriceUSD);
			modelTicketPurchaseChd.setSellingPriceBDT(sellingPriceBDT);
			
			System.out.println("fromCountryId: "+fromCountryId);
			
			modelTicketPurchaseChd.setActiveStatus(activeStatus);
			modelTicketPurchaseChd.setEntryTimeStamp(entryTime);
			modelTicketPurchaseChd.setEnteredBy(systemUserId);
			modelTicketPurchaseChd.setAgentPassengerName(agentPassengerName);
			modelTicketPurchaseChd.setPurchasePriceUSD(ticketAMTUSD);
			modelTicketPurchaseChd.setPurchasePriceBDT(ticketAMTBDT);
			
			daoTicketPurchaseChd.saveTicketPurchaseChd(modelTicketPurchaseChd);
			
			
		}
		
	
	}
	
	
	@Transactional
	public void saveTicketSellChd(Long userId, Double sellingPriceUSD, Double sellingPriceBDT, 
			int activeStatus, Long ticketSellMstId, Long ticketPurchaseMstId, String agentPassengerName) 
	{
		
		
		System.out.println("In saveTicketSellChd method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		List <ModelTicketPurchaseChd> modelTicketPurchaseChdList =daoTicketPurchaseChd.getTicketPurchaseChdByMstId(ticketPurchaseMstId);
		Integer size = modelTicketPurchaseChdList.size();
		System.out.println("Size: "+size);
		for(Integer i=0; i<size; i++) 
		{
			Long ticketPurchaseChdId= modelTicketPurchaseChdList.get(i).getTicketPurchaseChdId();
			
			ModelTicketPurchaseChd modelTicketPurchaseChdId2 = new ModelTicketPurchaseChd();
			modelTicketPurchaseChdId2.setTicketPurchaseChdId(ticketPurchaseChdId);
			
			ModelTicketSellChd modelTicketSellChd = new ModelTicketSellChd();
			modelTicketSellChd.setModelTicketPurChaseChd(modelTicketPurchaseChdId2);
			
			ModelTicketSellMst modelTicketSellMst = new ModelTicketSellMst();
			modelTicketSellMst.setTicketSellMstId(ticketSellMstId);
			modelTicketSellChd.setModelTicketSellMst(modelTicketSellMst);
			ModelUser modelUser = new ModelUser();
			if(userId==0) 
			{
				modelTicketSellChd.setModelUser(null);
			}
			else {
				modelUser.setUserId(userId);
				modelTicketSellChd.setModelUser(modelUser);
			}

			modelTicketSellChd.setTicketAMTUSD(sellingPriceUSD);
			modelTicketSellChd.setTicketAMTBDT(sellingPriceBDT);
			modelTicketSellChd.setActiveStatus(activeStatus);
			modelTicketSellChd.setEnteredBy(systemUserId);
			modelTicketSellChd.setEntryTimeStamp(entryTime);
			modelTicketSellChd.setAgentPassengerName(agentPassengerName);
			
			daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
			
		}		
		
	}


	
	@Transactional
	public Long savePurchasePaymentMst(Long tranId) 
	{
		System.out.println("In savePurchasePaymentMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		List<ModelTran>modelTranList = daoTran.getTranByTranId(tranId);
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setParentTranId(modelTranList.get(0).getTranId());
		modelPaymentMst.setPaymentDate(entryTime);
		modelPaymentMst.setPaymentAmount(modelTranList.get(0).getTranAMT());
		modelPaymentMst.setModelCurrency(modelTranList.get(0).getModelCurrency());
		modelPaymentMst.setPaymentType(1);
		modelPaymentMst.setPaymentModelId(null);
		modelPaymentMst.setRemarks(null);
		modelPaymentMst.setActiveStatus(modelTranList.get(0).getActiveStatus());
		modelPaymentMst.setEnteredBy(systemUserId);
		modelPaymentMst.setEntryTimeStamp(entryTime);
		daoPaymentMst.savePaymentMst(modelPaymentMst);
		
		return modelPaymentMst.getPaymentMstId();
	}
	
	@Transactional
	public void savePurchasePaymentChd(Long paymentMstId, String baseTable, int activeStatus) 
	{
		System.out.println("In savePurchasePaymentChd method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		Optional<ModelPaymentMst> modelPaymentMstOpt = paymentMstRepository.findById(paymentMstId);
		Long parentTranId = modelPaymentMstOpt.get().getParentTranId();
		System.out.println("parentTranId: "+parentTranId);
		
		//List<ModelTran> modelTranList = daoTran.getTranByBaseTranTable(baseTable);
		List<ModelTran> modelTranList = daoTran.getTranByParentTranId(parentTranId);
		int size=modelTranList.size();
		System.out.println("Size: "+size);
		for(int i=0;i<size;i++) 
		{
			Long chidTranId= modelTranList.get(i).getTranId();
			Double tranAMT=modelTranList.get(i).getTranAMT();
		
		ModelPaymentChd modelPaymentChd = new ModelPaymentChd();
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setPaymentMstId(paymentMstId);
		modelPaymentChd.setModelPaymentMst(modelPaymentMst);
		

		modelPaymentChd.setChildTranId(chidTranId);
		modelPaymentChd.setPaymentAmount(tranAMT);
		modelPaymentChd.setActiveStatus(activeStatus);
		modelPaymentChd.setEnteredBy(systemUserId);
		modelPaymentChd.setEntryTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd);
		}
		
	}
	
	
	@Transactional
	public Long saveSellPaymentMst(Long tranId) 
	{
		System.out.println("In saveSellPaymentMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		List<ModelTran>modelTranList = daoTran.getTranByTranId(tranId);
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setParentTranId(modelTranList.get(0).getTranId());
		modelPaymentMst.setPaymentDate(entryTime);
		modelPaymentMst.setPaymentAmount(modelTranList.get(0).getTranAMT());
		modelPaymentMst.setModelCurrency(modelTranList.get(0).getModelCurrency());
		modelPaymentMst.setPaymentType(1);
		modelPaymentMst.setPaymentModelId(null);
		modelPaymentMst.setRemarks(null);
		modelPaymentMst.setActiveStatus(modelTranList.get(0).getActiveStatus());
		modelPaymentMst.setEnteredBy(systemUserId);
		modelPaymentMst.setEntryTimeStamp(entryTime);
		daoPaymentMst.savePaymentMst(modelPaymentMst);
		
		return modelPaymentMst.getPaymentMstId();
	}
	
	@Transactional
	public void saveSellPaymentChd(Long sellPaymentMstId, int activeStatus) 
	{
		System.out.println("In saveSellPaymentChd method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		Optional<ModelPaymentMst> modelPaymentMstOpt = paymentMstRepository.findById(sellPaymentMstId);
		Long parentTranId = modelPaymentMstOpt.get().getParentTranId();
		System.out.println("parentTranId: "+parentTranId);
		
		List<ModelTran> modelTranList = daoTran.getTranByParentTranId(parentTranId);
		
		int size=modelTranList.size();
		System.out.println("Size: "+size);
		for(int i=0;i<size;i++) 
		{
			Long chidTranId= modelTranList.get(i).getTranId();
			Double tranAMT=modelTranList.get(i).getTranAMT();
		
		ModelPaymentChd modelPaymentChd = new ModelPaymentChd();
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setPaymentMstId(sellPaymentMstId);
		modelPaymentChd.setModelPaymentMst(modelPaymentMst);
		

		modelPaymentChd.setChildTranId(chidTranId);
		modelPaymentChd.setPaymentAmount(tranAMT);
		modelPaymentChd.setActiveStatus(activeStatus);
		modelPaymentChd.setEnteredBy(systemUserId);
		modelPaymentChd.setEntryTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd);
		}
		
	}
	
	
	@Transactional
	public Long savePaymentMstTicketSellMst(Long tranId) 
	{
		System.out.println("In savePaymentMstTicketSellMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		Optional<ModelTran> modelTranOpt =  tranRepository.findById(tranId);
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setParentTranId(modelTranOpt.get().getParentTranId());
		modelPaymentMst.setPaymentDate(entryTime);
		modelPaymentMst.setPaymentAmount(modelTranOpt.get().getTranAMT());
		modelPaymentMst.setModelCurrency(modelTranOpt.get().getModelCurrency());
		modelPaymentMst.setPaymentType(2);
		modelPaymentMst.setPaymentModelId(null);
		modelPaymentMst.setRemarks(null);
		modelPaymentMst.setActiveStatus(modelTranOpt.get().getActiveStatus());
		modelPaymentMst.setEnteredBy(systemUserId);
		modelPaymentMst.setEntryTimeStamp(entryTime);
		daoPaymentMst.savePaymentMst(modelPaymentMst);
		
		return modelPaymentMst.getPaymentMstId();
	}
	
	@Transactional
	public Long saveTicketSellMst(Long ticketPurchaseMstId, Integer sellQty, Long toAgentId, 
			int activeStatus,Long ticketRequestId) 
	{
		System.out.println("In saveTicketSellMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		ModelTicketSellMst modelTicketSellMst = new ModelTicketSellMst();
		
		ModelTicketPurchaseMst modelTicketPurchaseMst = new ModelTicketPurchaseMst();
		modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
		modelTicketSellMst.setModelTicketPurchaseMst(modelTicketPurchaseMst);
		modelTicketSellMst.setSellQty(sellQty);
		modelTicketSellMst.setSellDate(entryTime);
		
		ModelAgent modelAgent = new ModelAgent();
		if(toAgentId==0) 
		{
			modelTicketSellMst.setModelAgent(null);
		}
		else {
			modelAgent.setAgentId(toAgentId);
			modelTicketSellMst.setModelAgent(modelAgent);
		}
		
		modelTicketSellMst.setEnteredBy(systemUserId);
		modelTicketSellMst.setEntryTimeStamp(entryTime);
		modelTicketSellMst.setActiveStatus(activeStatus);
		ModelTicketRequest modelTicketRequest = new ModelTicketRequest();
		if(ticketRequestId==0||ticketRequestId==null) 
		{
			modelTicketSellMst.setModelTicketRequest(null);
		}
		else {
			modelTicketRequest.setTicketRequestId(ticketRequestId);
			modelTicketSellMst.setModelTicketRequest(modelTicketRequest);
		}
		
		
		daoTicketSellMst.saveTicketSellMst(modelTicketSellMst);
		
		return modelTicketSellMst.getTicketSellMstId();
		
	}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getticketpurchasemstundone", method = RequestMethod.GET)
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstUnDone(
			@RequestParam("fromAgentId")Long fromAgentId,
			@RequestParam("toAgentId")Long toAgentId,
			@RequestParam("passportNo")String passportNo,
			@RequestParam("userId")Long userId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("fromCountryId")Long fromCountryId,
			@RequestParam("fromCityId")Long fromCityId,
			@RequestParam("toCountryId")Long toCountryId,
			@RequestParam("toCityId")Long toCityId){
		
		System.out.println("userId: " + userId+" fromAgentId "+fromAgentId+" toAgentId: "+toAgentId+" passportNo "+passportNo+" fromCountryId "+fromCountryId+" fromCityId "+fromCityId+" toCountryId "+toCountryId+" toCityId "+toCityId);
		
		try {
		
		List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseMst.getTicketPurchaseMstUnDone(fromAgentId,toAgentId,passportNo,userId,fromCountryId,fromCityId,toCountryId,toCityId,fromDate,toDate);
		System.out.println("Size: "+modelTicketPurchaseCustomList.size());
		
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getticketpurchasemstdone", method = RequestMethod.GET)
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstDone(
			@RequestParam("fromAgentId")Long fromAgentId,
			@RequestParam("toAgentId")Long toAgentId,
			@RequestParam("passportNo")String passportNo,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("userId")Long userId,
			@RequestParam("fromCountryId")Long fromCountryId,
			@RequestParam("fromCityId")Long fromCityId,
			@RequestParam("toCountryId")Long toCountryId,
			@RequestParam("toCityId")Long toCityId){
		
		System.out.println("userId: " + userId);
		
		try {
		
		List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseMst.getTicketPurchaseMstDone(fromAgentId,toAgentId,passportNo,userId,fromCountryId,fromCityId,toCountryId,toCityId,fromDate,toDate);
		System.out.println("Size: "+modelTicketPurchaseCustomList.size());
		
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path = "/ticketpurchasecontroller/saveticketpurchasechd", method = RequestMethod.POST) 
	public List<ModelTicketPurchaseChd> saveTicketPurchaseChd(ModelTicketPurchaseMst modelTicketPurchaseMst,
			ModelAirLine modelAirLine,ModelTicketPurchaseChd modelTicketPurchaseChd,ModelUser modelUser,
			
			@RequestParam("ticketPurchaseMstId") Long ticketPurchaseMstId,
			@RequestParam("ticketPurchaseChdId") Long ticketPurchaseChdId,
			@RequestParam("ticketNo")String ticketNo,
			@RequestParam("agentPassengerName")String agentPassengerName,
			@RequestParam("userId") Long userId,
			@RequestParam("pNR") String pNR,
			@RequestParam("fromCountryId") Long fromCountryId,
			@RequestParam("fromCityId") Long fromCityId,
			@RequestParam("toCountryId") Long toCountryId,
			@RequestParam("toCityId") Long toCityId,
			@RequestParam("ticketAMTUSD") Double ticketAMTUSD,
			@RequestParam("ticketAMTBDT") Double ticketAMTBDT,
			@RequestParam("purchasePriceUSD") Double purchasePriceUSD,
			@RequestParam("purchasePriceBDT") Double purchasePriceBDT,
			@RequestParam("sellingPriceUSD") Double sellingPriceUSD,
			@RequestParam("sellingPriceBDT") Double sellingPriceBDT,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks") String remarks
			) {
	
		System.out.println("IN ticketpurchasecontroller saveTicketPurchaseChd Method fromCity: "+fromCityId+" toCity: "+toCityId);
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {
			
			if(ticketPurchaseChdId==null) 
			{
			
				modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
				modelTicketPurchaseChd.setModelTicketPurchaseMst(modelTicketPurchaseMst);
				ModelCountry modelCountry = new ModelCountry();
				modelCountry.setCountryId(fromCountryId);
				modelTicketPurchaseChd.setCountryFrom(modelCountry);
				ModelCountry modelCountry2 = new ModelCountry();
				modelCountry2.setCountryId(toCountryId);
				modelTicketPurchaseChd.setCountryTo(modelCountry2);
				ModelCity modelCity = new ModelCity();
				modelCity.setCityId(fromCityId);
				modelTicketPurchaseChd.setCityFrom(modelCity);
				ModelCity modelCity2 = new ModelCity();
				modelCity2.setCityId(toCityId);
				modelTicketPurchaseChd.setCityTo(modelCity2);
				if(userId==0 || userId==null) 
				{
					modelTicketPurchaseChd.setModelUser(null);
				}
				else {
					modelUser.setUserId(userId);
					modelTicketPurchaseChd.setModelUser(modelUser);
				}
				
				modelTicketPurchaseChd.setpNR(pNR);
				modelTicketPurchaseChd.setTicketNo(ticketNo);
				System.out.println("Agent Passenger Name: "+agentPassengerName);
				modelTicketPurchaseChd.setAgentPassengerName(agentPassengerName);
				modelTicketPurchaseChd.setTicketAMTUSD(ticketAMTUSD);
				modelTicketPurchaseChd.setTicketAMTBDT(ticketAMTBDT);
				modelTicketPurchaseChd.setSellingPriceUSD(sellingPriceUSD);
				modelTicketPurchaseChd.setSellingPriceBDT(sellingPriceBDT);
				modelTicketPurchaseChd.setActiveStatus(activeStatus);
				modelTicketPurchaseChd.setRemarks(remarks);
				modelTicketPurchaseChd.setEnteredBy(systemUserId);
				modelTicketPurchaseChd.setEntryTimeStamp(entryTime);
				modelTicketPurchaseChd.setPurchasePriceUSD(purchasePriceUSD);
				modelTicketPurchaseChd.setPurchasePriceBDT(purchasePriceBDT);

				daoTicketPurchaseChd.saveTicketPurchaseChd(modelTicketPurchaseChd);
		}
		else {
		System.out.println("ticketPurchaseChdId Id : " + ticketPurchaseChdId);
		
		Optional<ModelTicketPurchaseChd> existsTicketPurchaseChd=ticketPurchaseChdRepository.findById(ticketPurchaseChdId);
				
		modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
		modelTicketPurchaseChd.setModelTicketPurchaseMst(modelTicketPurchaseMst);
		ModelCountry modelCountry = new ModelCountry();
		modelCountry.setCountryId(fromCountryId);
		modelTicketPurchaseChd.setCountryFrom(modelCountry);
		ModelCountry modelCountry2 = new ModelCountry();
		modelCountry2.setCountryId(toCountryId);
		modelTicketPurchaseChd.setCountryTo(modelCountry2);
		ModelCity modelCity = new ModelCity();
		modelCity.setCityId(fromCityId);
		modelTicketPurchaseChd.setCityFrom(modelCity);
		ModelCity modelCity2 = new ModelCity();
		modelCity2.setCityId(toCityId);
		modelTicketPurchaseChd.setCityTo(modelCity2);
		if(userId==0 || userId==null) 
		{
			modelTicketPurchaseChd.setModelUser(null);
		}
		else {
			modelUser.setUserId(userId);
			modelTicketPurchaseChd.setModelUser(modelUser);
		}
		modelTicketPurchaseChd.setpNR(pNR);
		modelTicketPurchaseChd.setTicketNo(ticketNo);
		modelTicketPurchaseChd.setAgentPassengerName(agentPassengerName);
		modelTicketPurchaseChd.setTicketAMTUSD(ticketAMTUSD);
		modelTicketPurchaseChd.setTicketAMTBDT(ticketAMTBDT);
		modelTicketPurchaseChd.setSellingPriceUSD(sellingPriceUSD);
		modelTicketPurchaseChd.setSellingPriceBDT(sellingPriceBDT);
		modelTicketPurchaseChd.setActiveStatus(activeStatus);
		modelTicketPurchaseChd.setRemarks(remarks);
		modelTicketPurchaseChd.setUpdatedBy(systemUserId);
		modelTicketPurchaseChd.setUpdateTimeStamp(entryTime);
		modelTicketPurchaseChd.setEnteredBy(existsTicketPurchaseChd.get().getEnteredBy());
		modelTicketPurchaseChd.setEntryTimeStamp(existsTicketPurchaseChd.get().getEntryTimeStamp());
		modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
		modelTicketPurchaseChd.setModelTicketPurchaseMst(modelTicketPurchaseMst);
		modelTicketPurchaseChd.setPurchasePriceUSD(purchasePriceUSD);
		modelTicketPurchaseChd.setPurchasePriceBDT(purchasePriceBDT);
	
		daoTicketPurchaseChd.saveTicketPurchaseChd(modelTicketPurchaseChd);
		
		// ticketSellChdEdit
		
		List<ModelTicketSellChd> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByTicketPurchaseChdId(modelTicketPurchaseChd.getTicketPurchaseChdId());
			
		Long ticketSellChdId = modelTicketSellChdList.get(0).getTicketSellChdId();
		Long ticketSellMstId = modelTicketSellChdList.get(0).getModelTicketSellMst().getTicketSellMstId();
		
		ModelTicketPurchaseChd modelTicketPurchaseChdId2 = new ModelTicketPurchaseChd();
		modelTicketPurchaseChdId2.setTicketPurchaseChdId(ticketPurchaseChdId);
		
		ModelTicketSellChd modelTicketSellChd = new ModelTicketSellChd();
		modelTicketSellChd.setModelTicketPurChaseChd(modelTicketPurchaseChdId2);
		
		ModelTicketSellMst modelTicketSellMst = new ModelTicketSellMst();
		modelTicketSellMst.setTicketSellMstId(ticketSellMstId);
		modelTicketSellChd.setModelTicketSellMst(modelTicketSellMst);
		modelTicketSellChd.setModelUser(modelUser);
		modelTicketSellChd.setTicketAMTUSD(sellingPriceUSD);
		modelTicketSellChd.setTicketAMTBDT(sellingPriceBDT);
		modelTicketSellChd.setActiveStatus(activeStatus);
		modelTicketSellChd.setEnteredBy(modelTicketSellChdList.get(0).getEnteredBy());
		modelTicketSellChd.setEntryTimeStamp(modelTicketSellChdList.get(0).getEntryTimeStamp());
		modelTicketSellChd.setUpdatedBy(systemUserId);
		modelTicketSellChd.setUpdateTimeStamp(entryTime);
		modelTicketSellChd.setpNR(pNR);
		modelTicketSellChd.setAgentPassengerName(agentPassengerName);
		modelTicketSellChd.setTicketSellChdId(ticketSellChdId);
		
		daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
		
		
		}
			
		List<ModelTicketPurchaseChd> modelTicketPurchaseChdList = daoTicketPurchaseChd.getTicketPurchaseChdByMstId(modelTicketPurchaseChd.getModelTicketPurchaseMst().getTicketPurchaseMstId());
		System.out.println("Size : " + modelTicketPurchaseChdList.size());

	 return modelTicketPurchaseChdList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	
}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getticketpurchasechdbymstid", method = RequestMethod.GET)
	public List<ModelTicketPurchaseCustom> getTicketPurchaseChdByMstId(
			@RequestParam("ticketPurchaseMstId")Long ticketPurchaseMstId
			){
		
		System.out.println("ticketPurchaseMstId: " + ticketPurchaseMstId);
		
		try {
		System.out.println("IN getTicketPurchaseChdByMstId Method");
		List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseChd.getTicketPurchaseChdByMst(ticketPurchaseMstId);
		System.out.println("Size: "+modelTicketPurchaseCustomList.size());
		
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getticketpurchasemstbyid", method = RequestMethod.GET)
	public List<ModelTicketPurchaseCustom> getTicketPurchaseMstById(
			@RequestParam("ticketPurchaseMstId")Long ticketPurchaseMstId){
		
		System.out.println("ticketPurchaseMstId: " + ticketPurchaseMstId);
		
		try {
		
			List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseMst.getTicketPurchaseMstById(ticketPurchaseMstId);
			System.out.println("Size : " + modelTicketPurchaseCustomList.size());
		
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getcitybycountryid")	
	public List<ModelCity> getCityByCountryId(@RequestParam("countryId") Long countryId){
		
		
		
		List<ModelCity> modelCity = daoCity.getCityByCountryId(countryId);
		System.out.println("Size: "+modelCity.size());
		
		return modelCity;
	}
	
	

	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getpurchasemst")	
	public List<ModelTicketPurchaseCustom> getPurchaseMst(@RequestParam("ticketPurchaseMstId")Long ticketPurchaseMstId){
		
		System.out.println("ticketPurchaseMstId: " + ticketPurchaseMstId);
		
		try {
		
			List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseMst.getTicketPurchaseMstById(ticketPurchaseMstId);
			System.out.println("Size : " + modelTicketPurchaseCustomList.size());
		
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	

	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/getpurchasechd")	
	public List<ModelTicketPurchaseChd> getPurchaseChd(@RequestParam("ticketPurchaseMstId")Long ticketPurchaseMstId){
		
System.out.println("ticketPurchaseMstId: " + ticketPurchaseMstId);
		
		try {
		
		List<ModelTicketPurchaseChd> modelTicketPurchaseChdList = daoTicketPurchaseChd.getTicketPurchaseChdByMstId(ticketPurchaseMstId);
		System.out.println("Size : " + modelTicketPurchaseChdList.size());

	 return modelTicketPurchaseChdList;
	 
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketpurchasecontroller/saveticketpurchasecancel", method = RequestMethod.POST)	
	public List<ModelTicketPurchaseCustom> SaveTicketPurchaseCancel(ModelTicketPurchaseCancel modelTicketPurchaseCancel,
			@RequestParam("ticketPurchaseCancelId")Long ticketPurchaseCancelId,
			@RequestParam("ticketPurchaseChdId")Long ticketPurchaseChdId,
			@RequestParam("ticketPurchaseMstId")Long ticketPurchaseMstId,
			@RequestParam("cancelDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date cancelDate,
			@RequestParam("qty")Integer qty,
			@RequestParam("cancelReason")String cancelReason,
			@RequestParam("penaltyAmount")Double penaltyAmount,
			@RequestParam("remarks")String remarks,
			@RequestParam("activeStatus")int activeStatus){
		
		System.out.println("in ticketPurChaseController saveticketpurchasecancel method: " + ticketPurchaseChdId);
		
		try {
		
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
				
		Optional<ModelTicketPurchaseChd> modelTicketPurchaseCancelOpt = ticketPurchaseChdRepository.findById(ticketPurchaseChdId);
					modelTicketPurchaseCancel.setCancelDate(cancelDate);
					ModelTicketPurchaseChd modelTicketPurchaseChd = new ModelTicketPurchaseChd();
					modelTicketPurchaseChd.setTicketPurchaseChdId(ticketPurchaseChdId);
					modelTicketPurchaseCancel.setModelTicketPurchaseChd(modelTicketPurchaseChd);
					modelTicketPurchaseCancel.setQty(qty);
					modelTicketPurchaseCancel.setCancelReason(cancelReason);
					modelTicketPurchaseCancel.setPenaltyAmount(penaltyAmount);
					modelTicketPurchaseCancel.setActiveStatus(activeStatus);
					modelTicketPurchaseCancel.setEnteredBy(systemUserId);
					modelTicketPurchaseCancel.setEntryTimeStamp(entryTime);
					
					daoTicketPurchaseCancel.saveTicketPurchaseCancel(modelTicketPurchaseCancel);
					
					System.out.println("now purchaseChd modification");
					
					ModelTicketPurchaseChd modelTicketPurchaseChd2 = new ModelTicketPurchaseChd();
					modelTicketPurchaseChd2.setAgentPassengerName(modelTicketPurchaseCancelOpt.get().getAgentPassengerName());
					modelTicketPurchaseChd2.setCityFrom(modelTicketPurchaseCancelOpt.get().getCityFrom());
					modelTicketPurchaseChd2.setCityTo(modelTicketPurchaseCancelOpt.get().getCityTo());
					modelTicketPurchaseChd2.setCountryFrom(modelTicketPurchaseCancelOpt.get().getCountryFrom());
					modelTicketPurchaseChd2.setCountryTo(modelTicketPurchaseCancelOpt.get().getCountryTo());
					modelTicketPurchaseChd2.setEnteredBy(modelTicketPurchaseCancelOpt.get().getEnteredBy());
					modelTicketPurchaseChd2.setEntryTimeStamp(modelTicketPurchaseCancelOpt.get().getEntryTimeStamp());
					modelTicketPurchaseChd2.setModelTicketPurchaseMst(modelTicketPurchaseCancelOpt.get().getModelTicketPurchaseMst());
					modelTicketPurchaseChd2.setModelUser(modelTicketPurchaseCancelOpt.get().getModelUser());
					modelTicketPurchaseChd2.setpNR(modelTicketPurchaseCancelOpt.get().getpNR());
					modelTicketPurchaseChd2.setPurchasePriceBDT(modelTicketPurchaseCancelOpt.get().getPurchasePriceBDT());
					modelTicketPurchaseChd2.setPurchasePriceUSD(modelTicketPurchaseCancelOpt.get().getPurchasePriceUSD());
					modelTicketPurchaseChd2.setRemarks(remarks);
					modelTicketPurchaseChd2.setSellingPriceBDT(modelTicketPurchaseCancelOpt.get().getPurchasePriceBDT());
					modelTicketPurchaseChd2.setSellingPriceUSD(modelTicketPurchaseCancelOpt.get().getPurchasePriceUSD());
					modelTicketPurchaseChd2.setTicketAMTBDT(modelTicketPurchaseCancelOpt.get().getTicketAMTBDT());
					modelTicketPurchaseChd2.setTicketAMTUSD(modelTicketPurchaseCancelOpt.get().getTicketAMTUSD());
					modelTicketPurchaseChd2.setTicketNo(modelTicketPurchaseCancelOpt.get().getTicketNo());
					modelTicketPurchaseChd2.setTicketPurchaseChdId(ticketPurchaseChdId);
					modelTicketPurchaseChd2.setUpdatedBy(systemUserId);
					modelTicketPurchaseChd2.setUpdateTimeStamp(entryTime);
					modelTicketPurchaseChd2.setActiveStatus(0);
					
					daoTicketPurchaseChd.saveTicketPurchaseChd(modelTicketPurchaseChd2);
			
			Double singleAMT = modelTicketPurchaseCancelOpt.get().getPurchasePriceBDT();		
					
			//this.saveTicketPurchaseCancelMst(ticketPurchaseMstId, singleAMT);
			
			Long ticketSellChdId= this.saveSellChdPurchaseCancel(ticketPurchaseChdId);
			Optional<ModelTicketSellChd> modelTicketSellChdOpt = ticketSellChdRepository.findById(ticketSellChdId);
			List<ModelTicketSellMst> modelTicketSellMstList = daoTicketSellMst.getTicketSellMstByPurchaseMstId(ticketPurchaseMstId);
			//Long ticketSellMstId = modelTicketSellChdOpt.get().getModelTicketSellMst().getTicketSellMstId();
			Long ticketSellMstId = modelTicketSellMstList.get(0).getTicketSellMstId();
			//Long ticketPurchase
			Long childTranId= this.saveTicketPurchaseCancelTran(ticketSellMstId, ticketSellChdId, penaltyAmount);
			List<ModelTran> modelTranList = daoTran.getTranByBaseTranId(ticketSellChdId);
			Long previousChildTranId = modelTranList.get(0).getTranId();
					
			List<ModelTicketSellChd> modelTicketSellChd = daoTicketSellChd.getTicketSellChdByTicketPurchaseChdId(ticketPurchaseChdId);
			
			Long paymentMstPurchaseCancelId = this.savePaymentMstPurchaseCancel(childTranId);		
				//this.savePaymentChdPuchaseChdCancel(previousChildTranId, childTranId, penaltyAmount);	
			this.savePaymentChdPuchaseChdCancel(paymentMstPurchaseCancelId, childTranId, penaltyAmount);		
				
					
			List<ModelTicketPurchaseCustom> modelTicketPurchaseCustomList = daoTicketPurchaseChd.getTicketPurchaseChdByMst(ticketPurchaseMstId);		
			System.out.println("Size: "+modelTicketPurchaseCustomList.size());
			
		return modelTicketPurchaseCustomList;
		
		}		
		catch(Exception e) {			
		 e.printStackTrace();
		 System.out.println("error");
			return null;
		}
	}
	
	public void saveTicketPurchaseCancelMst(Long ticketPurchaseMstId, Double singleAMT) {
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		
		System.out.println("In saveTicketPurchaseCancelMst method");
		
		Optional<ModelTicketPurchaseMst> modelTicketPurchaseOpt = ticketPurchaseMstRepository.findById(ticketPurchaseMstId);
		int i = modelTicketPurchaseOpt.get().getNoOfTickets();
		//Double amount= modelTicketPurchaseOpt.get().getTotalAmount()-singleAMT;
		Double amount= modelTicketPurchaseOpt.get().getTotalAmount();
		//i=i-1;
		ModelTicketPurchaseMst modelTicketPurchaseMst = new ModelTicketPurchaseMst();
		modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
		modelTicketPurchaseMst.setFlightDate(modelTicketPurchaseOpt.get().getFlightDate());
		modelTicketPurchaseMst.setPurchaseDate(modelTicketPurchaseOpt.get().getPurchaseDate());
		modelTicketPurchaseMst.setModelTicketOwnerCompany(modelTicketPurchaseOpt.get().getModelTicketOwnerCompany());
		modelTicketPurchaseMst.setModelTicketRequest(modelTicketPurchaseOpt.get().getModelTicketRequest());
		modelTicketPurchaseMst.setRemarks(modelTicketPurchaseOpt.get().getRemarks());
		if(i==0) 
		{
			modelTicketPurchaseMst.setActiveStatus(0);
		}
		else {
			modelTicketPurchaseMst.setActiveStatus(modelTicketPurchaseOpt.get().getActiveStatus());
		}
		
		modelTicketPurchaseMst.setTotalAmount(amount);
		modelTicketPurchaseMst.setEnteredBy(modelTicketPurchaseOpt.get().getEnteredBy());
		modelTicketPurchaseMst.setEntryTimeStamp(modelTicketPurchaseOpt.get().getEntryTimeStamp());
		modelTicketPurchaseMst.setUpdatedBy(systemUserId);
		modelTicketPurchaseMst.setUpdateTimeStamp(entryTime);
		modelTicketPurchaseMst.setNoOfTickets(i);
		
		daoTicketPurchaseMst.saveTicketPurchaseMst(modelTicketPurchaseMst);
		
		
		
	}
	
	public Long saveSellChdPurchaseCancel(Long ticketPuchaseChdId) 
	{
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("In saveSellChdPurchaseCancel Method");
		
		List<ModelTicketSellChd>modelTicketSellChdOpt = daoTicketSellChd.getTicketSellChdByTicketPurchaseChdId(ticketPuchaseChdId);
		ModelTicketSellChd modelTicketSellChd = new ModelTicketSellChd();
		modelTicketSellChd.setActiveStatus(0);
		modelTicketSellChd.setAgentPassengerName(null);
		modelTicketSellChd.setModelUser(null);
		modelTicketSellChd.setpNR(null);
		modelTicketSellChd.setRemarks(null);
		modelTicketSellChd.setQty(1);
		modelTicketSellChd.setTicketAMTBDT(modelTicketSellChdOpt.get(0).getTicketAMTBDT());
		modelTicketSellChd.setTicketAMTUSD(modelTicketSellChdOpt.get(0).getTicketAMTUSD());
		modelTicketSellChd.setUpdatedBy(systemUserId);
		modelTicketSellChd.setUpdateTimeStamp(entryTime);
		modelTicketSellChd.setTicketSellChdId(modelTicketSellChdOpt.get(0).getTicketSellChdId());
		modelTicketSellChd.setModelTicketPurChaseChd(modelTicketSellChdOpt.get(0).getModelTicketPurChaseChd());
		modelTicketSellChd.setModelTicketSellMst(modelTicketSellChdOpt.get(0).getModelTicketSellMst());
		
		daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
		
		return modelTicketSellChd.getTicketSellChdId();
	
	}
	
	public Long saveTicketPurchaseCancelTran(Long ticketSellMstId,Long ticketSellChdId, Double penaltyAMT) 
	{
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("in saveTicketPurchaseCancelTran method");
		
		System.out.println("ticketSellChdId: "+ticketSellChdId+" ticketSellMstId: "+ticketSellMstId);
		
		List<ModelTran> modelTranList = daoTran.getTranByBaseTranId(ticketSellChdId);
		System.out.println("Size: "+modelTranList.size());
		
		Double amount = penaltyAMT;
		
		ModelTran modelTran = new ModelTran();
		modelTran.setBaseTranId(ticketSellChdId);
		modelTran.setParentTranId(null);
		String x ="ATTM_TICKET_PURCHASE_CANCEL";
		modelTran.setBaseTranTable(x);
		modelTran.setModelCurrency(modelTranList.get(0).getModelCurrency());
		modelTran.setOtherOwnerId(modelTranList.get(0).getOtherOwnerId());
		modelTran.setOtherOwnerTypeId(modelTranList.get(0).getOtherOwnerTypeId());
		modelTran.setTranDate(entryTime);
		modelTran.setTranOwnerId(modelTranList.get(0).getTranOwnerId());
		modelTran.setTranOwnerTypeId(modelTranList.get(0).getTranOwnerTypeId());
		modelTran.setTranAMT(amount);
		modelTran.setActiveStatus(modelTranList.get(0).getActiveStatus());
		
		ModelTranType modelTrantype = new ModelTranType();
		
		String typeName="Ticket Purchase Cancel";
		
		List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
		
		Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
		
		modelTrantype.setTranTypeId(tranTypeId);
		modelTran.setModelTranType(modelTrantype);
		
		daoTran.saveTran(modelTran);
		
		return modelTran.getTranId();
	}
	
	
	//public void savePaymentChdPuchaseChdCancel(Long previousChildTranId,Long childTranId,Double penaltyAMT)
	public void savePaymentChdPuchaseChdCancel(Long paymentMstPurchaseCancelId,Long childTranId,Double penaltyAMT)
	{
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("in savePaymentChdPuchaseChdCancel method");
		
		/*
		List<ModelPaymentChd> modelPaymentChdList = daoPaymentChd.getPaymentChdIdByChilTran(previousChildTranId);
		ModelPaymentChd modelPaymentChd = new ModelPaymentChd();
		modelPaymentChd.setActiveStatus(0);
		modelPaymentChd.setChildTranId(previousChildTranId);
		modelPaymentChd.setEnteredBy(modelPaymentChdList.get(0).getEnteredBy());
		modelPaymentChd.setEntryTimeStamp(modelPaymentChdList.get(0).getEntryTimeStamp());
		modelPaymentChd.setModelPaymentMst(modelPaymentChdList.get(0).getModelPaymentMst());
		modelPaymentChd.setPaymentAmount(null);
		modelPaymentChd.setPaymentChdId(modelPaymentChdList.get(0).getChildTranId());
		modelPaymentChd.setRemarks(modelPaymentChdList.get(0).getRemarks());
		modelPaymentChd.setUpdatedBy(systemUserId);
		modelPaymentChd.setUpdateTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd);
		*/
		ModelPaymentChd modelPaymentChd2 = new ModelPaymentChd();
		modelPaymentChd2.setActiveStatus(1);
		modelPaymentChd2.setChildTranId(childTranId);
		modelPaymentChd2.setEnteredBy(systemUserId);
		modelPaymentChd2.setEntryTimeStamp(entryTime);
		//modelPaymentChd2.setModelPaymentMst(modelPaymentChdList.get(0).getModelPaymentMst());
		ModelPaymentMst modelPaymentMst= new ModelPaymentMst();
		modelPaymentMst.setPaymentMstId(paymentMstPurchaseCancelId);
		modelPaymentChd2.setModelPaymentMst(modelPaymentMst);
		modelPaymentChd2.setPaymentAmount(penaltyAMT);
		modelPaymentChd2.setRemarks(null);
		//modelPaymentChd2.setUpdatedBy(systemUserId);
		//modelPaymentChd2.setUpdateTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd2);
		
	}
	
	public Long savePaymentMstPurchaseCancel(Long parentTranId) 
	{
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("in savePaymentMstPurchaseCancel method");
		
		Optional<ModelTran> modelTranOpt = tranRepository.findById(parentTranId);
		ModelPaymentMst modelPaymenyMst = new ModelPaymentMst();
		modelPaymenyMst.setPaymentDate(entryTime);
		modelPaymenyMst.setParentTranId(parentTranId);
		modelPaymenyMst.setPaymentAmount(modelTranOpt.get().getTranAMT());
		modelPaymenyMst.setPaymentType(1);
		modelPaymenyMst.setActiveStatus(1);
		modelPaymenyMst.setEnteredBy(systemUserId);
		modelPaymenyMst.setEntryTimeStamp(entryTime);
		modelPaymenyMst.setModelCurrency(modelTranOpt.get().getModelCurrency());
		modelPaymenyMst.setPaymentModelId(null);
		
		
		daoPaymentMst.savePaymentMst(modelPaymenyMst);
		
		
		return modelPaymenyMst.getPaymentMstId();
		
	}
	
	
}
