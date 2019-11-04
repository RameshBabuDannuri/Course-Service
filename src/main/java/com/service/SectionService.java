package com.service;

import com.model.Chapter;
import com.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);
    Section updateSection(Section section);
    void deleteSection(Long sectionId);
    Chapter addSectionsToChapter(Chapter chapter, List<Section> sections);
    Chapter addSectionToChapter(Chapter chapter, Section section);
    Section getSectionById(Long id);
    List<Section> getSectionsByChapterId(Long chapterId);
}
