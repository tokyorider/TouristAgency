package ru.db.touristAgency.abstracts.models;

public interface AbstractMapper<E extends AbstractEntity, D extends AbstractDto<E>> {
    E toEntity(D d);

    D toDto(E e);
}
