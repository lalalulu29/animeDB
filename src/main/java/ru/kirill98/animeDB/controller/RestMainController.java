package ru.kirill98.animeDB.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.dto.FormAnime;
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

    @RequestMapping(value = "add_list_anime", method = RequestMethod.POST)
    public void getListAnime(@RequestBody List<FormAnime> listFormAnime) {
        log.info("Was get list anime");
        dao.addListAnime(mapperAnime.toListAnime(listFormAnime));
    }

    @RequestMapping(value = "del_anime/{id}", method = RequestMethod.GET)
    public void delAnime(@PathVariable(name = "id") Integer id) {
        log.info(String.format("Was tried del anime, with id: %d", id));
        dao.delAnime(id);
    }

    @RequestMapping(value = "find_anime/{id}", method = RequestMethod.GET)
    public String findAnime(@PathVariable(name = "id") Integer id) {
        log.info(String.format("Was tried find anime, with id: %d", id));
        Anime anime = dao.getAnimeById(id);
        if(anime == null) {
            return "Anime not found";
        }
        return mapperAnime.toFormAnime(anime).toString();
    }

    @RequestMapping(value = "anime_name/{name}", method = RequestMethod.GET)
    public String findAnimeByEnName(@PathVariable("name") String name) {
        if(name.length() < 3) {
            log.info(String.format("Name: `%s` has %d symbols. System cant find this name", name, name.length()));
            return "So short name";
        }
        log.info(String.format("Was tried find anime, with name: %s",name));
        List<Anime> animeList = dao.getAnimeByEnName(name);
        if (animeList.isEmpty()) {
            return "Anime not found";
        }
        return mapperAnime.toListFormAnime(animeList).toString();
    }
}
