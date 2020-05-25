package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogForm {
    private String alias;
    private String title;
    private String subTitle;
    private MultipartFile background;
    private MultipartFile avatar;
}
