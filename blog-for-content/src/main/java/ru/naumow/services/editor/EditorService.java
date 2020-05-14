package ru.naumow.services.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.User;

public interface EditorService {

    void onEditorOpen();

    FileInfo processImageSaving(MultipartFile multipartFile);

    FileInfo processMdTextSaving(String mdText);

    void submitPost(String mdText, String blogAlias, User user, String type);

}
