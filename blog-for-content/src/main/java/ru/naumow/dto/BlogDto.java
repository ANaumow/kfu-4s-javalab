package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entity.Blog;

import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private Long id;
    private String title;
    private String alias;

    public static BlogDto from(Blog blog) {
        return BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .alias(blog.getAlias())
                .build();
    }
}
