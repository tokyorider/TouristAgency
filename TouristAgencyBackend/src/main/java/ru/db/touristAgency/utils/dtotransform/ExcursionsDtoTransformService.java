package ru.db.touristAgency.utils.dtotransform;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.abstracts.models.AbstractMapper;
import ru.db.touristAgency.excursions.service.ExcursionsService;
import ru.db.touristAgency.models.dtos.ExcursionDto;
import ru.db.touristAgency.models.excursions.Excursion;
import ru.db.touristAgency.models.excursions.ExcursionAgency;
import ru.db.touristAgency.repositories.ExcursionAgenciesRepository;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ExcursionsDtoTransformService implements AbstractMapper<Excursion, ExcursionDto> {
    private final ModelMapper modelMapper;

    private final ExcursionsService excursionsService;

    private final ExcursionAgenciesRepository agenciesRepository;

    @Override
    public Excursion toEntity(ExcursionDto dto) {
        return modelMapper.map(dto, Excursion.class);
    }

    @Override
    public ExcursionDto toDto(Excursion excursion) {
        return modelMapper.map(excursion, ExcursionDto.class);
    }

    @PostConstruct
    public void configureMapper() {
        modelMapper.createTypeMap(Excursion.class, ExcursionDto.class)
                .addMappings(mapper -> mapper.using(entityToIdConverter())
                        .map(Excursion::getAgency, ExcursionDto::setAgencyId));
        modelMapper.createTypeMap(ExcursionDto.class, Excursion.class)
                .addMappings(mapper -> mapper.using(idToAgencyConverter())
                        .map(ExcursionDto::getAgencyId, Excursion::setAgency));
    }

    private <E extends AbstractEntity> Converter<E, Integer> entityToIdConverter() {
        return context -> context.getSource().getId();
    }

    private Converter<Integer, ExcursionAgency> idToAgencyConverter() {
        return context -> agenciesRepository.findById(context.getSource()).orElseThrow(() -> new IllegalArgumentException("Неверный id"));
    }
}
