package ru.kirill98.animeDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "en_anime_name")
    private String enAnimeName;

    @Column(name = "ru_anime_name")
    private String ruAnimeName;

    @Column(name = "genre")
    private String genre;

    @Column(name = "announced_episodes")
    private Integer announcedEpisodes;

    @Column(name = "released_episodes")
    private Integer releasedEpisodes;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "published")
    private String published;

    @Column(name = "started")
    private Date started;

    @Column(name = "description")
    private String description;

//    Anime() {}

}
