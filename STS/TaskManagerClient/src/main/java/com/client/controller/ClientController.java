package com.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.client.dao.MyTask;
import com.client.dao.MyTaskUser;
import com.client.service.WebClientService;

import lombok.extern.log4j.Log4j2;



/*
 * don't quote me on this, but in my understanding,
 * the model is kind of a container to send data between controller and view
 * the model has a view name (by default, the string it returns)
 * model attributes are shorter lived (i.e. go to the view and then disappear)
 * 
 * see below for how to use model and session
 */
@Log4j2
@Controller
public class ClientController {
	
	@GetMapping("/register")
	public String registerView() {
		return "register";
	}

	@GetMapping("/login")
	public String loginView() {
		return "login";
	}

	/*
	 * creates an empty task object to be populated and returned by the user
	 */
	@GetMapping("/addTask")
	public String addNewTaskView(Model model) {
		model.addAttribute("newTask", new MyTask());
		return "create";
	}
	
	@GetMapping("/modify")
	public String modifyView(Model model, @ModelAttribute("task") MyTask task) {
		model.addAttribute("task", task);
		return "update";
	}

	@GetMapping("/userHome")
	public ModelAndView goToUserHome( Model model ) {
		ModelAndView mv = new ModelAndView();			// demonstrates how to use ModelAndView
		mv.setViewName("userhome");						// functionally equivalent to the method below
		mv.addObject("task", new MyTask());
		return mv;
	}
	
//	functionally equivalent to the above method
//
//	public String goToUserHome(Model model) {
//		model.addObject("task", new MyTask());
//		return "userhome";
//	}
	
	// --- post methods --- 
	@PostMapping("/register")
	public String registerNewUser(@RequestParam("name") String name, 
								  @RequestParam("pass") String pass) {
		WebClientService.registerUser(name, pass);
		return "home";
	}
	
	
	/*
	 * the login and logout methods access the underlying HttpSession
	 * to access it, just include it as an argument for the method
	 */
	@PostMapping("/login")
	public String loginUser(@RequestParam("name") String name, 
							@RequestParam("pass") String pass,
							HttpSession session) {
		try {
			// try to log in, and get the user if successful
			MyTaskUser user = WebClientService.loginUser(name, pass);
			System.out.println(user);
			// if the user cannot login, stay on the login page
			if(user == null) {
				return "login";
			
			// if the user can login, redirect to the user home page
			// and save the user as a session attribute
			} else {
				session.setAttribute("user", user);
				return "redirect:/userHome";
			}
		} catch (Exception e) {
			log.error("something went wrong in login user");
			return "home";
		}		
	}
	
	@GetMapping("/logout")
	public String logout( HttpSession session ) {
		session.invalidate();
		return "home";
	}
	
	/*
	 * the following methods are used to perform CRUD operations on tasks
	 * 
	 * the user is stored as a session attribute, and is retrieved when a change is performed
	 * after the change is performed, the updated user is saved to the session 
	 */
	
	@PostMapping("/addTask")
	public String addNewTask( @ModelAttribute("newTask") MyTask newTask, 
							  @SessionAttribute("user") MyTaskUser user, 
							  HttpSession session ) {
		user = WebClientService.addTaskToUser(user, newTask);
		session.setAttribute("user", user);
		return "redirect:/userHome";
	}
	
	@PostMapping("/modify")
	public String modifyTask(@ModelAttribute("task") MyTask task, 
							 @SessionAttribute("user") MyTaskUser user, 
							 HttpSession session) {
		user = WebClientService.updateTask(user, task);
		session.setAttribute("user", user);
		return "redirect:/userHome";
	}
	
	@PostMapping("/remove")
	public String removeTask(@ModelAttribute("task") MyTask task, 
							 @SessionAttribute("user") MyTaskUser user, 
							 HttpSession session) {
		user = WebClientService.removeTaskFromUser(user, task);
		session.setAttribute("user", user);
		return "redirect:/userHome";
	}
	
	@GetMapping("/addTestTask")
	public String addDemoTask( HttpSession session, 
							   @SessionAttribute("user") MyTaskUser user ) {
		MyTask newTask = new MyTask();
		newTask.setTaskName("DEMO TASK");
		newTask.setTaskDesc("a test task");
		newTask.setStartDate("01/01/2000");
		newTask.setEndDate("01/02/2000");
		newTask.setEmail("testEmail");
		newTask.setPriority("low");
		
		// add task and update the user's session representation
		user = WebClientService.addTaskToUser(user, newTask);
		session.setAttribute("user", user);
		return "redirect:/userHome";
	}

}
