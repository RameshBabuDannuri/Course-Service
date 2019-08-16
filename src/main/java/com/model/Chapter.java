package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "please enter chapter name")
    @Size(min = 3, max = 50 ,
            message = "Chapter name must be between 3 and 50 characters long")
    private String chapterName;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    List<Section> sections;

    public Chapter() {
    }

    public Chapter(String chapterName, String description) {
        this.chapterName = chapterName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    public void addSection(Section section) {
        if (sections == null){
            sections = new ArrayList<>();
        }
        sections.add(section);
    }

}
