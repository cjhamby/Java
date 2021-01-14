package com.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * A controller for rendering the home page view
 * 
 * @author cjham
 *
 */

@Controller
public class ViewController {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@GetMapping("/")
	public ModelAndView getIndex(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("feedback", new FeedbackItem());
		mv.addObject("feedbacks", feedbackRepository.findAll());
		return mv;
	}
}
