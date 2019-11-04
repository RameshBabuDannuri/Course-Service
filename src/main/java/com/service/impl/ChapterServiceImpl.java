package com.service.impl;

import com.exceptions.ResourceNotFoundException;
import com.exceptions.ResourcesNotAvailableException;
import com.model.Chapter;
import com.model.Course;
import com.repository.ChapterRepository;
import com.service.ChapterService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    CourseService courseService;

    public Chapter getChapterById(Long chapterId) {
        Chapter chapter = chapterRepository.getOne(chapterId);
        if(chapter == null){
            throw  new ResourceNotFoundException("Chapter with id "+ chapterId+" not exist");
        }
        return chapter;
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
        Course course = courseService.getCourseById(courseId);
        if (course == null){
            throw new ResourceNotFoundException("Course with id "+courseId+" not exist");
        }
        Set<Chapter> chapters =  course.getChapters();
        if(chapters.isEmpty()){
            throw new ResourcesNotAvailableException("Course with name "+course.getCourseName()+" do not have chapters");
        }
        return chapters;
    }

    @Override
    public Chapter createChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }


}
