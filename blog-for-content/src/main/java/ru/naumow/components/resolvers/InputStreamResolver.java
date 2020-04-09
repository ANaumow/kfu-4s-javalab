package ru.naumow.components.resolvers;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface InputStreamResolver {

    InputStream toInputStream(MultipartFile multipartFile);

    InputStream toInputStream(String text);

}
