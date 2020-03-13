package ru.naumow.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.model.FileInfo;

public interface FileInfoService {

    FileInfo getFileInfoByStorageFilename(String url);

    Resource getResourceByFileInfo(FileInfo fileInfo);

    void save(FileInfo fileInfo);

}
