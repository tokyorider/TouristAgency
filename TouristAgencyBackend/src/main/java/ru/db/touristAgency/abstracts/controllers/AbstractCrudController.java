package ru.db.touristAgency.abstracts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.abstracts.models.AbstractMapper;
import ru.db.touristAgency.abstracts.services.CrudService;
import ru.db.touristAgency.utils.FindRequestParams;
import ru.db.touristAgency.utils.filters.Filter;

@RequiredArgsConstructor
public abstract class AbstractCrudController<E extends AbstractEntity, S extends CrudService<E>, D extends AbstractDto<E>>
        implements CrudController<E, D> {
    private final S s;

    private final AbstractMapper<E, D> mapper;

    @Override
    @GetMapping("/{id}")
    public D get(@PathVariable int id) {
        return mapper.toDto(s.get(id));
    }

    @Override
    @GetMapping
    public Page<D> getAll(Pageable pageable) {
        return s.getAll(pageable).map(mapper::toDto);
    }

    protected <F extends Filter<E>> Page<D> getAllByFilter(F f, FindRequestParams requestParams) {
        return s.getAllByFilter(f, requestParams).map(mapper::toDto);
    }

    @Override
    @PostMapping
    public void add(@RequestBody D e) {
        s.add(mapper.toEntity(e));
    }

    @Override
    @PutMapping
    public void update(@RequestBody D e) {
        s.update(mapper.toEntity(e));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        s.delete(id);
    }
}
