package com.service;

import com.model.Video;
import com.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public  Video save(Video video) {
          return videoRepository.save(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.getOne(id);
    }

    public void deleteVideo(Video video) {
        videoRepository.delete(video);
    }
}
