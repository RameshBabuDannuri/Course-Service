package com.service;

import com.model.Chapter;
import com.model.Course;

import java.util.Set;

public interface ChapterService {
    Chapter createChapter(Chapter chapter);
    Chapter updateChapter(Chapter chapter);
    Chapter deleteChapter(Long chapterId);
    Chapter getChapterById(Long chapterId);
    Set<Chapter> getChaptersByCourseID(Long courseId);

}
