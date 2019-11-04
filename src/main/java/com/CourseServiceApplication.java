package com;

import com.service.impl.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceApplication.class);

	@Autowired
	CourseServiceImpl courseService;
	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
		logger.debug("--Application Started--");
	}

	@Override
	public void run(String... args) throws Exception {

		//Course course = new Course("java" , 2000d);
	//	courseService.save(course);


	}
}
