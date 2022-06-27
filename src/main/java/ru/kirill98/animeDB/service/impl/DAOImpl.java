package ru.kirill98.animeDB.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.service.DAO;

import java.util.List;


@RequiredArgsConstructor
@Log4j
@Service
public class DAOImpl implements DAO {
    private final SessionFactory factory = new Configuration().configure().addAnnotatedClass(Anime.class).buildSessionFactory();

    public void addAnime(Anime anime) {
        log.info(String.format("Start write anime with name: %s to DB",anime.getEnAnimeName()));
        Transaction transaction = null;
        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            session.save(anime);
            transaction.commit();
            log.info(String.format("Was wrote anime with name: %s to DB",anime.getEnAnimeName()));
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public void addListAnime(List<Anime> listAnime) {
        log.info("Start write list anime to DB");
        for (Anime anime : listAnime) {
            addAnime(anime);
        }
        log.info("Was wrote list anime to DB");
    }

    @Override
    public void delAnime(Integer id) {
        log.info(String.format("Start delete anime from DB with id: %d",id));
        Transaction  transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Anime anime = session.get(Anime.class, id);
            session.delete(anime);
            transaction.commit();
            log.info(String.format("Was deleted anime by id: %d",id));
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
    public Anime getAnimeById(Integer id) {
        log.info(String.format("Start search anime from DB with id: %d", id));
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Anime anime = session.get(Anime.class, id);
            transaction.commit();
            log.info(String.format("Was find anime by id: %d", id));
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

    @Override
    public List getAnimeByEnName(String name) {
        log.info(String.format("Start search anime with english name: %s", name));
        try(Session session = factory.openSession()) {
            String hql = "from Anime where enAnimeName like ?1";
            Query producer = session.createQuery(hql);
            producer.setParameter(1, "%" + name + "%");
            return producer.getResultList();
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }
}
