package ru.db.touristAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.models.tours.TouristsGroup;

@Repository
public interface TouristsGroupsRepository extends JpaRepository<TouristsGroup, Integer> {
}
