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

import com.biziitech.attm.bg.dao.DaoCurrency;
import com.biziitech.attm.bg.dao.DaoUser;
import com.biziitech.attm.bg.dao.DaoUserObject;
import com.biziitech.attm.bg.model.ModelCurrency;
import com.biziitech.attm.bg.model.ModelUser;
import com.biziitech.attm.bg.model.ModelUserObject;
import com.biziitech.attm.bg.repository.AgentRepository;
import com.biziitech.attm.bg.repository.PaymentMstRepository;
import com.biziitech.attm.bg.repository.TicketPurchaseChdRepository;
import com.biziitech.attm.bg.repository.TicketPurchaseMstRepository;
import com.biziitech.attm.bg.repository.TicketRequestRepository;
import com.biziitech.attm.bg.repository.TicketSellChdRepository;
import com.biziitech.attm.bg.repository.TicketSellMstRepository;
import com.biziitech.attm.bg.repository.TranRepository;
import com.biziitech.attm.bg.repository.UserRepository;
import com.biziitech.attm.custom.model.ModelTicketPurchaseCustom;
import com.biziitech.attm.custom.model.ModelTicketSellCustom;
import com.biziitech.attm.dao.DaoLogon;
import com.biziitech.attm.dao.DaoPaymentChd;
import com.biziitech.attm.dao.DaoPaymentMst;
import com.biziitech.attm.dao.DaoTicketSellCancel;
import com.biziitech.attm.dao.DaoTicketSellChd;
import com.biziitech.attm.dao.DaoTicketSellMst;
import com.biziitech.attm.dao.DaoTran;
import com.biziitech.attm.dao.DaoTranType;
import com.biziitech.attm.model.ModelAgent;
import com.biziitech.attm.model.ModelAirLine;
import com.biziitech.attm.model.ModelPaymentChd;
import com.biziitech.attm.model.ModelPaymentMst;
import com.biziitech.attm.model.ModelTicketPurchaseChd;
import com.biziitech.attm.model.ModelTicketPurchaseMst;
import com.biziitech.attm.model.ModelTicketRequest;
import com.biziitech.attm.model.ModelTicketSellCancel;
import com.biziitech.attm.model.ModelTicketSellChd;
import com.biziitech.attm.model.ModelTicketSellMst;
import com.biziitech.attm.model.ModelTran;
import com.biziitech.attm.model.ModelTranType;


@Controller
public class TicketSellController {
	
	@Autowired
	private DaoTicketSellMst daoTicketSellMst;
	
	@Autowired
	private DaoTicketSellChd daoTicketSellChd;
	
	@Autowired
	private DaoTicketSellCancel daoTicketSellCancel;
		
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private TranRepository tranRepository;
	
	@Autowired
	private PaymentMstRepository paymentMstRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DaoUser daoUser;
	
	@Autowired
	private DaoUserObject daoUserObject;
	
	@Autowired
	private DaoLogon daoLogon;
	
	@Autowired
	private DaoTran daoTran;
	
	@Autowired
	private DaoTranType daoTranType;
	
	@Autowired
	private DaoCurrency daoCurrency;
	
	@Autowired
	private TicketRequestRepository ticketRequestRepository;
	
	@Autowired
	private TicketSellChdRepository ticketSellChdRepository;
	
	@Autowired
	private TicketSellMstRepository ticketSellMstRepository;
	
	@Autowired
	private TicketPurchaseChdRepository ticketPurchaseChdRepository;
	
	@Autowired
	private TicketPurchaseMstRepository ticketPurchaseMstRepository;
	
	@Autowired
	private DaoPaymentMst daoPaymentMst;
	
	@Autowired
	private DaoPaymentChd daoPaymentChd;
	
	
	private Long systemUserId;
	
	@RequestMapping(path="/ticketsellcontroller/init/{userId}")
	public String init(@PathVariable Long userId,Model model) {
		
		
		
		List<ModelAgent> agentList= agentRepository.getAgentName();
		model.addAttribute("agentList",agentList);
		
		List<ModelUser> modelUser = userRepository.getUserNameInOrder();
		model.addAttribute("userList", modelUser);
		
		
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
		
		
		
		return "ticketSell";	
	}
	
/*	
	@ResponseBody	
	@RequestMapping(path = "/ticketsellcontroller/saveticketsellchd", method = RequestMethod.POST) 
	public List<ModelTicketSellCustom> saveTicketSellChd(ModelTicketSellChd modelTicketSellChd,
			@RequestParam("ticketSellChdId") Long ticketSellChdId,
			@RequestParam("ticketSellMstId") Long ticketSellMstId,
			@RequestParam("ticketPurchaseChdId") Long ticketPurchaseChdId,
			@RequestParam("sellUserId") Long sellUserId,
			@RequestParam("pNR") String pNR,
			@RequestParam("agentPassengerName") String agentPassengerName,
			@RequestParam("ticketAMTUSD") Double ticketAMTUSD,
			@RequestParam("ticketAMTBDT") Double ticketAMTBDT,
			@RequestParam("activeStatus") int activeStatus,
			@RequestParam("remarks")String remarks
			)
	*/
	
	public void saveTicketSellChd(Long ticketSellMstId, Long ticketPurchaseChdId, Long paymentMstId,Long tranSellMstId,Long userId)
	{
		
		System.out.println("In ticketsellcontroller saveTicketSellChd Method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		Optional<ModelTicketSellMst>existsSellMst = ticketSellMstRepository.findById(ticketSellMstId);
		
		int i = existsSellMst.get().getSellQty();
		
		for(int j=0;j<i;j++) {
		
		ModelTicketSellChd modelTicketSellChd = new ModelTicketSellChd();
		
		ModelTicketSellMst modelTicketSellMst = new ModelTicketSellMst();
		modelTicketSellMst.setTicketSellMstId(ticketSellMstId);
		modelTicketSellChd.setModelTicketSellMst(modelTicketSellMst);
		
		Optional<ModelTicketPurchaseChd> existsPurchaseChd = ticketPurchaseChdRepository.findById(ticketPurchaseChdId);
		
		ModelTicketPurchaseChd modelTicketPurchaseChd = new ModelTicketPurchaseChd();
		if(ticketPurchaseChdId== 0 || ticketPurchaseChdId==null) 
		{
			
			modelTicketSellChd.setModelTicketPurChaseChd(null);
		}
		else 
		{
			modelTicketPurchaseChd.setTicketPurchaseChdId(ticketPurchaseChdId);
			modelTicketSellChd.setModelTicketPurChaseChd(modelTicketPurchaseChd);
		}
		
		
		//Long sellUserId;
		ModelUser modelUser= new ModelUser();
		if(userId==0 || userId==null) {
			modelTicketSellChd.setModelUser(null);
		}
		else {
			modelUser.setUserId(userId);
			modelTicketSellChd.setModelUser(modelUser);
		}
		
		
		/*
		ModelUser modelUser = new ModelUser();
		if(sellUserId ==0 || sellUserId== null) 
		{
			modelTicketSellChd.setModelUser(null);
		}
		else 
		{
			modelUser.setUserId(sellUserId);
			modelTicketSellChd.setModelUser(modelUser);
		}
		*/
		
		//modelTicketSellChd.setModelUser(existsPurchaseChd.get().getModelUser());
		
		String pNR = existsPurchaseChd.get().getpNR();
		modelTicketSellChd.setpNR(pNR);
		String agentPassengerName = existsPurchaseChd.get().getAgentPassengerName();
		modelTicketSellChd.setAgentPassengerName(agentPassengerName);
		Double ticketAMTUSD = existsPurchaseChd.get().getTicketAMTUSD();
		modelTicketSellChd.setTicketAMTUSD(ticketAMTUSD);
		Double ticketAMTBDT = existsPurchaseChd.get().getTicketAMTBDT();
		modelTicketSellChd.setTicketAMTBDT(ticketAMTBDT);
		int activeStatus = existsPurchaseChd.get().getActiveStatus();
		modelTicketSellChd.setActiveStatus(activeStatus);
		String remarks = existsPurchaseChd.get().getRemarks();
		modelTicketSellChd.setRemarks(remarks);
		modelTicketSellChd.setEnteredBy(systemUserId);
		modelTicketSellChd.setEntryTimeStamp(entryTime);
		modelTicketSellChd.setQty(1);
		daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
		
		
		
		Long toAgentId;
		Long baseTranId = modelTicketSellChd.getTicketSellChdId();
		
		Double ticketAMT = modelTicketSellChd.getTicketAMTBDT();
		
		//Optional<ModelTicketPurchaseMst> existTicketPurchaseMst = ticketPurchaseMstRepository.findById(existsPurchaseChd.get().getModelTicketPurchaseMst().getTicketPurchaseMstId());
		//Optional<ModelTicketSellMst> existsSellMst = ticketSellMstRepository.findById(ticketSellMstId);
		if(existsSellMst.get().getModelAgent()!=null) 
		{
			toAgentId = existsSellMst.get().getModelAgent().getAgentId();
		}
		else 
		{
			toAgentId = 0L;
		}
		//Long airLineId=existTicketPurchaseMst.get().getModelTicketOwnerCompany2().getTicketOwnerCompanyId();
		
		Long tranId = null;
		String x = "ATTM_TICKET_SELL_CHD";
		//this.saveTran(tranId, baseTranId,ticketSellMstId, ticketAMT, sellUserId, toAgentId, airLineId, activeStatus,x);
		
		Long fromAgentId;
		if(existsSellMst.get().getModelTicketRequest().getFromAgent()==null) 
		{
			fromAgentId=null;
		}
		else {
			fromAgentId=existsSellMst.get().getModelTicketRequest().getFromAgent().getAgentId();
		}
		
		Long tranSellChdId=this.saveTranSellChd(ticketSellMstId, tranSellMstId, userId, fromAgentId, toAgentId,
				 activeStatus);
		
		this.savePaymentChd(paymentMstId,ticketSellMstId,baseTranId,activeStatus,tranSellChdId);
		
		}
		
		}
		
			
			
			
			
		
		@ResponseBody	
		@RequestMapping(path = "/ticketsellcontroller/updateticketsellchd", method = RequestMethod.POST) 
		public List<ModelTicketSellCustom> updateTicketSellChd(ModelTicketSellChd modelTicketSellChd,
				@RequestParam("ticketSellChdId") Long ticketSellChdId,
				@RequestParam("ticketSellMstId") Long ticketSellMstId,
				@RequestParam("ticketPurchaseChdId") Long ticketPurchaseChdId,
				@RequestParam("sellUserId") Long sellUserId,
				@RequestParam("pNR") String pNR,
				@RequestParam("sellQty") int sellQty,
				@RequestParam("agentPassengerName") String agentPassengerName,
				@RequestParam("ticketAMTUSD") Double ticketAMTUSD,
				@RequestParam("ticketAMTBDT") Double ticketAMTBDT,
				@RequestParam("activeStatus") int activeStatus,
				@RequestParam("remarks")String remarks
				)	
		{
			System.out.println("In ticketsellcontroller saveTicketSellChd Method");
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			try {
				Optional<ModelTicketSellChd> existTicketSellChd = ticketSellChdRepository.findById(ticketSellChdId);
				
				
				modelTicketSellChd.setModelTicketSellMst(existTicketSellChd.get().getModelTicketSellMst());
				
				
				modelTicketSellChd.setModelTicketPurChaseChd(existTicketSellChd.get().getModelTicketPurChaseChd());
				
				ModelUser modelUser = new ModelUser();
				if(sellUserId ==0 || sellUserId== null) 
				{
					modelTicketSellChd.setModelUser(null);
				}
				else 
				{
					modelUser.setUserId(sellUserId);
					modelTicketSellChd.setModelUser(modelUser);
				}
				
				modelTicketSellChd.setpNR(pNR);
				modelTicketSellChd.setAgentPassengerName(agentPassengerName);
				modelTicketSellChd.setTicketAMTUSD(ticketAMTUSD);
				modelTicketSellChd.setTicketAMTBDT(ticketAMTBDT);
				modelTicketSellChd.setActiveStatus(activeStatus);
				modelTicketSellChd.setRemarks(remarks);
				modelTicketSellChd.setEnteredBy(existTicketSellChd.get().getEnteredBy());
				modelTicketSellChd.setEntryTimeStamp(existTicketSellChd.get().getEntryTimeStamp());
				modelTicketSellChd.setUpdatedBy(systemUserId);
				modelTicketSellChd.setUpdateTimeStamp(entryTime);
				modelTicketSellChd.setQty(sellQty);
				modelTicketSellChd.setTicketSellChdId(ticketSellChdId);
				daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
				
				String baseTranTable = "ATTM_TICKET_SELL_CHD";
				
				List<ModelTran> existsTran = daoTran.getTranByBaseTranId(modelTicketSellChd.getTicketSellChdId(), baseTranTable);
				
				//Long tranId = existsTran.get(0).getTranId();
				//Long baseTranId = existsTran.get(0).getBaseTranId();
				//Long toAgentId = existsTran.get(0).getModelAgent().getAgentId();
				//Long airLineId = existsTran.get(0).getModelAirLine().getAirLineId();
				//Double ticketAMT = modelTicketSellChd.getTicketAMTBDT();
				
				//this.saveTran(tranId, baseTranId,ticketSellMstId, ticketAMT, sellUserId, toAgentId, airLineId, activeStatus,baseTranTable);
				
				
				
				List<ModelTicketSellCustom> modelTicketSellChdList =  daoTicketSellChd.getTicketSellChdById(ticketSellChdId);	
				
				return modelTicketSellChdList;
				
				
			}		
			catch(Exception e) {			
			e.printStackTrace();
			System.out.println("error");
			return null;
			}
			
		}
	
	public Long saveTranMst(Long tranId, Long ticketSellMstId, Double tranAMT,
			Long userId, Long toAgentId, Long airLineId, int activeStatus, String baseTranTable)
	{
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		System.out.println("In saveTranMst method");
		
		System.out.println("tranId: "+tranId+" baseTranId: "+ticketSellMstId+" tranAMT: "+tranAMT+" userId: "+userId+" toAgentId: "+toAgentId+" airLineId: "+airLineId+" activeStatus: "+activeStatus);
		
		ModelTran modelTran = new ModelTran();
		
		if(tranId==null)
		{
		
			//ModelTran modelTran = new ModelTran();
			
			modelTran.setBaseTranId(ticketSellMstId);
			
			System.out.println("saveTran 1");
			modelTran.setBaseTranTable(baseTranTable);
			ModelUser modelUser = new ModelUser();
			System.out.println("saveTran 2");
			if(userId==0||userId==null) 
			{
				modelTran.setModelUser(null);
			}
			else 
			{
				modelUser.setUserId(userId);
				modelTran.setModelUser(modelUser);
			}
			
			ModelAgent modelAgent = new ModelAgent();
			System.out.println("saveTran 3");
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
			System.out.println("saveTran 4");
			modelTran.setModelAirLine(modelAirLine);
			modelTran.setTranAMT(tranAMT);
			modelTran.setActiveStatus(activeStatus);
			modelTran.setTranDate(entryTime);
			modelTran.setTranOwnerId(toAgentId);
			modelTran.setTranOwnerTypeId(1);
			modelTran.setOtherOwnerId(userId);
			modelTran.setOtherOwnerTypeId(2);
			System.out.println("saveTran 5");
			ModelTranType modelTrantype = new ModelTranType();
			
			String typeName="Ticket Sell";
			
			List<ModelTranType> modelTranTypeList = daoTranType.getTranTypeByName(typeName);
			
			Long tranTypeId = modelTranTypeList.get(0).getTranTypeId();
			
			modelTrantype.setTranTypeId(tranTypeId);
			modelTran.setModelTranType(modelTrantype);
			
			System.out.println("saveTran 6");
			String currencyName= "Taka";
			
			List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
			Long currencyId = modelCurrencyList.get(0).getCurrencyId();
			
			ModelCurrency modelCurrency = new ModelCurrency(); 
			modelCurrency.setCurrencyId(currencyId);
			modelTran.setModelCurrency(modelCurrency);
			modelTran.setParentTranId(null);
			System.out.println("saveTran 7");
			
			daoTran.saveTran(modelTran);
		
				
		}
		else {
			
		Optional<ModelTran> existsTran = tranRepository.findById(tranId);
		System.out.println("baseTranId: "+existsTran.get().getBaseTranId());
			
			modelTran.setBaseTranId(existsTran.get().getBaseTranId());
			String x = existsTran.get().getBaseTranTable();
			System.out.println("x: "+x);
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
			modelTran.setTranDate(existsTran.get().getTranDate());
			
			
			modelTran.setModelTranType(existsTran.get().getModelTranType());
			
			String currencyName= "Taka";
			
			List<ModelCurrency> modelCurrencyList = daoCurrency.getCurrencyByName(currencyName);
			Long currencyId = modelCurrencyList.get(0).getCurrencyId();
			
			ModelCurrency modelCurrency = new ModelCurrency(); 
			modelCurrency.setCurrencyId(currencyId);
			modelTran.setModelCurrency(modelCurrency);
			modelTran.setTranId(existsTran.get().getTranId());
			modelTran.setParentTranId(existsTran.get().getParentTranId());
			daoTran.saveTran(modelTran);
			
		}
		
		return modelTran.getTranId();
	}
	
	@Transactional
	public Long saveTranSellChd(Long ticketSellMstId, Long tranSellMstId,Long userId,Long fromAgentId, Long toAgentId,
			int activeStatus) 
	{
		System.out.println("in saveTranSellChd method  ticketSellMstId: "+ticketSellMstId);
		//List<ModelTicketSellCustom> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByMstId(ticketSellMstId);
		List<ModelTicketSellChd> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByMstId(ticketSellMstId);
		Optional<ModelTran> modelTranOpt = tranRepository.findById(tranSellMstId);
		int n= modelTicketSellChdList.size();
		System.out.println("Size: "+n);
		
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			System.out.println("In saveTranSellChd method");
			
			ModelTran modelTran = new ModelTran();
			modelTran.setBaseTranId(modelTicketSellChdList.get(0).getTicketSellChdId());
			String x = "ATTM_TICKET_SELL_CHD";
			modelTran.setBaseTranTable(x);
			ModelUser modelUser = new ModelUser();
			if(userId==null) 
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
			
			
			modelTran.setModelAirLine(modelTranOpt.get().getModelAirLine());
			
			modelTran.setOtherOwnerId(modelTranOpt.get().getOtherOwnerId());
			modelTran.setOtherOwnerTypeId(modelTranOpt.get().getOtherOwnerTypeId());

			
			
			modelTran.setTranAMT(modelTicketSellChdList.get(0).getTicketAMTBDT());
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
		
			return modelTran.getTranId();
	}
	
	
	@Transactional
	public Long saveTranSellChdCancel(Long ticketSellChdId, Long tranSellChdCancelId,Long userId,Long fromAgentId, Long toAgentId,Double penaltyAMT) 
	{
		System.out.println("in saveTranSellChdCancel method  ticketSellChdId: "+ticketSellChdId);
		List<ModelTran> modelTranList = daoTran.getTranByBaseTranId(ticketSellChdId);
		Optional<ModelTicketSellChd> existsChd = ticketSellChdRepository.findById(ticketSellChdId);
		
		Long ticketRequestId=existsChd.get().getModelTicketSellMst().getModelTicketRequest().getRequesterId();
		Optional<ModelTicketRequest> existsRequest = ticketRequestRepository.findById(ticketRequestId);
		
		Long airLineId = existsRequest.get().getModelTicketOwnerCompany().getTicketOwnerCompanyId();
		
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
			
			System.out.println("In saveTranSellChdCancel method");
			
			ModelTran modelTran = new ModelTran();
			modelTran.setBaseTranId(tranSellChdCancelId);
			String x = "ATTM_TICKET_SELL_CANCEL";
			modelTran.setBaseTranTable(x);
			ModelUser modelUser = new ModelUser();
			if(userId==null) 
			{
				modelTran.setModelUser(null);
			}
			else 
			{
				modelUser.setUserId(userId);
				modelTran.setModelUser(modelUser);
				
			}
			
			modelTran.setTranOwnerId(modelTranList.get(0).getTranOwnerId());
			modelTran.setTranOwnerTypeId(modelTranList.get(0).getTranOwnerTypeId());
			
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
			
			modelTran.setOtherOwnerId(modelTranList.get(0).getOtherOwnerId());
			modelTran.setOtherOwnerTypeId(modelTranList.get(0).getOtherOwnerTypeId());
			
			ModelAirLine modelAirLine = new ModelAirLine();
			modelAirLine.setAirLineId(airLineId);
			modelTran.setModelAirLine(modelAirLine);
			
			modelTran.setTranAMT(penaltyAMT);
			modelTran.setActiveStatus(1);
			modelTran.setTranDate(entryTime);
			
			ModelTranType modelTrantype = new ModelTranType();
			
			String typeName="Ticket Sell Cancel";
			
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
			modelTran.setParentTranId(modelTranList.get(0).getTranId());
			
			daoTran.saveTran(modelTran);
		
			return modelTran.getTranId();
	}
	
	@Transactional
	public Long savePaymentMst(Long baseTranId, String baseTranTable,Long tranSellMstId) 
	{
		System.out.println("In savePaymentMst method");
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		System.out.println("baseTranId: "+baseTranId+" baseTranTable: "+baseTranTable);
		List<ModelTran>modelTranList = daoTran.getTranByBaseTranId(baseTranId);
		//Optional<ModelTicketSellMst>exixtsSellMst = ticketSellMstRepository.findById(baseTranId);
		System.out.println("Size: "+modelTranList.size());
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		//ModelTran modelTran = new ModelTran();
		//modelTran.setParentTranId(baseTranId);
		modelPaymentMst.setParentTranId(tranSellMstId);
		modelPaymentMst.setPaymentDate(entryTime);
		modelPaymentMst.setPaymentAmount(modelTranList.get(0).getTranAMT());
		modelPaymentMst.setModelCurrency(modelTranList.get(0).getModelCurrency());
		modelPaymentMst.setPaymentType(2);
		modelPaymentMst.setPaymentModelId(null);
		modelPaymentMst.setRemarks(null);
		modelPaymentMst.setActiveStatus(modelTranList.get(0).getActiveStatus());
		modelPaymentMst.setEnteredBy(systemUserId);
		modelPaymentMst.setEntryTimeStamp(entryTime);
		daoPaymentMst.savePaymentMst(modelPaymentMst);
		
		return modelPaymentMst.getPaymentMstId();
	}
	
	@Transactional
	public void savePaymentChd(Long paymentMstId,Long ticketSellMstId,Long baseTranId, int activeStatus,Long tranSellChdId) 
	{
		System.out.println("In savePaymentChd method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		String baseTable="ATTM_TICKET_SELL_CHD";
		System.out.println("baseTable 1 ticketSellMstId: "+ticketSellMstId);
		//List<ModelTran> modelTranList = daoPaymentMst.getTranByParentTranId(ticketSellMstId);
		//System.out.println("Size: "+modelTranList.size());
		//Long paymentMstId = paymentMstId;
		System.out.println("baseTable 2 ");
		List<ModelTran> modelTranList = daoTran.getTranByBaseTranId(baseTranId, baseTable);
		
		int size=modelTranList.size();
		System.out.println("Size: "+size);
		for(int i=0;i<size;i++) 
		{
			Long childTranId= modelTranList.get(i).getTranId();
			Double tranAMT=modelTranList.get(i).getTranAMT();
		
		ModelPaymentChd modelPaymentChd = new ModelPaymentChd();
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setPaymentMstId(paymentMstId);
		modelPaymentChd.setModelPaymentMst(modelPaymentMst);
		
		//ModelTran modelTran = new ModelTran();
		//modelTran.setTranId(chidTranId);
		modelPaymentChd.setChildTranId(childTranId);
		modelPaymentChd.setPaymentAmount(tranAMT);
		modelPaymentChd.setActiveStatus(activeStatus);
		modelPaymentChd.setEnteredBy(systemUserId);
		modelPaymentChd.setEntryTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd);
		}
		
	}
	
	@ResponseBody	
	@RequestMapping(path = "/ticketsellcontroller/saveticketsellmst", method = RequestMethod.POST) 
	public List<ModelTicketSellCustom> saveTicketSellMst(ModelTicketSellMst modelTicketSellMst,
			//@RequestParam("ticketSellMstId") Long ticketSellMstId,
			@RequestParam("sellDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date sellDate,
			@RequestParam("ticketPurchaseMstId") Long ticketPurchaseMstId,
			@RequestParam("ticketPurchaseChdId") Long ticketPurchaseChdId,
			@RequestParam("ticketRequestId") Long ticketRequestId,
			@RequestParam("sellQty") Integer sellQty,
			@RequestParam("toAgentId") Long toAgentId,
			@RequestParam("userId") Long userId,
			@RequestParam("remarks") String remarks,
			@RequestParam("activeStatus") int activeStatus
			
			){
		
		System.out.println("In ticketsellcontroller saveTicketSellMst Method");
		System.out.println("userId: "+userId);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {
			
			Optional<ModelTicketPurchaseMst> existTicketPurchaseMst = ticketPurchaseMstRepository.findById(ticketPurchaseMstId);
			
			modelTicketSellMst.setSellDate(sellDate);
			modelTicketSellMst.setSellQty(sellQty);
			
			ModelTicketPurchaseMst modelTicketPurchaseMst = new ModelTicketPurchaseMst();
			modelTicketPurchaseMst.setTicketPurchaseMstId(ticketPurchaseMstId);
			modelTicketSellMst.setModelTicketPurchaseMst(modelTicketPurchaseMst);
			
			ModelTicketRequest modelTicketRequest = new ModelTicketRequest();
			if(ticketRequestId==0||ticketRequestId==null) 
			{
				modelTicketSellMst.setModelTicketRequest(null);
			}
			else {
			
			modelTicketRequest.setTicketRequestId(ticketRequestId);
			modelTicketSellMst.setModelTicketRequest(modelTicketRequest);
			
			}
			
			ModelAgent modelAgent = new ModelAgent();
			if(toAgentId==0 || toAgentId==null)
			{
				modelTicketSellMst.setModelAgent(null);
			}
			else {
				modelAgent.setAgentId(toAgentId);
				modelTicketSellMst.setModelAgent(modelAgent);
			}
			modelTicketSellMst.setActiveStatus(activeStatus);
			modelTicketSellMst.setRemarks(remarks);
			modelTicketSellMst.setEnteredBy(systemUserId);
			modelTicketSellMst.setEntryTimeStamp(entryTime);
			
			
			daoTicketSellMst.saveTicketSellMst(modelTicketSellMst);
			
			Optional<ModelTicketPurchaseChd> existsPurchaseChd = ticketPurchaseChdRepository.findById(ticketPurchaseChdId);
			
			Long ticketSellMstId = modelTicketSellMst.getTicketSellMstId();
			
			Long airLineId;
			
			if(existTicketPurchaseMst.get().getModelTicketOwnerCompany2().getTicketOwnerCompanyId()==0||existTicketPurchaseMst.get().getModelTicketOwnerCompany2().getTicketOwnerCompanyId()==null) 
			{
				airLineId=null;
			}
			else {
				airLineId=existTicketPurchaseMst.get().getModelTicketOwnerCompany2().getTicketOwnerCompanyId();
			}
			
			
			Double ticketAMT=existsPurchaseChd.get().getSellingPriceBDT();
			Long tranId = null;
			//Long sellUserId = modelTicketSellMst.getModelTicketRequest().getRequesterId();
			
			String baseTranTable= "ATTM_TICKET_SELL_MST";
						
			Long tranSellMstId=this.saveTranMst(tranId, ticketSellMstId, ticketAMT,
					userId,toAgentId,airLineId,activeStatus, baseTranTable);
			
			Long paymentMstId=this.savePaymentMst(ticketSellMstId,baseTranTable,tranSellMstId);
			
			this.saveTicketSellChd(ticketSellMstId, ticketPurchaseChdId,paymentMstId,tranSellMstId,userId);
			
			List<ModelTicketSellCustom> modelTicketSellMstList = daoTicketSellMst.getTicketSellMstByMstId(modelTicketSellMst.getTicketSellMstId());
			System.out.println("Size: "+modelTicketSellMstList.size());
		
		return modelTicketSellMstList;
		
				}		
				catch(Exception e) {			
				e.printStackTrace();
				System.out.println("error");
				return null;
				}
		}
	
	
	@ResponseBody	
	@RequestMapping(path = "/ticketsellcontroller/updateticketsellmst", method = RequestMethod.POST) 
	public List<ModelTicketSellCustom> updateTicketSellMst(ModelTicketSellMst modelTicketSellMst,
			@RequestParam("ticketSellMstId") Long ticketSellMstId,
			@RequestParam("sellDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date sellDate,
			@RequestParam("ticketRequestId") Long ticketRequestId,
			@RequestParam("sellQty") Integer sellQty,
			@RequestParam("toAgentId") Long toAgentId,
			@RequestParam("remarks") String remarks,
			@RequestParam("activeStatus") int activeStatus
			
			){
		
		System.out.println("In ticketsellcontroller updateTicketSellMst Method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		try {
			
			Optional<ModelTicketSellMst> existTicketSellMst = ticketSellMstRepository.findById(ticketSellMstId);
			
			modelTicketSellMst.setSellDate(sellDate);
			modelTicketSellMst.setSellQty(sellQty);
			
			ModelTicketPurchaseMst modelTicketPurchaseMst = new ModelTicketPurchaseMst();
			modelTicketPurchaseMst.setTicketPurchaseMstId(existTicketSellMst.get().getModelTicketPurchaseMst().getTicketPurchaseMstId());
			modelTicketSellMst.setModelTicketPurchaseMst(modelTicketPurchaseMst);
			
			ModelTicketRequest modelTicketRequest = new ModelTicketRequest();
			if(ticketRequestId==0||ticketRequestId==null) 
			{
				modelTicketSellMst.setModelTicketRequest(null);
			}
			else {
			
			modelTicketRequest.setTicketRequestId(ticketRequestId);
			modelTicketSellMst.setModelTicketRequest(modelTicketRequest);
			
			}
			
			ModelAgent modelAgent = new ModelAgent();
			if(toAgentId==null)
			{
				modelTicketSellMst.setModelAgent(null);
			}
			else {
				modelAgent.setAgentId(toAgentId);
				modelTicketSellMst.setModelAgent(modelAgent);
			}
			modelTicketSellMst.setActiveStatus(activeStatus);
			modelTicketSellMst.setRemarks(remarks);
			modelTicketSellMst.setEnteredBy(existTicketSellMst.get().getEnteredBy());
			modelTicketSellMst.setEntryTimeStamp(existTicketSellMst.get().getEntryTimeStamp());
			modelTicketSellMst.setUpdatedBy(systemUserId);
			modelTicketSellMst.setUpdateTimeStamp(entryTime);
			
			modelTicketSellMst.setTicketSellMstId(ticketSellMstId);
			
			daoTicketSellMst.saveTicketSellMst(modelTicketSellMst);
			
			
			
			List<ModelTicketSellCustom> modelTicketSellMstList = daoTicketSellMst.getTicketSellMstByMstId(modelTicketSellMst.getTicketSellMstId());
			System.out.println("Size: "+modelTicketSellMstList.size());
		
		return modelTicketSellMstList;
		
				}		
				catch(Exception e) {			
				e.printStackTrace();
				System.out.println("error");
				return null;
				}
		}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getticketsellundone")
	public List<ModelTicketSellCustom> getTicketSellUnDone(
			@RequestParam("userId")Long userId,
			@RequestParam("fromCountryId")Long fromCountryId,
			@RequestParam("fromCityId")Long fromCityId,
			@RequestParam("toCountryId")Long toCountryId,
			@RequestParam("toCityId")Long toCityId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("fromAgentId")Long fromAgentId,
			@RequestParam("toAgentId")Long toAgentId
			
			){
		
		System.out.println("In ticketSellController getTicketSellUnDone Method");
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelTicketSellCustom> modelTicketSellChdList = daoTicketSellMst.getTicketSellUnDoneChdDetails(userId,
				fromCountryId,fromCityId,toCountryId,toCityId,fromAgentId,toAgentId,fromDate,toDate);
		System.out.println("Size: "+modelTicketSellChdList.size());
		
		return modelTicketSellChdList;
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getticketselldone")
	public List<ModelTicketSellCustom> getTicketSellDone(
			@RequestParam("userId")Long userId,
			@RequestParam("fromCountryId")Long fromCountryId,
			@RequestParam("fromCityId")Long fromCityId,
			@RequestParam("toCountryId")Long toCountryId,
			@RequestParam("toCityId")Long toCityId,
			@RequestParam("fromDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date fromDate,
			@RequestParam("toDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date toDate,
			@RequestParam("fromAgentId")Long fromAgentId,
			@RequestParam("toAgentId")Long toAgentId
			
			){
		
		System.out.println("In ticketSellController getTicketSellDone Method");
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelTicketSellCustom> modelTicketSellChdList = daoTicketSellMst.getTicketSellDoneChdDetails(userId,
				fromCountryId,fromCityId,toCountryId,toCityId,fromAgentId,toAgentId,fromDate,toDate);
		System.out.println("Size: "+modelTicketSellChdList.size());
		
		return modelTicketSellChdList;
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getticketsellmstbyrequestid")
	public List<ModelTicketSellCustom> getTicketSellMstByRequestId(
			@RequestParam("ticketRequestId")Long ticketRequestId
			
			
			){
		
		System.out.println("In ticketSellController getTicketSellMstByRequestId Method");
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelTicketSellCustom> modelTicketSellMstList = daoTicketSellMst.getTicketSellMstByRequestId(ticketRequestId);
		System.out.println("Size: "+modelTicketSellMstList.size());
		
		return modelTicketSellMstList;
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getticketsellchdbymstid")
	public List<ModelTicketSellCustom> getTicketSellChdByMstId(
			@RequestParam("ticketSellMstId")Long ticketSellMstId
			
			
			){
		
		System.out.println("In ticketSellController getTicketSellChdByMstId Method");
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelTicketSellCustom> modelTicketSellChdList = daoTicketSellChd.getTicketSellChdByMstId2(ticketSellMstId);
		System.out.println("Size: "+modelTicketSellChdList.size());
		
		return modelTicketSellChdList;
	}
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getselfticketpurchase")
	public List<ModelTicketPurchaseCustom> getSelfTicketPurchase(
			@RequestParam("ticketRequestId")Long ticketRequestId
			
			
			){
		
		System.out.println("In ticketSellController getSelfTicketPurchase Method");
		
		
		//List<ModelBgUser> modelUser = bgUserRepository.getUserDetailsById(userId);
		List<ModelTicketPurchaseCustom> selfTicketPurchaseList = daoTicketSellMst.getSelfTicketPurchase();
		System.out.println("Size: "+selfTicketPurchaseList.size());
		
		return selfTicketPurchaseList;
	}
	
	
	@ResponseBody
	@RequestMapping(path="/ticketsellcontroller/getsellchdcancel")
	public List<ModelTicketSellCustom> getSellChdCancel(ModelTicketSellCancel modelTicketSellCancel,
			@RequestParam("ticketSellChdId")Long ticketSellChdId,
			@RequestParam("cancelDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date cancelDate,
			@RequestParam("sellChdCancelReason")String sellChdCancelReason,
			@RequestParam("sellChdCancelRemarks")String sellChdCancelRemarks,
			@RequestParam("penaltyAMT")Double penaltyAMT,
			@RequestParam("cancelQty")int cancelQty
			
			
			
			){
		
		System.out.println("In ticketSellController getSellChdCancel Method");
		
		//System.out.println("In ticketsellcontroller saveTicketSellMst Method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		
		modelTicketSellCancel.setCancelDate(cancelDate);
		modelTicketSellCancel.setCancelReason(sellChdCancelReason);
		modelTicketSellCancel.setEnteredBy(systemUserId);
		modelTicketSellCancel.setEntryTimeStamp(entryTime);
		modelTicketSellCancel.setPenaltyAmount(penaltyAMT);
		modelTicketSellCancel.setQty(cancelQty);
		daoTicketSellCancel.saveTicketSellCancel(modelTicketSellCancel);
		
		
		
		Optional<ModelTicketSellChd> existsChd = ticketSellChdRepository.findById(ticketSellChdId);
		
		ModelTicketSellChd modelTicketSellChd = new ModelTicketSellChd();
		modelTicketSellChd.setActiveStatus(0);
		modelTicketSellChd.setAgentPassengerName(existsChd.get().getAgentPassengerName());
		modelTicketSellChd.setEnteredBy(existsChd.get().getEnteredBy());
		modelTicketSellChd.setEntryTimeStamp(existsChd.get().getEntryTimeStamp());
		modelTicketSellChd.setModelTicketPurChaseChd(existsChd.get().getModelTicketPurChaseChd());
		modelTicketSellChd.setModelTicketSellMst(existsChd.get().getModelTicketSellMst());
		modelTicketSellChd.setModelUser(existsChd.get().getModelUser());
		modelTicketSellChd.setpNR(existsChd.get().getpNR());
		modelTicketSellChd.setQty(existsChd.get().getQty());
		modelTicketSellChd.setRemarks(existsChd.get().getRemarks());
		modelTicketSellChd.setTicketAMTBDT(existsChd.get().getTicketAMTBDT());
		modelTicketSellChd.setTicketAMTUSD(existsChd.get().getTicketAMTUSD());
		modelTicketSellChd.setUpdatedBy(systemUserId);
		modelTicketSellChd.setUpdateTimeStamp(entryTime);
		modelTicketSellChd.setTicketSellChdId(ticketSellChdId);
		daoTicketSellChd.saveTicketSellChd(modelTicketSellChd);
		
		Long ticketSellMstId=existsChd.get().getModelTicketSellMst().getTicketSellMstId();
		
		Optional<ModelTicketSellMst> existsMst = ticketSellMstRepository.findById(ticketSellMstId);
		
		Long fromAgentId;
		
		if(existsMst.get().getModelTicketRequest().getFromAgent()==null) 
		{
			fromAgentId=null;	
		}
		else {
			fromAgentId=existsMst.get().getModelTicketRequest().getFromAgent().getAgentId();
		}
		Long toAgentId;
		if(existsMst.get().getModelTicketRequest().getToAgent()==null)
		{
			toAgentId=null;
		}
		else {
			toAgentId=existsMst.get().getModelTicketRequest().getToAgent().getAgentId();
		}
		
		Long userId;
		if(existsChd.get().getModelUser()==null) 
		{
			userId=null;
		}else {
			userId=existsChd.get().getModelUser().getUserId();
		}
		
		Long tranSellChdCancelId=modelTicketSellCancel.getTicketSellCancelId();
		
		Long tranChdCancelId=this.saveTranSellChdCancel(ticketSellChdId,tranSellChdCancelId,userId,fromAgentId, toAgentId,penaltyAMT);
		
		Long paymentMstId = this.saveCancelPaymentMst(tranChdCancelId);
		this.saveCancelPaymentChd(paymentMstId,tranChdCancelId);
		
		List<ModelTicketSellCustom> sellChdList = daoTicketSellChd.getTicketSellChdByMstId2(ticketSellMstId);
		System.out.println("Size: "+sellChdList.size());
		
		return sellChdList;
	}
	
	@Transactional
	public Long saveCancelPaymentMst(Long tranChdCancelId) 
	{
		System.out.println("In saveCancelPaymentMst method");
		
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		System.out.println("tranChdCancelId: "+tranChdCancelId);
		Optional<ModelTran>modelTranOpt = tranRepository.findById(tranChdCancelId);
		//Optional<ModelTicketSellMst>exixtsSellMst = ticketSellMstRepository.findById(baseTranId);
		//System.out.println("Size: "+modelTranList.size());
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		//ModelTran modelTran = new ModelTran();
		//modelTran.setParentTranId(baseTranId);
		modelPaymentMst.setParentTranId(tranChdCancelId);
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
	public void saveCancelPaymentChd(Long paymentMstId,Long tranChdCancelId) 
	{
		System.out.println("In saveCancelPaymentChd method");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
		
		String baseTable="ATTM_TICKET_SELL_CHD";
		System.out.println("baseTable 1 paymentMstId: "+paymentMstId);
		//List<ModelTran> modelTranList = daoPaymentMst.getTranByParentTranId(ticketSellMstId);
		//System.out.println("Size: "+modelTranList.size());
		//Long paymentMstId = paymentMstId;
		System.out.println("baseTable 2 ");
		Optional<ModelTran>modelTranOpt = tranRepository.findById(tranChdCancelId);
		//List<ModelTran> modelTranList = daoTran.getTranByBaseTranId(baseTranId, baseTable);
		
		//int size=modelTranList.size();
		//System.out.println("Size: "+size);
		//for(int i=0;i<size;i++) 
		//{
			Long childTranId= modelTranOpt.get().getTranId();
			Double tranAMT=modelTranOpt.get().getTranAMT();
		
		ModelPaymentChd modelPaymentChd = new ModelPaymentChd();
		
		ModelPaymentMst modelPaymentMst = new ModelPaymentMst();
		modelPaymentMst.setPaymentMstId(paymentMstId);
		modelPaymentChd.setModelPaymentMst(modelPaymentMst);
		
		//ModelTran modelTran = new ModelTran();
		//modelTran.setTranId(chidTranId);
		modelPaymentChd.setChildTranId(childTranId);
		modelPaymentChd.setPaymentAmount(tranAMT);
		modelPaymentChd.setActiveStatus(1);
		modelPaymentChd.setEnteredBy(systemUserId);
		modelPaymentChd.setEntryTimeStamp(entryTime);
		
		daoPaymentChd.savePaymentChd(modelPaymentChd);
		//}
		
	}

}
