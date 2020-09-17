package ru.db.touristAgency.utils.dtotransform;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.abstracts.models.AbstractMapper;
import ru.db.touristAgency.cargoes.service.CargoesService;
import ru.db.touristAgency.models.dtos.TourDto;
import ru.db.touristAgency.models.dtos.TouristInfo;
import ru.db.touristAgency.models.tourists.Tourist;
import ru.db.touristAgency.models.tours.Cargo;
import ru.db.touristAgency.models.tours.Tour;
import ru.db.touristAgency.models.tours.TouristsGroup;
import ru.db.touristAgency.tourists.service.TouristsService;
import ru.db.touristAgency.tours.service.ToursService;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ToursDtoTransformService implements AbstractMapper<Tour, TourDto> {
    private final ModelMapper modelMapper;

    private final CargoesService cargoesService;

    private final TouristsService touristsService;

    private final ToursService toursService;

    public TouristInfo getInfoFromTourist(Tourist tourist) {
        return modelMapper.map(tourist, TouristInfo.class);
    }

    @Override
    public Tour toEntity(TourDto dto) {
        return modelMapper.map(dto, Tour.class);
    }

    public TourDto toDto(Tour tour) {
        return modelMapper.map(tour, TourDto.class);
    }

    @PostConstruct
    public void configureMapper() {
        modelMapper.createTypeMap(Tourist.class, TouristInfo.class);

        modelMapper.createTypeMap(Tour.class, TourDto.class)
                .addMappings(mapper -> {
                    mapper.using(entityToIdConverter())
                            .map(Tour::getCargo, TourDto::setCargoId);
                    mapper.using(entityToIdConverter())
                            .map(Tour::getGroup, TourDto::setGroupId);
                    mapper.using(entityToIdConverter())
                            .map(Tour::getTourist, TourDto::setTouristId);
                });

        modelMapper.createTypeMap(TourDto.class, Tour.class)
                .addMappings(mapper -> {
                    mapper.using(idToCargoConverter())
                            .map(TourDto::getCargoId, Tour::setCargo);
                    mapper.using(idToTouristConverter())
                            .map(TourDto::getTouristId, Tour::setTourist);
                    mapper.using(idToGroupConverter())
                            .map(TourDto::getGroupId, Tour::setGroup);
                });
    }

    private <E extends AbstractEntity> Converter<E, Integer> entityToIdConverter() {
        return context -> context.getSource().getId();
    }

    private Converter<Integer, Cargo> idToCargoConverter() {
        return context -> cargoesService.get(context.getSource());
    }

    private Converter<Integer, Tourist> idToTouristConverter() {
        return context -> touristsService.get(context.getSource());
    }

    private Converter<Integer, TouristsGroup> idToGroupConverter() {
        return context -> toursService.getTouristsGroup(context.getSource());
    }

    //private Converter<Tour, TourDto> toDtoConverter() {
//        return context -> {
//            Set<ExcursionDto> excursionsDtos = excursionsService.getAll(Pageable.unpaged())
//                    .stream()
//                    .filter(excursion -> excursion.getTours().contains(context.getSource()))
//                    .map(this::toDto)
//                    .collect(Collectors.toSet());
//
//            context.getDestination().setExcursions(excursionsDtos);
//
//            return context.getDestination();
//        };
   // }
}
