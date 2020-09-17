package ru.db.touristAgency.tours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.db.touristAgency.abstracts.controllers.AbstractCrudController;
import ru.db.touristAgency.models.dtos.TourDto;
import ru.db.touristAgency.models.enums.TouristType;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.models.tours.Tour;
import ru.db.touristAgency.models.utils.Interval;
import ru.db.touristAgency.tours.service.ToursService;
import ru.db.touristAgency.utils.dtotransform.ToursDtoTransformService;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/tours")
public class ToursController extends AbstractCrudController<Tour, ToursService, TourDto> {
    private final ToursService toursService;

    public ToursController(@Autowired ToursService toursService,
                           @Autowired ToursDtoTransformService transformService) {
        super(toursService, transformService);

        this.toursService = toursService;
    }

    @GetMapping("/by-group")
    public Collection<Tour> getAllTouristsInGroup(@RequestParam int groupId,
                                                  @RequestParam(required = false)
                                                          String touristTypeName) {
        return toursService.getAllTouristsInGroup(groupId, touristTypeName);
    }

    @GetMapping("/settlements")
    public Map<Hotel, Collection<Tour>> getSettlementsList(
            @RequestParam int groupId,
            @RequestParam(required = false) String touristTypeName,
            @RequestBody Set<Integer> hotelsIds)
    {
            return toursService.getSettlementsList(groupId, touristTypeName, hotelsIds);
    }

    @GetMapping("/amount")
    public long getToursAmountByInterval(@RequestBody Interval interval,
                                         @RequestParam(required = false) String touristTypeName) {
        return toursService.getToursAmountByInterval(interval, touristTypeName);
    }

    @GetMapping("/tourist-types/stats")
    public Map<TouristType, Float> getTouristTypesStatistics(
            @RequestBody(required = false) Interval interval) {
        return toursService.getTouristTypesStatistics(interval);
    }
}
