package ru.kirill98.animeDB.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.animeDB.entity.Log;
import ru.kirill98.animeDB.entity.dto.FormLog;
import ru.kirill98.animeDB.repository.MapperLog;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j
@Service
public class MapperLogImpl implements MapperLog {

    @Override
    public Log toLog(FormLog formLog) {
        log.info(String.format("Start mapping log from form log with id: %d", formLog.getId()));
        return new Log().toBuilder()
                .id(formLog.getId())
                .date(formLog.getDate())
                .logger(formLog.getLogger())
                .level(formLog.getLevel())
                .message(formLog.getMessage())
                .build();

    }

    @Override
    public FormLog toFormLog(Log gotLog) {
        log.info(String.format("Start mapping form log from log with id: %d", gotLog.getId()));
        return new FormLog().toBuilder()
                .id(gotLog.getId())
                .date(gotLog.getDate())
                .logger(gotLog.getLogger())
                .level(gotLog.getLevel())
                .message(gotLog.getMessage())
                .build();
    }

    @Override
    public List<Log> toListLog(List<FormLog> formLogs) {
        log.info("Start mapping list log from list forms log");
        List<Log> readyListLog = new ArrayList<>();
        for (FormLog formLog : formLogs) {
            readyListLog.add(toLog(formLog));
        }
        return readyListLog;
    }

    @Override
    public List<FormLog> toListFormLog(List<Log> listLog) {
        log.info("Start mapping list form log from lost log");
        List<FormLog> readyListFormLog = new ArrayList<>();
        for (Log log1 : listLog) {
            readyListFormLog.add(toFormLog(log1));
        }
        return readyListFormLog;
    }
}
