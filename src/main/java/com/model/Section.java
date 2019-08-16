package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please enter Section name")
    @Size(min=2, max = 50, message = "please enter section name between 2 to 50 characters")
    private String sectionName;
    private String description;

    @ManyToOne
    private Chapter chapter;

    @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
    @JoinColumn(name = "section_id")
    private List<Video> videos;

    public Section() {
    }

    public Section(String sectionName, String description) {
        this.sectionName = sectionName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
    public void addVideos(Video video) {
        if(videos == null){
            videos = new ArrayList<Video>();
        }
        videos.add(video);

    }


}
