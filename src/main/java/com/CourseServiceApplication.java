package com;

import com.model.Course;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseServiceApplication implements CommandLineRunner {

	@Autowired
	CourseService courseService;
	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Course course = new Course("java" , 2000d);
	//	courseService.save(course);


	}
}
