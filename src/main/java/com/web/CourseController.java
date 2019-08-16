package com.web;

import com.model.Chapter;
import com.model.Course;
import com.model.Section;
import com.model.Video;
import com.service.ChapterService;
import com.service.CourseService;
import com.service.SectionService;
import com.service.ValidationErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/courses")
public class CourseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private  SectionService sectionService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createCourse(@Valid  @RequestBody Course course, BindingResult result){

        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        course.setCourseName(course.getCourseName().toUpperCase());
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);

    }
    @PutMapping("")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody Course course , BindingResult result){
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if (hasErrors != null){
            return hasErrors;
        }
        course.setCourseName(course.getCourseName().toUpperCase());
        return new ResponseEntity<>(courseService.updateCourse(course), HttpStatus.OK);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>( courseService.getAll() , HttpStatus.OK);
    }
    @GetMapping("/{courseName}")
    public ResponseEntity<List<Course>> getCourseByName(@PathVariable String courseName){
        return new ResponseEntity<>( courseService.findByName(courseName)  ,HttpStatus.OK);
    }

    // Chapter APIs

    @PostMapping("/{courseId}/chapters")
    public ResponseEntity<?> addChaptersToCourse(@PathVariable Long courseId , @RequestBody Set<Chapter> chapters){
       Course course =   courseService.addChapters(courseId , chapters);

       return new ResponseEntity<>(course , HttpStatus.CREATED);
    }
    @PostMapping("/{courseId}/chapter")
    public ResponseEntity<?> addChapterToCourse(
            @PathVariable Long courseId , @Valid @RequestBody Chapter chapter,BindingResult result){
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors!=null){
            return  hasErrors;
        }
        Course course =   courseService.addChapter(courseId , chapter);

        return new ResponseEntity<>(course , HttpStatus.CREATED);
    }
    @PutMapping("/chapters")
    public ResponseEntity<?> updateChapter(@Valid @RequestBody Chapter chapter , BindingResult result){
        Chapter chapter1 = courseService.updateChapter(chapter);
        return new ResponseEntity(chapter1 , HttpStatus.OK);
    }
    @DeleteMapping("/chapters/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long chapterId){
        Chapter chapter = courseService.deleteChapter(chapterId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{courseId}/chapters")
    public ResponseEntity<?> getChaptersByCourse(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getChaptersByCourseID(courseId), HttpStatus.OK);
    }

    //=======>  Section APIs <===========

    @PostMapping("/{chapterId}/sections")
    public ResponseEntity<?> addSectionsToChapter(@PathVariable Long chapterId ,List<Section> sections){
       Chapter chapter = chapterService.getChapterById(chapterId);
       Chapter chapter1 = chapterService.addSectionsToChapter(chapter , sections);
        return new ResponseEntity<>(chapter1 , HttpStatus.CREATED);

    }
    @PostMapping("/{chapterId}/section")
    public ResponseEntity<?> addSectionToChapter(
            @PathVariable Long chapterId ,@Valid @RequestBody Section section,BindingResult result){
        Chapter chapter = chapterService.getChapterById(chapterId);
        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        Chapter chapter1 = chapterService.addSectionToChapter(chapter , section);
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
    //====> Video Apis <=====

    @PostMapping("/{sectionId}/videos")
    public ResponseEntity<?> addVideosToSection(@PathVariable Long sectionId ,List<Video> videos){
        Section section = sectionService.getSectionById(sectionId);
        Section section1 = chapterService.addVideosToSection(section , videos);
        return new ResponseEntity<>(section1 , HttpStatus.CREATED);

    }
    @PutMapping("/videos")
    public ResponseEntity<?> updateVideo(@Valid @RequestBody Video video , BindingResult result){

        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        Video video1 =  chapterService.updateVideo(video);
        return new ResponseEntity<>(video1 , HttpStatus.OK);
    }
    @DeleteMapping("/videos/{videoId}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long videoId){
        chapterService.deleteVideo(videoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
