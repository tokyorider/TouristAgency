package ru.db.touristAgency.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.utils.filters.Filter;

@Repository
public interface HotelsRepository extends FilteringJpaRepository<Hotel> {
    @Query(
            "SELECT h " +
            "FROM Hotel h " +
            "WHERE (:#{#filter.name} is null or lower(h.title) like :#{#filter.name})"
    )
    @Override
    Page<Hotel> findAllByFilter(@Param("filter") Filter<Hotel> filter, Pageable pageable);
}
