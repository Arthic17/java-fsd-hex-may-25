package com.springboot.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.Learner;

public interface LearnerRepository extends JpaRepository<Learner,Integer> {
	@Query("select l from Learner l where l.user.username=?1")
	Learner getLearnerByUsername(String username);


	@Query("select lc.learner from LearnerCourse lc where lc.course.id=?1")
	List<Learner> getLearnersByCourseId(int courseId);
}
