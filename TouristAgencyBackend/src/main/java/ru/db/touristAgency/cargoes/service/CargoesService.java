package ru.db.touristAgency.cargoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.services.AbstractCrudService;
import ru.db.touristAgency.models.tours.Cargo;
import ru.db.touristAgency.repositories.CargoesRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CargoesService extends AbstractCrudService<Cargo, CargoesRepository> {
    private final CargoesRepository cargoesRepository;

    public CargoesService(@Autowired CargoesRepository cargoesRepository) {
        super(cargoesRepository);

        this.cargoesRepository = cargoesRepository;
    }

    public Map<String, Float> getCargoTypesStatistics() {
        List<String> cargoTypes = cargoesRepository.findAll()
                .stream()
                .map(Cargo::getType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Map<String, Float> percentages = cargoTypes
                .stream()
                .collect(Collectors.toMap(Function.identity(), type ->
                        (float) (cargoTypes
                                .stream()
                                .filter(type::equals)
                                .count()) / cargoTypes.size() * 100));

        percentages.forEach((type, percentage) -> {
            percentages.put(type, Float.valueOf(String.format("%.1f", percentage)));
        });

        return percentages;
    }
}
