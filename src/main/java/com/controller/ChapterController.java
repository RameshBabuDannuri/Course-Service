package com.controller;

import com.model.Chapter;
import com.model.Course;
import com.service.ChapterService;
import com.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @PutMapping("/courses/chapters")
    public ResponseEntity<?> updateChapter(@Valid @RequestBody Chapter chapter , BindingResult result){
        Chapter chapter1 = chapterService.updateChapter(chapter);
        return new ResponseEntity(chapter1 , HttpStatus.OK);
    }
    @DeleteMapping("/courses/chapters/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long chapterId){
        Chapter chapter = chapterService.deleteChapter(chapterId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("courses/{courseId}/chapters")
    public ResponseEntity<?> getChaptersByCourseId(@PathVariable Long courseId){
        return new ResponseEntity<>(chapterService.getChaptersByCourseID(courseId), HttpStatus.OK);
    }

}
