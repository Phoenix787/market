package ru.xenya.market.ui.utils.converters;

import com.vaadin.flow.templatemodel.ModelEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToStringEncoder implements ModelEncoder<LocalDate, String> {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String encode(LocalDate value) {
        return value == null ? null : value.format(DATE_FORMATTER);
    }

    @Override
    public LocalDate decode(String value) {
        return LocalDate.parse(value, DATE_FORMATTER);
    }
}
