package ru.db.touristAgency.tourists.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.db.touristAgency.abstracts.controllers.AbstractCrudController;
import ru.db.touristAgency.models.tourists.Tourist;
import ru.db.touristAgency.tourists.service.TouristsService;
import ru.db.touristAgency.utils.dtotransform.DefaultDtoTransformService;

@RestController
@RequestMapping("/tourists")
public class TouristsController extends AbstractCrudController<Tourist, TouristsService, Tourist> {
    private final TouristsService touristsService;

    public TouristsController(@Autowired TouristsService touristsService,
                              @Autowired DefaultDtoTransformService<Tourist> transformService) {
        super(touristsService, transformService);

        this.touristsService = touristsService;
    }

    //@GetMapping("/{id}/info")
    //public TouristInfo getTouristInfo(@PathVariable int id) {
        //return touristsService.getTouristInfo(id);
    //}
}
