package ru.db.touristAgency.abstracts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.abstracts.repositories.FilteringJpaRepository;
import ru.db.touristAgency.utils.FindRequestParams;
import ru.db.touristAgency.utils.filters.Filter;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class AbstractCrudService<E extends AbstractEntity, R extends FilteringJpaRepository<E>>
        implements CrudService<E> {
    private final R r;

    @Override
    public E get(int id) {
        return r.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id"));
    }

    @Override
    public Page<E> getAll(Pageable pageable) {
        return r.findAll(pageable);
    }

    @Override
    public Page<E> getAllByFilter(Filter<E> filter, FindRequestParams requestParams) {
        Pageable pageable = getPageable(requestParams);

        return r.findAllByFilter(filter, pageable);
    }

    private Pageable getPageable(FindRequestParams requestParams) {
        Sort sort = getSort(requestParams);
        Pageable pageable = getPageable(requestParams, sort);
        int intPageSize = 20;

        return pageable.isPaged() ? pageable : PageRequest.of(0, intPageSize, sort);
    }

    private Sort getSort(FindRequestParams requestParams) {
        if (Objects.nonNull(requestParams) && Objects.nonNull(requestParams.getOrderBy())) {
            if (requestParams.getOrder() == FindRequestParams.Order.ASCENDING) {
                return Sort.by(Sort.Direction.ASC).ascending();
            } else {
                return Sort.by(Sort.Direction.DESC).descending();
            }
        } else {
            return Sort.unsorted();
        }
    }

    private Pageable getPageable(FindRequestParams requestParams, Sort sort) {
        if (Objects.nonNull(requestParams) && Objects.nonNull(requestParams.getPage()) && Objects.nonNull(requestParams.getPageSize())) {
            return PageRequest.of(requestParams.getPage(), requestParams.getPageSize(), sort);
        } else {
            return Pageable.unpaged();
        }
    }

    @Override
    @Transactional
    public void add(E e) {
        r.save(e);
    }

    @Override
    @Transactional
    public void update(E e) {
        if (Objects.isNull(e.getId())) {
            throw new IllegalArgumentException("Id необходим");
        } else if (!r.existsById(e.getId())) {
            throw new IllegalArgumentException("Неверный id");
        }

        r.save(e);
    }

    @Override
    @Transactional
    public void delete(int id) {
        if (!r.existsById(id)) {
            throw new IllegalArgumentException("Неверный id");
        }

        r.deleteById(id);
    }
}
