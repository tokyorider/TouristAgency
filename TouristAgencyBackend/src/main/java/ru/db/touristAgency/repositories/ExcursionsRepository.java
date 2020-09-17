package ru.db.touristAgency.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.models.excursions.Excursion;
import ru.db.touristAgency.utils.filters.Filter;

import java.time.LocalDate;

@Repository
public interface ExcursionsRepository extends FilteringJpaRepository<Excursion> {
    @Query(
            "SELECT e " +
            "FROM Excursion e " +
            "WHERE (:#{#filter.arrangementDate} is null or lower(e.arrangementDate) like :#{#filter.arrangementDate})"
    )
    @Override
    Page<Excursion> findAllByFilter(@Param("filter") Filter<Excursion> filter, Pageable pageable);

    Page<Excursion> findAllByArrangementDateAfter(LocalDate date, Pageable pageable);
}