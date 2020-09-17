package ru.db.touristAgency.models.tours;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.db.touristAgency.abstracts.models.AbstractDto;
import ru.db.touristAgency.abstracts.models.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Cargoes")
public class Cargo implements AbstractEntity, AbstractDto<Cargo> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private int placesAmount;

    private float weight;

    private String type;

    @Type(type = "org.hibernate.type.BigDecimalType")
    private BigDecimal packagePrice;

    @Type(type = "org.hibernate.type.BigDecimalType")
    private BigDecimal insurancePrice;

    public BigDecimal getTotalPrice() {
        return packagePrice.add(insurancePrice);
    }
}
