package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NonNull
    private Long       id;
    @NonNull
    private String     avatarUrl;
    @NonNull
    private String     name;
    @NonNull
    private String     surname;
    @NonNull
    private String     vocation;

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
