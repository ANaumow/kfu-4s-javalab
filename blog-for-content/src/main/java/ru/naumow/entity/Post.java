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
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private boolean active;

    private Integer level;
    @OneToOne(fetch = FetchType.EAGER)
    private Content content;

    private LocalDateTime cratedAt;

    @ManyToOne
    private Blog blog;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<User> likes;

}
