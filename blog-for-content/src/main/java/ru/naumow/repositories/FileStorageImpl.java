package ru.naumow.repositories;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.SaveFileResult;

import java.io.*;

@Component
public class FileStorageImpl implements FileStorage {

    @Override
    public SaveFileResult save(MultipartFile multipartFile, String url) {
        try {
            return this.save(multipartFile.getInputStream(), url);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public SaveFileResult save(CharSequence charSequence, String url) {
        return this.save(new ByteArrayInputStream(charSequence.toString().getBytes()), url);
    }

    @Override
    public SaveFileResult save(InputStream inputStream, String url) {
        File file = createFile(url);
        try (InputStream in = new BufferedInputStream(inputStream);
             OutputStream out = new BufferedOutputStream(new FileOutputStream(file))
        ) {
            IOUtils.copy(in, out);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return SaveFileResult.builder()
                .size(file.length())
                .build();
    }

    private File createFile(String url) {
        File file = new File(url);
        if (file.exists())
            throw new IllegalStateException("file already exists");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return file;
    }

}
