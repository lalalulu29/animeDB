package ru.kirill98.animeDB.testHibernate;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.repository.HibernateFactory;

import java.io.Serializable;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Log4j
public class TestCRUD {
    private static Session session;
    private static Anime anime;
    private static Integer id;

    @BeforeAll
    static void createEntity() {
        anime = new Anime().toBuilder()
                .enAnimeName("TEST")
                .ruAnimeName("ТЕСТ")
                .releasedEpisodes(2)
                .isFinished(false)
                .started(new Date())
                .build();

    }

    @BeforeEach
    public void createSessionFactory() {
        session = HibernateFactory.getSessionFactory().openSession();
        System.out.println("Session was create");

        session.beginTransaction();
        Serializable created = session.save(anime);
        id = (Integer) created;
        session.getTransaction().commit();
    }



    @Test
    public void createTest() {
        session.beginTransaction();
        Serializable created = session.save(anime);
        id = (Integer) created;
        session.getTransaction().commit();
        System.out.println("Create test was SUCCESS");
    }

    @Test
    public void readTest() {
        Anime animeFromDB = session.find(Anime.class, id);
        assertEquals(anime.getEnAnimeName(), animeFromDB.getEnAnimeName());
    }

    @Test
    public void updateTest() {
        session.beginTransaction();
        Anime animeFromDB = session.find(Anime.class, id);
        animeFromDB.setEnAnimeName("TEST_UPDATE");
        session.update(animeFromDB);
        session.getTransaction().commit();
        Anime newAnimeFromDB = session.find(Anime.class, id);
        assertEquals(animeFromDB.getEnAnimeName(), newAnimeFromDB.getEnAnimeName());
    }

    @Test
    public void deleteTest() {
        Anime animeFromDB = session.find(Anime.class, id);
        session.beginTransaction();
        session.delete(animeFromDB);
        session.getTransaction().commit();
        Anime deletedAnimeFromDB = session.find(Anime.class, id);
        Assertions.assertNull(deletedAnimeFromDB);
    }


    @AfterEach
    public void cancelSession() {


        Anime animeFromDB = session.find(Anime.class, id);
        if (animeFromDB != null) {
            session.beginTransaction();
            session.delete(animeFromDB);
            session.getTransaction().commit();
        }
        session.close();
        System.out.println("Session was close");
    }

    @AfterAll
    static void delEntity() {
        anime = null;
    }

}
