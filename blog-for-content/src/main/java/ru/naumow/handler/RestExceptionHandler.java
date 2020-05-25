package ru.naumow.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.naumow.exceptions.BlogNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(BlogNotFoundException.class)
    private ResponseEntity<?> handleBlogNotFound(BlogNotFoundException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "blog not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
