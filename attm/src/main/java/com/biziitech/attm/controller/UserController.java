package com.biziitech.attm.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UserController {
	
	@RequestMapping(path="/usercontroller/init", method=RequestMethod.GET)
	public String init() {
		
		
		return "user";
	}

}
