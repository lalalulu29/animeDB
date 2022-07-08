package ru.kirill98.animeDB.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Anime;
import ru.kirill98.animeDB.entity.Log;
import ru.kirill98.animeDB.repository.DAOAnime;

import java.util.List;


@RequiredArgsConstructor
@Log4j
@Service
public class DAOAnimeImpl implements DAOAnime {
    private final SessionFactory factoryAnime = new Configuration().configure().addAnnotatedClass(Anime.class).buildSessionFactory();


    @Override
    public List<Anime> getAllAnime() {
        log.info("Start get all anime");
        try(Session session = factoryAnime.openSession()) {
            String hql = "from Anime";
            Query query = session.createQuery(hql);
            return query.getResultList();
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public void addAnime(Anime anime) {
        log.info(String.format("Start write anime with name: %s to DB",anime.getEnAnimeName()));
        Transaction transaction = null;
        try (Session session = factoryAnime.openSession()) {

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
        try(Session session = factoryAnime.openSession()) {
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
        try(Session session = factoryAnime.openSession()) {
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
    public List<Anime> getAnimeByEnName(String name) {
        log.info(String.format("Start search anime with english name: %s", name));
        try(Session session = factoryAnime.openSession()) {
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

    @Override
    public Long countAnime() {
        log.info("Start count anime");
        try(Session session = factoryAnime.openSession()) {
            String hql = "select count(*) from Anime";
            Query producer = session.createQuery(hql);
            return (Long) producer.getResultList().get(0);
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return 0L;
        }
    }

    @Override
    public List<Anime> getAnimeByRangeId(
            Integer startValue,
            Integer finishValue) {
        log.info(String.format("Start search anime from DB with start id: %d and end id: %d", startValue, finishValue));
        try(Session session = factoryAnime.openSession()) {
            String hql = "from Anime where id between ?1 and ?2";
            Query producer = session.createQuery(hql);
            producer.setParameter(1, startValue);
            producer.setParameter(2, finishValue);
            return producer.getResultList();
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return null;
        }

    }
}
