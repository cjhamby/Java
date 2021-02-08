package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.sql.ordering.antlr.OrderByFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.Address;
import com.app.dao.Order;
import com.app.dao.StoreAccount;
import com.app.data.OrderRepository;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.service.StoreAccountService;

import lombok.extern.log4j.Log4j2;



/*
 * Only the username is tied to the session
 * The address is tied to model, limiting its scope to the pages which need it
 */
@Log4j2
@Controller
public class UserController {
		
	@Autowired
	private StoreAccountService storeAccountService;
	
	@Autowired
	private OrderService orderService;
	
//	@Autowired
//	private OrderRepository orderRepo;
	
	@Autowired
	private ProductService productService;
	
	/*
	 * access denied page
	 */
	@RequestMapping("/no")
	public String permissionDeniedPage() {
		return "accessdenied";
	}
		
	/*
	 * Navbar "User Page" link
	 */
	@GetMapping("/user/home")
	public String goToUserHome(HttpSession session) {
		try {
			// no one is signed in yet, redirect to login page
			// note: spring security may be responsible for redirecting to login
			//       so this could be redundant
			if(session.getAttribute("currentUser") == null) {
				return "redirect:/login";
			}
		} catch (Exception e) {
			return "redirect:/login";
		}
		return "userhome";
	}

	
	/*
	 * this is called when the user signs in, on the login page
	 */
	@PostMapping("/user/home")
	public String goToUserHome2(HttpSession session, @RequestParam("username") String username) {
		
		// not sure if this is ever an issue...
		if(session == null) {
			log.warn("no session");
			return "redirect:/login";
		}
		
		session.setAttribute("currentUser", username);
		return "userhome";
	}
	
	
	/*
	 * This page shows the current address(es) and gives an option to change them
	 */
	@GetMapping("/user/address")
	public String goToAddressPage(HttpSession session, Model model) {
		String username = session.getAttribute("currentUser").toString();
		StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		model.addAttribute("address", account.getAddress()); 

		return "address";
	}
	
	/*
	 * this page allows the current address to be modified
	 */
	@GetMapping("/user/address/modify")
	public String getAddAddressPage(HttpSession session, Model model) {
		String username = session.getAttribute("currentUser").toString();
		StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		model.addAttribute("address", account.getAddress()); 

		return "addressmodify";
	}
	
	/*
	 * the modified address is posted to this address
	 */
	@PostMapping("/user/address/modify")
	public String addNewAddress(HttpSession session, @ModelAttribute("address") Address address) {
		String username = session.getAttribute("currentUser").toString();
		StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		account.setAddress(address);
		
		return "address";
	}
	
	/*
	 * find every order the user has placed
	 */
	@GetMapping("/user/orders")
	public String showUserOrders(HttpSession session, Model model) {
		String username = session.getAttribute("currentUser").toString();
		List<Order> orders = orderService.getOrdersByUser(username);

		if(orders.size()!=0) {
			model.addAttribute("orders", orders);			
		}
		return "myorders";
	}
	
	@GetMapping("/user/orders/{id}")
	public String showOrderDetails(
			@PathVariable("id") String orderId,
			HttpSession session,
			Model model) {
		try {
			
			// throws OrderNotFoundException
			Order order = orderService.getOrderById(Integer.parseInt(orderId));
			
			// throws ProductNotFoundException
			model.addAttribute("products", productService.getProductsInOrder(order));
			model.addAttribute("address", order.getShippingAddress());
			
			
			
			return "orderdetails";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}
}
