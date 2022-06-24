package ru.kirill98.animeDB.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.FormAnime;
import ru.kirill98.animeDB.service.DAO;
import ru.kirill98.animeDB.service.MapperAnime;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j
public class RestMainController {
    private final DAO dao;
    private final MapperAnime mapperAnime;

    @RequestMapping(value = "add_anime", method = RequestMethod.POST)
    public void getAnime(@RequestBody FormAnime formAnime) {
        log.info(String.format("Was get anime, with body: %s", formAnime.toString()));
        dao.addAnime(mapperAnime.toAnime(formAnime));
    }

    @RequestMapping(value = "add_animes", method = RequestMethod.POST)
    public void getAnimes(@RequestBody List<FormAnime> formAnimes) {
        log.info("Was get animes");
        dao.addAnimes(mapperAnime.toAnimes(formAnimes));
    }

    @RequestMapping(value = "del_anime/{id}", method = RequestMethod.GET)
    public void delAnime(@PathVariable(name = "id") Integer id) {
        log.info(String.format("Was tried del anime, with id: %d", id));
        dao.delAnime(id);
    }

    @RequestMapping(value = "find_anime/{id}", method = RequestMethod.GET)
    public String findAnime(@PathVariable(name = "id") Integer id) {
        log.info(String.format("Was tried find anime, with id: %d", id));
        Anime anime = dao.getAnime(id);
        if(anime == null) {
            return "Anime not found";
        }
        return mapperAnime.toFormAnime(anime).toString();
    }
}
