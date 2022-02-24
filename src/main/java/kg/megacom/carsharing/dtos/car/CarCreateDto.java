package kg.megacom.carsharing.dtos.car;

import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import kg.megacom.carsharing.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateDto {

    private String manufacturer;
    private String model;
    private Double volume;
    private String color;
    private CarStatus carStatus;
    private CarType carType;
    private Double pricePerDay;
    private Double fuelPerKm;
    private FuelType fuelType;
}