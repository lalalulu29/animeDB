package ru.kirill98.animeDB.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
@Log4j
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testRequest() {
        return "index";
    }
}

