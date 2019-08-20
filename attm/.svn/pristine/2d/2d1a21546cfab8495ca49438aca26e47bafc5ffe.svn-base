package com.biziitech.attm.bg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.bg.daoimp.DaoCurrencyImp;
import com.biziitech.attm.bg.repository.CurrencyRepository;

@Controller
public class CurrencyListController {

	@Autowired 
	private CurrencyRepository currencyRepository;
	
	@Autowired 
	private DaoCurrencyImp daoCurrencyImp;
	
	@RequestMapping(path = "/currency/list", method = RequestMethod.GET)
    public String getCurrencyList(Model model) {
	 
	   
        return "currency_list";
   }
	
	@RequestMapping(path="/currency/search")
	public String getCurrencyList(Model model,@RequestParam("currencyName")String currencyName,@RequestParam("shortCode")String shortCode
			,@RequestParam("remarks")String remarks, @RequestParam(value = "active", required = false) String active
				
											
			) {
		
        int status=1;
		
		if(active!=null)
			status=1;
		else
			status=0;
		
		model.addAttribute("currencyList", daoCurrencyImp.getCurrencyListByCraiteria(currencyName, shortCode, remarks, status));	
		return "currency_list";
	}
}
