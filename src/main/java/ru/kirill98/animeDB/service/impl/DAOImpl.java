package ru.kirill98.animeDB.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.service.DAO;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Log4j
@Service
public class DAOImpl implements DAO {
    private final SessionFactory factory = new Configuration().configure().addAnnotatedClass(Anime.class).buildSessionFactory();

    public void addAnime(Anime anime) {
        log.info("Start write anime to DB");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            session.save(anime);
            transaction.commit();
            log.info("Was wrote anime to DB");
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public void addAnimes(List<Anime> animes) {
        log.info("Start write animes to DB");
        for (Anime anime : animes) {
            addAnime(anime);
        }
        log.info("Was wrote animes to DB");
    }

    @Override
    public void delAnime(Integer id) {
        log.info("Start delete anime from DB");
        Transaction  transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Anime anime = session.get(Anime.class, id);
            session.delete(anime);
            transaction.commit();
            log.info("Was deleted anime by id");
        } catch (HibernateException exception){
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(exception.getMessage());
            exception.printStackTrace();
        } catch (IllegalArgumentException argumentException) {
            log.error(argumentException.getMessage());
            argumentException.printStackTrace();
        }
    }

    @Override
    public Anime getAnime(Integer id) {
        log.info("Start search anime from DB");
        Transaction  transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Anime anime = session.get(Anime.class, id);
            transaction.commit();
            log.info("Was find anime by id");
            return anime;
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }
}
