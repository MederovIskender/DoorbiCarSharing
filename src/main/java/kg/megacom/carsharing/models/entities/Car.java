package kg.megacom.carsharing.models.entities;

import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import kg.megacom.carsharing.enums.FuelType;
import kg.megacom.carsharing.models.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car extends BaseEntity {

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "volume", nullable = false)
    private Double volume;

    @Column(name = "color", nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(name = "carStatus", nullable = false)
    private CarStatus carStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "carType", nullable = false)
    private CarType carType;

    @Column(name = "pricePerDay", nullable = false)
    private Double pricePerDay;

    @Column(name = "fuelPerKm", nullable = false)
    private Double fuelPerKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuelType", nullable = false)
    private FuelType fuelType;

}
