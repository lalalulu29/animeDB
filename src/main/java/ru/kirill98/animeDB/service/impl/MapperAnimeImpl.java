package ru.kirill98.animeDB.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.dto.FormAnime;
import ru.kirill98.animeDB.service.MapperAnime;

import java.util.ArrayList;
import java.util.List;


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
        List<Anime> readyListAnime = new ArrayList<>();
        for (FormAnime anime : listAnime) {
            readyListAnime.add(toAnime(anime));
        }
        return readyListAnime;
    }

    @Override
    public List<FormAnime> toListFormAnime(List<Anime> listAnime) {
        log.info("Start mapping list anime from forms anime");
        List<FormAnime> readyListFormAnime = new ArrayList<>();
        for (Anime anime : listAnime) {
            readyListFormAnime.add(toFormAnime(anime));
        }
        return readyListFormAnime;
    }
}
