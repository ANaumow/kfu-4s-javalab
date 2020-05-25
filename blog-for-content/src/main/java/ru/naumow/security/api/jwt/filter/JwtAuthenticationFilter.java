package ru.naumow.security.api.jwt.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.naumow.security.api.jwt.authentication.JwtAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component( "jwtAuthenticationFilter")
@Profile("api")
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запрос в HTTP
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // получаем токен
        String token = request.getHeader("Authorization");
        // создаем объект аутентификации
        Authentication authentication = new JwtAuthentication(token);
        // кладем его в контекст для текущего потока
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // отправили запрос дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
