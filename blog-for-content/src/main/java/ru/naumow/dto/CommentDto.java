package ru.naumow.dto;

import lombok.*;
import ru.naumow.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NonNull
    private Long id;

    @NonNull
    private UserDto user;

    @NonNull
    private String text;

    @NonNull
    private LocalDateTime cratedAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .cratedAt(comment.getCratedAt())
                .text(comment.getText())
                .user(UserDto.from(comment.getUser()))
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
    }
}
