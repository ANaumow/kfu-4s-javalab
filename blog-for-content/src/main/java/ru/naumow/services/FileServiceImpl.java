package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.components.generators.FilenameGenerator;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.dto.FileDto;
import ru.naumow.dto.SaveFileResult;
import ru.naumow.entity.FileInfo;
import ru.naumow.repositories.FileInfoRepository;
import ru.naumow.repositories.FileStorage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.UnaryOperator;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private StorageFilenameResolver filenameResolver;

    @Autowired
    private FilenameGenerator filenameGenerator;

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private FileStorage fileStorage;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Transactional
    @Override
    public FileDto saveMultipart(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            return this.save(inputStream, fileInfo -> {
                fileInfo.setOriginalFilename(multipartFile.getOriginalFilename());
                fileInfo.setContentType(multipartFile.getOriginalFilename());
                return fileInfo;
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Transactional
    @Override
    public FileDto saveRawText(String text) {
        return this.save(new ByteArrayInputStream(text.getBytes()), fileInfo -> {
            fileInfo.setContentType(MediaType.TEXT_PLAIN_VALUE);
            return fileInfo;
        });
    }

    private FileDto save(InputStream inputStream, UnaryOperator<FileInfo> operator) {
        String generatedName = filenameGenerator.generate();
        String localUrl = filenameResolver.localUrl(generatedName);
        String publicUrl = filenameResolver.publicUrl(generatedName);
        SaveFileResult result = fileStorage.save(inputStream, localUrl);
        FileInfo fileInfo = FileInfo.builder()
                .storageFilename(generatedName)
                .size(result.getSize())
                .createdAt(timeResolver.now())
                .build();
        operator.apply(fileInfo);
        fileInfoRepository.save(fileInfo);
        return FileDto.builder()
                .id(fileInfo.getId())
                .contentType(fileInfo.getContentType())
                .localUrl(localUrl)
                .publicUrl(publicUrl)
                .size(result.getSize())
                .build();
    }

}
