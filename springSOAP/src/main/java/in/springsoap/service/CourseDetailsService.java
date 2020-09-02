package in.springsoap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import in.springsoap.model.Course;

@Component
public class CourseDetailsService {

	public enum Status {
		SUCCESS, FALIURE;
	}

	// Creating List hold in static block
	private static List<Course> course = new ArrayList<Course>();

	static {
		Course course1 = new Course(1, "Spring", "Framework to develop Dynamic Web Project");
		course.add(course1);
		Course course2 = new Course(2, "Spring MVC", "Framework to develop Dynamic Web Project in more Efficient way");
		course.add(course2);
		Course course3 = new Course(3, "Spring Boot",
				"Framework provide an abstraction layer over Spring and Spring MVC");
		course.add(course3);
		Course course4 = new Course(4, "Maven",
				"It's a build automation tool which used to download dependencies for project");
		course.add(course4);
	}

	// get 1 course
	// Course findById(int id)
	public Course findById(int id) {
		Iterator<Course> iterator = course.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				return course;
			}
		}
		return null;
	}

	// get more than 1 courses
	// List<Course> findAll()
	public List<Course> findAll() {
		return course;
	}

	// delete cources
	// Course deleteById(int id)
	/*
	 * public String deleteById(int id) { boolean val=false; String indicate = null;
	 * Iterator<Course> iterator=course.iterator(); while (iterator.hasNext()) {
	 * Course course=iterator.next(); if(course.getId()==id) { iterator.remove();
	 * val=true; } } if (val) { indicate ="Record deleted..."; }else {
	 * indicate="Unable to delete cause Record not Found..."; } return indicate; }
	 */
	public Status deleteById(int id) {
		Iterator<Course> iterator = course.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FALIURE;
	}

}
