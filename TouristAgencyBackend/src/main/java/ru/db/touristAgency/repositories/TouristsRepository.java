package ru.db.touristAgency.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.models.tourists.Tourist;
import ru.db.touristAgency.utils.filters.Filter;

@Repository
public interface TouristsRepository extends FilteringJpaRepository<Tourist> {
    @Query(
            "SELECT t " +
            "FROM Tourist t " +
            "WHERE (:#{#filter.name} is null or lower(t.surname) like :#{#filter.name})"
    )
    @Override
    Page<Tourist> findAllByFilter(@Param("filter") Filter<Tourist> filter, Pageable pageable);
}
