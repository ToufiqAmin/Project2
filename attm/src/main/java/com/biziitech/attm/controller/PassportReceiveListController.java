package com.biziitech.attm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biziitech.attm.dao.DaoPassportReceive;




@Controller
public class PassportReceiveListController {

	@Autowired
	private DaoPassportReceive daoPassportReceive;
	
	@RequestMapping(path="/passportreceivecontroller/list")
	public String list(Model model) {
		 
		 return "passportReceiveList";
	}
	
	@RequestMapping(path="/passportreceivecontroller/search")
	public String getPassportReceiveList(Model model,
			 @RequestParam("userId")Long userId,
			 @RequestParam("receiveDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date receiveDate,
			 @RequestParam(value = "active", required = false) String active
														
			) {
		
        int status=1;
		
		if(active!=null)
			status=1;
		else
			status=0;
		
		model.addAttribute("passportReceiveList",daoPassportReceive.getPassportReceiveListByCraiteria(userId, receiveDate, status));	
		return "passportReceiveList";
	}
}
