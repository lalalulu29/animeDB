package ru.kirill98.animeDB.repository;


import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.Log;

import java.util.List;

public interface DAOAnime {
    List<Anime> getAllAnime();
    void addAnime(Anime anime);
    void addListAnime(List<Anime> animes);
    void delAnime(Integer id);
    Anime getAnimeById(Integer id);
    List<Anime> getAnimeByEnName(String name);
    Long countAnime();
    List<Anime> getAnimeByRangeId(Integer startValue, Integer finishValue);

}
