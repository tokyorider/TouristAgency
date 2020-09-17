package ru.db.touristAgency.abstracts.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;

public interface CrudController<E extends AbstractEntity, D extends AbstractDto<E>> {
    D get(int id);

    Page<D> getAll(Pageable pageable);

    void add(D e);

    void update(D e);

    void delete(int id);
}
