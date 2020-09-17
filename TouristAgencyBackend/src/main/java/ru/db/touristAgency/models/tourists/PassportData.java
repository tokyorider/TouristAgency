package ru.db.touristAgency.models.tourists;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Embeddable
@Table(name = "PassportDatas")
@AttributeOverrides({
        @AttributeOverride(name = "series", column = @Column(name = "passportSeries"))
})
class PassportData {
    @Id
    @JsonIgnore
    private int id;

    private String series;

    private String passportId;
}
