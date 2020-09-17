package ru.db.touristAgency.abstracts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.utils.filters.Filter;

@NoRepositoryBean
public interface FilteringJpaRepository<E extends AbstractEntity> extends JpaRepository<E, Integer>,
        JpaSpecificationExecutor<E> {
    Page<E> findAllByFilter(Filter<E> filter, Pageable pageable);
}
