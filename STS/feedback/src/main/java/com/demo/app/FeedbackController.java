package com.demo.app;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * a restful controller for feedback items
 * @author cjham
 *
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackRepository feedbackRepository;

	@GetMapping
	public Iterable<FeedbackItem> getFeedback(){
		return feedbackRepository.findAll();
	}
	
	@PostMapping
	public FeedbackItem postFeedback(@ModelAttribute("feedback") FeedbackItem item) {
		FeedbackItem newFeedback = item;
		feedbackRepository.save(newFeedback);
		return newFeedback;
	}
	
	@GetMapping("/{id}")
	public FeedbackItem getFeedback(@PathVariable("id") String id)
			throws NoSuchElementException {
		return feedbackRepository.findById(Integer.parseInt(id)).orElseThrow();
	}
}
