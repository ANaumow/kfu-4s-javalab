package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.SignUpDto;
import ru.naumow.entity.Blog;
import ru.naumow.entity.User;
import ru.naumow.entity.UserRole;
import ru.naumow.entity.UserStatus;
import ru.naumow.repositories.BlogRepository;
import ru.naumow.repositories.UsersRepository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired private UsersRepository usersRepository;
    @Autowired private BlogRepository  blogRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private LocalDateTimeResolver timeResolver;


    @Override
    public void signUp(SignUpDto form) {
        String email = form.getEmail();
        String password = form.getPassword();

        boolean isEmailAvailable = !usersRepository.findByEmail(email).isPresent();
        if (!isEmailAvailable) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        Blog blog = Blog.builder()
                .alias(form.getBlogAlias())
                .title("")
                .build();
        blogRepository.save(blog);

        String confirmId = UUID.randomUUID().toString();
        User user = User.builder()
                .role(UserRole.USER)
                .email(email)
                .name(form.getName())
                .surname(form.getSurname())
                .vocation(form.getVocation())
                .hashPassword(encodedPassword)
                .status(UserStatus.CONFIRMED)
                .avatarUrl("def/avatar")
                .createdAt(timeResolver.now())
                .build();
        usersRepository.save(user);
    }


}
