package ru.naumow.services;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.FileDto;

public interface FileService {

    FileDto saveMultipart(MultipartFile multipartFile);

    FileDto saveRawText(String text);

}
