package com.controller;

import com.model.Chapter;
import com.model.Course;
import com.service.CourseService;
import com.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class ChapterController {
    @Autowired
    private  CourseService courseService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/courses/{courseId}/chapter")
    public ResponseEntity<?> addChapterToCourse(
            @PathVariable Long courseId , @Valid @RequestBody Chapter chapter, BindingResult result){
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors!=null){
            return  hasErrors;
        }
        Course course =   courseService.addChapter(courseId , chapter);

        return new ResponseEntity<>(course , HttpStatus.CREATED);
    }
    @PutMapping("/courses/chapters")
    public ResponseEntity<?> updateChapter(@Valid @RequestBody Chapter chapter , BindingResult result){
        Chapter chapter1 = courseService.updateChapter(chapter);
        return new ResponseEntity(chapter1 , HttpStatus.OK);
    }
    @DeleteMapping("/courses/chapters/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long chapterId){
        Chapter chapter = courseService.deleteChapter(chapterId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("courses/{courseId}/chapters")
    public ResponseEntity<?> getChaptersByCourse(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getChaptersByCourseID(courseId), HttpStatus.OK);
    }

}
