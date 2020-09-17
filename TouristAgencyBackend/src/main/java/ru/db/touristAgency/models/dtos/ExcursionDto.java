package ru.db.touristAgency.models.dtos;

import lombok.Data;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.models.excursions.Excursion;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExcursionDto implements AbstractDto<Excursion> {
    private int id;

    private String title;

    private String description;

    private int agencyId;

    private LocalDate arrangementDate;

    private BigDecimal price;
}
