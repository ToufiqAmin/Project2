package com.biziitech.attm.bg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.bg.daoimp.DaoCurrencyImp;
import com.biziitech.attm.bg.model.ModelCurrency;
import com.biziitech.attm.bg.repository.CurrencyRepository;



@Controller
public class CurrencyController {

	@Autowired
	private DaoCurrencyImp daoCurrencyImp;
	
	@Autowired 
	private CurrencyRepository currencyRepository;
	
	 @RequestMapping(path = "/currency/init", method = RequestMethod.GET)
	    public String createCurrency(Model model) {
		 
		 ModelCurrency currency=new ModelCurrency();
		 model.addAttribute("currency", currency);
		 
		 String msg=" ";
	     model.addAttribute("message", " ");
	        
	     return "currency";

	}
	 
	 @RequestMapping(path = "/currency/save", method = RequestMethod.POST) 
 	public  String saveCurrency(ModelCurrency currency, @RequestParam(value = "defalutFlag", required = false) String defalutFlag) {
 	    
		 
		 
		// ModelCurrency modelCurrency= new ModelCurrency();
  
		 
		 if(defalutFlag==null) {
			 currency.setDefaultFlag(0);
		 }
		 else {
			 currency.setDefaultFlag(1);
		 }
		
	
 	if(currency.getCurrencyId()==null )
 			{
 			java.util.Date date = new java.util.Date();
 			java.sql.Timestamp entryTime = new java.sql.Timestamp(date.getTime());
 			currency.setEntryTimestamp(entryTime);
 			currency.setEnteredBy(1L);
 			
 			daoCurrencyImp.saveCurrency(currency);
 			}
 			else
 			{
 				Optional<ModelCurrency> existscurrency=currencyRepository.findById(currency.getCurrencyId());
 				currency.setEnteredBy(existscurrency.get().getEnteredBy());
 				currency.setEntryTimestamp(existscurrency.get().getEntryTimestamp());
 				daoCurrencyImp.saveCurrency(currency);
				
 			}
 
 	 return "redirect:/currency/init/"+currency.getCurrencyId();
 	
   }
	 
	   @RequestMapping(path = "/currency/init/{id}", method = RequestMethod.GET)
	    public String editCurrency(@PathVariable Long id, Model model) {
	    
	     Optional<ModelCurrency> currencyById;
	     currencyById=currencyRepository.findById(id);
	     if( currencyById.get().getActiveStatus()==1)
	    	 currencyById.get().setActive(true); 
	      else
	    	  currencyById.get().setActive(false);	
	          model.addAttribute("currency", currencyById);
				
		 
	        if(id!=0) {
	        	String msg="Successfully Saved";
	        	 model.addAttribute("message",msg );
	        }
	        else
	        {
	        	String msg="Some Error Occured";
	        	 model.addAttribute("message",msg);
	        }
	               
	        return "currency";
	}
	   
	   
	   @RequestMapping(path = "/currency/update/{id}", method = RequestMethod.GET)
	    public String updateCurrency(@PathVariable Long id, Model model) {
	    	
		   Optional<ModelCurrency> currencyById;
		     currencyById=currencyRepository.findById(id);
		     if( currencyById.get().getActiveStatus()==1)
		    	 currencyById.get().setActive(true); 
		      else
		    	  currencyById.get().setActive(false);	
		          model.addAttribute("currency", currencyById);
					
			 
		        if(id!=0) {
		        	String msg="";
		        	 model.addAttribute("message",msg );
		        }
		        else
		        {
		        	String msg="Some Error Occured";
		        	 model.addAttribute("message",msg);
		        }
		               
		        return "currency";
	    } 	
	 
	 
	
}
