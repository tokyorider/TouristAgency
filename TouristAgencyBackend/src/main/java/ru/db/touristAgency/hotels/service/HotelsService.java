package ru.db.touristAgency.hotels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.services.AbstractCrudService;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.repositories.HotelsRepository;

@Service
public class HotelsService extends AbstractCrudService<Hotel, HotelsRepository> {
    public HotelsService(@Autowired HotelsRepository hotelsRepository) {
        super(hotelsRepository);
    }
}
