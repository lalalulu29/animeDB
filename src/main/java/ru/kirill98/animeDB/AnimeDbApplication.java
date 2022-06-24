package ru.kirill98.animeDB;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j
@SpringBootApplication
public class AnimeDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeDbApplication.class, args);
		log.info("Was start app");
	}

}
