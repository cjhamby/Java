package com.app.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import com.app.dao.Product;
import com.app.data.CDRepository;
import com.app.data.InstrumentRepository;
import com.app.service.ProductService;
import com.exception.ProductNotFoundException;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class StoreController {

	/*
	 * in an ideal world, the ProductServices would contain their relevant Repositories
	 * however, this is a challenge for another day
	 */
	@Autowired
	private ProductService<Instrument> instrumentService;
	@Autowired
	private InstrumentRepository instrumentRepo;
	@Autowired
	private ProductService<CD> cdService;
	@Autowired
	private CDRepository cdRepo;
	
	
	@GetMapping("/store/addSuccess")
	public String goToAddSuccessPage() {
		return "addsuccess";
	}
	
	@GetMapping("/store/cart")
	public String goToCartPage() {
		return "carthome";
	}
	
	@GetMapping("/store/checkout")
	public String goToCheckoutPage() {
		
		return "checkout";
	}
	
	/*
	 * there are two types of product in the store: CD and Instrument
	 * Each type of product has a dedicated store section
	 * to add an item to the cart, post it to the cart
	 */
	@PostMapping("/store/addToCart")
	public String addToCart(
			@RequestParam("productType") String productType,
			@RequestParam("productId") String productId,  
			HttpSession session) {
		try {
			Cart cart = (Cart)(session.getAttribute("cart"));
			
			if(cart==null) {
				cart = new Cart();
				log.info("created cart");
				session.setAttribute("cart", cart);
			}
			
			log.info("finding product " + productId);
			if(productType.equals("instrument")) {
				log.info("finding instrument");
				cart.addToCart(instrumentRepo.findById(Integer.parseInt(productId)).orElseThrow(ProductNotFoundException::new));
			} else if (productType.equals("cd")) {
				log.info("finding cd");
				cart.addToCart(cdRepo.findById(Integer.parseInt(productId)).orElseThrow(ProductNotFoundException::new));
			}
			return "redirect:/store/addSuccess";
			
		} catch (Exception e) {
			log.debug(e);
			return "redirect:/";
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
		List<Instrument> allProducts = instrumentService.getProductsInStore(instrumentRepo);
		
		// a list of products to be shown on the page
		List<Instrument> products = new ArrayList<>();
		
		// how many items to show on a single page
		int itemsPerPage = 5;
		
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
		List<CD> allProducts = cdService.getProductsInStore(cdRepo);
		
		int itemsPerPage = 5;
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
