package com.gs.erp.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.erp.models.BasicCourse;
import com.gs.erp.models.CourseCreation;

public interface CourseCreationRepository extends JpaRepository<CourseCreation, Integer>{

	BasicCourse save(BasicCourse basicCourse);

}
