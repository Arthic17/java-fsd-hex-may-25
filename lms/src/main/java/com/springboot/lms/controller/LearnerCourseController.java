package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.service.LearnerCourseService;

@RestController
public class LearnerCourseController {

	
	@Autowired
	private LearnerCourseService learnerCourseService;
	
	@PostMapping("/api/learner/enroll/course/{learnerId}/{courseId}")
	public LearnerCourse enrollLearnerInCourse(@PathVariable("learnerId") int learnerId,
			@PathVariable("courseId") int courseId,
			@RequestBody LearnerCourse learnerCourse) {
		return learnerCourseService.enrollLearnerInCourse(learnerId, courseId, learnerCourse);}
	
	
	
	
	
	@GetMapping("/api/learner/enroll/course/{courseId}")
	public List<Learner> getLearnerByCourseId(@PathVariable int courseId){
		return learnerCourseService.getLearnerByCourseId(courseId);
	}
	
	@GetMapping("/api/course/enroll/learner/{learnerId}")
	public List<Course> getCoursesByLearnerId(@PathVariable("learnerId") int learnerId) {
		return learnerCourseService.getCourseByLearnerId(learnerId);
	}

}
