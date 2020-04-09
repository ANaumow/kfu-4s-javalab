package ru.naumow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Blog blog;

    private String name;
    private String surname;
    private String vocation;
    private String email;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "subId.user", fetch = FetchType.EAGER)
    private Set<Subscription> subs;

}
