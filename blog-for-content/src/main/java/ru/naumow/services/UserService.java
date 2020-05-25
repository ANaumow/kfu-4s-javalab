package ru.naumow.services;

import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.FileDto;
import ru.naumow.entity.User;

public interface UserService {

    FileDto setAvatar(MultipartFile multipartFile, User user);

}
