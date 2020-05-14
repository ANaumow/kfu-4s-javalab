package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Blog;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    @NonNull
    private Long id;

    @NonNull
    private String alias;
    @NonNull
    private String title;
    @NonNull
    private String subTitle;

    private String avatarUrl;
    private String backgroundUrl;

    @NonNull
    private List<PostDto> posts;

    private int subCount;
    private int postCount;

    public static BlogDto from(Blog blog, List<PostDto> posts) {
        return BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .subTitle(blog.getSubTitle())
                .avatarUrl(blog.getAvatarUrl())
                .backgroundUrl(blog.getBackgroundUrl())
                .alias(blog.getAlias())
                .posts(posts)
                .postCount(posts.size())
                .subCount(blog.getSubs().size())
                .build();
    }
}
