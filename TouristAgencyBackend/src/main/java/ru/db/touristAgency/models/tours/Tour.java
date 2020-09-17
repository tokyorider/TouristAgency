package ru.db.touristAgency.models.tours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.models.enums.TouristType;
import ru.db.touristAgency.models.excursions.Excursion;
import ru.db.touristAgency.models.tourists.Tourist;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Tours")
public class Tour implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TouristType type;

    @ManyToOne
    private TouristsGroup group;

    @ManyToOne
    private Tourist tourist;

    @ManyToOne
    private Hotel wishHotel;

    @Basic
    private LocalDate begin;

    @Basic
    private LocalDate end;

    @OneToOne
    private Cargo cargo;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ExcursionOrders",
            joinColumns = @JoinColumn(name = "tourId"),
            inverseJoinColumns = @JoinColumn(name = "excursionId")
    )
    private Set<Excursion> excursions = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "TouristsHotels",
            joinColumns = @JoinColumn(name = "tourId"),
            inverseJoinColumns = @JoinColumn(name = "hotelId")
    )
    private Set<Hotel> hotels = new HashSet<>();
}