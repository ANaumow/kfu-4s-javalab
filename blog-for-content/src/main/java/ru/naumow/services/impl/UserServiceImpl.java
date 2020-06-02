package ru.naumow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.naumow.dto.FileDto;
import ru.naumow.entity.User;
import ru.naumow.repositories.UsersRepository;
import ru.naumow.services.FileService;
import ru.naumow.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FileService fileService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public FileDto setAvatar(MultipartFile multipartFile, User user) {
        FileDto fileDto = fileService.saveMultipart(multipartFile);
        user.setAvatarUrl(fileDto.getPublicUrl());
        usersRepository.save(user);
        return fileDto;
    }

}
