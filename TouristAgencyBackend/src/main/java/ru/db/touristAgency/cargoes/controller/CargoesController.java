package ru.db.touristAgency.cargoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.db.touristAgency.abstracts.controllers.AbstractCrudController;
import ru.db.touristAgency.cargoes.service.CargoesService;
import ru.db.touristAgency.models.tours.Cargo;
import ru.db.touristAgency.utils.dtotransform.DefaultDtoTransformService;

import java.util.Map;

@RestController
@RequestMapping("/cargoes")
public class CargoesController extends AbstractCrudController<Cargo, CargoesService, Cargo> {
    private final CargoesService cargoesService;

    CargoesController(@Autowired CargoesService cargoesService,
                      @Autowired DefaultDtoTransformService<Cargo> transformService) {
        super(cargoesService, transformService);

        this.cargoesService = cargoesService;
    }

    @GetMapping("/stats")
    public Map<String, Float> getTypesStatistics() {
        return cargoesService.getCargoTypesStatistics();
    }
}
