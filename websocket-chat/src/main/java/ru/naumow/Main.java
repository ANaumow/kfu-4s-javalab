package ru.naumow;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("12345"));
        // $2a$10$xLGG05asOh0QnEdYAbeF4e4h34XNipDGfHLgAOVjOxOdBjSRarWs2
    }

}
