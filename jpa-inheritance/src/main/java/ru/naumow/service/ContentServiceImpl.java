package ru.naumow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.entity.ImageContent;
import ru.naumow.repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    @Transactional
    public void save(Integer sizeX, Integer sizeY, String name) {
        ImageContent imageContent = ImageContent.builder()
                .sizeX(sizeX)
                .sizeY(sizeY)
                .name(name)
                .build();
        contentRepository.save(imageContent);
    }

}
