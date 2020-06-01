package ru.naumow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "video_channel")
public class VideoChannel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany
    @Where(clause = "type = 'video'")
    private List<Content> contents;

    @PostLoad
    private void loaded() {
        System.out.println(contents.size() + " contents were loaded");
    }

}
