package ru.kirill98.animeDB.entity.dto;

import lombok.*;

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

    @Override
    public String toString() {

        return "{" +
                "\"enAnimeName\": \"" + getEnAnimeName() + "\"," +
                "\"ruAnimeName\": \"" + getRuAnimeName() + "\"," +
                "\"genre\": \"" + getGenre() + "\"," +
                "\"announcedEpisodes\": \"" + getAnnouncedEpisodes() + "\"," +
                "\"releasedEpisodes\": \"" + getReleasedEpisodes() + "\"," +
                "\"isFinished\": \"" + getIsFinished() + "\"," +
                "\"published\": \"" + getPublished() + "\"," +
                "\"started\": \"" + getStarted() + "\"," +
                "\"description\": \"" + getDescription() + "\"" +
                "}";
    }
}
