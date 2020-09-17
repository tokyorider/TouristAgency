package ru.db.touristAgency.hotels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.db.touristAgency.abstracts.controllers.AbstractCrudController;
import ru.db.touristAgency.hotels.service.HotelsService;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.utils.dtotransform.DefaultDtoTransformService;

@RestController
@RequestMapping("/hotels")
public class HotelsController extends AbstractCrudController<Hotel, HotelsService, Hotel> {
    public HotelsController(@Autowired HotelsService hotelsService,
                            @Autowired DefaultDtoTransformService<Hotel> transformService) {
        super(hotelsService, transformService);
    }
}
