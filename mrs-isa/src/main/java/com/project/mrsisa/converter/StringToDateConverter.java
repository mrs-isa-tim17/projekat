package com.project.mrsisa.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDateConverter {

    public LocalDate convert(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        return LocalDate.parse(date, formatter);
    }
}
