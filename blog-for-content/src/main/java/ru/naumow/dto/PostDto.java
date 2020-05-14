package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NonNull
    private Long id;

    @NonNull
    private String contentUrl;
    @NonNull
    private LocalDateTime cratedAt;
    @NonNull
    private String type;

    @NonNull
    private List<CommentDto> comments;
    @NonNull
    private List<UserDto> likes;

    private int likeCount;
    private int commentCount;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .contentUrl(post.getContent().getUrl())
                .cratedAt(post.getCratedAt())
                .comments(CommentDto.from(post.getComments()))
                .likes(UserDto.from(post.getLikes()))
                .likeCount(post.getLikes().size())
                .commentCount(post.getComments().size())
                .type(post.getType())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

}
