package com.service;

import com.exceptions.ResourceNotFoundException;
import com.model.Section;
import com.model.Video;
import com.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public  Video save(Video video) {
          return videoRepository.save(video);
    }
    public Section addVideosToSection(Section section, List<Video> videos) {
        for (Video video:videos
        ) {
            section.addVideos(video);
           videoRepository.save(video);
        }
        return section;
    }

    public Video updateVideo(Video video) {
        Video video1 = videoRepository.getOne(video.getId());
        if (video1==null){
            throw new ResourceNotFoundException("Video with id "+video.getId()+" not exist");

        }
        video1 = videoRepository.save(video);
        return video1;
    }

    public void deleteVideo(Long videoId) {
        Video video = videoRepository.getOne(videoId);
        if (video == null){
            throw new ResourceNotFoundException("Video with id "+videoId+" not exist");
        }
        videoRepository.delete(video);

    }
}
