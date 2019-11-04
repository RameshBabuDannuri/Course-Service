package com.service;

import com.model.Section;
import com.model.Video;

import java.util.List;

public interface VideoService {
    Video createVideo(Video video);
    Video updateVideo(Video video);
    void deleteVideo(Long videoId);
    Section addVideosToSection(Section section, List<Video> videos);
}
