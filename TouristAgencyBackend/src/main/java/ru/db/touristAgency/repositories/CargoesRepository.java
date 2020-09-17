package ru.db.touristAgency.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.models.tours.Cargo;
import ru.db.touristAgency.utils.filters.Filter;

@Repository
public interface CargoesRepository extends FilteringJpaRepository<Cargo> {
    @Query(
            "SELECT c " +
            "FROM Cargo c " +
            "WHERE (:#{#filter.name} is null or lower(c.weight) like :#{#filter.name})"
    )
    @Override
    Page<Cargo> findAllByFilter(@Param("filter") Filter<Cargo> filter, Pageable pageable);
}
