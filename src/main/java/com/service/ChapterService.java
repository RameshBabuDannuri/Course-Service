package com.service;

import com.exceptions.ResourceNotFoundException;
import com.exceptions.ResourcesNotAvailableException;
import com.model.Chapter;
import com.model.Course;
import com.model.Section;
import com.model.Video;
import com.repository.ChapterRepository;
import com.repository.SectionRepository;
import com.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private VideoService videoService;
    public Chapter getChapterById(Long chapterId) {
        Chapter chapter = chapterRepository.getOne(chapterId);
        if(chapter == null){
            throw  new ResourceNotFoundException("Chapter with id "+ chapterId+" not exist");
        }
        return chapter;
    }




    public Section updateSection(Section section) {
        Section section1 = sectionService.getSectionById(section.getId());
        if (section1==null){
            throw new ResourceNotFoundException("Section with id "+section.getId()+" not exist");

        }
        section1 = sectionService.save(section1);
        return section1;
    }
    public void deleteSection(Long sectionId) {
        Section section = sectionService.getSectionById(sectionId);
        if (section == null){
            throw new ResourceNotFoundException("section with id "+ sectionId+" not exist");
        }
        sectionService.deleteSection(section);
    }

    public List<Section> getSectionsByChapterId(Long chapterId) {
        List<Section> sections = sectionService.findSectionByChapterId(chapterId);
        if (sections == null){
            throw new ResourcesNotAvailableException("Sections not availabe");
        }
        return sections;
    }





}
