package com.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	
	/*
	 * a cookie is normally associated with a response
	 * 
	 */
	@PostMapping("/addcookie")
	public String addCookie(@RequestParam("value") String cookieVal) {
		return "sessionpage";
	}
	
	
}
