package ru.db.touristAgency.models.tours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TouristsGroups")
public class TouristsGroup implements AbstractEntity, AbstractDto<TouristsGroup> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private Set<Tour> tours = new HashSet<>();
}
