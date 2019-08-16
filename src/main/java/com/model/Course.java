package com.model;


import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "please enter course Name")
    @Size(min = 1 , max = 50 ,message = "Course Name must be between 1 and 50 characters long")
    private String courseName;
    @NotNull(message = "please enter price details")
    private Double price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endDate;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
            @JoinColumn(name = "course_id")
    Set<Chapter> chapters;

    public Course() {
    }

    public Course(String courseName, Double price) {
        this.courseName = courseName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        if (chapters == null){
            chapters = new HashSet<>();
        }
        this.chapters = chapters;
    }
    public void addChapter(Chapter chapter) {
        if (chapters == null){
            chapters = new HashSet<>();
        }
        chapters.add(chapter);
    }
}
