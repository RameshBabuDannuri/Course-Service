package com.service.impl;
import com.exceptions.ResourceNotCreatedException;
import com.exceptions.ResourceNotFoundException;
import com.model.Chapter;
import com.model.Course;
import com.repository.CourseRepository;
import com.service.ChapterService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    ChapterService chapterService;
    @Override
    public Course createCourse(Course course) {
        Course course1 = courseRepository.save(course);
        if(course1 == null){
            throw new ResourceNotCreatedException("Course not created");
        }
        return course1;
    }
    @Override
    public List<Course> getAll() {
        List<Course> courses = courseRepository.findAll();
        if (courses == null){
            throw new ResourceNotFoundException("Courses are not available");
        }
        return courses;
    }
    @Override
    public Course updateCourse(Course course) {
        Course course1 = courseRepository.getOne(course.getId());
        if (course1 == null){
           throw new ResourceNotFoundException("course with id"+course.getId()+" not found");
        }
        return courseRepository.save(course);

    }

    @Override
    public List<Course> findByName(String courseName) {
        List<Course> courses =  courseRepository.findByCourseName(courseName);
        if(courses.isEmpty()){
            throw new ResourceNotFoundException("Course with name "+courseName+" not found");
        }
        return courses;
    }

    @Override
    public Course addChapterToCourse(Long courseId, Chapter chapter) {
        Course course = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id "+courseId+" Not Available");
        }
        course.addChapter(chapter);
        Chapter chapter1 = chapterService.createChapter(chapter);
        return course;
    }
    @Override
    public Course addChaptersToCourse(Long courseId, Set<Chapter> chapters) {
        Course course =courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id "+courseId+" not exist");
        }
        for (Chapter chapter:chapters){
            course.addChapter(chapter);
            chapterService.createChapter(chapter);
        }
        return course;
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course  = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id"+ courseId+" not exist");
        }
        courseRepository.delete(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id"+ courseId+" not exist");
        }
        return course;

    }









}


