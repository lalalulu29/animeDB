package ru.kirill98.animeDB.repository;

import ru.kirill98.animeDB.entity.Log;

import java.util.List;

public interface DAOLog {
    List<Log> getAllLogs();
    Log getLogById(Integer id);
}
