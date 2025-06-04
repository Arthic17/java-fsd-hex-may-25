package com.springboot.lms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.NotEnrolledInCourseException;
import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.LearnerCourseRepository;
import com.springboot.lms.repository.LearnerRepository;

@Service
public class LearnerCourseService {
	private  CourseRepository courseRepository;
	private  LearnerRepository learnerRepository;
	private  LearnerCourseRepository learnerCourseRepository;
	public LearnerCourseService(CourseRepository courseRepository, LearnerRepository learnerRepository,
			LearnerCourseRepository learnerCourseRepository) {
	
		this.courseRepository = courseRepository;
		this.learnerRepository = learnerRepository;
		this.learnerCourseRepository = learnerCourseRepository;
	}

public LearnerCourse enrollLearnerInCourse(int learnerId,int courseId,LearnerCourse learnerCourse) {
	
	Learner learner=learnerRepository.findById(learnerId)
			.orElseThrow(()->new ResourceNotFoundException("Learner ID Invalid"));


	 Course course=courseRepository.findById(courseId)
			 .orElseThrow(()->
			 new ResourceNotFoundException("Course ID Invalid"));
	 learnerCourse.setEnrollDate(LocalDate.now());
	 learnerCourse.setLearner(learner);
	 learnerCourse.setCourse(course);
	 
	 return learnerCourseRepository.save(learnerCourse);

}


public List<Learner> getLearnerByCourseId(int courseId){
	return learnerCourseRepository.getLearnerByCourseId(courseId);
}


public List<Course> getCourseByLearnerId(int learnerId){
	learnerRepository.findById(learnerId)
 	.orElseThrow(()-> new ResourceNotFoundException("Learner ID Invalid"));
	
	 List<Course> list = learnerCourseRepository.getCourseByLearnerId(learnerId);
	 if(list != null && list.isEmpty())
		 throw new NotEnrolledInCourseException("Learner not enrolled in any course!!");
	return list;
}
}
