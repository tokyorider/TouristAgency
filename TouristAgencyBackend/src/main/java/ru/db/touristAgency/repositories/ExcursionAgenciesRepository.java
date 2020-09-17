package ru.db.touristAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.db.touristAgency.models.excursions.ExcursionAgency;

@Repository
public interface ExcursionAgenciesRepository extends JpaRepository<ExcursionAgency, Integer> {
}
