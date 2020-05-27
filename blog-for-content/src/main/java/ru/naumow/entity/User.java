package ru.naumow.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String vocation;
    private String email;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private LocalDateTime createdAt;
    private String avatarUrl;

    private String confirmationUiid;

}
