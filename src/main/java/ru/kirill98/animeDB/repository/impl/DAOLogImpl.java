package ru.kirill98.animeDB.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Log;
import ru.kirill98.animeDB.repository.DAOLog;

import java.util.List;

@RequiredArgsConstructor
@Log4j
@Service
public class DAOLogImpl implements DAOLog {
    private final SessionFactory factoryLog = new Configuration().configure().addAnnotatedClass(Log.class).buildSessionFactory();

    @Override
    public List<Log> getAllLogs() {
        try(Session session = factoryLog.openSession()) {
            String hql = "from Log";
            return session.createQuery(hql).getResultList();
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public Log getLogById(Integer id) {
        log.info(String.format("Start search log from DB with id: %d", id));
        Transaction transaction = null;
        try(Session session = factoryLog.openSession()) {
            transaction = session.beginTransaction();
            Log logResult = session.get(Log.class, id);
            transaction.commit();
            log.info(String.format("Was find anime by id: %d", id));
            return logResult;
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
