package ru.naumow.service;

import ru.naumow.entities.Stats;
import ru.naumow.entities.VideoChannel;

import java.util.List;

public interface ContentService {

    void like(Long contentId, String name);

    void create(String body, String type, Long id);

    List<VideoChannel> videos();

    Stats stats();

}
