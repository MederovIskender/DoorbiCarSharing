package kg.megacom.carsharing.mappers;

import kg.megacom.carsharing.dtos.car.CarResponse;
import kg.megacom.carsharing.models.entities.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public CarResponse mapToCarResponse(Car car) {
        CarResponse carResponse = new CarResponse();

        carResponse.setId(car.getId());
        carResponse.setManufacturer(car.getManufacturer());
        carResponse.setModel(car.getModel());
        carResponse.setVolume(car.getVolume());
        carResponse.setColor(car.getColor());
        carResponse.setCarStatus(car.getCarStatus());
        carResponse.setCarType(car.getCarType());
        carResponse.setPricePerDay(car.getPricePerDay());
        carResponse.setFuelPerKm(car.getFuelPerKm());
        carResponse.setFuelType(car.getFuelType());
        return carResponse;
    }

    public List<CarResponse> mapToCarResponseList(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    public Car mapToCar(CarResponse carResponse) {
        Car car = new Car();
        car.setId(carResponse.getId());
        car.setManufacturer(carResponse.getManufacturer());
        car.setModel(carResponse.getModel());
        car.setVolume(carResponse.getVolume());
        car.setColor(carResponse.getColor());
        car.setCarStatus(carResponse.getCarStatus());
        car.setCarType(carResponse.getCarType());
        car.setPricePerDay(carResponse.getPricePerDay());
        car.setFuelPerKm(carResponse.getFuelPerKm());
        car.setFuelType(carResponse.getFuelType());
        return car;
    }
}