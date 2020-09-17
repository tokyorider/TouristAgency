package ru.db.touristAgency.models.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Interval {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate begin;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate end;

    public Interval(LocalDate begin, LocalDate end) {
        if (end.isBefore(begin)) {
            throw new IllegalArgumentException("Некорректный временной промежуток");
        }

        this.begin = begin;
        this.end = end;
    }

    public boolean overlaps(Interval interval) {
        return (end.isEqual(interval.getBegin()) || end.isAfter(interval.getBegin()) &&
                (begin.isBefore(interval.getEnd()) || begin.isEqual(interval.getEnd())));
    }

    public boolean contains(LocalDate date) {
        return (end.isEqual(date) || end.isAfter(date) &&
                (begin.isBefore(date) || begin.isEqual(date)));
    }
}
