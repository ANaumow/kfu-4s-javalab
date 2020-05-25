package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Blog;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private BlogInfo info;
    private UserDto owner;

    private int           postCount;
    private List<PostDto> posts;

    public static BlogDto from(Blog blog, List<PostDto> posts) {
        return BlogDto.builder()
                .info(BlogInfo.from(blog))
                .owner(UserDto.from(blog.getOwner()))
                .postCount(posts.size())
                .posts(posts)
                .build();
    }
}
