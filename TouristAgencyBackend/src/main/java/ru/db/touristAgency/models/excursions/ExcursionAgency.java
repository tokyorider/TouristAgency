package ru.db.touristAgency.models.excursions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.db.touristAgency.abstracts.models.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ExcursionAgencies")
public class ExcursionAgency implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "agency")
    private Set<Excursion> excursions = new HashSet<>();

    @JsonIgnore
    public int getPopularity() {
        return excursions
                .stream()
                .map(Excursion::getPopularity)
                .reduce(0, Integer::sum);
    }
}
