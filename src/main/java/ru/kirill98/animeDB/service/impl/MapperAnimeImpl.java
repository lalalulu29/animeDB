package ru.kirill98.animeDB.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.FormAnime;
import ru.kirill98.animeDB.service.MapperAnime;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Log4j
@Service
public class MapperAnimeImpl implements MapperAnime {

    @Override
    public Anime toAnime(FormAnime anime) {
        log.info("Start mapping anime from form anime");
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
        log.info("Start mapping form anime from anime");
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
    public List<Anime> toAnimes(List<FormAnime> animes) {
        log.info("Start mapping list forms anime from anime");
        List<Anime> readyAnimes = new ArrayList<>();
        for (FormAnime anime : animes) {
            readyAnimes.add(toAnime(anime));
        }
        return readyAnimes;
    }

    @Override
    public List<FormAnime> toFormAnimes(List<Anime> animes) {
        log.info("Start mapping list anime from forms anime");
        List<FormAnime> readyFormAnimes = new ArrayList<>();
        for (Anime anime : animes) {
            readyFormAnimes.add(toFormAnime(anime));
        }
        return readyFormAnimes;
    }
}
