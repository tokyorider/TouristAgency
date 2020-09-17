package ru.db.touristAgency.models.excursions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.models.tours.Tour;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Excursions")
public class Excursion implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToOne
    private ExcursionAgency agency;

    @Basic
    private LocalDate arrangementDate;

    @Type(type = "org.hibernate.type.BigDecimalType")
    private BigDecimal price;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "ExcursionOrders",
            joinColumns = @JoinColumn(name = "excursionId"),
            inverseJoinColumns = @JoinColumn(name = "tourId")
    )
    private Set<Tour> tours = new HashSet<>();

    @JsonIgnore
    public int getPopularity() {
        return tours.size();
    }
}