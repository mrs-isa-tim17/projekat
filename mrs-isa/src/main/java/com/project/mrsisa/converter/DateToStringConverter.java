package com.project.mrsisa.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateToStringConverter {
    public String convert(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return date.format(formatter);
    }
}
