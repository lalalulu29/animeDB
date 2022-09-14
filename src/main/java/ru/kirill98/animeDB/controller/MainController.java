package ru.kirill98.animeDB.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.dto.FormAnime;
import ru.kirill98.animeDB.repository.DAOAnime;
import ru.kirill98.animeDB.repository.MapperAnime;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Log4j
public class MainController {
    private final DAOAnime dao;
    private final MapperAnime mapperAnime;

    @Value("${anime.maxInOnePage}")
    private Integer maxInOnePage;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainView(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            Model model) {
        log.info("Was got page with all anime");
        Long allAnimeCount = dao.countAnime();
        int maxPage = (int) ((double)allAnimeCount / maxInOnePage);
        model.addAttribute("max_page", maxPage);
        model.addAttribute("count_page", page);
        List<Anime> listAnime;
        if(page == 1) {
            listAnime = dao.getAnimeByRangeId(1, maxInOnePage);
        } else if(page == maxPage) {
            listAnime = dao.getAnimeByRangeId(page * maxInOnePage + 1, Math.toIntExact(allAnimeCount));
        } else {
            listAnime = dao.getAnimeByRangeId(page * maxInOnePage + 1, page * maxInOnePage + maxInOnePage);
        }
        List<FormAnime> listFormAnime = mapperAnime.toListFormAnime(listAnime);
        model.addAttribute("listAnime", listFormAnime);
        log.info(String.format("Returned all anime (%d pieces)", listFormAnime.size()));
        return "index";
    }
}

