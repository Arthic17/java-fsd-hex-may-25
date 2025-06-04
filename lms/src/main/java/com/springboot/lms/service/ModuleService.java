package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.CModule;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.ModuleRepository;

@Service
public class ModuleService {

	
	private CourseRepository courseRepository;
	private ModuleRepository moduleRepository;
	
	public ModuleService(CourseRepository courseRepository,ModuleRepository moduleRepository) {
		this.courseRepository = courseRepository;
		this.moduleRepository = moduleRepository;
	}
	
	
	public CModule addModule(int courseId,CModule module) {
		Course course=courseRepository.findById(courseId)
				.orElseThrow(()-> new ResourceNotFoundException("Course Not Found, Id Given is Invalid!"));
		module.setCourse(course);
		return moduleRepository.save(module);
	}
	
	
	public List<CModule> getModuleByCourseId(int courseId){
		courseRepository.findById(courseId)
 		.orElseThrow(()-> new ResourceNotFoundException("Course Not Found, Id Given is Invalid!"));
		
		return moduleRepository.getModuleByCourseId(courseId);
	}
}
