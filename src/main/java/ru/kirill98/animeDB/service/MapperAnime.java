package ru.kirill98.animeDB.service;

import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.FormAnime;

import java.util.List;

public interface MapperAnime {
    Anime toAnime(FormAnime anime);
    FormAnime toFormAnime(Anime anime);
    List<Anime> toAnimes(List<FormAnime> animes);
    List<FormAnime> toFormAnimes(List<Anime> animes);
}
