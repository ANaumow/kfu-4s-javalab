package ru.naumow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.entities.Content;
import ru.naumow.entities.Stats;
import ru.naumow.entities.VideoChannel;
import ru.naumow.repositories.ContentRepository;
import ru.naumow.repositories.VideoRepository;

import java.util.List;
import java.util.Stack;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void like(Long contentId, String name) {
        throw new IllegalStateException("method is not implemented yet");
    }

    @Override
    @Transactional
    public void create(String body, String type, Long id) {
        Content content = Content.builder().body(body).type(type).build();
        contentRepository.save(content);
        VideoChannel channel = videoRepository.findById(id).orElseThrow(IllegalStateException::new);
        channel.getContents().add(content);
    }

    @Override
    public List<VideoChannel> videos() {
        return videoRepository.findAll();
    }

    @Override
    @Transactional
    public Stats stats() {
        return contentRepository.getStats();
    }

}

