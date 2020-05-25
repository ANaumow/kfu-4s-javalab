package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Blog;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogInfo {

    private Long   id;
    private int    subCount;
    private String alias;
    private String title;
    private String subTitle;
    private String avatarUrl;
    private String backgroundUrl;

    public static BlogInfo from(Blog blog) {
        return BlogInfo.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .subTitle(blog.getSubTitle())
                .avatarUrl(blog.getAvatarUrl())
                .backgroundUrl(blog.getBackgroundUrl())
                .alias(blog.getAlias())
                .subCount(blog.getSubs() == null ? 0 : blog.getSubs().size())
                .build();
    }

}
