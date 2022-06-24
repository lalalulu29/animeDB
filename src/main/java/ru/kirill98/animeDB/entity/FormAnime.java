package ru.kirill98.animeDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormAnime {
    private String enAnimeName;
    private String ruAnimeName;
    private String genre;
    private Integer announcedEpisodes;
    private Integer releasedEpisodes;
    private Boolean isFinished;
    private String published;
    private Date started;
    private String description;
}
