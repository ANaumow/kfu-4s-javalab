package ru.naumow.services.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.FileDto;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.User;

public interface EditorService {

    void submitPost(User user, String mdText, Long postId, String type);

    FileDto processImageSaving(MultipartFile multipartFile);

    FileDto processMdTextSaving(String text);

    String makeSuccessfulUrl(User user);
}
