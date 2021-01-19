package com.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.client.dao.MyTask;
import com.client.dao.MyTaskUser;
import com.client.service.WebClientService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ClientController {
	
	@GetMapping("/register")
	public String registerLanding() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@RequestParam("name") String name, @RequestParam("pass") String pass) {
		System.out.println("made it");
		WebClientService.registerUser(name, pass);
		return "home";
	}
	
	@GetMapping("/login")
	public String loginLanding() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("name") String name, @RequestParam("pass") String pass, Model model, RedirectAttributes redirectAttr) {
		try {
			MyTaskUser user = WebClientService.loginUser(name, pass);
			if(user == null) {
				return "login";
			} else {
				model.addAttribute("user", user);		
				redirectAttr.addAttribute("user", user);
				return "redirect:/userHome";
			}
		} catch (Exception e) {
			System.out.println("error here");
			return "home";
		}		
	}
	
	@GetMapping("/addTask")
	public String addNewTaskLanding(Model model) {
		model.addAttribute("newTask", new MyTask());
		return "create";
	}
	
	@PostMapping("/addTask")
	public String addNewTask(Model model) {
		MyTaskUser user = (MyTaskUser)model.getAttribute("user");
		MyTask newTask = (MyTask)model.getAttribute("newTask");
		model.addAttribute("user", WebClientService.addTaskToUser(user, newTask));
		return "redirect:/userHome";
	}
	
	@GetMapping("/userHome")
	public ModelAndView goToUserHome(Model model ,RedirectAttributes redirectAttr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userhome");
		mv.addAllObjects(redirectAttr.asMap());
		mv.addAllObjects(model.asMap());	// retain model objects
		return mv;
	}

	@PostMapping("/logout")
	public ModelAndView logout(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("loginId", -1);
		return mv;
	}
}
