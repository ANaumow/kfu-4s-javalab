package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Comment;
import ru.naumow.entity.Post;
import ru.naumow.entity.User;
import ru.naumow.model.UserData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String        contentUrl;
    private LocalDateTime cratedAt;
    private String        type;

    private List<CommentDto> comments;
    private List<UserDto>    likes;

    private int likeCount;
    private int commentCount;

    public static PostDto from(Post post) {
        List<User> likes = post.getLikes() == null ? new ArrayList<>() : post.getLikes();
        List<Comment> comments = post.getComments() == null ? new ArrayList<>() : post.getComments();
        String url = post.getContent() == null ? null : post.getContent().getUrl();

        return PostDto.builder()
                .id(post.getId())
                .contentUrl(url)
                .cratedAt(post.getCratedAt())
                .comments(CommentDto.from(comments))
                .likes(UserDto.from(likes))
                .likeCount(likes.size())
                .commentCount(comments.size())
                .type(post.getType())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

}
