package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;

    private List<ContentDto> images;

    private List<ContentDto> videos;

    private List<ContentDto> music;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .images(ContentDto.from(Optional.ofNullable(user.getImages()).orElse(new ArrayList<>())))
                .music(ContentDto.from(Optional.ofNullable(user.getMusic()).orElse(new ArrayList<>())))
                .videos(ContentDto.from(Optional.ofNullable(user.getVideos()).orElse(new ArrayList<>())))
                .build();
    }

}
