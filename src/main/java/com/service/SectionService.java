package com.service;

import com.exceptions.ResourceNotFoundException;
import com.model.Section;
import com.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public Section save(Section section) {
       return sectionRepository.save(section);
    }

    public Section getSectionById(Long id) {
        Section section =  sectionRepository.getOne(id);
        if (section == null) {
            throw new ResourceNotFoundException("Section with id "+id+" not found");
        }
        return section;
    }


    public List<Section> findSectionByChapterId(Long chapterId) {
        return sectionRepository.findByChapterId(chapterId);
    }

    public void deleteSection(Section section) {
        sectionRepository.delete(section);
    }
}
