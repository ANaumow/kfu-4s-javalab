package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long   id;
    private String email;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static List<UserDto> from(List<User> list) {
        return list.stream().map(UserDto::from).collect(Collectors.toList());
    }

}
