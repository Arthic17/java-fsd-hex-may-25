package com.springboot.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.CourseRepository;

@Service
public class CourseService {
	private CourseRepository courseRepository;
    private AuthorRepository authorRepository;
    Logger logger = LoggerFactory.getLogger("CourseService");
	public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository) {
		super();
		this.courseRepository = courseRepository;
		  this.authorRepository = authorRepository;
	}

	  public Course postCourse(Course course, String username) {
	        Author author = authorRepository.getAuthorByUsername(username);
	        logger.info("Author record fetched by username");
              course.setAuthor(author);
              logger.info("Adding.. Author to Database");
	        return courseRepository.save(course);
	    }

	    public List<Course> getAllCourses(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return courseRepository.findAll(pageable).getContent();
	    }
	
	
	
}
