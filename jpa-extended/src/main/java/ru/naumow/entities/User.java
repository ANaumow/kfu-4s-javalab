package ru.naumow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "owner")
    @Where(clause = "type = 'image'")
    private List<Content> images;

    @OneToMany(mappedBy = "owner")
    @Where(clause = "type = 'video'")
    private List<Content> videos;

    @OneToMany(mappedBy = "owner")
    @Where(clause = "type = 'music'")
    private List<Content> music;

}
