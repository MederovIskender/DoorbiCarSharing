package kg.megacom.carsharing.services;

import kg.megacom.carsharing.dtos.car.CarCreateDto;
import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import kg.megacom.carsharing.models.entities.Car;

import java.util.List;

public interface CarService {
    Car save(CarCreateDto carCreateDto);
    List<Car> getByStatusOrType(CarStatus carStatus, CarType carType);
    Car getById(Long carId);
}
