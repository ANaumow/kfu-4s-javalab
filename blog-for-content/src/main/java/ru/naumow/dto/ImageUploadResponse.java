package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadResponse {
    Integer success;
    String  message;
    String  url;

    public static ImageUploadResponse Ok(String url) {
        return ImageUploadResponse.builder()
                .success(1)
                .url(url)
                .build();
    }

    public static ImageUploadResponse Error(String msg) {
        return ImageUploadResponse.builder()
                .success(0)
                .message(msg)
                .build();
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
