package com.springboot.lms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.CModule;
import com.springboot.lms.service.ModuleService;

@RestController
@RequestMapping("/api/module")
public class ModuleController {
  
	
	@Autowired
	private ModuleService moduleService;
	
	
	@PostMapping("/add/{courseId}")
	public CModule addModule(@PathVariable("courseId") int courseId,
			@RequestBody CModule module) {

		return moduleService.addModule(courseId, module);
	}
	
	
	@GetMapping("/get")
	public List<CModule> getModuleByCourseId(@RequestParam int courseId)
	{
		return moduleService.getModuleByCourseId(courseId);
	}
}
