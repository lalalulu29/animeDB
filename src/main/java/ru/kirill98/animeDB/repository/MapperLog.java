package ru.kirill98.animeDB.repository;

import ru.kirill98.animeDB.entity.Log;
import ru.kirill98.animeDB.entity.dto.FormLog;

import java.util.List;

public interface MapperLog {

    Log toLog(FormLog formLog);
    FormLog toFormLog(Log gotLog);
    List<Log> toListLog(List<FormLog> formLogs);
    List<FormLog> toListFormLog(List<Log> listLog);
}

