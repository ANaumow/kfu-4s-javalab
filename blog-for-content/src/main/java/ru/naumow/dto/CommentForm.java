package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumow.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
    private Long postId;
    private String text;

}
