package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.Address;
import com.app.dao.StoreAccount;
import com.app.service.StoreAccountService;


@Controller
public class UserController {
		
	@Autowired
	private StoreAccountService storeAccountService;
	
	@RequestMapping("/no")
	public String permissionDeniedPage() {
		return "accessdenied";
	}
		
	@GetMapping("/user/home")
	public String goToUserHome(HttpSession session) {
		try {
			if(session.getAttribute("currentUser") == null) {
				return "redirect:/login";
			}
		} catch (Exception e) {
			return "redirect:/login";
		}
		return "userhome";
	}

	@PostMapping("/user/home")
	public String goToUserHome2(HttpSession session, @RequestParam("username") String username) {
		if(session == null) {
			System.out.println("no session");
			return "redirect:/login";
		}
		
		session.setAttribute("currentUser", username);
		return "userhome";
	}
	
	@GetMapping("/user/address")
	public String goToAddressPage(HttpSession session, Model model) {
		model.addAttribute("addresses", ((StoreAccount)storeAccountService.loadUserByUsername(session.getAttribute("currentUser").toString())).getAddress() );
		session.setAttribute("address" ,  ((StoreAccount)storeAccountService.loadUserByUsername(session.getAttribute("currentUser").toString())).getAddress());
		System.out.println(((StoreAccount)storeAccountService.loadUserByUsername(session.getAttribute("currentUser").toString())));
		
		return "address";
	}
	
	@GetMapping("/user/address/add")
	public String getAddAddressPage(HttpSession session) {
		String username = session.getAttribute("currentUser").toString();
		System.out.println("get username: " + username);
		StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		System.out.println("address: " + account.getAddress());
		session.setAttribute("address", account.getAddress()); 
		System.out.println("address: " + session.getAttribute("address"));
		System.out.println(account);
		return "addressadd";
	}
	@PostMapping("/user/address/add")
	public String addNewAddress(HttpSession session, @RequestParam("address") Address address) {
		String username = session.getAttribute("currentUser").toString();
		System.out.println("post username: " + username);
		StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		System.out.println(account);
		account.setAddress(address);
		
		return "address";
	}
}
