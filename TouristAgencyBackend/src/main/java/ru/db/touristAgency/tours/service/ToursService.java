package ru.db.touristAgency.tours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.db.touristAgency.abstracts.services.AbstractCrudService;
import ru.db.touristAgency.hotels.service.HotelsService;
import ru.db.touristAgency.models.enums.TouristType;
import ru.db.touristAgency.models.tours.Hotel;
import ru.db.touristAgency.models.tours.Tour;
import ru.db.touristAgency.models.tours.TouristsGroup;
import ru.db.touristAgency.models.utils.Interval;
import ru.db.touristAgency.repositories.TouristsGroupsRepository;
import ru.db.touristAgency.repositories.ToursRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ToursService extends AbstractCrudService<Tour, ToursRepository> {
    private final HotelsService hotelsService;

    private final ToursRepository toursRepository;

    private final TouristsGroupsRepository groupsRepository;

    public ToursService(@Autowired HotelsService hotelsService, @Autowired ToursRepository toursRepository,
                        @Autowired TouristsGroupsRepository groupsRepository) {
        super(toursRepository);

        this.hotelsService = hotelsService;
        this.toursRepository = toursRepository;
        this.groupsRepository = groupsRepository;
    }

    public TouristsGroup getTouristsGroup(int id) {
        return groupsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет такой группы"));
    }

    private TouristType getTouristType(String typeName) {
        try {
            return TouristType.valueOf(typeName.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Нет ткой категории туристов");
        }
    }

    public Collection<Tour> getAllTouristsInGroup(int groupId, String typeName) {
        TouristsGroup group = getTouristsGroup(groupId);

        if (typeName != null) {
            TouristType type = getTouristType(typeName);

            return toursRepository.findAllByGroupAndType(group, type);
        } else {
            return toursRepository.findAllByGroup(group);
        }
    }

    public Map<Hotel, Collection<Tour>> getSettlementsList(
            int groupId, String typeName, Set<Integer> hotelsIds) {
        TouristsGroup group = getTouristsGroup(groupId);
        Set<Hotel> hotels = hotelsIds
                .stream()
                .map(hotelsService::get)
                .collect(Collectors.toSet());

        if (typeName != null) {
            TouristType type = getTouristType(typeName);

            return hotels
                    .stream()
                    .collect(Collectors.toMap(Function.identity(),
                            hotel -> toursRepository.findAllByGroupAndWishHotelAndType(
                                    group, hotel, type)));
        } else {
            return hotels
                    .stream()
                    .collect(Collectors.toMap(Function.identity(),
                            hotel -> toursRepository.findAllByGroupAndWishHotel(group, hotel)));
        }
    }

    public Collection<Tour> getToursByInterval(Interval interval) {
        if (interval == null) {
            interval = new Interval(LocalDate.MIN, LocalDate.now());
        }
        final Interval finalInterval = interval;

        return toursRepository
                .findAll()
                .stream()
                .filter(tour ->
                        finalInterval.overlaps(new Interval(tour.getBegin(), tour.getEnd())))
                .collect(Collectors.toSet());
    }

    public long getToursAmountByInterval(Interval interval, String typeName) {
        if (typeName != null) {
            TouristType type = getTouristType(typeName);

            return getToursByInterval(interval)
                    .stream()
                    .filter(tour -> tour.getType().equals(type))
                    .count();
        } else {
            return getToursByInterval(interval).size();
        }
    }

    private float getTouristTypesStatistics(TouristType type, Collection<Tour> tours) {
        float percentage = (float) tours
                .stream()
                .filter(tour -> tour.getType().equals(type))
                .count() / tours.size() * 100;

        return Float.valueOf(String.format("%.1f", percentage));
    }

    public Map<TouristType, Float> getTouristTypesStatistics(Interval interval) {
        Collection<Tour> tours = getToursByInterval(interval);

        return Arrays.stream(TouristType.values())
                .collect(Collectors.toMap(Function.identity(), type ->
                        getTouristTypesStatistics(type, tours)));
    }
}
