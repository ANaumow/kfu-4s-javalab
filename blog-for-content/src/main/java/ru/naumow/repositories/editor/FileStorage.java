package ru.naumow.repositories.editor;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.SaveFileOrder;
import ru.naumow.dto.SaveFileResult;
import ru.naumow.entity.FileInfo;

import java.io.File;

public interface FileStorage {

    File createOrLoad(String name);

    File load(String name);

    FileInfo saveFile(MultipartFile file);

    FileInfo saveTextToFile(String text);

    SaveFileResult saveFile(SaveFileOrder order);

}
