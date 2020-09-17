package ru.db.touristAgency.models.tourists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;
import ru.db.touristAgency.models.enums.Sex;
import ru.db.touristAgency.models.tours.Tour;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Tourists")
public class Tourist implements AbstractEntity, AbstractDto<Tourist> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String passportId;

    private String passportSeries;

    @JsonIgnore
    @OneToMany(mappedBy = "tourist")
    private Set<Tour> tours = new HashSet<>();
}
