package ru.db.touristAgency.excursions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.services.AbstractCrudService;
import ru.db.touristAgency.models.excursions.Excursion;
import ru.db.touristAgency.models.excursions.ExcursionAgency;
import ru.db.touristAgency.models.utils.Interval;
import ru.db.touristAgency.repositories.ExcursionAgenciesRepository;
import ru.db.touristAgency.repositories.ExcursionsRepository;
import ru.db.touristAgency.utils.CollectionsUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;

@Service
public class ExcursionsService extends AbstractCrudService<Excursion, ExcursionsRepository> {
    private final ExcursionsRepository excursionsRepository;

    private final ExcursionAgenciesRepository agenciesRepository;

    public ExcursionsService(@Autowired ExcursionsRepository excursionsRepository,
                             @Autowired ExcursionAgenciesRepository agenciesRepository) {
        super(excursionsRepository);

        this.excursionsRepository = excursionsRepository;
        this.agenciesRepository = agenciesRepository;
    }

    public Page<Excursion> getAllActualExcursions(Pageable pageable) {
        return excursionsRepository.findAllByArrangementDateAfter(LocalDate.now(), pageable);
    }

    public long getExcursionsOrdersAmountByInterval(Interval interval) {
        return excursionsRepository
                .findAll()
                .stream()
                .filter(excursion -> interval.contains(excursion.getArrangementDate()))
                .map(Excursion::getPopularity)
                .reduce(0, Integer::sum);

    }

    public Collection<Excursion> getTopPopularExcursions(int amount) {
        return CollectionsUtils.getTopElements(
                excursionsRepository.findAll(),
                Comparator.comparing(Excursion::getPopularity),
                amount);
    }

    public Collection<ExcursionAgency> getTopPopularAgencies(int amount) {
        return CollectionsUtils.getTopElements(
                agenciesRepository.findAll(),
                Comparator.comparing(ExcursionAgency::getPopularity),
                amount);
    }
}
