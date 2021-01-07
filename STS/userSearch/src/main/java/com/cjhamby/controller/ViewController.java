package com.cjhamby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * provides basic view routing 
 */
@Controller
@RequestMapping("/")
public class ViewController {
	
	@GetMapping					// URL the user tries to access
	public String getIndex() {	// 
		return "index";			// name of the view (jsp file) to return 
	}
	
	@GetMapping("/search")
	public String getSearchPage() {
		return "search"; 
	}
	
	@GetMapping("/failure")
	public String getFailPage() {
		return "failure";
	}

}
