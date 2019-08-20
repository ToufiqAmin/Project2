package com.biziitech.attm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.dao.DaoManPowerClearance;




@Controller
public class ManPowerClearanceListController {
	
	@Autowired
	private DaoManPowerClearance daoManPowerClearance;
	
	@RequestMapping(path="/manpowerclearancelistcontroller/init")
	public String init(Model model) {
		
		
		
		
		 
		 return "manPowerClearanceList";
	}
	
	@RequestMapping(path="/manpowerclearancelistcontroller/search")
	public String getManPowerClearanceList(Model model, @RequestParam("userName")String userName,
			@RequestParam("sendDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date sendDate,
			 @RequestParam("receiveDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date receiveDate,
			 @RequestParam(value = "active", required = false) String active
														
			) {
		
        int status=1;
		
		if(active!=null)
			status=1;
		else
			status=0;
		
		model.addAttribute("manPowerCleranceList",daoManPowerClearance.getManPower_ATTMByCraiteria(userName, sendDate, receiveDate, status));	
		return "manPowerClearanceList";
	}

}
