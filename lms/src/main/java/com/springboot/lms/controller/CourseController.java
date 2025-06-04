package com.springboot.lms.controller;

import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Course;
import com.springboot.lms.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	
	
	@Autowired
	private CourseService courseService;
	
	//add course to db
	//input:course
	Logger logger = LoggerFactory.getLogger("CourseController");
		
	@PostMapping("/add") 
	public Course postCourse(Principal principal, @RequestBody Course course) {
		String username = principal.getName(); 
		return courseService.postCourse(course, username);
	}

	@GetMapping("/all")
	public List<Course> getAllCourses(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "1000000") Integer size) {
		if (page == 0 && size == 1000000)
		logger.info("No Pagination call for all courses");
		return courseService.getAllCourses(page, size);
	}
}
