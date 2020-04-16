package ru.naumow.services.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.components.generators.FilenameGenerator;
import ru.naumow.components.resolvers.InputStreamResolver;
import ru.naumow.components.resolvers.StorageFilenameResolver;
import ru.naumow.dto.SaveFileOrder;
import ru.naumow.dto.SaveFileResult;
import ru.naumow.entity.Content;
import ru.naumow.entity.FileInfo;
import ru.naumow.entity.Post;
import ru.naumow.model.UserSessionData;
import ru.naumow.repositories.ContentRepository;
import ru.naumow.repositories.PostRepository;
import ru.naumow.repositories.editor.FileInfoRepository;
import ru.naumow.repositories.editor.FileStorage;

import java.io.InputStream;

@Service
public class EditorServiceImpl implements EditorService {
    @Autowired private InputStreamResolver     inputStreamResolver;
    @Autowired private StorageFilenameResolver storageFilenameResolver;
    @Autowired private FilenameGenerator       filenameGenerator;

    @Autowired private FileStorage        fileStorage;
    @Autowired private FileInfoRepository fileInfoRepository;
    @Autowired private PostRepository     postRepository;
    @Autowired private ContentRepository  contentRepository;

    @Autowired private UserSessionData userSessionData;

    @Override
    public void onEditorOpen() {
    }

    @Override
    public FileInfo processImageSaving(MultipartFile multipartFile) {
        assertImageType(multipartFile.getContentType());

        InputStream multiPartInputStream = inputStreamResolver.toInputStream(multipartFile);
        String generatedFilename = filenameGenerator.generate();
        String storageFilepath = storageFilenameResolver.localPath();

        FileInfo fileInfo = processInputStreamSaving(multiPartInputStream, storageFilepath, generatedFilename);
        fileInfo.setOriginalFilename(multipartFile.getOriginalFilename());
        fileInfo.setContentType(multipartFile.getContentType());

        fileInfoRepository.save(fileInfo);

        return fileInfo;
    }

    @Override
    public FileInfo processMdTextSaving(String mdText) {
        InputStream multiPartInputStream = inputStreamResolver.toInputStream(mdText);
        String generatedFilename = filenameGenerator.generate();
        String storageFilepath = storageFilenameResolver.localPath();

        FileInfo fileInfo = processInputStreamSaving(multiPartInputStream, storageFilepath, generatedFilename);
        fileInfo.setContentType("text/plain");

        fileInfoRepository.save(fileInfo);

        return fileInfo;
    }

    @Override
    public void submitPost(String mdText) {
        FileInfo fileInfo = processMdTextSaving(mdText);
        Content content = Content.builder()
                .url(storageFilenameResolver.sharedUrl(fileInfo.getStorageFilename()))
                .build();
        contentRepository.save(content);
        Post post = Post.builder()
                .blog(userSessionData.getUser().getBlog())
                .content(content)
                .build();
        postRepository.save(post);
    }

    private FileInfo processInputStreamSaving(
            InputStream inputStream, String path, String filename) {

        SaveFileOrder order = SaveFileOrder.builder()
                .filename(filename)
                .path(path)
                .inputStream(inputStream)
                .build();

        SaveFileResult result = fileStorage.saveFile(order);

        return FileInfo.builder()
                .storageFilename(filename)
                .size(result.getSize())
                .build();
    }

    private void assertImageType(String contentType) {
        if (contentType == null)
            throw new IllegalStateException("content type is null");
        if (!contentType.matches("image/.+"))
            throw new IllegalStateException("content type is not an image");
    }


}
