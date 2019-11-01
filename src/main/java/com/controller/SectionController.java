package com.controller;

import com.model.Chapter;
import com.model.Section;
import com.service.ChapterService;
import com.service.SectionService;
import com.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/chapters/{chapterId}/sections")
    public ResponseEntity<?> addSectionsToChapter(@PathVariable Long chapterId , List<Section> sections){
        Chapter chapter = chapterService.getChapterById(chapterId);
        Chapter chapter1 = sectionService.addSectionsToChapter(chapter , sections);
        return new ResponseEntity<>(chapter1 , HttpStatus.CREATED);

    }
    @PostMapping("/chapters/{chapterId}/section")
    public ResponseEntity<?> addSectionToChapter(
            @PathVariable Long chapterId , @Valid @RequestBody Section section, BindingResult result){
        Chapter chapter = chapterService.getChapterById(chapterId);
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        Chapter chapter1 = sectionService.addSectionToChapter(chapter , section);
        return new ResponseEntity<>(chapter1 , HttpStatus.CREATED);
    }
    @PutMapping("/sections")
    public ResponseEntity<?> updateSection(@Valid  @RequestBody Section section , BindingResult result){

        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        Section section1 = chapterService.updateSection(section);
        return new ResponseEntity<>(section1 , HttpStatus.CREATED);
    }
    @DeleteMapping("/sections/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long sectionId){
        chapterService.deleteSection(sectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/chapters/{chapterId}/sections")
    public ResponseEntity<List<Section>> getSectionsByChapterId(@PathVariable Long chapterId){
        List<Section> sections = chapterService.getSectionsByChapterId(chapterId);
        return new ResponseEntity<>(sections , HttpStatus.OK);
    }
}
