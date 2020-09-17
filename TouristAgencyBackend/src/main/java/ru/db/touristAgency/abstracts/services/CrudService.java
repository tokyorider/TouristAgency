package ru.db.touristAgency.abstracts.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.utils.FindRequestParams;
import ru.db.touristAgency.utils.filters.Filter;

public interface CrudService<E extends AbstractEntity> {
    E get(int id);

    Page<E> getAll(Pageable pageable);

    Page<E> getAllByFilter(Filter<E> filter, FindRequestParams requestParams);

    void add(E e);

    void update(E e);

    void delete(int id);
}
