package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long   id;
    private String avatarUrl;
    private String name;
    private String surname;
    private String vocation;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .avatarUrl(user.getAvatarUrl())
                .name(user.getName())
                .surname(user.getSurname())
                .vocation(user.getVocation())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

}
