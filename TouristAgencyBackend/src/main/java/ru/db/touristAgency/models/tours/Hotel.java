package ru.db.touristAgency.models.tours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Hotels")
public class Hotel implements AbstractEntity, AbstractDto<Hotel> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "TouristsHotels",
            joinColumns = @JoinColumn(name = "hotelId"),
            inverseJoinColumns = @JoinColumn(name = "tourId")
    )
    private Set<Tour> tours = new HashSet<>();

    @Override
    public String toString() {
        return title;
    }
}
