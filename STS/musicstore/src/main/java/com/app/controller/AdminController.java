package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.service.OrderService;
import com.app.service.StoreAccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StoreAccountService storeAccountService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public String goToAdminHome() {
		return "adminhome";
	}
	
	@GetMapping("/orders")
	public String showAllOrders(Model model) {
		model.addAttribute("orders", orderService.getAllOrders());
		return "allorders";
	}
	
	@GetMapping("/users")
	public String showAllUsers(Model model) {
		model.addAttribute("users", storeAccountService.getAllAccounts());
		return "allusers";
	}
}
