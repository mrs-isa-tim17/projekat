package com.project.mrsisa.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToString {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    public String format(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }
}
