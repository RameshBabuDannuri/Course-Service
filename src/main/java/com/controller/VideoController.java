package com.controller;

import com.model.Section;
import com.model.Video;
import com.service.SectionService;
import com.service.ValidationErrorService;
import com.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @PostMapping("/sections/{sectionId}/videos")
    public ResponseEntity<?> addVideosToSection(@PathVariable Long sectionId , List<Video> videos){
        Section section = sectionService.getSectionById(sectionId);
        Section section1 = videoService.addVideosToSection(section , videos);
        return new ResponseEntity<>(section1 , HttpStatus.CREATED);

    }
    @PutMapping("/videos")
    public ResponseEntity<?> updateVideo(@Valid @RequestBody Video video , BindingResult result){

        ResponseEntity<?> hasErrors = validationErrorService.mapValidationService(result);
        if(hasErrors != null){
            return hasErrors;
        }
        Video video1 =  videoService.updateVideo(video);
        return new ResponseEntity<>(video1 , HttpStatus.OK);
    }
    @DeleteMapping("/videos/{videoId}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long videoId){
        videoService.deleteVideo(videoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
