package com.biziitech.attm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HeaderManualController {
	
	@RequestMapping(path="/header")
	public String init(Model model) {
		
		
		
		 
		 return "logon";
	}

}
