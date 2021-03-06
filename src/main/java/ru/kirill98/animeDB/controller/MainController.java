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
import ru.kirill98.animeDB.service.DAO;
import ru.kirill98.animeDB.service.MapperAnime;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Log4j
public class MainController {
    private final DAO dao;
    private final MapperAnime mapperAnime;

    @Value("${anime.maxInOnePage}")
    private Long maxInOnePage;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainView(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            Model model) {
        log.info("Was got page with all anime");
        if(page == 0) {
            page = 1;
        }
        Long allAnimeCount = dao.countAnime();
        int maxPage = (int) ((double)allAnimeCount / maxInOnePage);
        model.addAttribute("max_page", maxPage);
        model.addAttribute("count_page", page);
        List<Anime> listAnime;
        if(page == 1) {
            listAnime = dao.getAnimeByRangeId(1, 10);
        } else if(page == maxPage) {
            System.out.println("Start value: " + (page) * 10 + " and end value: " + Math.toIntExact(allAnimeCount));
            listAnime = dao.getAnimeByRangeId((page) * 10, Math.toIntExact(allAnimeCount));
        } else {
            System.out.println("Start value: " + page * 10 + " and end value: " + (page * 10 + 9));
            listAnime = dao.getAnimeByRangeId(page * 10, page * 10 + 9);
        }
        List<FormAnime> listFormAnime = mapperAnime.toListFormAnime(listAnime);
        model.addAttribute("listAnime", listFormAnime);
        log.info(String.format("Returned all anime (%d pieces)", listFormAnime.size()));
        return "index";
    }
}

