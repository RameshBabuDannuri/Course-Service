package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "please enter video Name")
    @Size(min = 3, max = 100, message = "please enter video name between 3 to 100 characters")
    private String  videoName;
    @NotBlank(message = "please enter video link")
    private String videoLink;
    private String videoDescription;
    private Time Duration;
    @ManyToOne
    @JsonIgnore
    private Section section;

    public Video() {
    }

    public Video(String videoName, String videoLink) {
        this.videoName = videoName;
        this.videoLink = videoLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public Time getDuration() {
        return Duration;
    }

    public void setDuration(Time duration) {
        Duration = duration;
    }
}
