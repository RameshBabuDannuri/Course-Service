package com.service;

import com.model.Chapter;
import com.model.Course;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course createCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long courseId);
    Course getCourseById(Long courseId);
    List<Course> getAll();
    List<Course> findByName(String courseName);
    Course addChapterToCourse(Long courseId, Chapter chapter);
    Course addChaptersToCourse(Long courseId, Set<Chapter> chapter);
}
