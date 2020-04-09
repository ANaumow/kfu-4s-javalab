package ru.naumow.services.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.entity.FileInfo;

public interface EditorService {

    void onEditorOpen();

    FileInfo processImageSaving(MultipartFile multipartFile);

    FileInfo processMdTextSaving(String mdText);

    void submitPost(String mdText);

}
