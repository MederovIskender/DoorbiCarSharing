package kg.megacom.carsharing.repositories;

import kg.megacom.carsharing.enums.CarStatus;
import kg.megacom.carsharing.enums.CarType;
import kg.megacom.carsharing.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByCarStatus(CarStatus carStatus);
    List<Car> findAllByCarType(CarType carType);
    List<Car> findAllByCarStatusAndCarType(CarStatus carStatus, CarType carType);
}
