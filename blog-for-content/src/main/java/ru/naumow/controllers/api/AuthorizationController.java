package ru.naumow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.naumow.dto.SignInDto;
import ru.naumow.dto.TokenDto;
import ru.naumow.security.api.jwt.service.SignInService;

@RestController
@Profile("api")
public class AuthorizationController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }

}
