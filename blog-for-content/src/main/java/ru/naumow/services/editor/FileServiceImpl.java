package ru.naumow.services.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.entity.FileInfo;
import ru.naumow.components.generators.FilenameGeneratorImpl;
import ru.naumow.repositories.editor.FileInfoRepository;
import ru.naumow.repositories.editor.FileStorage;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorage fileStorage;

    @Autowired
    private FilenameGeneratorImpl filenameGenerator;

    @Override
    public FileInfo saveImage(MultipartFile multipartFile) {
        String type = multipartFile.getContentType();

        if (type == null)
            throw new IllegalStateException("content type is null");

        if (!type.matches("image/.+"))
            throw new IllegalStateException("content type is not an image");

        return saveFile(multipartFile);
    }

    @Override
    public FileInfo saveFile(MultipartFile multipartFile) {
        String generatedFilename = filenameGenerator.generate();
        FileInfo fileInfo = FileInfo.builder()
                .originalFilename(multipartFile.getOriginalFilename())
                .storageFilename(generatedFilename)
                .contentType(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .build();
        fileInfoRepository.save(fileInfo);

        File destination = fileStorage.createOrLoad(generatedFilename);
        transfer(multipartFile, destination);

        return fileInfo;
    }

    private void transfer(MultipartFile multipartFile, File file) {
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

}
