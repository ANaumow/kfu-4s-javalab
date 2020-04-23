package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entity.User;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Principal {
    private Long id;
    private String login;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }

    @Override
    public String getName() {
        return login;
    }
}
