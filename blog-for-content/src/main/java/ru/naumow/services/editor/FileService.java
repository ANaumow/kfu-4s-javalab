package ru.naumow.services.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.entity.FileInfo;

public interface FileService {

    FileInfo saveImage(MultipartFile multipartFile);

    FileInfo saveFile(MultipartFile multipartFile);



}
