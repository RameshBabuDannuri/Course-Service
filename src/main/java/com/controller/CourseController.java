package com.controller;

import com.exceptions.ResourceNotFoundException;
import com.model.Chapter;
import com.model.Course;
import com.service.CourseService;
import com.service.impl.CourseServiceImpl;
import com.service.ValidationErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class CourseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseService courseService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        logger.info("get all course method is started");

        List<Course> courses =    courseService.getAll();
        return new ResponseEntity<>(courses,
                HttpStatus.OK);
    }
    @GetMapping("/courses/{name}")
    public ResponseEntity<List<Course>> getCourseByName(@PathVariable String name){

        return new ResponseEntity<>( courseService.findByName(name)  ,HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid  @RequestBody Course course, BindingResult result){

        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        course.setCourseName(course.getCourseName().toUpperCase());
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);

    }
    @PutMapping("/courses")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody Course course , BindingResult result){
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if (hasErrors != null){
            return hasErrors;
        }
        course.setCourseName(course.getCourseName().toUpperCase());
        return new ResponseEntity<>(courseService.updateCourse(course), HttpStatus.OK);
    }
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/courses/{courseId}/chapter")
    public ResponseEntity<?> addChapterToCourse(
            @PathVariable Long courseId , @Valid @RequestBody Chapter chapter, BindingResult result){
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors!=null){
            return  hasErrors;
        }
        Course course =   courseService.addChapterToCourse(courseId,chapter);

        return new ResponseEntity<>(course , HttpStatus.CREATED);
    }
}
