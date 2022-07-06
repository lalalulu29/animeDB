package ru.kirill98.animeDB.repository;

import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.dto.FormAnime;

import java.util.List;

public interface MapperAnime {
    Anime toAnime(FormAnime anime);
    FormAnime toFormAnime(Anime anime);
    List<Anime> toListAnime(List<FormAnime> animes);
    List<FormAnime> toListFormAnime(List<Anime> animes);
}
