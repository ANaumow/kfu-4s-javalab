package ru.naumow.security.api.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.naumow.entity.User;
import ru.naumow.repositories.UsersRepository;
import ru.naumow.security.api.jwt.authentication.JwtAuthentication;
import ru.naumow.security.details.UserDetailsImpl;

// проверить аутентификацию пользователя
@Component
@Profile("api")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    // секретный ключ, которым мы подписываем токен
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            // выполняю парсинг токена со своим секретным ключом
             claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }

        User user = usersRepository.findById(Long.parseLong(claims.get("sub", String.class))).orElseThrow(() -> new AccessDeniedException("bad token"));

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // создаем UserDetails
        /*UserDetails userDetails = UserDetailsImpl.builder()
                .userId(Long.parseLong(claims.get("sub", String.class)))
                .role(claims.get("role", String.class))
                .name(claims.get("name", String.class))
                .build();*/
        // аутентификация прошла успешно
        authentication.setAuthenticated(true);
        // положили в эту аутентификацию пользователя
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;
    }

    // проверяет, подходит ли текущий провайдер для этой аутентификации
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
