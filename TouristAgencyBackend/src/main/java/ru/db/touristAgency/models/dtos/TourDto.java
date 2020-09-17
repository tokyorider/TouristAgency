package ru.db.touristAgency.models.dtos;

import lombok.Data;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.models.tours.Tour;

import java.time.LocalDate;

@Data
public class TourDto implements AbstractDto<Tour> {
    private int id;

    private LocalDate begin;

    private LocalDate end;

    private int groupId;

    private int cargoId;

    private int touristId;
}
