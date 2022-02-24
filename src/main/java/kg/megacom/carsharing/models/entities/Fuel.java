package kg.megacom.carsharing.models.entities;

import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.models.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fuels")
public class Fuel extends BaseEntity {
    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "fulel_type",unique = true, nullable = false)
    private FuelType fuelType;

    @Column(name = "active", nullable = false)
    private boolean active;
}

