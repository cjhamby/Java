package com.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.CD;
import com.app.dao.Cart;
import com.app.dao.Instrument;
import com.app.dao.Order;
import com.app.dao.Product;
import com.app.dao.StoreAccount;
import com.app.data.CDRepository;
import com.app.data.InstrumentRepository;
import com.app.data.OrderRepository;
import com.app.data.ProductRepository;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.service.StoreAccountService;
import com.exception.ProductNotFoundException;

import lombok.extern.log4j.Log4j2;

/*
 * this controller handles 
 * - navigating through the store, 
 * - placing orders
 * - misc. cart stuff
 */
@Controller
@Log4j2
public class StoreController {

	// the service for finding products 
	@Autowired
	private ProductService productService;
	
	// the service for finding store accounts
	@Autowired
	private StoreAccountService storeAccountService;
	
	// the service for placing orders
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/store/addSuccess")
	public String goToAddSuccessPage() {
		return "addsuccess";
	}
	
	@GetMapping("/store/cart")
	public String goToCartPage() {
		return "carthome";
	}
	
	/*
	 * the checkout page shows the order summary and has a few links for modifying the details
	 */
	@GetMapping("/store/checkout")
	public String goToCheckoutPage(HttpSession session, Model model) {
		StoreAccount account;
		try {
			String username = session.getAttribute("currentUser").toString();
			if(username.equals(null)) {
				throw new Exception("no one signed in");
			}
			account = (StoreAccount)storeAccountService.loadUserByUsername(username);
		} catch (Exception e) {
			return "redirect:/login";
		}
		
		
		try {
			model.addAttribute("address", account.getAddress()); 
			return "checkout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}
	
	@GetMapping("/store/placeorder")
	public String placeOrder(HttpSession session) {
		try {
			String username = session.getAttribute("currentUser").toString();
			StoreAccount account = (StoreAccount)storeAccountService.loadUserByUsername(username);
			Cart cart = (Cart)session.getAttribute("cart");

			if(account.equals(null)) {
				return "redirect:/login";
			}
			
			if(cart.equals(null)) {
				return "redirect:/error";
			}
			
			orderService.placeOrder(username, cart, account.getAddress());
			session.removeAttribute("cart");
			return "ordersuccess";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error";
		}
		
	}
	
	/*
	 * there are two types of product in the store: CD and Instrument
	 * Each type of product has a dedicated store section
	 * to add an item to the cart, post it to the cart
	 */
	@PostMapping("/store/addToCart")
	public String addToCart( HttpSession session, @RequestParam("productId") String productId ) {
		try {
			Cart cart = (Cart)(session.getAttribute("cart"));
			
			if(cart==null) {
				cart = new Cart();
				log.info("created cart");
				session.setAttribute("cart", cart);
			}
			
			log.info("finding product " + productId);			
			int pid = Integer.parseInt(productId);
			
			// the following works on the premise that instruments and cds will not share ID values
			// check instrument repo, then check cd repo
			
			try {
				cart.addToCart(productService.findProductById(pid).get());
				return "addsuccess";
				
			} catch (ProductNotFoundException e) {
				log.error("product not found");
				return "redirect:/error";
			}
			
		} catch (Exception e) {
			log.debug(e);
			return "redirect:/error";
		}
	}
	
	@PostMapping("/store/removeFromCart")
	public String removeFromCart(
			@RequestParam("productId") String productId,
			HttpSession session) {
		try {
			
			Cart cart = (Cart)(session.getAttribute("cart"));
			cart.removeFromCart(Integer.parseInt(productId));	
			if(cart.getCartItems().size() == 0) {
				session.removeAttribute("cart");
			}
			return "redirect:/store/cart";
		} catch (Exception e) {
			return "redirect:/error";
		}
		
		
	}
	
	
	/*
	 * default store page
	 * complete with pagination
	 */
	@GetMapping("/store/instruments")
	public String goToInstrumentStore(@ModelAttribute("page") String pageRequested, Model model) {
		
		/*
		 * the default page is 0
		 * if another page has been specified, use that instead
		 */
		int page = 0;
		if(!pageRequested.isEmpty()) {
			page = Integer.parseInt(pageRequested);
		}
		
		// a list of products in the store
		List<Instrument> allProducts = productService.getInstruments();
		
		// a list of products to be shown on the page
		List<Instrument> products = new ArrayList<>();
		
		// how many items to show on a single page
		int itemsPerPage = 3;
		
		// create a selection group of products to show on the page
		for(int i=0; i<itemsPerPage; i++) {
			int productIndex = page*itemsPerPage + i;
			
			if(productIndex >= allProducts.size()) { // no more products in the store
				break;
			}
			products.add(allProducts.get(productIndex));
		}
		
		// pagination book-keeping
		if(products.size() < itemsPerPage) {		// no next page
			model.addAttribute("morePages", false);
		} else {
			model.addAttribute("morePages", true);
		}
		
		model.addAttribute("page", page);
		model.addAttribute("products", products);
		return "instruments";
	}
	
	
	/*
	 * functionally equivalent to the instrument store method above
	 */
	@GetMapping("/store/cds")
	public String goToCDStore(@ModelAttribute("page") String pageRequested, Model model) {
		int page = 0;
		if(!pageRequested.isEmpty()) {
			page = Integer.parseInt(pageRequested);
		}
		
		List<CD> products = new ArrayList<>();
		List<CD> allProducts = productService.getCDs();
		
		int itemsPerPage = 3;
		for(int i=0; i<itemsPerPage; i++) {
			int productIndex = page*itemsPerPage + i;
			
			if(productIndex >= allProducts.size()) {
				break;								 
			}
			products.add(allProducts.get(productIndex));
		}
		
		if(products.size() < itemsPerPage) {
			model.addAttribute("morePages", false);
		} else {
			model.addAttribute("morePages", true);
		}
		
		model.addAttribute("page", page);
		model.addAttribute("products", products);
		return "cdstore";
	}
	
	// navigation links
	@GetMapping("/store/instruments/nav")
	public String goToInstrumentStorePage(@RequestParam("page") String page, Model model) {
		model.addAttribute("page", page);
		return "forward:/store/instruments";
	}
	
	@GetMapping("/store/cds/nav")
	public String goToCDStorePage(@RequestParam("page") String page, Model model) {
		model.addAttribute("page", page);
		return "forward:/store/cds";
	}	
}
