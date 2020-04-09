package ru.naumow.repositories.editor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.components.generators.FilenameGenerator;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.dto.SaveFileOrder;
import ru.naumow.dto.SaveFileResult;
import ru.naumow.entity.FileInfo;

import java.io.*;

@Repository
public class FileStorageImpl implements FileStorage {

    @Autowired
    private StorageFilenameResolver filenameResolver;

    @Autowired
    private FilenameGenerator filenameGenerator;

    @Override
    public File createOrLoad(String filename) {
        File file = new File(filenameResolver.localUrl(filename));
        file.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return file;
    }

    @Override
    public File load(String filename) {
        File file = new File(filenameResolver.localUrl(filename));
        if (file.exists()) {
            throw new IllegalStateException("file does not exist");
        }
        return file;
    }

    @Override
    public FileInfo saveFile(MultipartFile multipartFile) {
        String generatedFilename = filenameGenerator.generate();
        File destination = createOrLoad(generatedFilename);
        transfer(multipartFile, destination);
        return FileInfo.builder()
                .originalFilename(multipartFile.getOriginalFilename())
                .storageFilename(generatedFilename)
                .contentType(multipartFile.getContentType())
                .size(destination.length())
                .build();
    }

    @Override
    public FileInfo saveTextToFile(String text) {
        String generatedFilename = filenameGenerator.generate();
        File destination = createOrLoad(generatedFilename);
        transfer(text, destination);
        return FileInfo.builder()
                .storageFilename(generatedFilename)
                .contentType("text/plain")
                .size(destination.length())
                .build();
    }

    @Override
    public SaveFileResult saveFile(SaveFileOrder order) {
        File file = createFile(order.getPath() + order.getFilename());
        try (InputStream in = new BufferedInputStream(order.getInputStream());
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
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

    private void transfer(String text, File file) {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private void transfer(MultipartFile multipartFile, File file) {
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

}
