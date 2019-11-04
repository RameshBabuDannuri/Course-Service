package com.service.impl;

import com.exceptions.ResourceNotFoundException;
import com.exceptions.ResourcesNotAvailableException;
import com.model.Chapter;
import com.model.Section;
import com.repository.SectionRepository;
import com.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public Section createSection(Section section) {
       return sectionRepository.save(section);
    }
    public Section updateSection(Section section) {
        Section section1 = sectionRepository.getOne(section.getId());
        if (section1==null){
            throw new ResourceNotFoundException("Section with id "+section.getId()+" not exist");
        }
        section1.setSectionName(section.getSectionName());
        section1.setDescription(section.getDescription());
        section1 = sectionRepository.save(section1);
        return section1;
    }
    public void deleteSection(Long sectionId) {
        Section section = sectionRepository.getOne(sectionId);
        if (section == null){
            throw new ResourceNotFoundException("section with id "+ sectionId+" not exist");
        }
        sectionRepository.delete(section);
    }


    public Chapter addSectionsToChapter(Chapter chapter, List<Section> sections) {
        for (Section section: sections
        ) {
            chapter.addSection(section);
            sectionRepository.save(section);
        }
        return chapter;
    }

    public Section getSectionById(Long id) {
        Section section =  sectionRepository.getOne(id);
        if (section == null) {
            throw new ResourceNotFoundException("Section with id "+id+" not found");
        }
        return section;
    }

    public void deleteSection(Section section) {
        sectionRepository.delete(section);
    }

    public Chapter addSectionToChapter(Chapter chapter, Section section) {
        chapter.addSection(section);
        sectionRepository.save(section);
        return chapter;
    }

    public List<Section> getSectionsByChapterId(Long chapterId) {
        List<Section> sections = sectionRepository.findByChapterId(chapterId);
        if (sections == null){
            throw new ResourcesNotAvailableException("Sections not availabe");
        }
        return sections;
    }
}
