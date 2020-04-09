package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entity.Blog;
import ru.naumow.entity.User;
import ru.naumow.entity.UserRole;
import ru.naumow.entity.UserStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long       id;
    private Blog       blog;
    private String     name;
    private String     surname;
    private String     vocation;
    private String     email;
    private UserStatus status;
    private UserRole   role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .blog(user.getBlog())
                .name(user.getName())
                .surname(user.getSurname())
                .vocation(user.getVocation())
                .email(user.getEmail())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
    }
}
