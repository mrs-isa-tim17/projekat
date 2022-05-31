package com.project.mrsisa.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    public LocalDateTime format(String dateTime){
        return LocalDateTime.parse(dateTime,formatter);
    }
}
