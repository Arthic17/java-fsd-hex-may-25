package com.springboot.lms.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Learner;
import com.springboot.lms.service.LearnerService;
@RestController
public class LearnerController {

	@Autowired
	private LearnerService learnerService;
	 private Logger logger = LoggerFactory.getLogger("LearnerController");
	@PostMapping("/api/learner/add")
	public Learner insertLearner(@RequestBody Learner learner) {
		return  learnerService.insertLearner(learner); 
	}
	
	@GetMapping("/api/learner/get-all")
	public ResponseEntity<?> getAll(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(learnerService.getAll());
		}
	
	@DeleteMapping("/api/learner/delete/{id}")
	public ResponseEntity<?> deleteLearner(@PathVariable int id) {
		learnerService.deleteLearner(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Learner Deleted");
	}
	
	 @GetMapping("/api/learner/get-one")
	    public Learner getLearnerByUsername(Principal principal) {
	    	
	    	String username = principal.getName(); 
	    	return learnerService.getLearnerByUsername(username) ;
	}
	
	@PutMapping("/api/learner/update/{id}")
	public Learner updateLearner(@PathVariable int id,@RequestBody Learner updatedLearner) {
		 logger.info("ID given is : " + id);
		return learnerService.updateLearner(id,updatedLearner);
	}
	
	 @GetMapping("/api/learner/course/{courseId}")
	    public List<?> getLearnersByCourseId(@PathVariable("courseId") int courseId) {
	        return learnerService.getLearnersByCourseId(courseId);
	    }
	
}
