package ru.db.touristAgency.utils.dtotransform;

import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.abstracts.models.AbstractMapper;

@Service
public class DefaultDtoTransformService<E extends AbstractEntity & AbstractDto<E>> implements AbstractMapper<E, E> {
    @Override
    public E toEntity(E e) {
        return e;
    }

    @Override
    public E toDto(E e) {
        return e;
    }
}
