package ru.db.touristAgency.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TouristInfo {
    private Set<TourDto> tours;

    public int getToursAmount() {
        return tours.size();
    }
}
