package ru.db.touristAgency.excursions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.db.touristAgency.abstracts.controllers.AbstractCrudController;
import ru.db.touristAgency.excursions.service.ExcursionsService;
import ru.db.touristAgency.models.dtos.ExcursionDto;
import ru.db.touristAgency.models.excursions.Excursion;
import ru.db.touristAgency.models.excursions.ExcursionAgency;
import ru.db.touristAgency.models.utils.Interval;
import ru.db.touristAgency.utils.dtotransform.ExcursionsDtoTransformService;

import java.util.Collection;

@RestController
@RequestMapping("/excursions")
public class ExcursionsController extends AbstractCrudController<Excursion, ExcursionsService, ExcursionDto> {
    private final ExcursionsService excursionsService;

    public ExcursionsController(@Autowired ExcursionsService excursionsService,
                                @Autowired ExcursionsDtoTransformService transformService) {
        super(excursionsService, transformService);

        this.excursionsService = excursionsService;
    }

    @GetMapping("/actual")
    public Page<Excursion> getAllActualExcursions(Pageable pageable) {
        return excursionsService.getAllActualExcursions(pageable);
    }

    @GetMapping("/orders/amount")
    public long getExcursionsOrdersAmountByInterval(@RequestBody Interval interval) {
        return excursionsService.getExcursionsOrdersAmountByInterval(interval);
    }

    @GetMapping("/most-popular")
    public Collection<Excursion> getTopPopularExcursions(@RequestParam int amount) {
        return excursionsService.getTopPopularExcursions(amount);
    }

    @GetMapping("/agencies/most-popular")
    public Collection<ExcursionAgency> getTopPopularAgencies(@RequestParam int amount) {
        return excursionsService.getTopPopularAgencies(amount);
    }
}
