package ru.db.touristAgency.tourists.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.services.AbstractCrudService;
import ru.db.touristAgency.models.tourists.Tourist;
import ru.db.touristAgency.repositories.TouristsRepository;

@Service
public class TouristsService extends AbstractCrudService<Tourist, TouristsRepository> {
//    private final ToursDtoTransformService toursDtoTransformService;

    public TouristsService(//@Autowired ToursDtoTransformService toursDtoTransformService,
                           @Autowired TouristsRepository touristsRepository) {
        super(touristsRepository);

//        this.toursDtoTransformService = toursDtoTransformService;
    }

//    public TouristInfo getTouristInfo(int id) {
//        Tourist tourist = get(id);
//
//        return toursDtoTransformService.getInfoFromTourist(tourist);
//    }
}
