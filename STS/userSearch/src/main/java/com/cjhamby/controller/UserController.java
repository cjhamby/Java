package com.cjhamby.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhamby.data.MyUser;
import com.cjhamby.data.UserRepository;
import com.cjhamby.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	/*
	 * ./users
	 * 
	 * the request should include a "uid" this method will then redirect to:
	 * ./users/uid
	 */
	@GetMapping
	public String goToUserPage(@RequestParam("uid") String uid) {
		if (uid.length() == 0) {
			return "redirect:/failure";
		}
		return "redirect:/users/" + uid;
	}

	/*
	 * ./users/add
	 * 
	 * provides a quick way to create users in the database doesn't re-add existing
	 * users, though
	 */
	@GetMapping("/add")
	public String addDummyUsers() {
		userService.makeDummyUsers();
		return "redirect:/search";
	}

	/*
	 * ./users/all
	 * 
	 * shows every user in the database, in JSON format
	 */
	@GetMapping("/all")
	public @ResponseBody Iterable<MyUser> getAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * ./users/id
	 * 
	 * shows a page with the user's name and the option to change it
	 */
	@GetMapping("/{id}")
	public String getUserById(@PathVariable String id, Model model) {
		try {
			Optional<MyUser> user = userRepository.findById(Integer.parseInt(id));
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
				return "update";
			} else {
				return "notfound";
			}
		} catch (Exception e) {
			return "notfound";
		}
	}

	/*
	 * ./users/id
	 * 
	 * the post request will have a parameter with the new user name
	 */
	@PostMapping("/{id}")
	public String updateUserName(@PathVariable String id, @RequestParam("newName") String newName, Model model) {
		try {
			Optional<MyUser> user = userRepository.findById(Integer.parseInt(id));
			if (user.isPresent() && newName.length() > 0) {
				MyUser newUser = user.get();
				newUser.setName(newName);
				userRepository.save(newUser);
				return "success";
			} else {
				return "redirect:/failure";
			}
		} catch (Exception e) {
			return "index";
		}
	}

}
