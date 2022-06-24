package ru.kirill98.animeDB.service;


import ru.kirill98.animeDB.entity.Anime;

import java.util.List;
import java.util.Optional;

public interface DAO {
    void addAnime(Anime anime);
    void addAnimes(List<Anime> animes);
    void delAnime(Integer id);
    Anime getAnime(Integer id);
}
