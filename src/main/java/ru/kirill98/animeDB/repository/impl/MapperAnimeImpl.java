package ru.kirill98.animeDB.repository.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.dto.FormAnime;
import ru.kirill98.animeDB.repository.MapperAnime;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j
@Service
public class MapperAnimeImpl implements MapperAnime {

    @Override
    public Anime toAnime(FormAnime anime) {
        log.info(String.format("Start mapping anime from form anime with name: %s", anime.getEnAnimeName()));
        return new Anime(null,
                anime.getEnAnimeName(),
                anime.getRuAnimeName(),
                anime.getGenre(),
                anime.getAnnouncedEpisodes(),
                anime.getReleasedEpisodes(),
                anime.getIsFinished(),
                anime.getPublished(),
                anime.getStarted(),
                anime.getDescription()
                );
    }

    @Override
    public FormAnime toFormAnime(Anime anime) {
        log.info(String.format("Start mapping form anime from anime with name: %s", anime.getEnAnimeName()));
        return new FormAnime().toBuilder()
                .enAnimeName(anime.getEnAnimeName())
                .ruAnimeName(anime.getRuAnimeName())
                .genre(anime.getGenre())
                .announcedEpisodes(anime.getAnnouncedEpisodes())
                .releasedEpisodes(anime.getReleasedEpisodes())
                .isFinished(anime.getIsFinished())
                .published(anime.getPublished())
                .started(anime.getStarted())
                .description(anime.getDescription())
                .build();
    }

    @Override
    public List<Anime> toListAnime(List<FormAnime> listAnime) {
        log.info("Start mapping list forms anime from anime");
        return listAnime.stream().map(this::toAnime).collect(Collectors.toList());
    }

    @Override
    public List<FormAnime> toListFormAnime(List<Anime> listAnime) {
        log.info("Start mapping list anime from forms anime");
        return listAnime.stream().map(this::toFormAnime).collect(Collectors.toList());
    }

}
