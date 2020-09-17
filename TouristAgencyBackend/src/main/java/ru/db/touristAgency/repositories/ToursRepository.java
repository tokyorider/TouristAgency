package ru.db.touristAgency.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.models.enums.TouristType;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.models.tours.Tour;
import ru.db.touristAgency.models.tours.TouristsGroup;
import ru.db.touristAgency.utils.filters.Filter;

import java.util.Collection;

@Repository
public interface ToursRepository extends FilteringJpaRepository<Tour> {
    Collection<Tour> findAllByGroup(TouristsGroup group);

    Collection<Tour> findAllByGroupAndType(TouristsGroup group, TouristType type);

    Collection<Tour> findAllByGroupAndWishHotel(TouristsGroup group, Hotel hotel);

    Collection<Tour> findAllByGroupAndWishHotelAndType(
            TouristsGroup group, Hotel hotel, TouristType type);

    @Query(
            "SELECT t " +
            "FROM Tour t " +
            "WHERE (:#{#filter.name} is null or lower(t.begin) like :#{#filter.name})"
    )
    @Override
    Page<Tour> findAllByFilter(@Param("filter") Filter<Tour> filter, Pageable pageable);
}
