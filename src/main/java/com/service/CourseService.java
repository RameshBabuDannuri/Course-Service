package com.service;

import com.exceptions.ResourcesNotAvailableException;
import com.exceptions.ResourceNotCreatedException;
import com.exceptions.ResourceNotFoundException;
import com.model.Chapter;
import com.model.Course;
import com.model.Section;
import com.repository.ChapterRepository;
import com.repository.CourseRepository;
import com.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    private ChapterService chapterService;

    public Course save(Course course) {
        Course course1 = courseRepository.save(course);
        if(course1 == null){
            throw new ResourceNotCreatedException("Course not created");
        }
        return course1;
    }

    public List<Course> getAll() {
        List<Course> courses = courseRepository.findAll();

        return courses;
    }

    public Course updateCourse(Course course) {
        Course course1 = courseRepository.getOne(course.getId());
        if (course1 == null){
           throw new ResourceNotFoundException("course with id"+course.getId()+" not found");
        }
        return courseRepository.save(course);

    }

    public List<Course> findByName(String courseName) {
        List<Course> courses =  courseRepository.findByCourseName(courseName);
        if(courses.isEmpty()){
            throw new ResourceNotFoundException("Course with name "+courseName+" not found");
        }
        return courses;
    }

    public void deleteCourse(Long courseId) {
        Course course  = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id"+ courseId+" not exist");
        }
        courseRepository.delete(course);
    }

    public Course getCourseById(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id"+ courseId+" not exist");
        }
        return course;

    }


    public Course addChapters(Long courseId, Set<Chapter> chapters) {
        Course course = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id "+courseId+" not exist");
        }
        for (Chapter chapter:chapters){
            course.addChapter(chapter);
            chapterRepository.save(chapter);
        }
        return course;
    }

    public Course addChapter(Long courseId, Chapter chapter) {
        Course course = courseRepository.getOne(courseId);
          if(course == null){
              throw new ResourceNotFoundException("Course with id "+courseId+" not exist");
          }
          course.addChapter(chapter);
          chapterRepository.save(chapter);
          return course;
    }

    public Chapter updateChapter(Chapter chapter) {
        Chapter chapter1 = chapterRepository.getOne(chapter.getId());
        if(chapter1 == null){
            throw new ResourceNotFoundException("Chapter with id "+chapter.getId()+" not exist");
        }
        chapterRepository.save(chapter);
        return chapter;
    }

    public Chapter deleteChapter(Long chapterId) {
        Chapter chapter = chapterRepository.getOne(chapterId);
        if (chapter == null){
            throw new ResourceNotFoundException("Chapter with id "+chapterId+" not exist");
        }
        chapterRepository.delete(chapter);
        return chapter;
    }

    public Set<Chapter> getChaptersByCourseID(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id "+courseId+" not exist");
        }
        Set<Chapter> chapters =  course.getChapters();
        if(chapters.isEmpty()){
            throw new ResourcesNotAvailableException("Course with name "+course.getCourseName()+" do not have chapters");
        }
        return chapters;
    }








}


