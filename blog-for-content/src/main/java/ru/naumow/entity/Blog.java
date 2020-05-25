package ru.naumow.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog", indexes = @Index(columnList = "alias"))
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    private String title;

    private String subTitle;

    @OneToOne
    private User owner;

    @OneToMany
    private List<Subscription> subs;

    @OneToMany(mappedBy = "blog")
    private List<Post> posts;

    private LocalDateTime cratedAt;

    private String avatarUrl;
    private String backgroundUrl;

}
