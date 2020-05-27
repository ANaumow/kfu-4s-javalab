package ru.naumow.services.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.FileDto;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.User;

public interface EditorService {

    PostDto submitPost(User user, String mdText, Long postId, String type);

    FileDto processImageSaving(MultipartFile multipartFile);

    FileDto processMdTextSaving(String text);

    String makeSuccessfulUrl(User user);
}
