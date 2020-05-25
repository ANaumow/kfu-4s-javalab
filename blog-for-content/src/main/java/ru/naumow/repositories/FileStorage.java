package ru.naumow.repositories;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.SaveFileResult;

import java.io.InputStream;

public interface FileStorage {

    SaveFileResult save(MultipartFile multipartFile, String url);

    SaveFileResult save(CharSequence charSequence, String url);

    SaveFileResult save(InputStream inputStream, String url);

}
