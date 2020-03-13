package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.model.FileInfo;
import ru.naumow.repositories.FileInfoRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Properties;

@Component
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    @Qualifier("fileService")
    private Properties properties;

    @Override
    public FileInfo getFileInfoByStorageFilename(String filename) {
        return fileInfoRepository.findByStorageFileName(filename)
                .orElseThrow(() -> new IllegalArgumentException("FileInfo not found"));
        /*
        File file = new File(fileInfo.getUrl());
        if (!file.exists()) {
            throw new IllegalArgumentException("file not found");
        }
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }*/
    }

    @Override
    public Resource getResourceByFileInfo(FileInfo fileInfo) {
        try {
            return new FileUrlResource(new File(fileInfo.getPath()).toURI().toURL());
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(FileInfo fileInfo) {
        LocalDateTime now = LocalDateTime.now();
        MultipartFile multipartFile = fileInfo.getMultipartFile();

        String storageFileName = String.valueOf((now.toString() + multipartFile.getOriginalFilename()).hashCode());
        String originalFilename = multipartFile.getOriginalFilename();
        Long size = multipartFile.getSize();
        String type = multipartFile.getContentType();
        String path = properties.getProperty("files.storage") + storageFileName;


        fileInfo.setStorageFileName(storageFileName);
        fileInfo.setOriginalFileName(originalFilename);
        fileInfo.setSize(size);
        fileInfo.setType(type);
        fileInfo.setPath(path);

        File storageFile = new File(path);
        try {
            storageFile.createNewFile();
            multipartFile.transferTo(storageFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        fileInfoRepository.save(fileInfo);
    }
}
