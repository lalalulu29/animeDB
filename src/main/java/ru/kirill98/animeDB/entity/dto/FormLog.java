package ru.kirill98.animeDB.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormLog {
    private Integer id;
    private Date date;
    private String logger;
    private String level;
    private String message;

    @Override
    public String toString() {

        return "{" +
                "\"id\": \"" + getId() + "\"," +
                "\"date\": \"" + getDate() + "\"," +
                "\"logger\": \"" + getLogger() + "\"," +
                "\"level\": \"" + getLevel() + "\"," +
                "\"message\": \"" + getMessage() +
                "}";
    }
}
